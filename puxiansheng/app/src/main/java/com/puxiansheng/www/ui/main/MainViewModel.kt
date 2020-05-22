package com.puxiansheng.www.ui.main

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.*
import com.puxiansheng.logic.data.device.DeviceDatabase
import com.puxiansheng.logic.data.device.DeviceRepository
import com.puxiansheng.logic.data.location.LocationRepository
import com.puxiansheng.logic.data.menu.MenuDatabase
import com.puxiansheng.logic.data.menu.MenuRepository
import com.puxiansheng.logic.data.system.SystemDatabase
import com.puxiansheng.logic.data.system.SystemRepository
import com.puxiansheng.logic.data.user.UserDatabase
import com.puxiansheng.logic.data.user.UserRepository
import com.puxiansheng.util.ext.SharedPreferencesUtil.Companion.get
import com.puxiansheng.util.ext.SharedPreferencesUtil.Companion.put
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import com.puxiansheng.www.ui.home.HomeFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File

class MainViewModel (application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    private val deviceRepository = DeviceRepository(DeviceDatabase.getInstance(context).deviceDao())
    private val userRepository = UserRepository((UserDatabase.getInstance(context).userDao()))
    private val systemRepository =
        SystemRepository(SystemDatabase.getInstance(context).systemConfigDao())
    private val menuRepository =
        MenuRepository(MenuDatabase.getInstance(context).menuDao())
    private val locationRepository = LocationRepository()

    private val ticker = ticker(
        delayMillis = 1000 * 60 * 3,
        initialDelayMillis = 0,
        context = viewModelScope.coroutineContext
    )

    var countDown: MutableLiveData<Int> = MutableLiveData()
    var lastFragment: Fragment = HomeFragment()
    val toastMsg = MutableLiveData<String>()
    val currentUser = MutableLiveData<User>()
    val currentCity = MutableLiveData<LocationNode>()
    val currentSignatureToken = MutableLiveData<String>()
    val currentSignatureTokenCode = MutableLiveData<Int>()
    val currentNewPackage = MutableLiveData<NewPackage>()

    val saveAddress = MutableLiveData<String>()

    fun requireLocalDevice() = deviceRepository.getDevice()

    /**
     * get the last local user information from database.
     * */
    suspend fun requestLastLocalUser() = userRepository.requestLastLocalUser()
//    fun requestLastLocalUser() = userRepository.requestLastLocalUser()

    /**
     * get the basic device information of the phone.
     * */
    fun requireDevice() = viewModelScope.launch(Dispatchers.IO) {
        deviceRepository.requireDevice()
    }


    /**
     * call remote api to get the signature token.
     *
     * @param device the basic device information.
     */
    fun getSignatureTokenFromRemote(device: Device) =
        viewModelScope.launch(Dispatchers.IO) {
            systemRepository.requireSignatureToken(device).let { apiResult ->
                if (apiResult.succeeded) {
                    apiResult as APIRst.Success
                    apiResult.data.data?.token?.let {
                        API.setSignatureToken(it)
                        currentSignatureToken.postValue(it)
                        currentSignatureTokenCode.postValue(apiResult.data.code)
                    }
                    apiResult.data.data?.newPackage?.let {
                        currentNewPackage.postValue(it)
                    }
                } else {
                    ticker.receive()
                }
            }
        }

    /**
     * automatically refresh the signature token by every three minutes.
     * */
    fun refreshSignatureToken(device: Device) = viewModelScope.launch {
        ticker.consumeEach {
            getSignatureTokenFromRemote(device)
        }
    }

    fun startCountDown() = viewModelScope.launch(Dispatchers.IO) {
        (60 downTo 0).forEach {
            delay(1000)
            countDown.postValue(it)
        }
    }

    fun getUserInformationFromRemote(localUser: User) = viewModelScope.launch(Dispatchers.IO) {
        userRepository.requireRemoteUserInfo(
        ).let { apiRst ->
            if (apiRst.succeeded) {
                (apiRst as APIRst.Success).let { apiResp ->
                    if (apiResp.data.code == API.CODE_SUCCESS) apiResp.data.data?.let { userInfo ->
                        userInfo.user?.let { user ->
                            localUser.loginTimestamp = System.currentTimeMillis()
                            localUser.loginState = 1
                            localUser.token = user.token
                            userRepository.insertUser(localUser)
                        }
                    } else {
                        toastMsg.postValue(apiResp.data.msg)
                        localUser.loginState = 0
                        userRepository.insertUser(localUser.copy(loginState = 0))
                    }
//                    API.logoutSignal.postValue(apiResp.data.code)
                }
            } else {
                (apiRst as APIRst.Error).let {
                    toastMsg.postValue(it.exception.message)
                    userRepository.insertUser(localUser.copy(loginState = 0))
                }
            }
        }
    }

    fun getSelectiveMenuDataFromRemote() {
        getIndustrySelectiveMenuDataFromRemote()
        getSizeSelectiveMenuDataFromRemote()
        getRentUnitSelectiveMenuDataFromRemote()
        getRentSelectiveMenuDataFromRemote()
        getPropertySelectiveMenuDataFromRemote()
    }

    private fun getIndustrySelectiveMenuDataFromRemote() = viewModelScope.launch(Dispatchers.IO) {
        menuRepository.requestRemoteIndustrySelectiveData(API.currentSignatureToken).let {
            if (it.succeeded) {
                (it as APIRst.Success).data.data?.list?.let { menuList ->
                    menuList.map { menu ->
                        menu.type = MenuItem.TYPE.INDUSTRY.value()
                        menu
                    }.let { list ->
                        menuRepository.insertOrUpdate(*list.toTypedArray())
                    }
                }
            }
        }
    }

    private fun getSizeSelectiveMenuDataFromRemote() = viewModelScope.launch(Dispatchers.IO) {
        menuRepository.requestRemoteSizeSelectiveData(API.currentSignatureToken).let {
            if (it.succeeded) {
                (it as APIRst.Success).data.data?.list?.let { menuList ->
                    menuList.map { menu ->
                        menu.type = MenuItem.TYPE.SIZE.value()
                        menu
                    }.let { list ->
                        menuRepository.insertOrUpdate(*list.toTypedArray())
                    }
                }
            }
        }
    }

    private fun getRentUnitSelectiveMenuDataFromRemote() = viewModelScope.launch(Dispatchers.IO) {
        menuRepository.requestRemoteRentUnitSelectiveData(API.currentSignatureToken).let {
            if (it.succeeded) {
                (it as APIRst.Success).data.data?.list?.let { menuList ->
                    menuList.map { menu ->
                        menu.type = MenuItem.TYPE.RENT_UNIT.value()
                        menu
                    }.let { list ->
                        menuRepository.insertOrUpdate(*list.toTypedArray())
                    }
                }
            }
        }
    }

    private fun getRentSelectiveMenuDataFromRemote() = viewModelScope.launch(Dispatchers.IO) {
        menuRepository.requestRemoteRentSelectiveData(API.currentSignatureToken).let {
            if (it.succeeded) {
                (it as APIRst.Success).data.data?.list?.let { menuList ->
                    menuList.map { menu ->
                        menu.type = MenuItem.TYPE.RENT.value()
                        menu
                    }.let { list ->
                        menuRepository.insertOrUpdate(*list.toTypedArray())
                    }
                }
            }
        }
    }

    private fun getPropertySelectiveMenuDataFromRemote() = viewModelScope.launch(Dispatchers.IO) {
        menuRepository.requestRemotePropertySelectiveData(API.currentSignatureToken).let {
            if (it.succeeded) {
                (it as APIRst.Success).data.data?.list?.let { menuList ->
                    menuList.map { menu ->
                        menu.type = MenuItem.TYPE.PROPERTY.value()
                        menu
                    }.let { list ->
                        menuRepository.insertOrUpdate(*list.toTypedArray())
                    }
                }
            }
        }
    }

    fun getSystemConfigFromRemote() = viewModelScope.launch(Dispatchers.IO) {
        systemRepository.requestRemoteSystemConfig().let { apiRst ->
            if (apiRst.succeeded) {
                (apiRst as APIRst.Success).data.data?.config?.let {
                    systemRepository.insertOrUpdateSystemConfig(it)
                }
            }
        }
    }

    fun getCurrentLocation() = viewModelScope.launch(Dispatchers.IO) {
        locationRepository.getCurrentLocationFromRemote().let { apiRst ->
            if (apiRst.succeeded) {
                (apiRst as APIRst.Success).data.data?.locationNode?.let { node ->
                    currentCity.postValue(node)
                    put(API.USER_CITY_ID,node.nodeID)
                }
            }
        }
    }

    fun openAPK(fileSavePath:String){
        var  file= File(Uri.parse(fileSavePath).getPath());
        var filePath = file.getAbsolutePath();
        var intent = Intent(Intent.ACTION_VIEW)
        var data: Uri
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//判断版本大于等于7.0
            data = FileProvider.getUriForFile(context, "com.puxiansheng.www.fileProvider", File(filePath));
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);// 给目标应用一个临时授权
        } else {
            data = Uri.fromFile(file);
        }
        intent.setDataAndType(data, "application/vnd.android.package-archive")
        context?.startActivity(intent)
    }



    fun openAPK(content: Uri) {
        var apkfile = File(content.toString())
        var mIntent = Intent(Intent.ACTION_VIEW)
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //判读版本是否在7.0以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            mIntent.setDataAndType(content, "application/vnd.android.package-archive");
        } else {
            mIntent.setDataAndType(
                Uri.fromFile(apkfile),
                "application/vnd.android.package-archive"
            );
        }
        context?.startActivity(mIntent)
    }

    fun forceLogout() {
        currentUser.value?.copy(loginState = 0)?.let { user ->
            put(API.LOGIN_USER_TOKEN,"")
            put( API.LOGIN_NICK_NAME,"")
            put( API.LOGIN_ACTUL_NAME,"")
            put( API.LOGIN_USER_ICON,"")
            put( API.LOGIN_USER_PHONE,"")
            put( API.LOGIN_USER_STATE,0)
            API.setAuthToken(user.token)
            currentUser.postValue(user)
        }
    }

    override fun onCleared() {
        ticker.cancel()
        super.onCleared()
    }

    fun saveLoginUser(): User? {
        var user: User? = User()
        user?.token = get(API.LOGIN_USER_TOKEN,"") as String
        user?.nickname =get(API.LOGIN_NICK_NAME,"") as String
        user?.actualName =get(API.LOGIN_ACTUL_NAME,"") as String
        user?.userPhoneNumber = get(API.LOGIN_USER_PHONE,"") as String
        user?.loginState = get( API.LOGIN_USER_STATE,0) as Int
        return user
    }


    fun pictureIntent(context: Activity,image : BannerImage){
        when (image.jump_type) {
//            1 -> {
//                when (image.jump_view) {
//                        "index" ->
//                    "transfer_list" ->
//                        Navigation.findNavController(context, R.id.homeNavHost)
//                            .navigate(
//                                R.id.action_mainFragment_to_transferOutOrdersFragment,
//                                Bundle().apply {
//                                    putInt(Order.TRANSFER_TYPE, Order.TRANSFER_TYPE_OUT)
//                                })
//                    "find_list" ->
//                        Navigation.findNavController(context, R.id.homeNavHost)
//                            .navigate(
//                                R.id.action_mainFragment_to_transferInOrdersFragment,
//                                Bundle().apply {
//                                    putInt(Order.TRANSFER_TYPE, Order.TRANSFER_TYPE_IN)
//                                })
//                    "activity_list" -> {//文章列表
//                        childFragmentManager.beginTransaction().hide(appModel.lastFragment)
//                            .show(
//                                infoHomeFragment
//                            ).commit()
//                        lastFragment = infoHomeFragment
//                    }
//                    "user_center" -> {
//                        childFragmentManager.beginTransaction().hide(appModel.lastFragment)
//                            .show(
//                                mineFragment
//                            ).commit()
//                        lastFragment = mineFragment
//                    }
//                    "shop_success" -> {//成功案例
//                        Navigation.findNavController(context, R.id.homeNavHost)
//                            .navigate(
//                                R.id.action_mainFragment_to_successCaseFragment,
//                                Bundle().apply {
//                                    putInt(Order.TRANSFER_TYPE, Order.TRANSFER_TYPE_OUT)
//                                })
//                    }
//                }
//            }
//            2 -> {//打开链接
//                val intent = Intent(Intent.ACTION_VIEW)
//                intent.data = Uri.parse(image.jump_param)
//                context.startActivity(intent)
//            }
//            3 -> {//找店详情
//                Navigation.findNavController(context, R.id.homeNavHost).navigate(
//                    R.id.action_mainFragment_to_transferInOrderDetailFragment,
//                    Bundle().apply {
//                        putInt("shopID", image.jump_param?.toInt() ?: 0)
//                    }
//                )
//            }
//            4 -> {//转铺详情
//                Navigation.findNavController(context, R.id.homeNavHost).navigate(
//                    R.id.action_mainFragment_to_transferOutOrderDetailFragment,
//                    Bundle().apply {
//                        putInt("shopID", image.jump_param?.toInt() ?: 0)
//                    }
//                )
//            }
//            5 -> {//文章详情
//                Navigation.findNavController(context, R.id.homeNavHost).navigate(
//                    R.id.action_mainFragment_to_infoDetailFragment,
//                    Bundle().apply {
//                        putString("url", image.jump_param)
//                    }
//                )
//            }
//            6 -> {//跳转第三方
//
//            }
        }

    }


}