package com.puxiansheng.www.ui.mine.favor

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.InfoItem
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.www.R
import com.puxiansheng.www.common.url
import com.puxiansheng.www.databinding.FragmentFavorInfoItemBinding
import com.puxiansheng.www.databinding.FragmentInfoItemBinding
import com.puxiansheng.www.databinding.FragmentOrderListEmptyBinding
import com.puxiansheng.www.ui.info.InfoDetailActivity
import kotlinx.android.extensions.LayoutContainer

class FavorInfoAdapter(
    val context: Context, var type: Int,
    private val onItemSelect: ((order: InfoItem?) -> Unit)? = null,
    private val onDelete: ((infoItem: InfoItem?) -> Unit)? = null
) : PagedListAdapter<InfoItem, FavorInfoAdapter.BaseViewHolder>(InfoItem.DIFF) {
    private var dataList: PagedList<InfoItem>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        LayoutInflater.from(parent.context).inflate(viewType, parent, false).let {
            when (viewType) {
                R.layout.fragment_info_item -> InfoHistoryViewHolder(it)
                R.layout.fragment_favor_info_item -> InfoFavorViewHolder(it)
                R.layout.fragment_order_list_empty -> EmptyOrderViewHolder(it)
                else -> EmptyOrderViewHolder(it)
            }
        }


    override fun getItemViewType(position: Int): Int {
        if (type == Order.Type.EMPTY.value() && position == itemCount - 1)
            return R.layout.fragment_order_list_empty
        return when (type) {
            InfoItem.Type.ARTICLE_FAVOR.value() -> R.layout.fragment_favor_info_item
            InfoItem.Type.ARTICLE_HISTORY.value() -> R.layout.fragment_info_item

            else -> R.layout.fragment_order_list_empty
        }
    }


    override fun getItemCount(): Int {
        if (type == Order.Type.EMPTY.value()) return 1 + super.getItemCount()
        return super.getItemCount()
    }


    override fun submitList(pagedList: PagedList<InfoItem>?) {
        dataList = pagedList!!
        super.submitList(pagedList)
        notifyDataSetChanged()
    }


    fun getDataCount(): Int {
        return dataList?.size ?: 0
    }




    inner class InfoFavorViewHolder(override val containerView: View) :
        BaseViewHolder(containerView) {

        private val binding = FragmentFavorInfoItemBinding.bind(containerView)

        @SuppressLint("SetTextI18n")
        override fun bind(infoItem: InfoItem?) {
            binding.title.text = infoItem?.title
            binding.data.text = infoItem?.date
            binding.pageViews.text = infoItem?.pageViews.toString()
            binding.icon.url(infoItem?.image ?: "")
            binding.itemLayout.setOnClickListener {
                val intent = Intent(context, InfoDetailActivity::class.java)
                intent.putExtra("url", infoItem?.jump_param)
                context.startActivity(intent)
//                onItemSelect?.let { select -> select(infoItem) }
            }

            binding.itemDelete.setOnClickListener {
                onDelete?.let { select -> select(infoItem) }
            }
        }
    }

    inner class InfoHistoryViewHolder(override val containerView: View) :
        BaseViewHolder(containerView) {

        private val binding = FragmentInfoItemBinding.bind(containerView)

        @SuppressLint("SetTextI18n")
        override fun bind(infoItem: InfoItem?) {
            binding.title.text = infoItem?.title
            binding.data.text = infoItem?.date
            binding.pageViews.text = infoItem?.pageViews.toString()
            binding.icon.url(infoItem?.image ?: "")
            binding.root.setOnClickListener {
                val intent = Intent(context, InfoDetailActivity::class.java)
                intent.putExtra("url", infoItem?.jump_param)
                context.startActivity(intent)
            }
        }
    }


    inner class EmptyOrderViewHolder(override val containerView: View) :
        BaseViewHolder(containerView) {
        override fun bind(item: InfoItem?) {
        }

        private val binding = FragmentOrderListEmptyBinding.bind(containerView)

    }

    abstract inner class BaseViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        abstract fun bind(item: InfoItem?)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (type != Order.Type.EMPTY.value())
            holder.bind(getItem(position))
    }


}