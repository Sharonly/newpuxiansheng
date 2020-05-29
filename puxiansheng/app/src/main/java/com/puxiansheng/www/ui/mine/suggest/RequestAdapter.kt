package com.puxiansheng.www.ui.mine.suggest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.http.RequestBean
import com.puxiansheng.www.R
import com.puxiansheng.www.databinding.RecyclerviewRequestItemBinding
import kotlinx.android.extensions.LayoutContainer

class RequestAdapter(
    private var list: List<RequestBean>
) : RecyclerView.Adapter<RequestAdapter.OrderItemViewHolder>() {
    var arrowIsDown = true
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RequestAdapter.OrderItemViewHolder = OrderItemViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.recyclerview_request_item,
            parent,
            false
        )
    )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(
        holder: RequestAdapter.OrderItemViewHolder,
        position: Int
    ) {
        holder.bind(list[position])
    }

    fun setMenuData(listData: List<RequestBean>) {
        list = listData.toMutableList()
        notifyDataSetChanged()
    }

    inner class OrderItemViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        val binding: RecyclerviewRequestItemBinding =
            RecyclerviewRequestItemBinding.bind(containerView)

        fun bind(menuItem: RequestBean) {
            binding.title.text = menuItem.title
            binding.iconArrow.setOnClickListener {
                if (arrowIsDown){
                    arrowIsDown = false
                    binding.iconArrow.setImageResource(R.mipmap.ic_arrow_up)
                    binding.answer.visibility = View.VISIBLE
                    binding.answer.text = menuItem.content
                }else{
                    arrowIsDown = true
                    binding.iconArrow.setImageResource(R.mipmap.ic_arrow_down)
                    binding.answer.visibility = View.GONE
                }
            }


        }
    }
}