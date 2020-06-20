package com.puxiansheng.www.ui.order

import android.app.Application
import android.util.Log
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

class NewTransferOutOrdersViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val orderRepository = OrderRepository(OrderDatabase.getInstance(context).getOrderDao())

    var currentPage = 1
    var currentCity = ""
    var industryIDs = ""
    var sizeRangeID = ""
    var areaIDs = ""
    var rentIDs = ""
    var sortBy = ""
    var sortType = ""
    var title = ""


    suspend fun getTransferOutOrdersFromRemote()  = withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
        orderRepository.getTransferOutOrdersFromRemote(
            title = title,
            page = currentPage,
            industry = industryIDs,
            size = sizeRangeID,
            area = areaIDs,
            sortBy = sortBy,
            sortType = sortType,
            rent = rentIDs,
            hot = null,
            top = null,
            recommend = null,
            city = currentCity
        ).let { apiRst ->
            if (apiRst.succeeded) {
                (apiRst as APIRst.Success).data.data?.data?.orders
            }else null
        }
    }


    suspend fun getTransferSuccessFromRemote() = withContext(viewModelScope.coroutineContext + Dispatchers.IO)  {
        orderRepository.getTransferSuccessFromRemote(
            title = title,
            page = currentPage,
            industry = industryIDs,
            size = sizeRangeID,
            area = areaIDs,
            sortBy = sortBy,
            sortType = sortType,
            rent = rentIDs,
            hot = null,
            top = null,
            recommend = null,
            city = currentCity
        ).let { apiRst ->
            if (apiRst.succeeded) {
                (apiRst as APIRst.Success).data.data?.data?.orders
            }else null
        }

    }
}