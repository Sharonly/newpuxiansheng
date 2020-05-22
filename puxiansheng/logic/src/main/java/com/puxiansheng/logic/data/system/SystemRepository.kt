package com.puxiansheng.logic.data.system

import com.puxiansheng.logic.bean.SignatureToken
import com.puxiansheng.logic.bean.Device
import com.puxiansheng.logic.bean.NewPackage
import com.puxiansheng.logic.bean.SystemConfig
import com.puxiansheng.logic.bean.http.HttpRespEmpty
import com.puxiansheng.util.http.APIResp
import com.puxiansheng.util.http.APIRst

class SystemRepository(private val systemDao: SystemDao) {
    private val localSystemConfigRepository = LocalSystemRepository(systemDao)
    private val remoteSystemConfigRepository = RemoteSystemRepository()

    fun insertOrUpdateSystemConfig(config: SystemConfig) =
        localSystemConfigRepository.insertOrUpdateLocalSystemConfig(config = config)

    fun requireSignatureToken(device: Device): APIRst<APIResp<SignatureToken>> =
        remoteSystemConfigRepository.requestSignatureToken(device)


    fun requestVerificationCode(
        phoneNumber: String,
        key: String,
        code: String,
        type: String
    )= remoteSystemConfigRepository.requestVerificationCode(
        phoneNumber = phoneNumber,
        key = key,
        code = code,
        type = type
    )

    fun requestRemoteSystemConfig() = remoteSystemConfigRepository.requestRemoteSystemConfig()

    fun requestImageCode() = remoteSystemConfigRepository.requestImageCode()
}