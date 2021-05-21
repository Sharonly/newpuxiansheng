package com.puxiansheng.www.ui.project

import android.content.Context
import android.content.Intent
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.ArticleBean
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.logic.bean.http.OrderDetailObject
import com.puxiansheng.logic.bean.http.ProjectDetailObject
import com.puxiansheng.www.R
import com.puxiansheng.www.common.url
import com.puxiansheng.www.tools.Utils
import com.puxiansheng.www.ui.order.TransferInOrderDetailActivity
import com.puxiansheng.www.ui.order.TransferOutOrderDetailActivity
import kotlinx.android.extensions.LayoutContainer

class ProjectHomeAdapter(var context: Context, var dataList: ArrayList<ProjectDetailObject>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var type: Int = Order.Type.EMPTY.value()

    fun addList(tempList: ArrayList<ProjectDetailObject>, isClean: Boolean) {
        if (isClean) {
            dataList.clear()
        }
        dataList.addAll(tempList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            val view = LayoutInflater.from(context)
                .inflate(R.layout.item_project_home_list, parent, false)
            return RecommendTransferOutViewHolder(view)
        }
        val view =
            LayoutInflater.from(context).inflate(R.layout.fragment_order_list_empty, parent, false)
        return object : RecyclerView.ViewHolder(view) {}
    }


    fun getDataCount(): Int {
        return dataList?.size ?: 0
    }

    override fun getItemCount(): Int {
        return if (dataList.size == 0) 1 else dataList.size
    }


//    override fun submitList(pagedList: PagedList<Order>?) {
//        dataList = pagedList!!
//        super.submitList(pagedList)
//    }

//    override fun getItemCount(): Int {
//        if (type == Order.Type.EMPTY.value()) return 1 + super.getItemCount()
//        return super.getItemCount()
//    }


    override fun getItemViewType(position: Int): Int {
        if (!dataList.isNullOrEmpty()) {
            return 1
        }
        return 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RecommendTransferOutViewHolder) {
            var shopInfo = dataList[position]
            println("item---》${shopInfo}")
            holder.shopIcon.url(shopInfo.theme_img)
            holder.shopTitle.text = shopInfo.title
            holder.subTitle.text = shopInfo.brand
            holder.shopIndustry.text = shopInfo.category_str
            holder.root.setOnClickListener {
                if (Utils.isFastClick()) {
                    val intent = Intent(context, ProjectDetailActivity::class.java)
                    Log.d("homeadapter"," shopInfo?.shopID = "+ shopInfo?.shopID)
                    intent.putExtra("shopId", shopInfo?.shopID)
                    context.startActivity(intent)
                }
            }
        }
    }


    class RecommendTransferOutViewHolder(containerView: View) :
        RecyclerView.ViewHolder(containerView) {
        val root = containerView.findViewById<View>(R.id.item_layout)
        val shopIcon = containerView.findViewById<ImageView>(R.id.shop_img)
        val shopTitle = containerView.findViewById<TextView>(R.id.title)
        val subTitle = containerView.findViewById<TextView>(R.id.sub_title)
        val shopIndustry = containerView.findViewById<TextView>(R.id.industry_lable)
        val shopLabel = containerView.findViewById<TextView>(R.id.lable)
    }


}