package com.puxiansheng.www.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puxiansheng.logic.bean.http.HttpRespBannerImages
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.ApiBaseResponse
import com.puxiansheng.www.http.RetrofitManage
import com.puxiansheng.www.tools.DevicesUtils
import com.puxiansheng.www.tools.SpUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewHomeViewModel : ViewModel(){

//    fun changeCompany(companyId:String): LiveData<ApiBaseResponse<ChangeCompanyResult>> {
//        val parames = HashMap<String, String>()
//        val sign = DevicesUtils.sign(
//            signatureToken = SpUtils.getInstance().getSigntoken(),
//            fieldMap = parames,
//            method = "GET"
//        )
//        return RetrofitManage.getApiServiceX().changeCompany(companyId,sign)
//    }


     fun requestMenuImage(): LiveData<ApiBaseResponse<HttpRespBannerImages>>{
                val parames = HashMap<String, String>()
        val sign = DevicesUtils.sign(
            signatureToken = SpUtils.getInstance().getSigntoken(),
            fieldMap = parames,
            method = "GET"
        )
        return RetrofitManage.getApiServiceX().getHomeMenu(sign)
    }



}

