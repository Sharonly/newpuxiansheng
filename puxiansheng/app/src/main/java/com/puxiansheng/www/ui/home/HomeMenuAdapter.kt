package com.puxiansheng.www.ui.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.BannerImage
import com.puxiansheng.logic.bean.MenuItem
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.logic.bean.RecommendOrderShop
import com.puxiansheng.www.R
import com.puxiansheng.www.common.drawableTop
import com.puxiansheng.www.common.url
import com.puxiansheng.www.common.urlIcon
import com.puxiansheng.www.databinding.DialogSelectiveMenuItemBinding
import com.puxiansheng.www.databinding.HomeMenuItemBinding
import com.puxiansheng.www.databinding.RecommendOrderItemBinding
import com.puxiansheng.www.ui.business.BusinessListActivity
import com.puxiansheng.www.ui.main.HomeActivity
import com.puxiansheng.www.ui.mine.setting.AboutUsActivity
import com.puxiansheng.www.ui.order.NewTransferInOrdersActivity
import com.puxiansheng.www.ui.order.NewTransferOutOrdersActivity
import com.puxiansheng.www.ui.order.NewTransferSuccessOrdersActivity
import com.puxiansheng.www.ui.release.fasttransfer.FastTransferInActivity
import com.puxiansheng.www.ui.release.fasttransfer.FastTransferOutActivity
import kotlinx.android.extensions.LayoutContainer

class HomeMenuAdapter( private var context: Context,
    private var list: List<BannerImage>
    ) : RecyclerView.Adapter<HomeMenuAdapter.OrderItemViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMenuAdapter.OrderItemViewHolder = OrderItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.home_menu_item,
                parent,
                false
            )
        )

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(
            holder: HomeMenuAdapter.OrderItemViewHolder,
            position: Int
        ) {
            holder.bind(list[position])
        }

    fun setMenuData(listData: List<BannerImage>) {
        list = listData.toMutableList()
        notifyDataSetChanged()
    }

    inner class OrderItemViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        val binding: HomeMenuItemBinding =
            HomeMenuItemBinding.bind(containerView)

        fun bind(menuItem: BannerImage) {
            binding.menuItem.text = menuItem.title
            binding.icon.urlIcon(menuItem.imageUrl)

            binding.root.setOnClickListener {
                when (menuItem.api_jump_type) {
                    1 -> {
                        when (menuItem.api_jump_view) {
                            "transfer_list" -> {
                                val intent =
                                    Intent(context, NewTransferOutOrdersActivity::class.java)
                                intent.putExtra("title", "*")
                                context.startActivity(intent)
                            }
                            "find_list" -> {
                                val intent =
                                    Intent(context, NewTransferInOrdersActivity::class.java)
                                intent.putExtra("title", "*")
                                context.startActivity(intent)
                            }

                            "activity_list" -> {//文章列表
                                val intent = Intent(context, HomeActivity::class.java)
                                intent.putExtra("name", "5")
                                context.startActivity(intent)
                            }
                            "join_list" -> {
                                val intent = Intent(context, BusinessListActivity::class.java)
                                intent.putExtra("title", "*")
                                context.startActivity(intent)
                            }

                            "quick_transfer" -> {
                                val intent = Intent(context, FastTransferOutActivity::class.java)
                                context.startActivity(intent)
                            }

                            "quick_find" -> {
                                val intent = Intent(context, FastTransferInActivity::class.java)
                                context.startActivity(intent)
                            }
                            "about_us" -> {
                                val intent = Intent(context, AboutUsActivity::class.java)
                                context.startActivity(intent)
                            }

                            "shop_success" -> {//成功案例
                                val intent =
                                    Intent(context, NewTransferSuccessOrdersActivity::class.java)
                                context.startActivity(intent)
                            }
                        }
                    }
                }
            }
        }
    }
}