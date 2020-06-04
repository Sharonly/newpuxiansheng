package com.puxiansheng.www.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.puxiansheng.www.databinding.DialogLoginSuccessTipsBinding

class LoginSuccessDialog():DialogFragment (){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DialogLoginSuccessTipsBinding.inflate(inflater).apply {
        lifecycleOwner = viewLifecycleOwner
        buttonOk.setOnClickListener {
           dismiss()
        }


    }.root


}