package com.puxiansheng.www.ui.order.dialog

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.puxiansheng.logic.bean.BannerImage
import com.puxiansheng.www.common.ImageSwitcher
import com.puxiansheng.www.common.ShopImageSwitcher
import com.puxiansheng.www.databinding.DialogShopImagesBinding
import com.puxiansheng.www.ui.main.dialog.UpgradeDialog
import kotlinx.coroutines.channels.ticker

class ShopImageDialog() : DialogFragment() {
    var mContext: Activity? = null
    var baners: List<BannerImage> = arrayListOf()

    companion object {
        private var instance: ShopImageDialog? = null

        fun getInstance(): ShopImageDialog {
            if (instance == null) {
                synchronized(ShopImageDialog::class.java) {
                    if (instance == null) {
                        instance =
                            ShopImageDialog()
                    }
                }
            }
            return instance!!
        }
    }



    fun setData(
        context: Activity,
        banersList: List<BannerImage>
    ) {
        mContext = context
        baners = banersList
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        dialog?.let {
            it.window?.let { window ->
                window.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
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
    ): View? = DialogShopImagesBinding.inflate(inflater).apply {
        lifecycleOwner = viewLifecycleOwner

        imgSwitcher.setImages(baners)
        imgIndex.text = imgSwitcher.getCurrentPos().toString() + "/" + baners.size
        imgSwitcher.listener = object : ShopImageSwitcher.OnPageChange {
            override fun onScrolled(index: Int) {
                imgIndex.text =
                    imgSwitcher.getCurrentPos().toString() + "/" + baners.size
            }
        }
        imgSwitcher.onImageClick { image: BannerImage ->
            dismiss()
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


}