package com.puxiansheng.www.ui.home

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.ArticleBean
import com.puxiansheng.logic.bean.BusinessBean
import com.puxiansheng.www.R
import com.puxiansheng.www.databinding.ArticleTitleItmBinding
import com.puxiansheng.www.ui.business.BusinessListAdapter
import com.puxiansheng.www.ui.info.InfoDetailActivity
import com.puxiansheng.www.ui.info.NewInfoDetailActivity
import kotlinx.android.extensions.LayoutContainer

class ArticleTitleAdapter(
     var list: List<ArticleBean>,  var context: Context
)  :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view = LayoutInflater.from(context)
                .inflate(R.layout.article_title_itm, parent, false)
            return OrderItemViewHolder(view)

    }


    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is OrderItemViewHolder) {
            var info = list[position]
            info?.title?.let {
                holder.title.text = it
                holder.title.setOnClickListener {
                    if(info?.title.isNotEmpty() && info?.shopID != 0) {
                        val intent = Intent(context, NewInfoDetailActivity::class.java)
                        intent.putExtra("url", info.jump_param)
                        intent.putExtra("shop_Id", info?.shopID)
                        intent.putExtra("title", info?.title)
                        intent.putExtra("img", "")
                        context.startActivity(intent)
                    }
                }
            }
        }
    }


fun setMenuData(listData: List<ArticleBean>) {
    list = listData.toMutableList()
    notifyDataSetChanged()
}


class OrderItemViewHolder(
    var containerView: View
) : RecyclerView.ViewHolder(containerView) {
    var title: TextView = containerView.findViewById(R.id.info_title)

}

}