package com.puxiansheng.logic.data.order

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.puxiansheng.logic.api.API
import com.puxiansheng.util.http.buildRequest
import okhttp3.*
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.File
import com.puxiansheng.logic.api.API.call
import com.puxiansheng.logic.bean.http.HttpRespMenuDate
import com.puxiansheng.util.http.APIResp
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded

class UploadImageWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    companion object {
        const val ReferenceID = "referenceID"
        const val ImagePath = "imagePath"
        const val TOKEN = "token"
    }

    override fun doWork(): Result {
        val referenceID = inputData.getString(ReferenceID)
        val imagePath = inputData.getString(ImagePath)
        val token = inputData.getString(TOKEN)

        if (imagePath.isNullOrEmpty() || token.isNullOrEmpty() || referenceID.isNullOrEmpty()) return Result.failure()

        return kotlin.runCatching {
            val file = File(imagePath)

            if (!file.exists()) return Result.failure()

            val rst: APIRst<APIResp<HttpRespMenuDate>> = buildRequest(
                url = API.DO_UPLOAD_IMAGE,
                parts = listOf(
                    MultipartBody.Part.createFormData(
                        "image",
                        file.name,
                        file.asRequestBody("image".toMediaTypeOrNull())
                    )
                ),
                fieldMap = mutableMapOf(
                    "id" to referenceID
                ).also {
                    it["sign"] = API.sign(signatureToken = token, fieldMap = it, method = "POST")
                }
            ).let {
                call(it)
            }
            rst
        }.fold(onSuccess = {
            return if (it.succeeded) {
                Result.success()
            } else {
                Result.failure()
            }
        }, onFailure = {
            Result.failure()
        })
    }
}