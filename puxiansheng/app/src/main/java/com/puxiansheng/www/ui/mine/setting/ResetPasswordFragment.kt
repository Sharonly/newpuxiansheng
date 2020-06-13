package com.puxiansheng.www.ui.mine.setting

import android.text.InputType
import android.util.Log
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.puxiansheng.logic.api.API
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity

import kotlinx.android.synthetic.main.fragment_reset_password.*

class ResetPasswordActivity : MyBaseActivity() {
    private lateinit var resetPasswordViewModel: ResetPasswordViewModel

    override fun getLayoutId(): Int {
        return R.layout.fragment_reset_password
    }

    override fun business() {
        resetPasswordViewModel = ViewModelProvider(this)[ResetPasswordViewModel::class.java]
    }


    fun initView() {
        button_back.setOnClickListener {
            onBackPressed()
        }

        input_older_password.addTextChangedListener { editable ->
            editable?.toString()?.let {
                resetPasswordViewModel.originalPassword = it
            }
        }
        input_new_password.addTextChangedListener { editable ->
            editable?.toString()?.let {
                resetPasswordViewModel.newPassword = it
            }
        }

        input_password_again.addTextChangedListener { editable ->
            editable?.toString()?.let {
                resetPasswordViewModel.newSecondPassword = it
            }
        }

        ic_eye_close.setOnClickListener {
            input_password_again.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

        }
        ic_eye_show.setOnClickListener {
            input_password_again.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        }

        reset_password.setOnClickListener {
            resetPasswordViewModel.originalPassword.let {
                if (it.isEmpty()) {
                    Toast.makeText(this, "请输入原密码", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }
            resetPasswordViewModel.newPassword.let {
                if (it.isEmpty()) {
                    Toast.makeText(this, "请输入新密码", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }
            resetPasswordViewModel.newSecondPassword.let {
                if (it.isEmpty()) {
                    Toast.makeText(this, "请再次输入新密码", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }
Log.d("---newPassword "," resetPasswordViewModel.newPassword = "+resetPasswordViewModel.newPassword+" 1 =  "+resetPasswordViewModel.newSecondPassword+"   2 = "+resetPasswordViewModel.originalPassword )
            if(resetPasswordViewModel.newPassword != resetPasswordViewModel.newSecondPassword){
                Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show()
            }

            resetPasswordViewModel.resetPassword()
        }




        resetPasswordViewModel.toastMsg.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        resetPasswordViewModel.resetResult.observe(this, Observer {
            if (it == API.CODE_SUCCESS) {
               onBackPressed()
            }
        })

    }


}