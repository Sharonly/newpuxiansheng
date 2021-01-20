package com.puxiansheng.www.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.puxiansheng.logic.bean.BannerImage
import com.puxiansheng.www.tools.UMengKeys
import com.puxiansheng.www.ui.business.BusinessListActivity
import com.puxiansheng.www.ui.info.InfoDetailActivity
import com.puxiansheng.www.ui.info.NewInfoDetailActivity
import com.puxiansheng.www.ui.info.WebViewActivity
import com.puxiansheng.www.ui.main.HomeActivity
import com.puxiansheng.www.ui.main.MainActivity
import com.puxiansheng.www.ui.message.MessageDetailActivity
import com.puxiansheng.www.ui.mine.setting.AboutUsActivity
import com.puxiansheng.www.ui.order.*
import com.puxiansheng.www.ui.release.InsertOrUpdateTransferInOrderActivity
import com.puxiansheng.www.ui.release.InsertOrUpdateTransferOutOrderActivity
import com.puxiansheng.www.ui.release.fasttransfer.FastTransferInActivity
import com.puxiansheng.www.ui.release.fasttransfer.FastTransferOutActivity
import com.umeng.analytics.MobclickAgent

object JumpUtils{

fun pictureIntent(context: Context, image: BannerImage) {
    Log.d(
        "---jump--",
        " pictureIntent--->${image.jump_param}--->${image.imageUrl}" + "   image.jump_type = " + image.jump_type + "  image.jump_view =  " + image.jump_view + "  image.jump_param = " + image.jump_param
    )
    when (image.jump_type) {
        1 -> {
            when (image.jump_view) {
                "index" -> {
                }
                "transfer_list" -> {
                    val intent = Intent(context, NewTransferOutOrdersActivity::class.java)
                    intent.putExtra("title", "*")
                    context.startActivity(intent)
                    MobclickAgent.onEvent(context, UMengKeys.PAGE_NAME, "NewTransferOutOrdersActivity")
                }
                "find_list" -> {
                    val intent = Intent(context, NewTransferInOrdersActivity::class.java)
                    intent.putExtra("title", "*")
                    context.startActivity(intent)
                    MobclickAgent.onEvent(context, UMengKeys.PAGE_NAME, "NewTransferInOrdersActivity")
                }


                "activity_list" -> {//文章列表
                    val intent = Intent(context, MainActivity::class.java)
                    intent.putExtra("name", "5")
                    context.startActivity(intent)
                }
                "user_center" -> {

                }

                "join_list" -> {
                    val intent = Intent(context, BusinessListActivity::class.java)
                    intent.putExtra("title", "*")
                    context.startActivity(intent)
                    MobclickAgent.onEvent(context, UMengKeys.PAGE_NAME, "BusinessListActivity")
                }

                "quick_transfer" -> {
                    val intent = Intent(context, FastTransferOutActivity::class.java)
                    context.startActivity(intent)
                    MobclickAgent.onEvent(context, UMengKeys.PAGE_NAME, "FastTransferOutActivity")
                }

                "quick_find" -> {
                    val intent = Intent(context, FastTransferInActivity::class.java)
                    context.startActivity(intent)
                    MobclickAgent.onEvent(context, UMengKeys.PAGE_NAME, "FastTransferInActivity")
                }
                "about_us" -> {
                    val intent = Intent(context, AboutUsActivity::class.java)
                    context.startActivity(intent)
                    MobclickAgent.onEvent(context, UMengKeys.PAGE_NAME, "AboutUsActivity")
                }

                "shop_success" -> {//成功案例
                    val intent = Intent(context, NewSuccessOrdersActivity::class.java)
                    intent.putExtra("type", 1)
                    context.startActivity(intent)
                    MobclickAgent.onEvent(context, UMengKeys.PAGE_NAME, "NewTransferSuccessOrdersActivity")
                }
            }
        }
        2 -> {//打开链接
//                val intent = Intent(Intent.ACTION_VIEW)
//                intent.data = Uri.parse(image.jump_param)
//                context.startActivity(intent)

            //TODO  2020/6/8
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra("url", image.jump_param)
            context.startActivity(intent)
        }

        3 -> {//找店详情
            val intent = Intent(context, TransferInOrderDetailActivity::class.java)
            intent.putExtra("shopID", image.jump_param)
            context.startActivity(intent)
            MobclickAgent.onEvent(context, UMengKeys.PAGE_NAME, "TransferInOrderDetailActivity")
        }

        4 -> {//转铺详情
            val intent = Intent(context, TransferOutOrderDetailActivity::class.java)
            intent.putExtra("shopID", image.jump_param)
            context.startActivity(intent)
            MobclickAgent.onEvent(context, UMengKeys.PAGE_NAME, "TransferOutOrderDetailActivity")
        }

        5 -> {//文章详情
            val intent = Intent(context, NewInfoDetailActivity::class.java)
            intent.putExtra("url", image.jump_param)
            intent.putExtra("shop_Id", image?.id)
            intent.putExtra("title", image?.title)
            intent.putExtra("img", image?.imageUrl)
            context.startActivity(intent)
            MobclickAgent.onEvent(context, UMengKeys.PAGE_NAME, "InfoDetailActivity")
        }

        6 -> {
            val intent = Intent(
                context,
                InsertOrUpdateTransferOutOrderActivity::class.java
            )
            intent.putExtra("shopID", image.jump_param)
            context.startActivity(intent)

        }
        7 -> {
            val intent = Intent(
                context,
                InsertOrUpdateTransferInOrderActivity::class.java
            )
            intent.putExtra("shopID", image.jump_param)
            context.startActivity(intent)
        }
        8 -> {
            val intent = Intent(
                context,
                TransferOutOrderDetailActivity::class.java
            )
            intent.putExtra("shopID", image.jump_param)
            context.startActivity(intent)
        }
        9 -> {
            val intent = Intent(
                context,
                MessageDetailActivity::class.java
            )
            intent.putExtra("noticeId", image.jump_param)
            intent.putExtra("category", 2)
            context.startActivity(intent)
        }

    }
}


}
