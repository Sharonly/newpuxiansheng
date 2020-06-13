package com.puxiansheng.www.ui.business

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.BusinessBean
import com.puxiansheng.www.R
import com.puxiansheng.www.common.url
import com.puxiansheng.www.databinding.FragmentInvestBusinessListItemBinding
import kotlinx.android.extensions.LayoutContainer

class BusinessAdapter(
    private val onItemSelect: ((business: BusinessBean?) -> Unit)? = null,
    private val onItemCall: ((business: BusinessBean?) -> Unit)? = null
) : PagedListAdapter<BusinessBean, BusinessAdapter.BusinessAdapterViewHolder>(BusinessBean.DIFF) {

    private var dataList: PagedList<BusinessBean>? = null

    inner class BusinessAdapterViewHolder(
        override val containerView: View
    ) : BusinessViewHolder(containerView) {
        private val binding = FragmentInvestBusinessListItemBinding.bind(containerView)

        @SuppressLint("SetTextI18n")
        override fun bind(item: BusinessBean?) {
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

            val spr = StringBuilder()
            item?.keywords?.let {
                    it.forEach {
                        spr.append(it)
                        spr.append(" ")
                    }
                binding.investLabel.text = spr.toString()
            }

            binding.btConsult.setOnClickListener {
                onItemCall?.let { select -> select(item) }
            }

            binding.root.setOnClickListener {
                onItemSelect?.let { select -> select(item) }
            }
        }
    }


    override fun submitList(pagedList: PagedList<BusinessBean>?) {
        dataList = pagedList!!
        super.submitList(pagedList)
        notifyDataSetChanged()
    }


    abstract inner class BusinessViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        abstract fun bind(item: BusinessBean?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BusinessAdapterViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_invest_business_list_item, parent, false)
    )

    override fun onBindViewHolder(holder: BusinessAdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}