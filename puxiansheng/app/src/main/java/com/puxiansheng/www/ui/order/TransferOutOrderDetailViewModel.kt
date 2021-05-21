package com.puxiansheng.www.ui.order

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.logic.bean.ServiceAgent
import com.puxiansheng.logic.bean.Shop
import com.puxiansheng.logic.bean.User
import com.puxiansheng.logic.data.common.CommonDataRepository
import com.puxiansheng.logic.data.order.OrderDatabase
import com.puxiansheng.logic.data.order.OrderRepository
import com.puxiansheng.logic.data.user.UserDatabase
import com.puxiansheng.logic.data.user.UserRepository
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.StringBuilder

class TransferOutOrderDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val orderRepository = OrderRepository(OrderDatabase.getInstance(context).getOrderDao())
    private val userRepository = UserRepository(UserDatabase.getInstance(context).userDao())
    private val commonDataRepository = CommonDataRepository()


    suspend fun requestTransferOutOrderDetail(
        shopID: String
    ) = withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
        orderRepository.getTransferOutOrderDetailFromRemote(shopID = shopID).let { apiRst ->
            if (apiRst.succeeded) (apiRst as APIRst.Success).data.data?.obj else null
//            if (apiRst.succeeded) (apiRst as APIRst.Success).data.data?.obj?.let {
//                try {
//                    Order(
//                        favorite = it.favorite,
//                        shop = Shop(
//                            shopID = it.shopID,
//                            title = it.title,
//                            size = it.size,
//                            rent = it.rent,
//                            fee = it.fee,
//                            address = it.address,
//                            industry = it.categoryStr,
//                            runningState = it.runningState,
//                            viewOpening = it.viewOpening,
//                            viewCanEmpty = it.viewCanEmpty,
//                            includeFacilities = it.includeFacilities,
//                            newLat = it.lat,
//                            newLng = it.lng,
//                            images = it.images,
//                            floor = it.floor,
//                            facilities = it.facilities,
//                            description = it.description,
//                            descriptionUrl = it.descriptionUrl,
//                            environment = it.environment,
//                            reason = it.reason,
//                            isSuccess = it.isSuccess,
//                            //formatted data
//                            formattedDate = it.formattedDate,
//                            formattedPageViews = it.formattedPageViews,
//                            formattedRent = it.formattedRent,
//                            formattedSize = it.formattedSize,
//                            formattedFee = it.formattedTransferFee,
//                            formattedFinalIndustry = it.formattedFinalIndustry,
//                            formattedFinalLocationNode = it.formattedFinalLocationNode,
//                            formattedLocationNodes = it.formattedLocationNodes,
//                            formattedFacilities = it.formattedFacilities,
//                            formattedIndustry = it.formattedIndustry?.let { strList ->
//                                val sb = StringBuilder()
//                                strList.forEach { str ->
//                                    sb.append(str)
//                                    sb.append(" ")
//                                }
//                                sb.toString()
//                            } ?: ""
//                        ), serviceAgent = ServiceAgent(
//                            name = it.shopOwnerName,
//                            phone = it.serviceAgentPhone
//                        ), shopOwner = User(
//                            actualName = it.shopOwnerName,
//                            userPhoneNumber = it.shopOwnerPhoneNumbr
//                        )
//                    )
//                } catch (e: Exception) {
//                    null
//                }
//            } else null
        }
    }

    suspend fun requestUserLikeShopList(
        cityId: String,
        shopID: String,
        page:Int
    ) = withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
        orderRepository.getUserLikeShopFromRemote(cityId = cityId, shopID = shopID,page = page).let { apiRst ->
          return@let  if (apiRst.succeeded) (apiRst as APIRst.Success).data.data?.data else null
        }
    }


    suspend fun favorite(
        objectID: String,
        type: Int
    ) = withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
        userRepository.favorite(
            objectID = objectID,
            type = type
        ).let {
            if (it.succeeded) (it as APIRst.Success).data else null
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