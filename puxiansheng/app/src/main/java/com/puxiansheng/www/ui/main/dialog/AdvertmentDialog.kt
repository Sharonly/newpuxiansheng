package com.puxiansheng.www.ui.main.dialog

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.puxiansheng.logic.bean.BannerImage
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.www.common.ImageSwitcher
import com.puxiansheng.www.common.JumpUtils
import com.puxiansheng.www.databinding.DialogAdvertBinding
import com.puxiansheng.www.ui.main.MainViewModel
import com.puxiansheng.www.ui.mine.relase.DeleteOrderDialog
import kotlinx.android.synthetic.main.fragment_transfer_out_order_detail.*
import kotlinx.android.synthetic.main.recycler_fast_transfer_bg_item.*
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.launch

class AdvertmentDialog(var context: Activity, var baners:List<BannerImage>
) : DialogFragment() {
    private lateinit var appModel: MainViewModel
    var listener: OnDissListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

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
    ): View? = DialogAdvertBinding.inflate(inflater).apply {
        lifecycleOwner = viewLifecycleOwner

        imgSwitcher.setImages(baners,false)
        imgIndex.text = imgSwitcher.getCurrentPos().toString() + "/" + baners.size
        imgSwitcher.listener = object : ImageSwitcher.OnPageChange {
            override fun onScrolled(index: Int) {
                imgIndex.text =
                    imgSwitcher.getCurrentPos().toString() + "/" + baners.size
            }
        }
        imgSwitcher.onImageClick { image: BannerImage ->
            Log.d("---jump--"," image= "+image.jump_param+"  ="+image.jump_type+"   ="+image.jump_view)
            JumpUtils.pictureIntent(context,image) }
        btClose.setOnClickListener {
            dismiss()
            listener?.onDiss()
        }

        imgSwitcher.loop(
            ticker = ticker(
                delayMillis = 1000 * 3,
                initialDelayMillis = 1000 * 3,
                context = lifecycleScope.coroutineContext
            ),
            coroutineScope = lifecycleScope
        )

//        lifecycleScope.launch {
//            appModel.submitAdvertImages("api_index_pop_up_ads").let {
//
//            }
//        }
    }.root

    interface OnDissListener {
        fun onDiss()
    }

}