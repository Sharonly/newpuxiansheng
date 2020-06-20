package com.puxiansheng.www.ui.release

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.puxiansheng.logic.bean.*
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
//            if (apiRst.succeeded) (apiRst as APIRst.Success).data.data?.obj?.let {
//                Order(
//                    favorite = it.favorite,
//                    shop = Shop(
//                        shopID = it.shopID,
//                        title = it.title,
//                        size = it.size,
//                        rent = it.rent,
//                        fee = it.fee,
//                        address = it.address,
//                        industry = it.industry,
//                        runningState = it.runningState,
//                        includeFacilities = it.includeFacilities,
//                        images = it.images,
//                        floor = it.floor,
//                        labels = it.labelList,
//                        facilities = it.facilities,
//                        description = it.description,
//                        descriptionUrl = it.descriptionUrl,
//                        environment = it.environment,
//                        reason = it.reason,
//                        //formatted data
//                        formattedDate = it.formattedDate,
//                        formattedPageViews = it.formattedPageViews,
//                        formattedRent = it.formattedRent,
//                        formattedSize = it.formattedSize,
//                        formattedFee = it.formattedTransferFee,
//                        formattedFinalLocationNode = it.formattedFinalLocationNode,
//                        formattedFinalIndustry = it.formattedFinalIndustry,
//                        formattedLocationNodes = it.formattedLocationNodes,
//                        formattedFacilities= it.formattedFacilities,
//                        formattedIndustry = it.formattedIndustry?.let { strList ->
//                            val sb = StringBuilder()
//                            strList.forEach { str ->
//                                sb.append(str)
//                                sb.append(" ")
//                            }
//                            sb.toString()
//                        } ?: ""
//                    ), serviceAgent = ServiceAgent(
//                        name = it.shopOwnerName,
//                        phone = it.serviceAgentPhone
//                    ), shopOwner = User(
//                        actualName = it.shopOwnerName,
//                        userPhoneNumber = it.shopOwnerPhoneNumbr
//                    )
//                )
//            } else null
        }
    }
}