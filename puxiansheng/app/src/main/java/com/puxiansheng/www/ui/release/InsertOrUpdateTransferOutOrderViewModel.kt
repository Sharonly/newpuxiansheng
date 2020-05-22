package com.puxiansheng.www.ui.release

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.*
import com.puxiansheng.logic.data.menu.MenuDatabase
import com.puxiansheng.logic.data.menu.MenuRepository
import com.puxiansheng.logic.data.order.OrderDatabase
import com.puxiansheng.logic.data.order.OrderRepository
import com.puxiansheng.logic.data.order.UploadImageWorker
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InsertOrUpdateTransferOutOrderViewModel(application: Application) :
    AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val orderRepository = OrderRepository(OrderDatabase.getInstance(context).getOrderDao())
    private val menuRepository = MenuRepository(MenuDatabase.getInstance(context).menuDao())

    //form data
    var type = ""//left it to be 0 only with the new order otherwise the id of the order.
    var title = ""
    var industry = ""
    var size = ""
    var rent = ""
    var fee = ""
    var area = ""
    var areatxt = ""
    var address = ""
    var exclusive = 0//rather can transfer without facility.
    var state = 0//rather the shop still running.
    var lng = 0.0
    var lat =0.0
    var floor = 0

    var images: String? = null
    var contactName: String? = null
    var contactPhone: String? = null
    var description: String? = null
    var facility: String? = null
    var environment: String? = null
    var reason: String? = null
    var label: String? = null
    //members

    var industries = MutableLiveData<MutableSet<MenuItem>>()
    var facilities = MutableLiveData<MutableSet<MenuItem>>()
    val saveArea = MutableLiveData<LocationNode>()
    val isCanEmpty = MutableLiveData<String>()

    var selectedImages = MutableLiveData<MutableSet<String>>().apply {
        postValue(mutableSetOf())
    }

    val toastMsg = MutableLiveData<String>()
    val submitResult = MutableLiveData<Int>()

    var selectiveFacilityMenuData = MutableLiveData<List<MenuItem>?>()

    fun submit() = viewModelScope.launch(Dispatchers.IO) {
        orderRepository.submitTransferOutOrder(
            type = type,
            title = title,
            industry = industry,
            size = size,
            rent = rent,
            fee = fee,
            area = area,
            address = address,
            lng = lng,
            lat = lat,
            exclusive = exclusive,
            state = state,
            images = selectedImages.value?.let { set ->
                val builder = StringBuilder()
                set.map { image ->
                    if (image.contains("http")) builder.append("$image,")
                }

                if (builder.isNotEmpty()) {
                    println("images = ${builder.substring(0, builder.lastIndex)}")
                    builder.substring(0, builder.lastIndex)
                } else {
                    println("images = $builder")
                    ""
                }
            },
            contactName = contactName,
            contactPhone = contactPhone,
            description = description,
            facility = facility,
            environment = environment,
            reason = reason,
            label = label,
            floor = floor
        ).let { apiRst ->
            if (apiRst.succeeded) {
                (apiRst as APIRst.Success).data.data?.submitResult?.let {
                    toastMsg.postValue(apiRst.data.msg)
                    apiRst.data.data?.submitResult?.id?.let { shopID ->
                        selectedImages.value?.let { imageSet ->
                            if (imageSet.size > 0)
                                imageSet.map { imageUrl ->
                                    imageUrl
                                }.let { urls ->
                                    uploadImage(imageUrls = urls, refID = shopID)
                                }
                        }
                        submitResult.postValue(apiRst.data.code)
                    }
                }
            } else {
                (apiRst as APIRst.Error)
                toastMsg.postValue(apiRst.exception.message)
            }
        }
    }

    private fun uploadImage(imageUrls: List<String>, refID: String?) = imageUrls.map {
        OneTimeWorkRequest.Builder(UploadImageWorker::class.java).setInputData(
            Data.Builder()
                .putString(UploadImageWorker.ImagePath, it)
                .putString(UploadImageWorker.ReferenceID, refID)
                .putString(UploadImageWorker.TOKEN, API.currentSignatureToken)
                .build()
        ).build()
    }.let {
        WorkManager.getInstance(context).enqueue(it)
    }

    suspend fun requestEditTransferOutOrderDetail(
        shopID: String
    ) = withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
        orderRepository.getEditTransferOutOrderDetailFromRemote(shopID = shopID).let { apiRst ->
            if (apiRst.succeeded) (apiRst as APIRst.Success).data.data?.obj?.let {
                try {
                    Order(
                        favorite = it.favorite,
                        shop = Shop(
                            shopID = it.shopID,
                            title = it.title,
                            size = it.size,
                            rent = it.rent,
                            fee = it.fee,
                            address = it.address,
                            industry = it.industry,
                            runningState = it.runningState,
                            includeFacilities = it.includeFacilities,
                            images = it.images,
                            floor = it.floor,
                            facilities = it.facilities,
                            description = it.description,
                            descriptionUrl = it.descriptionUrl,
                            environment = it.environment,
                            reason = it.reason,
                            isSuccess = it.isSuccess,
                            //formatted data
                            formattedDate = it.formattedDate,
                            formattedPageViews = it.formattedPageViews,
                            formattedRent = it.formattedRent,
                            formattedSize = it.formattedSize,
                            formattedFee = it.formattedTransferFee,
                            formattedFinalIndustry = it.formattedFinalIndustry,
                            formattedFinalLocationNode = it.formattedFinalLocationNode,
                            formattedLocationNodes = it.formattedLocationNodes,
                            formattedFacilities = it.formattedFacilities,
                            formattedIndustry = it.formattedIndustry?.let { strList ->
                                val sb = java.lang.StringBuilder()
                                strList.forEach { str ->
                                    sb.append(str)
                                    sb.append(" ")
                                }
                                sb.toString()
                            } ?: ""
                        ), serviceAgent = ServiceAgent(
                            name = it.shopOwnerName,
                            phone = it.serviceAgentPhone
                        ), shopOwner = User(
                            actualName = it.shopOwnerName,
                            userPhoneNumber = it.shopOwnerPhoneNumbr
                        )
                    )
                } catch (e: Exception) {
                    null
                }
            } else null
        }
    }
}