package com.puxiansheng.www.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.puxiansheng.www.databinding.DialogLoginSuccessTipsBinding

class LoginSuccessDialog(private val tipMsg:String):DialogFragment (){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DialogLoginSuccessTipsBinding.inflate(inflater).apply {
        lifecycleOwner = viewLifecycleOwner

        msg.text = tipMsg
        buttonOk.setOnClickListener {
           dismiss()
        }


    }.root


}