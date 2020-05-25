package com.puxiansheng.www.ui.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.ArticleBean
import com.puxiansheng.www.R
import com.puxiansheng.www.databinding.ArticleTitleItmBinding
import com.puxiansheng.www.ui.info.InfoDetailActivity
import com.puxiansheng.www.ui.order.TransferOutOrderDetailActivity
import kotlinx.android.extensions.LayoutContainer

class ArticleTitleAdapter(
    private var list: List<ArticleBean>, private var context: Context
    ) : RecyclerView.Adapter<ArticleTitleAdapter.OrderItemViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ArticleTitleAdapter.OrderItemViewHolder = OrderItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.article_title_itm,
                parent,
                false
            )
        )

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(
            holder: ArticleTitleAdapter.OrderItemViewHolder,
            position: Int
        ) {
            holder.bind(list[position])
        }

    fun setMenuData(listData: List<ArticleBean>) {
        list = listData.toMutableList()
        notifyDataSetChanged()
    }

    inner class OrderItemViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        val binding: ArticleTitleItmBinding = ArticleTitleItmBinding.bind(containerView)

        fun bind(menuItem: ArticleBean) {
            binding.infoTitle.text = menuItem.title
            binding.root.setOnClickListener {
                val intent = Intent(context, InfoDetailActivity::class.java)
//                intent.putExtra("url", it?.)
                context.startActivity(intent)
            }
        }
    }
}