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
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.puxiansheng.logic.bean.http.OrderDetailObject
import com.puxiansheng.util.Regular
import com.puxiansheng.util.ext.toast
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import com.puxiansheng.www.databinding.FragmentCardFastOutBinding
import com.puxiansheng.www.ui.home.dialog.FastReleaseCodeDialog
import com.puxiansheng.www.ui.mine.relase.DeleteOrderDialog
import com.puxiansheng.www.ui.release.dialog.ReleaseDialog
import com.puxiansheng.www.ui.release.fasttransfer.SimpleOrderViewModel
import kotlinx.android.synthetic.main.fragment_card_fast_out.*
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

        phoneNum.addTextChangedListener {
            viewModel.phone = it.toString()
        }
        btOut.setOnClickListener {
            if (viewModel.phone.isEmpty()) {
                phoneNum.requestFocus()
                Toast.makeText(requireContext(), "请先输入您的联系方式", Toast.LENGTH_SHORT).show()
            } else {
                if (Regular.isPhoneNumber(viewModel.phone)){
                    var fastReleaseDialog = FastReleaseCodeDialog(0, viewModel.phone)
                    fastReleaseDialog.show(
                        childFragmentManager,
                        FastReleaseCodeDialog::class.java.name
                    )
                    fastReleaseDialog.listener = object : FastReleaseCodeDialog.OnDisMissListener {
                        override fun onDisMiss() {
                            phoneNum.setText("")
                        }
                    }
                }else{
                    Toast.makeText(requireContext(), "请先输入正确的联系电话", Toast.LENGTH_SHORT).show()
                }
            }
        }

        lifecycleScope.launch {
            viewModel.requestFastNum().let {
                shopNum.text = it?.transfer_count?.count
            }
        }

    }.root


    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            viewModel.requestFastNum().let {
                shop_num.text = it?.transfer_count?.count
            }
        }
    }
}