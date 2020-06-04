package com.puxiansheng.www.ui.mine.setting

import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.puxiansheng.logic.api.API
import com.puxiansheng.www.R
import com.puxiansheng.www.databinding.FragmentResetPasswordBinding
import kotlinx.android.synthetic.main.activity_forget_password.*

class ResetPasswordFragment : Fragment() {
    private lateinit var resetPasswordViewModel: ResetPasswordViewModel


    override fun onAttach(context: Context) {
        super.onAttach(context)
        resetPasswordViewModel = ViewModelProvider(this)[ResetPasswordViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentResetPasswordBinding.inflate(inflater).apply {
        lifecycleOwner = viewLifecycleOwner

        buttonBack.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.homeNavHost).popBackStack()
        }

        inputOlderPassword.addTextChangedListener { editable ->
            editable?.toString()?.let {
                resetPasswordViewModel.originalPassword = it
            }
        }
        inputNewPassword.addTextChangedListener { editable ->
            editable?.toString()?.let {
                resetPasswordViewModel.newPassword = it
            }
        }

        inputPasswordAgain.addTextChangedListener { editable ->
            editable?.toString()?.let {
                resetPasswordViewModel.newSecondPassword = it
            }
        }

        icEyeClose.setOnClickListener {
            input_password_again.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

        }
        icEyeShow.setOnClickListener {
            input_password_again.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        }

        resetPassword.setOnClickListener {
            resetPasswordViewModel.originalPassword.let {
                if (it.isEmpty()) {
                    Toast.makeText(requireActivity(), "请输入原密码", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }
            resetPasswordViewModel.newPassword.let {
                if (it.isEmpty()) {
                    Toast.makeText(requireActivity(), "请输入新密码", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }
            resetPasswordViewModel.newSecondPassword.let {
                if (it.isEmpty()) {
                    Toast.makeText(requireActivity(), "请再次输入新密码", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }
Log.d("---newPassword "," resetPasswordViewModel.newPassword = "+resetPasswordViewModel.newPassword+" 1 =  "+resetPasswordViewModel.newSecondPassword+"   2 = "+resetPasswordViewModel.originalPassword )
            if(resetPasswordViewModel.newPassword != resetPasswordViewModel.newSecondPassword){
                Toast.makeText(context, "两次密码不一致", Toast.LENGTH_SHORT).show()
            }

            resetPasswordViewModel.resetPassword()
        }




        resetPasswordViewModel.toastMsg.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })

        resetPasswordViewModel.resetResult.observe(viewLifecycleOwner, Observer {
            if (it == API.CODE_SUCCESS) {
                Navigation.findNavController(requireActivity(), R.id.homeNavHost).navigateUp()
            }
        })

    }.root
}