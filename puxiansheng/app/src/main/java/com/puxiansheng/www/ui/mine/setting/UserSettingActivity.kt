package com.puxiansheng.www.ui.mine.setting

import android.Manifest
import android.app.Activity
import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.LocationNode
import com.puxiansheng.logic.bean.User
import com.puxiansheng.logic.util.LiveDataBus
import com.puxiansheng.uio.GlideApp
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.common.urlIcon
import com.puxiansheng.www.ui.main.LocationActivity
import kotlinx.android.synthetic.main.fragment_mine.user_icon
import kotlinx.android.synthetic.main.fragment_my_setting.*
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList


class UserSettingActivity : MyBaseActivity() {
    private lateinit var settingViewModel: SettingViewModel
    private val requestCodePermissions = 1001
    private val requiredPermissions = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    override fun getLayoutId(): Int {
        return R.layout.fragment_my_setting
    }

    override fun business() {
        initPermission()
        settingViewModel = ViewModelProvider(this)[SettingViewModel::class.java]
//        ActivityCompat.requestPermissions(this, requiredPermissions, requestCodePermissions)
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
                    user_location.text = it.cityName
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
        val drawableSelected = resources.getDrawable(R.mipmap.ic_sex_selected)
        val drawableNoSelect = resources.getDrawable(R.drawable.ic_sex_no_select)
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
                            SharedPreferencesUtil.put(API.LOGIN_NICK_NAME, it1)
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
                            SharedPreferencesUtil.put(API.USER_CITY_ID, it1)
                        }
                        SharedPreferencesUtil.put(API.USER_CITY_NAME, user_location.text)
                        onBackPressed()
                    }
                    Toast.makeText(this@UserSettingActivity, it.msg, Toast.LENGTH_SHORT)
                }
            }
        }



//        appModel.currentCity.observe(this@UserSettingActivity, Observer {
//            user_location.text = it.text
//        })


        LiveDataBus.get().with("changeIcon",Uri::class.java)?.observe(this, Observer {
              println("回调---imageIcon--—》${it}")
            cropImageUri=it
            crop(cropImageUri)
        })
    }

    private var  cropImageUri: Uri? =null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("---imageIcon", "  requestCode  = " + requestCode+"  resultCode = "+resultCode)
        if (requestCode == 101 && resultCode == Activity.RESULT_OK) {
//            crop(cropImageUri)
            Glide.with(this)
                .load(cropImageUri)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .error(R.mipmap.ic_default_icon)
                .into(user_icon)
//            lifecycleScope.launch {
//                Log.d("---imageIcon  ", "  cropImageUri  = " + cropImageUri)
//                cropImageUri?.let { cropImageUri = getFileUri(this@UserSettingActivity, it) }
//                getPath(this@UserSettingActivity, cropImageUri!!)?.let { it1 ->
//                    settingViewModel.submitUserIcon(it1).let {
//                        if (it?.code == API.CODE_SUCCESS) {
//                            settingViewModel.iconImg = it?.data?.imgIcon
//                        }
//                    }
//                }
//            }

        } else if (requestCode == 102 && resultCode == Activity.RESULT_OK) {
            //TODO 相册图片
            if (data?.data != null) {
                GlideApp.with(this).load(data.data).into(user_icon)
                lifecycleScope.launch {
                    data?.data?.path?.let {
                        getPath(this@UserSettingActivity, data.data!!)?.let { it1 ->
                            settingViewModel.submitUserIcon(it1).let {
                                if (it?.code == API.CODE_SUCCESS) {
                                    settingViewModel.iconImg = it?.data?.imgIcon
                                }
                            }
                        }
                    }
                }
            }
        }else if (requestCode==103){
//                 Glide.with(this)
//                .load(cropImageUri)
//                .skipMemoryCache(true)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .error(R.mipmap.ic_default_icon)
//                .into(user_icon)
                        lifecycleScope.launch {
                Log.d("---imageIcon  ", "  cropImageUri 22 = " + cropImageUri)
//                getPath(this@UserSettingActivity, cropImageUri!!)?.let { it1 ->
//                    settingViewModel.submitUserIcon(it1).let {
//                        if (it?.code == API.CODE_SUCCESS) {
//                            settingViewModel.iconImg = it?.data?.imgIcon
//                        }
//                    }
//                }
            }
        }
    }



//    fun  getImagePath(context: Context,uri :Uri):String {
//        var path = "null";
//        var cursor = context.contentResolver.query(uri, null, null, null, null)
//        if (cursor != null) {
//            if (cursor.moveToFirst()) {
//                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
//            }
//            cursor.close()
//        }
//        return path
//    }


     fun cutImage(uri: Uri) {
        if (uri == null) {
            Log.i("alanjet", "The uri is not exist.")
        }
        cropImageUri = uri
        val intent = Intent("com.android.camera.action.CROP")
        //com.android.camera.action.CROP这个action是用来裁剪图片用的
        intent.setDataAndType(uri, "image/*")
        // 设置裁剪
        intent.putExtra("crop", "true")
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1)
        intent.putExtra("aspectY", 1)
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150)
        intent.putExtra("outputY", 150)
        intent.putExtra("return-data", true)
        startActivityForResult(intent, 103)
    }

    fun crop(uri: Uri?) {
        val cropPhoto = File(externalCacheDir, "crop_image.jpg")
        try {
            if (cropPhoto.exists()) {
                cropPhoto.delete()
            }
            cropPhoto.createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        cropImageUri = Uri.fromFile(cropPhoto)
        // 裁剪图片意图
        val intent = Intent("com.android.camera.action.CROP")
        intent.setDataAndType(uri, "image/*")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION) //添加这一句表示对目标应用临时授权该Uri所代表的文件
        }
        intent.putExtra("crop", "true")
        // 裁剪框的比例，4：3
        intent.putExtra("aspectX", 3)
        intent.putExtra("aspectY", 3)
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 300)
        intent.putExtra("outputY", 300)
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString()) // 图片格式
        intent.putExtra("noFaceDetection", true) // 取消人脸识别
        intent.putExtra("return-data", false)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cropImageUri)
        startActivityForResult(intent, 103)
    }

   fun getFileUri(context: Context, uri: Uri): Uri? {
      var  realFilePath = getPath(context, uri)
        if (TextUtils.isEmpty(realFilePath)) {
            return null
        }
        return Uri.fromFile(File(realFilePath))
    }



//通用的从uri中获取路径的方法, 兼容以上说到的2个shceme
fun getPath(context: Context, uri: Uri): String? {
    val isKitKat =
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
    // DocumentProvider
    if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
        // ExternalStorageProvider
        if (isExternalStorageDocument(uri)) {
            val docId: String = DocumentsContract.getDocumentId(uri)
            val split = docId.split(":".toRegex()).toTypedArray()
            val type = split[0]
            if ("primary".equals(type, ignoreCase = true)) {
                return Environment.getExternalStorageDirectory()
                    .toString() + "/" + split[1]
            }
        } else if (isDownloadsDocument(uri)) {
            val id: String = DocumentsContract.getDocumentId(uri)
            val contentUri: Uri = ContentUris.withAppendedId(
                Uri.parse("content://downloads/public_downloads"),
                java.lang.Long.valueOf(id)
            )
            return getDataColumn(context, contentUri, null, null)
        } else if (isMediaDocument(uri)) {
            val docId: String = DocumentsContract.getDocumentId(uri)
            val split = docId.split(":".toRegex()).toTypedArray()
            val type = split[0]
            var contentUri: Uri? = null
            if ("image" == type) {
                contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            } else if ("video" == type) {
                contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
            } else if ("audio" == type) {
                contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
            }
            val selection = "_id=?"
            val selectionArgs = arrayOf(
                split[1]
            )
            return getDataColumn(context, contentUri, selection, selectionArgs)
        }
    } else if ("content".equals(uri.scheme, ignoreCase = true)) {
        // Return the remote address
        return if (isGooglePhotosUri(uri)) uri.lastPathSegment else getDataColumn(
            context,
            uri,
            null,
            null
        )
    } else if ("file".equals(uri.scheme, ignoreCase = true)) {
        return uri.path
    }
    return ""
}

//fun getId(context: Context, uri: Uri): Long {
//    val isKitKat =
//        Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
//    if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
//        if (isMediaDocument(uri)) {
//            val docId: String = DocumentsContract.getDocumentId(uri)
//            val split = docId.split(":".toRegex()).toTypedArray()
//            val type = split[0]
//            var contentUri: Uri? = null
//            if ("image" == type) {
//                contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//            } else if ("video" == type) {
//                contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
//            } else if ("audio" == type) {
//                contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
//            }
//            val selection = "_id=?"
//            val selectionArgs = arrayOf(
//                split[1]
//            )
//            return getIdColumn(context, contentUri, selection, selectionArgs)
//        }
//    } else {
//        return getIdColumn(context, uri, null, null)
//    }
//    return 0
//}

fun getDataColumn(
    context: Context, uri: Uri?, selection: String?,
    selectionArgs: Array<String>?
): String? {
    var cursor: Cursor? = null
    val column = "_data"
    val projection = arrayOf(
        column
    )
    try {
        cursor = context.contentResolver.query(
            uri!!, projection, selection, selectionArgs,
            null
        )
        if (cursor != null && cursor.moveToFirst()) {
            val index = cursor.getColumnIndexOrThrow(column)
            return cursor.getString(index)
        }
    } finally {
        cursor?.close()
    }
    return ""
}




fun getIdColumn(
    context: Context,
    uri: Uri?,
    selection: String?,
    selectionArgs: Array<String>?
): Long {
    var cursor: Cursor? = null
    val column = "_id"
    val projection = arrayOf(column)
    try {
        cursor =
            context.contentResolver.query(uri!!, projection, selection, selectionArgs, null)
        if (cursor != null && cursor.moveToFirst()) {
            val index = cursor.getColumnIndexOrThrow(column)
            return cursor.getLong(index)
        }
    } finally {
        cursor?.close()
    }
    return 0
}

fun isExternalStorageDocument(uri: Uri): Boolean {
    return "com.android.externalstorage.documents" == uri.authority
}

fun isDownloadsDocument(uri: Uri): Boolean {
    return "com.android.providers.downloads.documents" == uri.authority
}

fun isMediaDocument(uri: Uri): Boolean {
    return "com.android.providers.media.documents" == uri.authority
}

fun isGooglePhotosUri(uri: Uri): Boolean {
    return "com.google.android.apps.photos.content" == uri.authority
}

private fun getFileFromContentUri(
    contentUri: Uri,
    context: Context
): File? {
    if (contentUri == null) {
        return null
    }
    var file: File? = null
    val filePath: String
    val filePathColumn =
        arrayOf(MediaStore.MediaColumns.DATA)
    val contentResolver: ContentResolver = context.contentResolver
    val cursor: Cursor? = contentResolver.query(
        contentUri, filePathColumn, null,
        null, null
    )
    if (cursor != null) {
        cursor.moveToFirst()
        filePath = cursor.getString(cursor.getColumnIndex(filePathColumn[0]))
        cursor.close()
        if (!TextUtils.isEmpty(filePath)) {
            file = File(filePath)
        }
    }
    return file
}

//http://pxs3-img-test.oss-cn-shenzhen.aliyuncs.com/temp/admin_temp/fd50243d1cb4c75ac80b67428761920f28840234.jpg

//    override fun onResume() {
//        super.onResume()
//        MobclickAgent.onPageStart("UserSettingActivity") //统计页面，"MainScreen"为页面名称，可自定义
//    }
//
//    override fun onPause() {
//        super.onPause()
//        MobclickAgent.onPageEnd("UserSettingActivity")
//    }


    private fun initPermission() {
        val permissions = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val toApplyList: ArrayList<String> = ArrayList()
        for (perm in permissions) {
            if (PackageManager.PERMISSION_GRANTED !== ContextCompat.checkSelfPermission(
                    Objects.requireNonNull(this), perm)
            ) { toApplyList.add(perm) }
        }
        val tmpList = arrayOfNulls<String>(toApplyList.size)
        if (toApplyList.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                toApplyList.toArray(tmpList),
                101
            )
        }
    }


}