package com.puxiansheng.www.ui.home

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.puxiansheng.util.ext.toast
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import com.puxiansheng.www.databinding.FragmentCardFastOutBinding
import com.puxiansheng.www.ui.release.fasttransfer.SimpleOrderViewModel
import kotlinx.coroutines.launch

class FastOutFragment : Fragment() {
    private lateinit var viewModel: SimpleOrderViewModel
    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[SimpleOrderViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentCardFastOutBinding.inflate(inflater).apply {
        lifecycleOwner = viewLifecycleOwner

        lifecycleScope.launch {
            viewModel.requestFastNum().let {
                shopNum.text = it?.transfer_count?.count
            }
        }

        phoneNum.addTextChangedListener {
            viewModel.phone = it.toString()
        }
        btOut.setOnClickListener {
            if (viewModel.phone.isEmpty()){
                phoneNum.requestFocus()
            }else{
                lifecycleScope.launch {
                    viewModel.submitSimpleTransferOutOrder().let {
                        if (it.succeeded) {
                            it as APIRst.Success
                            it.data.msg.toast(requireActivity())
                        }
                    }
                }
            }
        }


    }.root
}