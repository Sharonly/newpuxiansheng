package com.puxiansheng.www.ui.mine.suggest

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.puxiansheng.www.databinding.DialogQuestionCallbackBinding

class QuestionDialog : DialogFragment() {
    private lateinit var binding: DialogQuestionCallbackBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = true
    }

    override fun onStart() {
        super.onStart()
        dialog?.let {
            it.window?.let { window ->
                window.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                window.setGravity(Gravity.BOTTOM)
                window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DialogQuestionCallbackBinding.inflate(inflater).apply {
        binding = this
        binding.lifecycleOwner = viewLifecycleOwner
        binding.btMyCallback.setOnClickListener {
            val intent = Intent(requireActivity(), UserCallBackActivity::class.java)
            startActivity(intent)
            dismiss()
        }


        binding.btCancel.setOnClickListener { dismiss() }

    }.root


}