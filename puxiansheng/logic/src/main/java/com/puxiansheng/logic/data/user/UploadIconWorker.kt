package com.puxiansheng.logic.data.user

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.puxiansheng.logic.api.API
import com.puxiansheng.util.http.buildRequest
import okhttp3.*
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.File
import com.puxiansheng.logic.api.API.call
import com.puxiansheng.logic.bean.http.HttpRespEmpty
import com.puxiansheng.logic.bean.http.HttpRespMenuDate
import com.puxiansheng.util.http.APIResp
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded

class UploadIconWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    companion object {
        const val ImagePath = "imagePath"
        const val TOKEN = "token"
    }

    override fun doWork(): Result {
        val imagePath = inputData.getString(ImagePath)
        val token = inputData.getString(TOKEN)

        if (imagePath.isNullOrEmpty() || token.isNullOrEmpty()) return Result.failure()

        return kotlin.runCatching {
            val file = File(imagePath)
            Log.d("---imageicon "," imagePath = "+imagePath+" token = "+file.exists())
            if (!file.exists()) return Result.failure()
            Log.d("---imageicon "," file.name = "+file.name)
            val rst: APIRst<APIResp<HttpRespEmpty>> = buildRequest(
                url = API.SAVE_USER_ICON,
                parts = listOf(
                    MultipartBody.Part.createFormData(
                        "header_img",
                        file.name,
                        file.asRequestBody("image".toMediaTypeOrNull())
                    )
                ),
                fieldMap = mutableMapOf<String,String>(
                ).also {
                    it["sign"] = API.sign(signatureToken = token, fieldMap = it, method = "POST")
                }
            ).let {
                call(it)
            }
            rst
        }.fold(onSuccess = {
            return if (it.succeeded) {
                Log.d("---imageicon "," it = "+it.succeeded)
                Result.success()
            } else {
                Result.failure()
            }
        }, onFailure = {
            Result.failure()
        })
    }
}