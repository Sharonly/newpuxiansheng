package com.puxiansheng.www.app

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import cn.jpush.android.api.NotificationMessage
import cn.jpush.android.service.JPushMessageReceiver
import com.google.gson.Gson
import com.puxiansheng.www.app.PXSPushReceiver.Test.DataBean
import com.puxiansheng.www.ui.business.BusinessListActivity
import com.puxiansheng.www.ui.info.NewInfoDetailActivity
import com.puxiansheng.www.ui.message.MessageDetailActivity
import com.puxiansheng.www.ui.mine.setting.AboutUsActivity
import com.puxiansheng.www.ui.order.*
import com.puxiansheng.www.ui.release.InsertOrUpdateTransferInOrderActivity
import com.puxiansheng.www.ui.release.InsertOrUpdateTransferOutOrderActivity
import com.puxiansheng.www.ui.release.fasttransfer.FastTransferInActivity
import com.puxiansheng.www.ui.release.fasttransfer.FastTransferOutActivity

class PXSPushReceiver : JPushMessageReceiver() {
    override fun onNotifyMessageArrived(
        context: Context,
        notificationMessage: NotificationMessage
    ) {
        super.onNotifyMessageArrived(context, notificationMessage)
    }

    override fun onNotifyMessageOpened(
        context: Context,
        notificationMessage: NotificationMessage
    ) {
        super.onNotifyMessageOpened(context, notificationMessage)
        try {
            val toJson = Gson().toJson(notificationMessage)
            val json = Gson().fromJson(notificationMessage.notificationExtras, Test::class.java)
            Log.e("JPush",
                json.jump_msg!!.jump_param + "  jump_type- " + json.jump_msg!!.jump_type + " jump_view = " + json.jump_msg!!.jump_view)
            pictureIntent(context, json.jump_msg!!)
            //   String json1 = new Gson().toJson(json.toString());
        } catch (e: Exception) {
            Log.e("conver fail ", e.message)
        }
    }

    private fun pictureIntent(context: Context, data: DataBean) {
        Log.d("JPush"," data.TYPR  = "+data.jump_type+"  data.jump_param? = "+ data.jump_param+" jump_view  ="+data.jump_view)
        when (data.jump_type) {
            1 -> when (data.jump_view) {
                "transfer_list" -> {
                    val intent = Intent(context, NewTransferOutOrdersActivity::class.java)
                    intent.putExtra("title", "*")
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent)
                }
                "find_list" -> {
                    val intent = Intent(context, NewTransferInOrdersActivity::class.java)
                    intent.putExtra("title", "*")
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent)
                }
                "article_list" -> {
                }
                "user_center" -> {
                }
                "shop_success" -> {
                    val intent2 = Intent(context, NewSuccessOrdersActivity::class.java)
                    intent2.putExtra("type", 1)
                    intent2.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent2)
                }

                "join_list" -> {
                    val intent2 = Intent(context, BusinessListActivity::class.java)
                    intent2.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent2)
                }
                "quick_transfer" -> {
                    val intent2 = Intent(context, FastTransferOutActivity::class.java)
                    intent2.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent2)
                }
                "quick_find" -> {
                    val intent2 = Intent(context, FastTransferInActivity::class.java)
                    intent2.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent2)
                }
                "about_us" -> {
                    val intent2 = Intent(context, AboutUsActivity::class.java)
                    intent2.putExtra("url", data.jump_param)
                    intent2.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent2)
                }

                "transfer_shop" -> {
                    val intent = Intent(context, NewTransferOutOrdersActivity::class.java)
                    intent.putExtra("title", "*")
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent)
                }

                "find_shop" -> {
                    val intent = Intent(context, NewTransferInOrdersActivity::class.java)
                    intent.putExtra("title", "*")
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent)
                }
                "notice_list" -> {
                    val intent = Intent(context, NewSuccessOrdersActivity::class.java)
                    intent.putExtra("type", 1)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent)
                }
            }

            2 -> {//打开链接
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(data.jump_param)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
            3 -> {//找店详情
                val intent = Intent(context, TransferInOrderDetailActivity::class.java)
                intent.putExtra("shopID", data.jump_param)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                Log.d("JPush","data.jump_param = "+data.jump_param)
                context.startActivity(intent)
            }
            4 -> {//转铺详情
                val intent = Intent(context, TransferOutOrderDetailActivity::class.java)
                intent.putExtra("shopID", data.jump_param)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
            5 -> {//文章详情
                val intent = Intent(context, NewInfoDetailActivity::class.java)
                intent.putExtra("url", data.jump_param)
                intent.putExtra("shop_Id", "")
                intent.putExtra("title","")
                intent.putExtra("img", "")
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
            6 -> {//转铺编辑
                val intent = Intent(context, InsertOrUpdateTransferOutOrderActivity::class.java)
                intent.putExtra("shopID", data.jump_param)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }

            7 -> {//zhao铺编辑
                val intent = Intent(context, InsertOrUpdateTransferInOrderActivity::class.java)
                intent.putExtra("shopID", data.jump_param)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
            8 -> {//案例详情
                val intent = Intent(context, NewSuccessOrdersActivity::class.java)
                intent.putExtra("url", data.jump_param)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
            9 -> {//消息详情
                val intent = Intent(context, MessageDetailActivity::class.java)
                intent.putExtra("noticeId", data.jump_param)
                intent.putExtra("category", 2)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
        }
    }

    inner class Test {
        var jump_msg: DataBean? = null

        inner class DataBean {
            var jump_param: String? = null
            var jump_type = 0
            var jump_view: String? = null

        }
    }

}