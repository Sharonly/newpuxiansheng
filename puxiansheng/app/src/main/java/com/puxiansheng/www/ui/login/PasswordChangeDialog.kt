package com.puxiansheng.www.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.puxiansheng.www.databinding.DialogLoginSuccessTipsBinding
import com.puxiansheng.www.databinding.DialogPasswordChangedBinding

class PasswordChangeDialog(): DialogFragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DialogPasswordChangedBinding.inflate(inflater).apply {
        lifecycleOwner = viewLifecycleOwner
        buttonOk.setOnClickListener {
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(intent)
        }


    }.root


}