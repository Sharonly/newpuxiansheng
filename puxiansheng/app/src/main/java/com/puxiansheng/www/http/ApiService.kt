package  com.puxiansheng.www.http

import androidx.lifecycle.LiveData
import com.puxiansheng.logic.bean.SignatureToken
import com.puxiansheng.logic.bean.http.HttpRespBannerImages
import com.puxiansheng.util.http.ApiBaseResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

interface ApiService {


    //获取签名token
    @FormUrlEncoded
    @POST("api/get_token.token")
    fun getSignToken(@FieldMap params: Map<String, String>): Call<ApiBaseResponse<SignatureToken>>

   // -----------------------首页------------------------

    //获取banner
    @GET("api/home_menu.html")
    fun getHomeMenu(@Query("sign") sign: String): LiveData<ApiBaseResponse<HttpRespBannerImages>>

}