package com.puxiansheng.www.ui.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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
import com.puxiansheng.www.tools.Utils
import com.puxiansheng.www.ui.business.BusinessListActivity
import com.puxiansheng.www.ui.business.BusinessListAdapter
import com.puxiansheng.www.ui.main.HomeActivity
import com.puxiansheng.www.ui.main.MainActivity
import com.puxiansheng.www.ui.mine.setting.AboutUsActivity
import com.puxiansheng.www.ui.order.NewSuccessOrdersActivity
import com.puxiansheng.www.ui.order.NewTransferInOrdersActivity
import com.puxiansheng.www.ui.order.NewTransferOutOrdersActivity
import com.puxiansheng.www.ui.release.fasttransfer.FastTransferInActivity
import com.puxiansheng.www.ui.release.fasttransfer.FastTransferOutActivity
import kotlinx.android.extensions.LayoutContainer

class HomeMenuAdapter(var context: Context,
     var list: List<BannerImage>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.home_new_menu_item, parent, false)
        return OrderItemViewHolder(view)
    }

   override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is OrderItemViewHolder) {
            var item = list[position]
            holder.menuItem.text = item.title
            holder.icon.urlIcon(item.imageUrl)
            holder.root.setOnClickListener{
                when (item.api_jump_type) {
                    1 -> {
                        when (item.api_jump_view) {
                            "transfer_list" -> {
                                if (Utils.isFastClick()) {
                                    val intent =
                                        Intent(context, NewTransferOutOrdersActivity::class.java)
                                    intent.putExtra("title", "*")
                                    context.startActivity(intent)
                                }
                            }
                            "find_list" -> {
                                if (Utils.isFastClick()) {
                                    val intent =
                                        Intent(context, NewTransferInOrdersActivity::class.java)
                                    intent.putExtra("title", "*")
                                    context.startActivity(intent)
                                }
                            }

                            "activity_list" -> {//文章列表
                                if (Utils.isFastClick()) {
                                    val intent = Intent(context, MainActivity::class.java)
                                    intent.putExtra("name", "5")
                                    context.startActivity(intent)
                                }
                            }
                            "join_list" -> {
                                if (Utils.isFastClick()) {
                                    val intent = Intent(context, BusinessListActivity::class.java)
                                    intent.putExtra("title", "*")
                                    context.startActivity(intent)
                                }
                            }

                            "quick_transfer" -> {
                                if (Utils.isFastClick()) {
                                    val intent =
                                        Intent(context, FastTransferOutActivity::class.java)
                                    context.startActivity(intent)
                                }
                            }

                            "quick_find" -> {
                                if (Utils.isFastClick()) {
                                    val intent = Intent(context, FastTransferInActivity::class.java)
                                    context.startActivity(intent)
                                }
                            }
                            "about_us" -> {
                                if (Utils.isFastClick()) {
                                    val intent = Intent(context, AboutUsActivity::class.java)
                                    context.startActivity(intent)
                                }
                            }

                            "shop_success" -> {//成功案例
                                if (Utils.isFastClick()) {
                                val intent =
                                    Intent(context, NewSuccessOrdersActivity::class.java)
                                context.startActivity(intent)
                                    }
                            }
                        }
                    }
                }
            }
        }
    }



    fun setMenuData(listData: List<BannerImage>) {
        list = listData.toMutableList()
        notifyDataSetChanged()
    }



    class OrderItemViewHolder(var containerView: View) : RecyclerView.ViewHolder(containerView) {
        val root: View = containerView.findViewById(R.id.root)
        val icon: ImageView = containerView.findViewById(R.id.icon)
        val menuItem: TextView = containerView.findViewById(R.id.menu_item)
    }
}