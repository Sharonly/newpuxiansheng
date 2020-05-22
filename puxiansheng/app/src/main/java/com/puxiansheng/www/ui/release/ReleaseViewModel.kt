package com.puxiansheng.www.ui.release

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReleaseViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is releaseFragment"
    }
    val text: LiveData<String> = _text
}