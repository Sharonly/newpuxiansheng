package com.puxiansheng.www.ui.mine.relase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.logic.bean.Shop
import com.puxiansheng.logic.data.order.OrderDatabase
import com.puxiansheng.logic.data.order.OrderRepository
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OrderSoldOutViewModel (application: Application) : AndroidViewModel(application){
    private val context = getApplication<Application>().applicationContext
    private val orderRepository = OrderRepository(OrderDatabase.getInstance(context).getOrderDao())

    fun refresh() {
        viewModelScope.launch {
            loadMore()
        }
    }

    fun loadMore(
    ) = viewModelScope.launch(Dispatchers.IO) {
        getRemoteSoldOutOrders()

    }

    suspend fun getRemoteSoldOutOrders() =
        withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
            orderRepository.getUserSoldOutOrder().let { apiRst ->
                if (apiRst.succeeded) (apiRst as APIRst.Success).data.data?.data else null
//                if (apiRst.succeeded) (apiRst as APIRst.Success).data.data?.data?.let {
//                    it.map { shop ->
//                        try {
//                            Order(
//                                state = shop.state,
//                                shop = Shop(
//                                    shopID = shop.shopID,
//                                    title = shop.title,
//                                    size = shop.size,
//                                    rent = shop.rent,
//                                    fee = shop.fee,
//                                    address = shop.address,
//                                    industry = shop.industry,
//                                    runningState = shop.runningState,
//                                    isTop = shop.isTop,
//                                    isHot = shop.isHot,
//                                    isRecommend = shop.isRecommend,
//                                    isLargeOrder = shop.isLargeOrder,
//                                    image = shop.image,
//                                    images = shop.images,
//                                    floor = shop.floor,
//                                    labels = shop.labelList,
//                                    reason = shop.reason,
//                                    isVip = shop.isVip,
//                                    category_acreage = shop.categoryAcreage,
//                                    data_type = shop.data_type,
//                                    jump_type = shop.jump_type,
//                                    jump_view = shop.jump_view,
//                                    jump_param = shop.jump_param,
//                                    formattedArea = shop.area_point_str,
//                                    formattedDate = shop.formattedDate,
//                                    formattedSize = shop.formattedSize,
//                                    formattedRent = shop.formattedRent,
//                                    formattedFee = shop.formattedTransferFee,
//                                    formattedIndustry = shop.view_category,
//                                    formattedFinalIndustry = shop.formattedFinalIndustry,
//                                    formattedFinalLocationNode = shop.formattedFinalLocationNode
//                                )
//                            )
//                        } catch (e: Exception) {
//                            null
//                        }
//                    }
//                } else null
            }
        }
}