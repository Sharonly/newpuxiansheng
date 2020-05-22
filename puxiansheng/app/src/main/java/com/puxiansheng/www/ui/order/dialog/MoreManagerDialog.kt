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
import androidx.lifecycle.lifecycleScope
import com.puxiansheng.logic.api.API
import com.puxiansheng.util.ext.SharedPreferencesUtil.Companion.get
import com.puxiansheng.www.R
import com.puxiansheng.www.databinding.DialogMoreManagerBinding
import com.puxiansheng.www.ui.login.LoginActivity
import com.puxiansheng.www.ui.order.TransferOutOrderDetailViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class MoreManagerDialog(
    private val shopID: String,
    private val type: Int,
    private val isfavorite: Int
) : DialogFragment() {
    private lateinit var binding: DialogMoreManagerBinding
    private lateinit var viewModel: TransferOutOrderDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[TransferOutOrderDetailViewModel::class.java]
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
    ): View? = DialogMoreManagerBinding.inflate(inflater).apply {
        binding = this
        binding.lifecycleOwner = viewLifecycleOwner

        if (isfavorite == 1) {
            btFavor.text = "取消收藏"
        } else {
            btFavor.text = "收藏"
        }

        btFavor.setOnClickListener {
            if (get(API.LOGIN_USER_TOKEN, "").toString().isNotEmpty()) {
                lifecycleScope.launch {
                    viewModel.favorite(
                        objectID = shopID,
                        type = type
                    )?.let { result ->
                        if (result.data?.result == 0) {
                            btFavor.text = "收藏"
                        } else {
                            btFavor.text = "取消收藏"
                            dismiss()
                        }
                        Toast.makeText(requireContext(), result.msg, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            } else {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
            }
        }

        btCancel.setOnClickListener { dismiss() }

    }.root

}