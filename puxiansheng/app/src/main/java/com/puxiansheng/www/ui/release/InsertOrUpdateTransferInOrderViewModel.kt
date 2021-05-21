package com.puxiansheng.www.ui.release

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.*
import com.puxiansheng.logic.data.common.CommonDataRepository
import com.puxiansheng.logic.data.menu.MenuDatabase
import com.puxiansheng.logic.data.menu.MenuRepository
import com.puxiansheng.logic.data.order.OrderDatabase
import com.puxiansheng.logic.data.order.OrderRepository
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.StringBuilder

class InsertOrUpdateTransferInOrderViewModel(application: Application) :
    AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val orderRepository = OrderRepository(OrderDatabase.getInstance(context).getOrderDao())
    private val menuRepository = MenuRepository(MenuDatabase.getInstance(context).menuDao())
    private val commonDataRepository = CommonDataRepository()

    //form data
    var type = "" //left it to be 0 only with the new order otherwise the id of the order.
    var title = ""
    var size = ""
    var rent = ""
    var industry = ""
    var area = ""
    var address = ""
    var description = ""
    var contactName = ""
    var contactPhone = ""
    var floor = 0
    var facility: String = ""

    val toastMsg = MutableLiveData<String>()
    val submitResult = MutableLiveData<Int>()
    var selectiveFacilityMenuData = MutableLiveData<List<MenuItem>?>()
    var selectiveAreaMenuData = MutableLiveData<List<LocationNode>?>()
    var facilities = MutableLiveData<MutableSet<MenuItem>>()

    fun submit() = viewModelScope.launch(Dispatchers.IO) {
        orderRepository.submitTransferInOrder(
            type = type,
            title = title,
            size = size,
            industry = industry,
            rent = rent,
            description = description,
            contactName = contactName,
            contactPhone = contactPhone,
            area = area,
            facility = facility,
            floor = floor
        ).let { apiRst ->
            if (apiRst.succeeded) {
                (apiRst as APIRst.Success).data.apply {
                    submitResult.postValue(this.code)
                    toastMsg.postValue(this.msg)
                }
            } else {
                (apiRst as APIRst.Error)
                toastMsg.postValue(apiRst.exception.message)
            }
        }
    }

    suspend fun requestEditTransferInOrderDetail(
        shopID: String
    ) = withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
        orderRepository.getEditTransferInOrderDetailFromRemote(shopID = shopID).let { apiRst ->
            if (apiRst.succeeded) (apiRst as APIRst.Success).data.data?.obj else null

        }
    }

    suspend fun getMultiAreaMenuDataFromRemote(ids:String) = withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
        menuRepository.requestRemoteMultiAreaSelectiveData("1",ids, API.currentSignatureToken).let {
            if (it.succeeded) (it as APIRst.Success).data.data?.areaObject else null

        }
    }


    suspend fun getConfigInfo(name:String) = withContext(
        context = viewModelScope.coroutineContext + Dispatchers.IO
    ) {
        commonDataRepository.getConfigUrlRemote(name = name).let {
            if (it.succeeded) (it as APIRst.Success).data?.data?.urls else null
        }
    }

}