package com.puxiansheng.www.ui.mine.setting

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.LocationNode
import com.puxiansheng.logic.bean.User
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.common.AppFragment
import com.puxiansheng.www.common.LiveDataBus
import com.puxiansheng.www.common.urlIcon
import com.puxiansheng.www.databinding.FragmentMySettingBinding
import com.puxiansheng.www.ui.login.LoginActivity
import com.puxiansheng.www.ui.main.LocationActivity
import com.puxiansheng.www.ui.main.MainViewModel
import kotlinx.android.synthetic.main.activity_relase_order_transfer_out.*
import kotlinx.android.synthetic.main.fragment_mine.*
import kotlinx.android.synthetic.main.fragment_mine.user_icon
import kotlinx.android.synthetic.main.fragment_my_setting.*
import kotlinx.coroutines.launch
import java.io.File


class UserSettingActivity : MyBaseActivity() {
    private lateinit var appModel: MainViewModel
    private lateinit var settingViewModel: SettingViewModel
    private var iconImageUri: Uri? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_my_setting
    }

    override fun business() {
        appModel = ViewModelProvider(this)[MainViewModel::class.java]
        settingViewModel = ViewModelProvider(this)[SettingViewModel::class.java]
        initView()
    }


     fun initView() {
         LiveDataBus.get().with("currentCity", LocationNode::class.java)?.observe(this, Observer {
             user_location.text = it.text
             settingViewModel.cityId = it.nodeID
         })


         lifecycleScope.launch {
             settingViewModel.getUserInformationFromRemote()?.let {
                 if (it is User) {
                     user_icon.urlIcon(it.icon)
                     input_nick_name.setText(it.nickName)
                     input_actual_name.setText(it.actualName)
                     input_user_phone.setText(it.userPhoneNumber)
                     user_location.text = it.userCityPath
                     settingViewModel.nickName = it.nickName
                     settingViewModel.actualName = it.actualName
                     settingViewModel.contactPhone = it.userPhoneNumber
                     if (it.userSex == 2) {
                         femle.isChecked = true
                         settingViewModel.sex = 2
                     } else {
                         male.isChecked = true
                         settingViewModel.sex = 1
                     }
                     if (it.cityId != 0) {
                         settingViewModel.cityId = it.cityId
                     } else {
                         settingViewModel.cityId =
                             SharedPreferencesUtil.get(API.USER_CITY_ID, 0).toString().toInt()
                     }
                     settingViewModel.iconImg = it.icon
                 }
             }
         }

         button_back.setOnClickListener {
             onBackPressed()
         }

         user_icon.setOnClickListener {
             ChangeIconDialog().show(supportFragmentManager, ChangeIconDialog::class.java.name)
         }

         input_nick_name.setText(SharedPreferencesUtil.get(API.LOGIN_NICK_NAME, "").toString())
         input_actual_name.setText(SharedPreferencesUtil.get(API.LOGIN_ACTUL_NAME, "").toString())


         input_nick_name.addTextChangedListener {
             settingViewModel.nickName = it.toString()
         }
         input_actual_name.addTextChangedListener {
             settingViewModel.actualName = it.toString()
         }


         if (SharedPreferencesUtil.get(API.USER_SEX, 0) == 0) {
             male.isChecked = true
         } else {
             femle.isChecked = true
         }
         val drawableSelected =
             resources.getDrawable(R.mipmap.ic_sex_selected)
         val drawableNoSelect =
             resources.getDrawable(R.drawable.ic_sex_no_select)
         rg_sex.setOnCheckedChangeListener { group, checkedId ->
             if (checkedId == R.id.male) {
                 male.setCompoundDrawables(drawableSelected, null, null, null)
                 femle.setCompoundDrawables(drawableNoSelect, null, null, null)
                 settingViewModel.sex = 1
             } else {
                 male.setCompoundDrawables(drawableNoSelect, null, null, null)
                 femle.setCompoundDrawables(drawableSelected, null, null, null)
                 settingViewModel.sex = 2
             }
         }



         user_location.setOnClickListener {
             val intent = Intent(this, LocationActivity::class.java)
             startActivity(intent)
         }

         bt_save.setOnClickListener {
             lifecycleScope.launch {
                 settingViewModel.submitUserInfo()?.let {

                     if (it.code == API.CODE_SUCCESS) {
                         SharedPreferencesUtil.put(API.LOGIN_USER_ICON, "")
                         settingViewModel.nickName?.let { it1 ->
                             SharedPreferencesUtil.put(
                                 API.LOGIN_NICK_NAME,
                                 it1
                             )
                         }
                         settingViewModel.actualName?.let { it1 ->
                             SharedPreferencesUtil.put(
                                 API.LOGIN_ACTUL_NAME,
                                 it1
                             )
                         }
                         settingViewModel.sex?.let { it1 ->
                             SharedPreferencesUtil.put(
                                 API.USER_SEX,
                                 it1
                             )
                         }
                         settingViewModel.contactPhone?.let { it1 ->
                             SharedPreferencesUtil.put(
                                 API.LOGIN_USER_PHONE,
                                 it1
                             )
                         }
                         settingViewModel.cityId?.let { it1 ->
                             SharedPreferencesUtil.put(
                                 API.USER_CITY_ID,
                                 it1
                             )
                         }
                         SharedPreferencesUtil.put(API.USER_CITY_NAME, user_location.text)
                         Toast.makeText(this@UserSettingActivity, "保存成功", Toast.LENGTH_SHORT)
                         Navigation.findNavController(this@UserSettingActivity, R.id.homeNavHost)
                             .popBackStack()
                     }
                 }
             }
         }



         appModel.currentCity.observe(this@UserSettingActivity, Observer {
             user_location.text = it.text
         })

     }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("---icon  ","  requestCode = "+requestCode+" resultCode = "+resultCode)
        if (requestCode == 101 && resultCode == Activity.RESULT_OK) {
            var storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES
            )
            var outputImage = File(storageDir, "pxs_icon.jpg")
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                iconImageUri = Uri.fromFile(outputImage)
            } else {
                iconImageUri = FileProvider.getUriForFile(
                    this,
                    "${this.packageName}.fileProvider",
                    outputImage
                )
            }
            Log.d("  icon  ","  outputImage = "+outputImage)
            var bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(this.iconImageUri as Uri))
                user_icon.setImageBitmap(bitmap)
        }else if(requestCode == 102 && resultCode == Activity.RESULT_OK){



        }
    }

}