package com.puxiansheng.www.ui.mine.relase

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.puxiansheng.www.databinding.DialogDeleteOrderBinding

class DeleteOrderDialog (private var dialogtitle:String): DialogFragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    var downLoadUrl: String = ""
    override fun onStart() {
        super.onStart()
        dialog?.let {
            it.window?.let { window ->
                window.setLayout(
                    resources.displayMetrics.widthPixels.times(0.9).toInt(),
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                window.setGravity(Gravity.CENTER)
                window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DialogDeleteOrderBinding.inflate(inflater).apply {
        title.text = dialogtitle

        buttonCancel.setOnClickListener { dismiss() }
        buttonOk.setOnClickListener { dismiss() }
    }.root


}