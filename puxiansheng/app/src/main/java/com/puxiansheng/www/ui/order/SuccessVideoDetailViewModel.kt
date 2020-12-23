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

class SuccessVideoDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val orderRepository = OrderRepository(OrderDatabase.getInstance(context).getOrderDao())
    private val userRepository = UserRepository(UserDatabase.getInstance(context).userDao())
    private val commonDataRepository = CommonDataRepository()

    suspend fun requestSuccessVideoDetail(
        shopID: String
    ) = withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
        orderRepository.getSuccessVideoDetailFromRemote(shopID = shopID).let { apiRst ->
            if (apiRst.succeeded) (apiRst as APIRst.Success).data.data?.data else null
        }
    }

    suspend fun requestRecommendShopList(
        cityId: String,
        shopID: String
    ) = withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
        orderRepository.getRecommendShopVideoFromRemote(cityId = cityId, shopID = shopID).let { apiRst ->
          return@let  if (apiRst.succeeded) (apiRst as APIRst.Success).data.data?.videos else null
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