package com.puxiansheng.www.common.player

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.puxiansheng.www.R

class NetTipsDialog(var context: Activity):DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val view=inflater.inflate(R.layout.layout_net_tips_dialog,container,false)
        view.findViewById<TextView>(R.id.bt_sure).setOnClickListener {
            dismiss()
            context.finish()
        }
        return view
    }



}