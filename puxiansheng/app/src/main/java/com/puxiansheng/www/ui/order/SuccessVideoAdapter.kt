package com.puxiansheng.www.ui.order

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.http.SuccessVideoBean
import com.puxiansheng.www.R
import com.puxiansheng.www.common.url
import kotlinx.android.extensions.LayoutContainer

class SuccessVideoAdapter(
    var context: Context, var list: ArrayList<SuccessVideoBean>,var type:Int
) :  RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        if (viewType == 1) {
            val view = LayoutInflater.from(context)
                .inflate(R.layout.succese_video_item, parent, false)
            return OrderItemViewHolder(view)
        }else if(viewType == 2){
            val view = LayoutInflater.from(context)
                .inflate(R.layout.home_video_item, parent, false)
            return OrderItemViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(context)
                    .inflate(R.layout.fragment_list_null, parent, false)
            return object : RecyclerView.ViewHolder(view) {}
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (!list.isNullOrEmpty()) {
            return type
        }
        return 0
    }


    override fun getItemCount(): Int {
        return if (list.size == 0) 1 else list.size
    }


    fun addList(tempList:List<SuccessVideoBean>, isClean: Boolean) {
        if (isClean) {
            list.clear()
            Log.e("successVideo","   list.clear()")
        }
        list.addAll(tempList)
        notifyDataSetChanged()

    }


    inner class OrderItemViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        val root: View = containerView.findViewById<View>(R.id.root)
        var shopTitle:TextView = containerView.findViewById(R.id.shop_title)
        val shopImg:ImageView = containerView.findViewById(R.id.shop_img)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SuccessVideoAdapter.OrderItemViewHolder) {
            var video = list[position]
            holder.shopTitle.text = video.title
            holder.shopImg.url(video.img)
            holder.shopImg.setOnClickListener {
                val intent = Intent(context, SucceseVideoDetailActivity::class.java)
                intent.putExtra("shopID", video?.videoId.toString())
                context.startActivity(intent)
            }
            holder.root.setOnClickListener {
                val intent = Intent(context, SucceseVideoDetailActivity::class.java)
                intent.putExtra("shopID", video?.videoId.toString())
                context.startActivity(intent)
            }
        }
    }

}