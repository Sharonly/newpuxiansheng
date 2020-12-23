package com.puxiansheng.www.ui.mine.suggest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.http.RequestBean
import com.puxiansheng.www.R
import com.puxiansheng.www.databinding.RecyclerviewSugetionCallbackItemBinding
import kotlinx.android.extensions.LayoutContainer

class QuestionAdapter(
    private var list: List<RequestBean>, private val onItemSelect: ((order: RequestBean?) -> Unit)? = null
    ) : RecyclerView.Adapter<QuestionAdapter.OrderItemViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionAdapter.OrderItemViewHolder = OrderItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recyclerview_sugetion_callback_item,
                parent,
                false
            )
        )

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(
            holder: QuestionAdapter.OrderItemViewHolder,
            position: Int
        ) {
            holder.bind(list[position])
        }

    fun setMenuData(listData: List<RequestBean>) {
        list = listData.toMutableList()
        notifyDataSetChanged()
    }

     class OrderItemViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        val binding: RecyclerviewSugetionCallbackItemBinding=
                RecyclerviewSugetionCallbackItemBinding.bind(containerView)

        fun bind(menuItem: RequestBean) {
            binding.questionType.text = menuItem.cate
            binding.questionContent.text =menuItem.content
            binding.questionReplay.text = menuItem.reply
            binding.time.text = menuItem.update_time


        }
    }
}