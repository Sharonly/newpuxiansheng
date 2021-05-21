package com.puxiansheng.www.ui.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.BannerImage
import com.puxiansheng.logic.bean.MenuItem
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.logic.bean.RecommendOrderShop
import com.puxiansheng.www.R
import com.puxiansheng.www.common.JumpUtils
import com.puxiansheng.www.common.drawableTop
import com.puxiansheng.www.common.url
import com.puxiansheng.www.common.urlIcon
import com.puxiansheng.www.databinding.DialogSelectiveMenuItemBinding
import com.puxiansheng.www.databinding.HomeMenuItemBinding
import com.puxiansheng.www.databinding.RecommendOrderItemBinding
import com.puxiansheng.www.tools.SpUtils
import com.puxiansheng.www.tools.UMengKeys
import com.puxiansheng.www.tools.Utils
import com.puxiansheng.www.ui.business.BusinessListActivity
import com.puxiansheng.www.ui.business.BusinessListAdapter
import com.puxiansheng.www.ui.info.NewInfoDetailActivity
import com.puxiansheng.www.ui.info.WebViewActivity
import com.puxiansheng.www.ui.login.LoginActivity
import com.puxiansheng.www.ui.main.HomeActivity
import com.puxiansheng.www.ui.main.MainActivity
import com.puxiansheng.www.ui.message.MessageDetailActivity
import com.puxiansheng.www.ui.mine.setting.AboutUsActivity
import com.puxiansheng.www.ui.mine.suggest.UserSuggestActivity
import com.puxiansheng.www.ui.order.*
import com.puxiansheng.www.ui.project.ProjectHomeListActivity
import com.puxiansheng.www.ui.release.InsertOrUpdateTransferInOrderActivity
import com.puxiansheng.www.ui.release.InsertOrUpdateTransferOutOrderActivity
import com.puxiansheng.www.ui.release.fasttransfer.FastTransferInActivity
import com.puxiansheng.www.ui.release.fasttransfer.FastTransferOutActivity
import com.umeng.analytics.MobclickAgent
import kotlinx.android.extensions.LayoutContainer

class HomeMenuAdapter(
    var context: Context,
    var list: List<BannerImage>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.home_menu_item, parent, false)
        return OrderItemViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is OrderItemViewHolder) {
            var item = list[position]
            holder.menuItem.text = item.title
            holder.icon.urlIcon(item.imageUrl)
            if(item.api_jump_view == "demand_list"){
                holder.lable.visibility = View.VISIBLE
            }else{
                holder.lable.visibility = View.GONE
            }
            holder.root.setOnClickListener {
                when (item.api_jump_type) {
                    1 -> {
                        when (item.api_jump_view) {
                            "index" -> {
                            }
                            "demand_list" ->{
                                if (Utils.isFastClick()) {
                                    val intent =
                                        Intent(context, ProjectHomeListActivity::class.java)
                                    context.startActivity(intent)
                                    MobclickAgent.onEvent(
                                        context,
                                        UMengKeys.PAGE_NAME,
                                        "ProjectHomeListActivity"
                                    )
                                }
                            }
                            "transfer_list" -> {
                                if (Utils.isFastClick()) {
                                    val intent =
                                        Intent(context, NewTransferOutOrdersActivity::class.java)
                                    intent.putExtra("title", "*")
                                    context.startActivity(intent)
                                    MobclickAgent.onEvent(
                                        context,
                                        UMengKeys.PAGE_NAME,
                                        "NewTransferOutOrdersActivity"
                                    )
                                }
                            }
                            "find_list" -> {
                                if (Utils.isFastClick()) {
                                    val intent =
                                        Intent(context, NewTransferInOrdersActivity::class.java)
                                    intent.putExtra("title", "*")
                                    context.startActivity(intent)
                                    MobclickAgent.onEvent(
                                        context,
                                        UMengKeys.PAGE_NAME,
                                        "NewTransferInOrdersActivity"
                                    )
                                }
                            }


                            "activity_list" -> {//文章列表
                                if (Utils.isFastClick()) {
                                    val intent = Intent(context, MainActivity::class.java)
                                    intent.putExtra("name", "5")
                                    context.startActivity(intent)
                                }
                            }
                            "user_center" -> {

                            }

                            "join_list" -> {
                                if (Utils.isFastClick()) {
                                    val intent = Intent(context, BusinessListActivity::class.java)
                                    intent.putExtra("title", "*")
                                    context.startActivity(intent)
                                    MobclickAgent.onEvent(
                                        context,
                                        UMengKeys.PAGE_NAME,
                                        "BusinessListActivity"
                                    )
                                }
                            }

                            "quick_transfer" -> {
                                if (Utils.isFastClick()) {
                                    val intent =
                                        Intent(context, FastTransferOutActivity::class.java)
                                    context.startActivity(intent)
                                    MobclickAgent.onEvent(
                                        context,
                                        UMengKeys.PAGE_NAME,
                                        "FastTransferOutActivity"
                                    )
                                }
                            }

                            "quick_find" -> {
                                if (Utils.isFastClick()) {
                                    val intent = Intent(context, FastTransferInActivity::class.java)
                                    context.startActivity(intent)
                                    MobclickAgent.onEvent(
                                        context,
                                        UMengKeys.PAGE_NAME,
                                        "FastTransferInActivity"
                                    )
                                }
                            }
                            "about_us" -> {
                                if (Utils.isFastClick()) {
                                    val intent = Intent(context, AboutUsActivity::class.java)
                                    intent.putExtra("url", item.jump_param)
                                    context.startActivity(intent)
                                    MobclickAgent.onEvent(
                                        context,
                                        UMengKeys.PAGE_NAME,
                                        "AboutUsActivity"
                                    )
                                }
                            }

                            "success_video" ->{
                                if (Utils.isFastClick()) {
                                    val intent =
                                        Intent(context, NewSuccessOrdersActivity::class.java)
                                    intent.putExtra("type", 2)
                                    context.startActivity(intent)
                                    MobclickAgent.onEvent(
                                        context,
                                        UMengKeys.PAGE_NAME,
                                        "NewTransferSuccessOrdersActivity"
                                    )
                                }
                            }

                            "shop_success" -> {//成功案例
                                if (Utils.isFastClick()) {
                                    val intent =
                                        Intent(context, NewSuccessOrdersActivity::class.java)
                                    intent.putExtra("type", 1)
                                    context.startActivity(intent)
                                    MobclickAgent.onEvent(
                                        context,
                                        UMengKeys.PAGE_NAME,
                                        "NewTransferSuccessOrdersActivity"
                                    )
                                }
                            }
                            "article_details" ->{

                            }
                            "transfer_shop" ->{
                                if (SpUtils.get(API.LOGIN_USER_TOKEN, "").toString().isNotEmpty()) {
                                    val intent =
                                        Intent(context, InsertOrUpdateTransferOutOrderActivity::class.java)
                                    intent.putExtra("shopID", "0")
                                    context.startActivity(intent)
                                    MobclickAgent.onEvent(context, UMengKeys.PAGE_NAME,"InsertOrUpdateTransferOutOrderActivity")
                                    MobclickAgent.onEvent(context, UMengKeys.LOGIN_USER_ID, SpUtils.get(
                                        API.LOGIN_USER_ID,
                                        0
                                    ).toString())
                                } else {
                                    val intent = Intent(context, LoginActivity::class.java)
                                    context.startActivity(intent)
                                }
                            }
                            "find_shop" ->{
                                if (SpUtils.get(API.LOGIN_USER_TOKEN, "").toString().isNotEmpty()) {
                                    val intent =
                                        Intent(context, InsertOrUpdateTransferInOrderActivity::class.java)
                                    intent.putExtra("shopID", "0")
                                    context.startActivity(intent)
                                    MobclickAgent.onEvent(context, UMengKeys.PAGE_NAME,"InsertOrUpdateTransferInOrderActivity")
                                    MobclickAgent.onEvent(context, UMengKeys.LOGIN_USER_ID, SpUtils.get(
                                        API.LOGIN_USER_ID,
                                        0
                                    ).toString())
                                } else {
                                    val intent = Intent(context, LoginActivity::class.java)
                                    context.startActivity(intent)
                                }
                            }

                            "article_cate" -> {
                                val i=Intent(context,MainActivity::class.java)
                                i.putExtra("index",1)
                                context.startActivity(i)
                            }
                            "submit_complaint" -> {
                                if (Utils.isFastClick()) {
                                    val intent =
                                        Intent(context, UserSuggestActivity::class.java)
                                    context.startActivity(intent)
                                    MobclickAgent.onEvent(
                                        context,
                                        UMengKeys.PAGE_NAME,
                                        "UserSuggestActivity"
                                    )
                                }
                            }
                        }
                    }
                    2 -> {//打开链接
//                val intent = Intent(Intent.ACTION_VIEW)
//                intent.data = Uri.parse(image.jump_param)
//                context.startActivity(intent)
                        if (Utils.isFastClick()) {
                            //TODO  2020/6/8
                            val intent = Intent(context, WebViewActivity::class.java)
                            intent.putExtra("url", item.api_jump_param)
                            context.startActivity(intent)
                        }
                    }

                    3 -> {//找店详情
                        if (Utils.isFastClick()) {
                            val intent = Intent(context, TransferInOrderDetailActivity::class.java)
                            intent.putExtra("shopID", item.api_jump_param)
                            context.startActivity(intent)
                            MobclickAgent.onEvent(
                                context,
                                UMengKeys.PAGE_NAME,
                                "TransferInOrderDetailActivity"
                            )
                        }
                    }

                    4 -> {//转铺详情
                        if (Utils.isFastClick()) {
                            val intent = Intent(context, TransferOutOrderDetailActivity::class.java)
                            intent.putExtra("shopID", item.api_jump_param)
                            context.startActivity(intent)
                            MobclickAgent.onEvent(
                                context,
                                UMengKeys.PAGE_NAME,
                                "TransferOutOrderDetailActivity"
                            )
                        }
                    }


                    6 -> {
                        if (Utils.isFastClick()) {
                            val intent = Intent(
                                context,
                                InsertOrUpdateTransferOutOrderActivity::class.java
                            )
                            intent.putExtra("shopID", item.api_jump_param)
                            context.startActivity(intent)
                        }
                    }
                    7 -> {
                        if (Utils.isFastClick()) {
                            val intent = Intent(
                                context,
                                InsertOrUpdateTransferInOrderActivity::class.java
                            )
                            intent.putExtra("shopID", item.api_jump_param)
                            context.startActivity(intent)
                        }
                    }
                    8 -> {
                        if (Utils.isFastClick()) {
                            val intent = Intent(
                                context,
                                TransferOutOrderDetailActivity::class.java
                            )
                            intent.putExtra("shopID", item.api_jump_param)
                            context.startActivity(intent)
                        }
                    }
                    9 -> {
                        if (Utils.isFastClick()) {
                            val intent = Intent(
                                context,
                                MessageDetailActivity::class.java
                            )
                            intent.putExtra("noticeId", item.api_jump_param)
                            intent.putExtra("category", 2)
                            context.startActivity(intent)
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
        val lable: ImageView = containerView.findViewById(R.id.lable)

    }
}