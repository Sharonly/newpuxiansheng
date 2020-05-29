package com.puxiansheng.www.ui.release.fasttransfer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.BannerImage
import com.puxiansheng.www.R
import com.puxiansheng.www.common.urlBg
import com.puxiansheng.www.databinding.DialogSelectiveMenuItemBinding
import com.puxiansheng.www.databinding.RecyclerFastTransferBgItemBinding
import kotlinx.android.extensions.LayoutContainer

class FastTransferImgAdapter(
private val facilities: List<BannerImage>
) : RecyclerView.Adapter<FastTransferImgAdapter.ImgItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImgItemViewHolder = ImgItemViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_fast_transfer_bg_item,
            parent,
            false
        )
    )

    override fun getItemCount(): Int = facilities.size

    override fun onBindViewHolder(
        holder: ImgItemViewHolder,
        position: Int
    ) {
        holder.bind(facilities[position])
    }

    inner class ImgItemViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        val binding: RecyclerFastTransferBgItemBinding =
            RecyclerFastTransferBgItemBinding.bind(containerView)

        fun bind(menuItem: BannerImage) {
//            binding.img. = menuItem.imageHeight
            binding.img.urlBg(menuItem.imageUrl)
        }
    }
}