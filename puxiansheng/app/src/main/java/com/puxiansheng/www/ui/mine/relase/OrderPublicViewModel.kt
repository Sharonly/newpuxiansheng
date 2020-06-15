package com.puxiansheng.www.ui.mine.relase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.logic.bean.ServiceAgent
import com.puxiansheng.logic.bean.Shop
import com.puxiansheng.logic.bean.User
import com.puxiansheng.logic.data.order.OrderDatabase
import com.puxiansheng.logic.data.order.OrderRepository
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.StringBuilder

class OrderPublicViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val orderRepository = OrderRepository(OrderDatabase.getInstance(context).getOrderDao())



    fun refresh() {
        viewModelScope.launch {
            loadMore()
        }
    }

    fun loadMore(
    ) = viewModelScope.launch(Dispatchers.IO) {
        getRemoteUserPublicOrders()

    }

    suspend fun deleteFavorTransferInOrderFromRemote(
        shopID: String
    ) = withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
        orderRepository.deleteFavorTransferInOrderFromRemote(shopID = shopID).let { apiRst ->
            if (apiRst.succeeded) {
                (apiRst as APIRst.Success).data
            }else{
                null
            }
        }
    }

    var type = ""
    var shopId = ""
    suspend fun refreshShopFromRemote(
    ) = withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
        orderRepository.refreshShopFromRemote(type = type,shopID = shopId).let { apiRst ->
            if (apiRst.succeeded) {
                (apiRst as APIRst.Success).data
            }else{
                null
            }
        }
    }

    suspend fun getRemoteUserPublicOrders() =
        withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
            orderRepository.getUserPublicOrder().let { apiRst ->
//            return@let if (apiRst.succeeded) (apiRst as APIRst.Success).data.data?.data else null
                if (apiRst.succeeded) (apiRst as APIRst.Success).data.data?.data?.let {
                    it.map { shop ->
                        try {
                            Order(
                                state = shop.state,
                                shop = Shop(
                                    shopID = shop.shopID,
                                    title = shop.title,
                                    size = shop.size,
                                    rent = shop.rent,
                                    fee = shop.fee,
                                    address = shop.address,
                                    industry = shop.industry,
                                    runningState = shop.runningState,
                                    isTop = shop.isTop,
                                    isHot = shop.isHot,
                                    isRecommend = shop.isRecommend,
                                    isLargeOrder = shop.isLargeOrder,
                                    isVip = shop.isVip,
                                    image = shop.image,
                                    images = shop.images,
                                    floor = shop.floor,
                                    reason = shop.reason,
                                    category_acreage = shop.categoryAcreage,
                                    data_type = shop.data_type,
                                    jump_type = shop.jump_type,
                                    jump_view = shop.jump_view,
                                    jump_param = shop.jump_param,
                                    formattedArea = shop.area_point_str,
                                    formattedDate = shop.formattedDate,
                                    formattedSize = shop.formattedSize,
                                    formattedRent = shop.formattedRent,
                                    formattedFee = shop.formattedTransferFee,
                                    formattedIndustry = shop.view_category,
                                    formattedFinalIndustry = shop.formattedFinalIndustry,
                                    formattedFinalLocationNode = shop.formattedFinalLocationNode
                                )
                            )
                        } catch (e: Exception) {
                            null
                        }
                    }
                } else null
            }
        }


}