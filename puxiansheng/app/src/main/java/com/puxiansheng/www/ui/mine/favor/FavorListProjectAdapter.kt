package com.puxiansheng.www.ui.mine.favor

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.http.ProjectDetailObject
import com.puxiansheng.www.R
import com.puxiansheng.www.common.urlIcon
import com.puxiansheng.www.tools.Utils
import com.puxiansheng.www.ui.project.ProjectDetailActivity

class FavorListProjectAdapter(var context: Context, var dataList: ArrayList<ProjectDetailObject>, var deleteListener: onDeleteListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
                .inflate(R.layout.fragment_order_list_empty, parent, false)
            return FavorProjectViewHolder(view)
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

    override fun getItemViewType(position: Int): Int {
        if (!dataList.isNullOrEmpty()) {
            return 1
        }
        return 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FavorProjectViewHolder) {
            var shopInfo = dataList[position]
            println("item---》${shopInfo}")
            holder.shopIcon.urlIcon(shopInfo?.theme_img)
            holder.shopTitle.text = shopInfo.title
            holder.shopIndustry.text = shopInfo.category_str
            holder.shopArea.text = shopInfo.shop_address
            holder.shopData.text = shopInfo.update_time
            holder.root.setOnClickListener {
                if (Utils.isFastClick()) {
                    val intent = Intent(context, ProjectDetailActivity::class.java)
                    intent.putExtra("shopId", shopInfo?.shopID)
                    context.startActivity(intent)
                }
            }

            holder.btDelete.setOnClickListener {
                deleteListener?.delete(shopInfo)
            }

        }
    }



     class FavorProjectViewHolder(containerView: View) :
        RecyclerView.ViewHolder(containerView) {
         val root = containerView.findViewById<View>(R.id.item_layout)
         val shopIcon = containerView.findViewById<ImageView>(R.id.shop_icon)
         val shopTitle = containerView.findViewById<TextView>(R.id.title)
         val subTitle = containerView.findViewById<TextView>(R.id.sub_title)
         val shopIndustry = containerView.findViewById<TextView>(R.id.industry_lable)
         val shopArea = containerView.findViewById<TextView>(R.id.area)
         val shopData = containerView.findViewById<TextView>(R.id.date)
         val shopLabel = containerView.findViewById<TextView>(R.id.lable)
        var btDelete = containerView.findViewById<TextView>(R.id.bt_delete)

    }






    interface onDeleteListener{
        fun delete(order:ProjectDetailObject)
    }


}