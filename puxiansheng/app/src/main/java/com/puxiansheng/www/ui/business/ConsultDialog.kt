package com.puxiansheng.www.ui.business

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.puxiansheng.logic.api.API
import com.puxiansheng.www.databinding.DialogBusinessConsultBinding
import kotlinx.coroutines.launch


class ConsultDialog() : DialogFragment() {
    private lateinit var viewModel: InvestBusnessViewModel

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
    ): View? = DialogBusinessConsultBinding.inflate(inflater).apply {
        viewModel =  ViewModelProvider(requireActivity())[InvestBusnessViewModel::class.java]

            name.addTextChangedListener {
                viewModel.name = it.toString()
        }

        phone.addTextChangedListener {
            viewModel.phone = it.toString()
        }

        buttonOk.setOnClickListener {
            lifecycleScope.launch {
                viewModel.submitUserInfo( viewModel.name,viewModel.phone)?.let {
                    if (it.code == API.CODE_SUCCESS) {
                        dismiss()
                        Toast.makeText(requireContext(),"提交成功",Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(requireContext(),it.msg,Toast.LENGTH_SHORT).show()
                    }
                }
            }

//            val intent = Intent(requireActivity(), MyReleaseAllActivity::class.java)
//            startActivity(intent)
//            dismiss()
        }
        iconCancel.setOnClickListener {
            dismiss()
        }

        buttonCancel.setOnClickListener {
            dismiss()
        }

    }.root
}