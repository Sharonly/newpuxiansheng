package com.puxiansheng.www.ui.order.dialog

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.puxiansheng.www.databinding.DialogSelectCanEmptyBinding
import com.puxiansheng.www.ui.release.InsertOrUpdateTransferOutOrderViewModel

class SelectCanEmptyDialog(private val onSelectCanEmpty: (stateMenuItem: String?) -> Unit) :
    DialogFragment() {
    private lateinit var binding: DialogSelectCanEmptyBinding
    private lateinit var viewModel: InsertOrUpdateTransferOutOrderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[InsertOrUpdateTransferOutOrderViewModel::class.java]
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
    ): View? = DialogSelectCanEmptyBinding.inflate(inflater).apply {
        binding = this
        binding.lifecycleOwner = viewLifecycleOwner
        btYes.setOnClickListener {
            viewModel.isCanEmpty.postValue("是")
            onSelectCanEmpty("是")
            dismiss()
        }
        btNo.setOnClickListener {
            viewModel.isCanEmpty.postValue("否")
            onSelectCanEmpty("否")
            dismiss()
        }


    }.root
}