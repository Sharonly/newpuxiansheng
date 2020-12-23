package com.puxiansheng.www.ui.order.dialog

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.puxiansheng.logic.bean.SortableItem

class SelectSortTypeViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext

    var selectedSortableItem: SortableItem? = null
    val selectedSortableItemPosition = MutableLiveData<Int>()

    val selectiveSortTypeData = MutableLiveData<List<SortableItem>>().apply {
        postValue(
            listOf(
                SortableItem(text = "综合排序", sortBy = "", sortType = ""),
                SortableItem(text = "租金升序", sortBy = "rent", sortType = "asc"),
                SortableItem(text = "租金降序", sortBy = "rent", sortType = "desc"),
                SortableItem(text = "面积升序", sortBy = "acreage", sortType = "asc"),
                SortableItem(text = "面积降序", sortBy = "acreage", sortType = "desc"),
                SortableItem(text = "时间升序", sortBy = "time", sortType = "asc"),
                SortableItem(text = "时间降序", sortBy = "time", sortType = "desc")
            )
        )
    }
}