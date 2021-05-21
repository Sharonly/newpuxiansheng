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
import com.puxiansheng.www.databinding.DialogSelectViewCountBinding
import com.puxiansheng.www.ui.project.ProjectListViewModel
import com.puxiansheng.www.ui.release.InsertOrUpdateTransferOutOrderViewModel

class SelectViewCountDialog(private val onSelectViewCount: (stateMenuItem: String?) -> Unit) :
    DialogFragment() {
    private lateinit var binding: DialogSelectViewCountBinding
    private lateinit var viewModel: ProjectListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[ProjectListViewModel::class.java]
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
    ): View? = DialogSelectViewCountBinding.inflate(inflater).apply {
        binding = this
        binding.lifecycleOwner = viewLifecycleOwner
        btYes.setOnClickListener {
            viewModel.mViewCount.postValue("从高到低")
            onSelectViewCount("从高到低")
            dismiss()
        }
        btNo.setOnClickListener {
            viewModel.mViewCount.postValue("从低到高")
            onSelectViewCount("从低到高")
            dismiss()
        }


    }.root
}