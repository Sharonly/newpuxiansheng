package com.puxiansheng.www.ui.login

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.puxiansheng.www.databinding.DialogLoginSuccessTipsBinding
import com.puxiansheng.www.databinding.DialogPasswordChangedBinding

class PasswordChangeDialog(): DialogFragment(){

    override fun onStart() {
        super.onStart()
        dialog?.let {
            it.window?.let { window ->
                window.setLayout(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
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
    ): View? = DialogPasswordChangedBinding.inflate(inflater).apply {
        lifecycleOwner = viewLifecycleOwner
        buttonOk.setOnClickListener {
            val intent = Intent(requireActivity().application, LoginActivity::class.java)
            startActivity(intent)
        }


    }.root


}