package com.puxiansheng.logic.data.user

import android.net.Uri
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.Address
import com.puxiansheng.logic.bean.User
import com.puxiansheng.logic.bean.http.HttpRespEmpty
import com.puxiansheng.logic.bean.http.HttpRespIconUpload
import com.puxiansheng.logic.bean.http.HttpRespUserInfo
import com.puxiansheng.util.http.APIResp
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.buildRequest
import java.io.File

class UserRepository(userDao: UserDao) {
    private val localUserRepository = LocalUserRepository(userDao)
    private val remoteUserRepository = RemoteUserRepository()

    suspend fun insertUser(user: User) = localUserRepository.insertUser(user = user)

    suspend fun requestLastLocalUser(): User? = localUserRepository.requestLastUser()

    fun login(
        userAccount: String,
        userPassword: String?,
        newPassword: String?,
        verificationCode: String?,
        wechatCode: String?
    ) = remoteUserRepository.login(
        userAccount = userAccount,
        userPassword = userPassword,
        verificationCode = verificationCode,
        newPassword = newPassword,
        wechatCode = wechatCode
    )

    fun loginByPass(
        userAccount: String,
        userPassword: String?
    ) = remoteUserRepository.loginByPass(
        userAccount = userAccount,
        userPassword = userPassword
    )

    fun loginByPhoneNum(
        userAccount: String,
        verificationCode: String?
    ) = remoteUserRepository.loginByPhoneNum(
        userAccount = userAccount,
        code = verificationCode
    )

    fun loginByWeChat(
        wechatCode: String
    ) = remoteUserRepository.loginByWeChat(
        weChatCode = wechatCode
    )

    fun register(
        userAccount: String,
        verificationCode: String?
    ) = remoteUserRepository.register(
        userAccount = userAccount,
        code = verificationCode
    )

    fun forgetPassword(
        userAccount: String,
        verificationCode: String,
        password: String,
        newPassword: String
    ) = remoteUserRepository.forgetPassword(
        userAccount = userAccount,
        code = verificationCode,
        password = password,
        newPassword = newPassword
    )


    fun logout() = remoteUserRepository.logout()

    fun resetPassword(
        password: String,
        userId: String,
        newPasswordAgain: String,
        newPassword: String
    ): APIRst<APIResp<HttpRespEmpty>> = remoteUserRepository.resetPassword(
        userId = userId,
        newPassword = newPassword,
        newPasswordAgain = newPasswordAgain,
        password = password
    )

    fun submitSuggestion(content: String, cate: String): APIRst<APIResp<HttpRespEmpty>> =
        remoteUserRepository.submitSuggestion(content = content, cate = cate)

    fun getRequestType() = remoteUserRepository.getRequestType()

    fun getRequestList() = remoteUserRepository.getRequestList()

    fun getUserCallBack() = remoteUserRepository.getUserCallBack()

    fun requireRemoteUserInfo() = remoteUserRepository.requireRemoteUserInfo()

    fun submitUserInfo(
        nickName: String,
        sex: String,
        actulName: String,
        iconImg: String?,
        address: String?,
        cityId: Int
    ): APIRst<APIResp<HttpRespEmpty>> =
        remoteUserRepository.submitUserInfo(
            nickName = nickName,
            sex = sex,
            actualName = actulName,
            headerImg = iconImg,
            address = address,
            cityId = cityId
        )

    fun submitProjectInfo(
        projectName: String,
        phone: String,
        name: String,
        money: String
    ): APIRst<APIResp<HttpRespEmpty>> =
        remoteUserRepository.submitProjectInfo(
            projectId = projectName,
            name = name,
            phone = phone,
            money = money
        )

    fun submitUserIcon(
        file: File
    ): APIRst<APIResp<HttpRespIconUpload>> =
        remoteUserRepository.submitUserIcon(
            file = file
        )

    fun favorite(
        objectID: String,
        type: Int
    ) = remoteUserRepository.favorite(
        objectID = objectID,
        type = type
    )

    fun requireHistorySearch(type: Int, userId: String) =
        remoteUserRepository.requireHistorySearch(type = type, userId = userId)

    fun deleteHistorySearch() = remoteUserRepository.deleteHistorySearch()
    fun requireRecommendSearch() = remoteUserRepository.requireRecommendSearch()

    fun releaseCount() = remoteUserRepository.getReleaseCountFromRemote()


    fun bindMobileNumber(
        phone: String,
        code: String,
        id: String
    ) = remoteUserRepository.bindMobileNumber(
        phone = phone,
        id = id,
        code = code
    )
}