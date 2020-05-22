package com.puxiansheng.www.common

import android.text.Editable
import android.text.TextWatcher

open class SimpleTextWatcher : TextWatcher {
    override fun afterTextChanged(editable: Editable?) = Unit

    override fun beforeTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

    override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
}