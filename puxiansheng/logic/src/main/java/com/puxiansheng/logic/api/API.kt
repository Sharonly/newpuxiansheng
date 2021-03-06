package com.puxiansheng.logic.api

import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.puxiansheng.util.ext.md5
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit
import kotlin.text.StringBuilder
import com.puxiansheng.util.http.*
import com.zhihu.matisse.BuildConfig
import okhttp3.Dispatcher

object API {
    /*
    1009 : 用户TOKEN失效
    4011 : appid不存在，和secret，或已停用*/
    const val CODE_SUCCESS = 200//成功状态码
    const val CODE_EMPTY_SIGNATURE_TOKEN = 1000//签名为空
    const val CODE_ERROR_SIGNATURE_TOKEN = 1001//签名错误
    const val CODE_ERROR_PARAMS = 1002//参数错误
    const val CODE_UNKNOWN_SIGNATURE_TOKEN = 1003//token异常

    const val CODE_ERROR_AUTH_TOKEN = 1004//登录token错误
    const val CODE_USER_NOT_EXIST = 1005//用户不存在
    const val CODE_ERROR_ACCOUNT_OR_PWD = 1006//用户名或密码错误

    const val CODE_BANNED_USER = 1007//用户被禁用
    const val CODE_AUTO_CODE_EMPTY = 1008//用户TOKEN为空
    const val CODE_AUTO_CODE_ERROR = 1009//用户TOKEN错误
    const val CODE_AUTO_CODE_EXPIRED = 1010//用户TOKEN过期
    const val CODE_AUTO_CODE_INVALID = 1011//用户TOKEN失效
    const val CODE_REQUIRE_LOGIN = 1012//重新登录


    const val CODE_SERVER_ERROR = 5000//服务器异常
    const val CODE_BAND_MOBILE_NUMBER = 4012//需要绑定手机号
    const val CODE_VERSION_NOT_EXIST = 4031//版本号不存在
    const val CODE_BANNED_VERSION = 4032//版本已停用

    const val CODE_ERROR_UNKNOWN = 9999//未知错误

    const val API_APP_ID = "cee34b0e9989df19"
    const val API_SECRET = "6385dab0cee34b0e9989df190522d449"
    const val API_VERSION = "344"

    const val WEIXIN_APP_ID ="wxe5266f2fb1236eee"

    //http://img1.puxiansheng.com/uploads/video/20210120160238/e4592617c4761f182b886d98e048a057dca49222.mp4
    //http://pxs3-img-test.oss-cn-shenzhen.aliyuncs.com/uploads/video/20210408142330/944c4ce22e925e00fb51b425b14524d10dafcc06.mp4
    const val LOGIN_USER_ID = "user_id"
    const val LOGIN_USER_TOKEN = "user_token"
    const val LOGIN_USER_PHONE = "user_phone"
    const val LOGIN_ACTUL_PHONE = "actual_phone"
    const val LOGIN_USER_ICON = "user_icon"
    const val LOGIN_NICK_NAME = "nick_name"
    const val USER_SEX = "user_sex"
    const val LOGIN_ACTUL_NAME = "actual_name"
    const val LOGIN_USER_STATE = "user_login_state"
    const val USER_CITY_ID = "city_id"
    const val USER_CITY_NAME = "city_name"

    private const val API_ADDRESS = "http://api3.51wpu.com.cn/"//测试340
    private const val STORAGE_ADDRESS = "https://api3.51wpu.com.cn/"

//    private const val API_ADDRESS = "https://api3.puxiansheng.com/"
//    private const val STORAGE_ADDRESS = "https://api3.puxiansheng.com/"//正式环境

    //login
    const val DO_LOGIN = API_ADDRESS + "api/login.html"
    const val DO_LOGIN_BY_PASS = API_ADDRESS + "api/user_login.html"
    const val DO_LOGIN_BY_PHONE = API_ADDRESS + "api/sms_login.html"
    const val DO_LOGIN_BY_WECHAT = API_ADDRESS + "api/wechat_login.html"
    const val DO_REGISTER = API_ADDRESS + "api/register.html"
    const val DO_FORGET_PASSWORD = API_ADDRESS + "api/user_change_pwd.html"

    const val DO_LOGOUT = API_ADDRESS + "api/login_out.html"
    const val DO_BIND_MOBILE_NUMBER = API_ADDRESS + "api/auth/bind_phone.html"
    const val DO_RESET_PASSWORD = API_ADDRESS + "api/user_change/edit_pwd.html"
    const val DO_FAVORITE = API_ADDRESS + "api/set_collect.html"
    const val GET_USER_INFO = API_ADDRESS + "api/get_userinfo.html"
    const val GET_SYSTEM_CONFIG = API_ADDRESS + "api/config.html"
    const val GET_TOKEN = API_ADDRESS + "api/get_token.token"
    const val GET_VERIFICATION_CODE = API_ADDRESS + "api/send_msg.html"
    const val GET_IMAGE_CODE = API_ADDRESS + "api/get_captcha.html"
    const val SAVE_USER_INFO = API_ADDRESS + "api/user_edit_save.html"
    const val SAVE_USER_ICON = API_ADDRESS+"api/upload/header_img.html"

    const val GET_HOME_MENU = API_ADDRESS+"api/home_menu.html"

    const val GET_NEW_HOME_MENU = API_ADDRESS+"api/get/nav_list.html"

    const val GET_NEW_HOME_VIDEO = API_ADDRESS+"api/get/recommend_success_video.html"

    const val GET_NEW_HOME_PROJECT = API_ADDRESS+"api/get/recommend_demand.html"
    const val GET_NEW_PROJECT_LIST = API_ADDRESS+"api/get/demand_list.html"
    const val GET_PROJECT_DETAIL = API_ADDRESS+"api/get/demand_info.html"
    const val GET_FAVOR_PROJECT = API_ADDRESS+"api/demand/get_collect_list.html"

    const val GET_JOIN_LIST = API_ADDRESS + "api/join/get_list.html"
    const val GET_JOIN_DETAIL = API_ADDRESS + "api/join/get_info.html"
    const val SUBMIT_JOIN_INFO = API_ADDRESS + "api/join/submit.html"
//search

    const val GET_HISTORY_SEARCH = API_ADDRESS + "api/history_search.html"
    const val DELETE_HISTORY_SEARCH = API_ADDRESS + "api/history/search/del.html"
    const val GET_RECOMMEND_SEARCH = API_ADDRESS + "api/transfer/hot/search.html"

    //images
    const val GET_IMAGES = API_ADDRESS + "api/get_images.html"
    const val GET_IMAGE = API_ADDRESS + "api/get_images_info.html"

    //marquee
    const val GET_HEADLINE = API_ADDRESS + "api/get_headlines.html"

    const val GET_ADVERT = API_ADDRESS + "api/ad/get_list.html"
    const val SUBMIT_ADVERT = API_ADDRESS +"api/ad/ad_report.html"

    //get user like
    const val GET_USER_LIKE_SHOP = API_ADDRESS + "api/transfer/get_user_like.html"
    const val GET_SUCCESS_VIDEO_RECOMMEND_SHOP = API_ADDRESS + "api/transfer/success_video_recommend.html"

    //info apis
    const val GET_INFO_CATEGORY = API_ADDRESS + "api/article/get_cate_new.html"
    const val GET_INFO_LIST = API_ADDRESS + "api/article/get_list.html"
    const val GET_INFO_DETAIL = API_ADDRESS + "api/article/new_details.html"

    //message
    const val GET_MESSAGE_CATEGORY = API_ADDRESS + "api/notice/get_cate_list.html"
    const val GET_MESSAGE_LIST = API_ADDRESS + "api/notice/get_msg_list.html"
    const val GET_MESSAGE_DETAIL = API_ADDRESS + "api/notice/get_info.html"


    //locations apis
    const val GET_AREA_BY_CITY_ID = API_ADDRESS + "api/area/list.html"
    const val GET_SUPPORTED_CITIES = API_ADDRESS + "api/area/get_open.html"
    const val GET_CURRENT_LOCATION = API_ADDRESS + "api/area/position.html"
    const val GET_NEW_SUPPORTED_CITIES = API_ADDRESS + "api/area/new_get_open.html"

    //order apis
    const val DO_UPLOAD_IMAGE = STORAGE_ADDRESS + "api/upload/transfer_img.html"
    const val SUBMIT_SIMPLE_TRANSFER_OUT_ORDER = API_ADDRESS + "api/transfer/fast_transfer.html"
    const val SUBMIT_SIMPLE_TRANSFER_IN_ORDER = API_ADDRESS + "api/find/fast_find.html"
    const val SUBMIT_TRANSFER_OUT_ORDER = API_ADDRESS + "api/transfer/shop_submit.html"
    const val SUBMIT_TRANSFER_IN_ORDER = API_ADDRESS + "api/find/shop_submit.html"
    const val GET_REMOTE_TRANSFER_OUT_ORDERS = API_ADDRESS + "api/transfer/get_list.html"
    const val GET_REMOTE_TRANSFER_IN_ORDERS = API_ADDRESS + "api/find/get_list.html"
    const val GET_REMOTE_TRANSFER_SUCCESS_ORDERS = API_ADDRESS + "api/transfer/get_success.html"
    const val GET_REMOTE_TRANSFER_SUCCESS_VIDEO = API_ADDRESS + "api/transfer/success_video.html"//成功案例视频列表
    const val GET_REMOTE_SUCCESS_VIDEO_DETAILS = API_ADDRESS + "api/transfer/success_video_details.html"
    const val GET_REMOTE_TRANSFER_OUT_ORDER = API_ADDRESS + "api/transfer_shop/info.html"
    const val GET_REMOTE_TRANSFER_IN_ORDER = API_ADDRESS + "api/find_shop/info.html"
    const val GET_MINE_TRANSFER_OUT_ORDERS = API_ADDRESS + "api/user/get_transfer_issue.html"
    const val GET_MINE_TRANSFER_IN_ORDERS = API_ADDRESS + "api/user/get_find_shop_issue.html"//api.pu.com/api/user/get_find_shop_issue.html
    const val DELETE_TRANSFER_IN_ORDER = API_ADDRESS + "api/user/del_find.html"
    const val DELETE_TRANSFER_OUT_ORDER = API_ADDRESS + "api/user/del_transfer.html"

    const val  DELETE_FAVOR_ORDER= API_ADDRESS + "api/user/collect_null.html"
    const val  REFRESH_SHOP =API_ADDRESS +"api/user/update_shop_time.html"
    const val DELETE_HISTORY_ORDER = API_ADDRESS + "api/new/clear_view_log.html"


    const val GET_EDIT_TRANSFER_OUT_ORDER =
        API_ADDRESS + "api/transfer_shop/edit_echo.html"//获取去编辑的转店
    const val GET_EDIT_TRANSFER_IN_ORDER = API_ADDRESS + "api/find_shop/edit_echo.html"//获取编辑的找店

    const val GET_SAVE_TRANSFER_OUT_ORDER =
        API_ADDRESS + "api/transfer/shop_submit_echo.html"


    //首页
    const val GET_RECOMMEND_TRANSFER_OUT_ORDER =
        API_ADDRESS + "api/transfer/get_new_recommend.html"//首页推荐转店
    const val GET_RECOMMEND_TRANSFER_IN_ORDER =
        API_ADDRESS + "api/find/get_new_recommend.html"//首页推荐找店

    const val GET_FAST_TRANSFER_NUM = API_ADDRESS+"api/fast/transfer_find_shop_count.html"

    const val GET_FAST_MINE_TRANSFER_NUM = API_ADDRESS + "api/user/get_my_issue.html"


    const val SUBMIT_SUGGESTION = API_ADDRESS + "api/feedback_submit.html"
    const val GET_REQUEST_TYPE = API_ADDRESS+"api/get_feedback_cate.html"
    const val GET_REQUEST_LIST = API_ADDRESS+"api/get_problems.html"
    const val GET_USER_CALLBACK = API_ADDRESS+"api/my_feedback.html"

    const val GET_FAVORITE = API_ADDRESS + "api/user/collect_log.html"
    const val GET_HISTORY = API_ADDRESS + "api/user/view_log.html"

    const val GET_ARTICLE_HISTORY = API_ADDRESS + "api/article/get_view_list"
    const val GET_ARTICLE_FAVORITE = API_ADDRESS + "api/article/get_collect_list.html"

    const val GET_USER_PULISHED = API_ADDRESS + "api/user/get_release_shop.html"
    const val GET_USER_SOLD_OUT = API_ADDRESS + "api/user/get_stop_shop.html"
    const val GET_USER_PROCESSING = API_ADDRESS + "api/user/get_verify_shop.html"


    //menu item apis
    const val GET_REMOTE_INDUSTRY_DATA = API_ADDRESS + "api/shop/get_industry.html"

    const val GET_REMOTE_SIZE_DATA = API_ADDRESS + "api/shop/get_acreage.html"
    const val GET_REMOTE_RENT_UNIT_DATA = API_ADDRESS + "api/shop/get_ret_unit.html"
    const val GET_REMOTE_RENT_DATA = API_ADDRESS + "api/shop/get_rent.html"
    const val GET_REMOTE_PROPERTY_DATA = API_ADDRESS + "api/shop/get_property.html"
    //新地址
    const val GET_REMOTE_NEW_AREA_DATA = API_ADDRESS +"api/new_area/list.html"
    
    //多地址
    const val GET_MULTI_CITY_AREA_DATA = API_ADDRESS+"api/many_new/area_list.html"

    //some simple data apis
    const val GET_HOME_PAGE_STATISTICS_DATA = API_ADDRESS + "api/shop/statistics.html"
    const val GET_USER_RELEASE_COUNT = API_ADDRESS + "api/user/release/count.html"
    const val GET_SERVICE_LINK = API_ADDRESS + "api/config/kf_url.html"

    const val GET_CONFIG_URL = API_ADDRESS+"api/config.html"
    const val GET_NEW_PXS_HEADLINE = API_ADDRESS+"api/get/recommend_transfer.html"


    private val interceptor = HttpInterceptor("", "","")

    val logoutSignal = MutableLiveData<Int>()
    val getToken = MutableLiveData<Int>()

    val httpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .dispatcher(Dispatcher().apply {
            maxRequests = 1
            maxRequestsPerHost = 1
        })
        .retryOnConnectionFailure(true)
        .callTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    var currentSignatureToken = ""
    var currentAuthToken = ""
    var cityid = ""

    fun setSignatureToken(token: String) {
        currentSignatureToken = token
        interceptor.setSignatureToken(token)
    }

    fun setAuthToken(token: String) {
        currentAuthToken = token
        interceptor.setAuthToken(token)
    }

    fun setCityId(id:String){
        cityid = id
        interceptor.setCityId(id)
    }

    fun callForJson(request: Request) = callForJson(
        req = request,
        client = httpClient
    )

    inline fun <reified T> call(request: Request) = call<T>(
        req = request,
        client = httpClient
    )

    inline fun <reified T> callAny(request: Request) = callAny<T>(
        req = request,
        client = httpClient
    )

    inline fun <reified T : Parcelable> callWithNewClient(
        request: Request,
        client: OkHttpClient
    ) = call<T>(
        req = request,
        client = client
    )

    /**
     * gen signed token for every http request as param in any http request body.
     * @param signatureToken given by remote server.
     * @param fieldMap which the params containing in the http request.
     * @param method simple string, please input "POST" or "GET" .etc as uppercase.
     * */
    fun sign(
        signatureToken: String?,
        fieldMap: MutableMap<String, String>,
        method: String
    ): String = StringBuilder().apply {
        if (!signatureToken.isNullOrEmpty()) append(signatureToken)
        fieldMap.toSortedMap().forEach {
            append(it.key).append(it.value)
        }
        append(method)
        if (!signatureToken.isNullOrEmpty()) append(signatureToken)
    }.toString().also {
        if (BuildConfig.DEBUG) println("API.sign() {original sign string = $it}")
    }.md5(null).also {
        if (BuildConfig.DEBUG) println("API.sign() {md5 sign string = $it}")
    }

    fun signNew(
        signatureToken: String?,
        fieldMap: MutableMap<String, String>,
        method: String
    ): String = StringBuilder().apply {
        if (!signatureToken.isNullOrEmpty()) append(signatureToken)
//            append(it.key).append(it.value)//"${key}->${map[key]}"
        var appStr = fieldMap["appid"]
        var channelStr = fieldMap["channel"]
        var deviceNoStr = fieldMap["device_no"]
        var secretStr = fieldMap["secret"]
        var versionStr = fieldMap["version"]
        append("appid").append(appStr).append("channel").append(channelStr).append("device_no").append(deviceNoStr).append("secret").append(secretStr).append("version").append(versionStr)
        var ss=  append(method)
        if (BuildConfig.DEBUG) println("API.sign() {original no signatureToken "+ss.toString())
        if (!signatureToken.isNullOrEmpty()) append(signatureToken)
    }.toString().also {
        if (BuildConfig.DEBUG) println("API.sign() {original sign string = $it}")
    }.md5(null).also {
        if (BuildConfig.DEBUG) println("API.sign() {md5 sign string = $it}")
    }
}