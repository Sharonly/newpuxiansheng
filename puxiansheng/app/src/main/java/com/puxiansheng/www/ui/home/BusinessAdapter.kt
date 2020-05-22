package com.puxiansheng.www.ui.home

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.Business
import com.puxiansheng.logic.bean.BusinessBean
import com.puxiansheng.www.common.url
import com.puxiansheng.www.databinding.FragmentInvestBusinessListItemBinding
import kotlinx.android.extensions.LayoutContainer

class BusinessAdapter(
    private val onItemSelect: ((business: Business?) -> Unit)? = null
) {
    inner class BusinessAdapterViewHolder(
        override val containerView: View
    ) : BusinessAdapter.BusinessViewHolder(containerView) {
        private val binding = FragmentInvestBusinessListItemBinding.bind(containerView)

        @SuppressLint("SetTextI18n")
        override fun bind(item: Business?) {
            item?.name?.let {
                binding.title.text = it
            }

            item?.thumb_img?.let { url ->
                binding.shopIcon.url(url)
            }

            item?.trades?.let {
                binding.investContent.text = it
            }

            item?.investment?.let {
                binding.investMoney.text = it
            }

            binding.btConsult.setOnClickListener {

            }

            binding.root.setOnClickListener {
                onItemSelect?.let { select -> select(item) }
            }
        }
    }

    abstract inner class BusinessViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        abstract fun bind(item: Business?)
    }

}