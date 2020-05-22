package com.puxiansheng.www.ui.mine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MineViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is mineFragment"
    }
    val text: LiveData<String> = _text
}