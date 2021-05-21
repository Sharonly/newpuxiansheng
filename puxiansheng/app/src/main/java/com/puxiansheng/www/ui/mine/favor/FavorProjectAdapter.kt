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
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.logic.bean.http.ProjectDetailObject
import com.puxiansheng.www.R
import com.puxiansheng.www.common.url
import com.puxiansheng.www.databinding.FragmentOrderListEmptyBinding
import com.puxiansheng.www.databinding.FragmentProjectItemBinding
import com.puxiansheng.www.tools.Utils
import com.puxiansheng.www.ui.project.ProjectDetailActivity
import kotlinx.android.extensions.LayoutContainer

class FavorProjectAdapter(
    val context: Context, var type: Int,
    private val onItemSelect: ((order: ProjectDetailObject?) -> Unit)? = null,
    private val onDelete: ((infoItem: ProjectDetailObject?) -> Unit)? = null
) : PagedListAdapter<ProjectDetailObject, FavorProjectAdapter.BaseViewHolder>(ProjectDetailObject.DIFF) {
    private var dataList: PagedList<ProjectDetailObject>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        LayoutInflater.from(parent.context).inflate(viewType, parent, false).let {
            when (viewType) {
                R.layout.fragment_info_item -> ProjectHistoryViewHolder(it)
                R.layout.fragment_favor_info_item -> ProjectFavorViewHolder(it)
                R.layout.fragment_order_list_empty -> EmptyOrderViewHolder(it)
                else -> EmptyOrderViewHolder(it)
            }
        }


    override fun getItemViewType(position: Int): Int {
        if (type == Order.Type.EMPTY.value() && position == itemCount - 1)
            return R.layout.fragment_order_list_empty
        return when (type) {
            ProjectDetailObject.Type.PROJECT_FAVOR.value() -> R.layout.item_project_list
            else -> R.layout.fragment_order_list_empty
        }
    }


    override fun getItemCount(): Int {
        if (type == Order.Type.EMPTY.value()) return 1 + super.getItemCount()
        return super.getItemCount()
    }


    override fun submitList(pagedList: PagedList<ProjectDetailObject>?) {
        dataList = pagedList!!
        super.submitList(pagedList)
        notifyDataSetChanged()
    }


    fun getDataCount(): Int {
        return dataList?.size ?: 0
    }




    inner class ProjectFavorViewHolder(override val containerView: View) :
        BaseViewHolder(containerView) {

        private val binding = FragmentProjectItemBinding.bind(containerView)

        @SuppressLint("SetTextI18n")
        override fun bind(shopInfo: ProjectDetailObject?) {
            println("item---》${shopInfo}")
            shopInfo?.theme_img?.let { binding.shopIcon.url(it) }
            binding.title.text = shopInfo?.title
            binding.industryLable.text = shopInfo?.category_str
            binding.area.text = shopInfo?.shop_address
            binding.date.text = shopInfo?.update_time
            binding.root.setOnClickListener {
                if (Utils.isFastClick()) {
                    val intent = Intent(context, ProjectDetailActivity::class.java)
                    intent.putExtra("shopId", shopInfo?.shopID)
                    context.startActivity(intent)
                }
            }

            binding.itemDelete.setOnClickListener {
                onDelete?.let { select -> select(shopInfo) }
            }
        }
    }

    inner class ProjectHistoryViewHolder(override val containerView: View) :
        BaseViewHolder(containerView) {

        private val binding = FragmentProjectItemBinding.bind(containerView)

        @SuppressLint("SetTextI18n")
        override fun bind(shopInfo: ProjectDetailObject?) {
            println("item---》${shopInfo}")
            shopInfo?.theme_img?.let { binding.shopIcon.url(it) }
            binding.title.text = shopInfo?.title
            binding.industryLable.text = shopInfo?.category_str
            binding.area.text = shopInfo?.shop_address
            binding.date.text = shopInfo?.update_time
            binding.root.setOnClickListener {
                if (Utils.isFastClick()) {
                    val intent = Intent(context, ProjectDetailActivity::class.java)
                    intent.putExtra("shopId", shopInfo?.shopID)
                    context.startActivity(intent)
                }
            }
        }
    }


    inner class EmptyOrderViewHolder(override val containerView: View) :
        BaseViewHolder(containerView) {
        override fun bind(item: ProjectDetailObject?) {
        }

        private val binding = FragmentOrderListEmptyBinding.bind(containerView)

    }

    abstract inner class BaseViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        abstract fun bind(item: ProjectDetailObject?)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (type != Order.Type.EMPTY.value())
            holder.bind(getItem(position))
    }


}