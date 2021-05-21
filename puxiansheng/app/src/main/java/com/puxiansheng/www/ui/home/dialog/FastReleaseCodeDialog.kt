package com.puxiansheng.www.ui.home.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.puxiansheng.logic.api.API
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import com.puxiansheng.www.R
import com.puxiansheng.www.databinding.DialogFastReleaseCodeBinding
import com.puxiansheng.www.tools.SpUtils
import com.puxiansheng.www.ui.home.viewmodel.FastReleaseViewModel
import com.zhiniao.player.utils.SoftInputUtils
import kotlinx.android.synthetic.main.layout_register.*
import kotlinx.coroutines.launch

class FastReleaseCodeDialog(private var type: Int, private var phone: String) : DialogFragment() {
    private lateinit var viewModel: FastReleaseViewModel


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

    var listener: OnDisMissListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DialogFastReleaseCodeBinding.inflate(inflater).apply {
        viewModel = ViewModelProvider(requireActivity())[FastReleaseViewModel::class.java]
        viewModel?.phoneNum = phone
        lifecycleScope.launch {
            viewModel?.requestVerificationCode()?.let {
                if (it == API.CODE_SUCCESS) {
                    viewModel?.startCountDown()
                    txtAgain.isEnabled = false
                }
            }
        }

        viewModel.phone = phone
        viewModel.city = SpUtils.get(API.USER_CITY_ID, 0).toString()
        desc.text = "已发送到手机" + phone.subSequence(0, 3) + "****" + phone.subSequence(7, 11)
        txtError.visibility = View.GONE
        txtAgain.setOnClickListener {
            lifecycleScope.launch {
                viewModel?.requestVerificationCode()?.let {
                    if (it == API.CODE_SUCCESS) {
                        viewModel?.startCountDown()
                        txtAgain.isEnabled = false

                    }
                }
            }
            txtError.setText("")
        }

        viewModel?.countDown?.observe(requireActivity(), Observer {
            if (it == 0) {
//                txtAgain.setTextColor(requireContext().resources.getColor(R.color.release_sava_text_color))
                context?.let { it1 -> ContextCompat.getColor(it1, R.color.release_sava_text_color) }
                    ?.let { it2 ->
                        txtAgain.setTextColor(it2)
                    }
                txtAgain.isEnabled = true
                countDown.text = ""
            } else {
                countDown.text = "${it}秒后可重新获取"
                context?.let { it1 -> ContextCompat.getColor(it1, R.color.text_gray) }?.let { it2 ->
                    txtAgain.setTextColor(
                        it2
                    )
                }
                txtAgain.isEnabled = false
            }
        })

//        num1.requestFocus()
        Handler().postDelayed(Runnable { showSoftInputFromWindow(num1) }, 1000)

//        showSoftInputFromWindow(num1)
//        SoftInputUtils.setEditFocusable(context, num1)
        num1.addTextChangedListener {
            if (it?.length!! > 0) {
                num2.isEnabled = true
                num2.requestFocus()
            }
        }

        num2.addTextChangedListener {
            if (it?.length!! > 0) {
                num3.isEnabled = true
                num3.requestFocus()
            } else {
                num1.requestFocus()
            }
        }

        num2.setOnKeyListener OnKeyListener@{ v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN && num2.text.isEmpty()) {
                num1.requestFocus()
                return@OnKeyListener true
            }
            false
        }

        num3.addTextChangedListener {
            if (it?.length!! > 0) {
                num4.isEnabled = true
                num4.requestFocus()
            } else {
                num2.requestFocus()
            }
        }

        num3.setOnKeyListener OnKeyListener@{ v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN && num3.text.isEmpty()) {
                num2.requestFocus()
                return@OnKeyListener true
            }
            false
        }

        num4.addTextChangedListener {
            if (it?.length!! > 0) {
                num5.isEnabled = true
                num5.requestFocus()
            } else {
                num3.requestFocus()
            }
        }

        num4.setOnKeyListener OnKeyListener@{ v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN && num4.text.isEmpty()) {
                num3.requestFocus()
                return@OnKeyListener true
            }
            false
        }

        num5.addTextChangedListener {
            if (it?.length!! > 0) {
                num6.isEnabled = true
                num6.requestFocus()
            } else {
                num4.requestFocus()
            }
        }

        num5.setOnKeyListener OnKeyListener@{ v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN && num5.text.isEmpty()) {
                txtError.text =""
                num4.requestFocus()
                return@OnKeyListener true
            }
            false
        }

        num6.addTextChangedListener {
            if (it?.length!! > 0) {
                viewModel.code =
                    num1.text.toString() + num2.text.toString() + num3.text.toString() + num4.text.toString() + num5.text.toString() + num6.text.toString()
                if (viewModel.code.length == 6) {
                    lifecycleScope.launch {
                        if (type == 0) {
                            viewModel.submitSimpleTransferOutOrder().let {
                                if (it.succeeded) {
                                    it as APIRst.Success
                                    if(it.data.msg.contains("成功")) {
                                        Toast.makeText(context, it.data.msg, Toast.LENGTH_LONG).show()
                                        listener?.onDisMiss()
                                        dismiss()
                                    }else{
                                        txtError.visibility = View.VISIBLE
                                        txtError.text = it.data.msg
                                    }
                                }
                            }
                        } else {
                            viewModel.submitSimpleTransferInOrder().let {
                                if (it.succeeded) {
                                    it as APIRst.Success
                                    if(it.data.msg.contains("成功")) {
                                        Toast.makeText(context, it.data.msg, Toast.LENGTH_LONG).show()
                                        listener?.onDisMiss()
                                        dismiss()
                                    }else{
                                        txtError.visibility = View.VISIBLE
                                        txtError.text = it.data.msg
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                num5.requestFocus()
            }
        }

        num6.setOnKeyListener OnKeyListener@{ v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN && num6.text.isEmpty()) {
                txtError.text =""
                num5.requestFocus()
                return@OnKeyListener true
            }
            false
        }



        btClose.setOnClickListener {
            dismiss()
        }

    }.root

    interface OnDisMissListener {
        fun onDisMiss()
    }

    fun showSoftInputFromWindow(editText: EditText) {
        editText.setFocusable(true)
        editText.setFocusableInTouchMode(true)
        editText.requestFocus()
        val inputManager: InputMethodManager = editText.getContext()
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.showSoftInput(editText, 0)
    }

}