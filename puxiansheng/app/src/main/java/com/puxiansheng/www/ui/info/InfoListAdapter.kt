package com.puxiansheng.www.ui.info

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.InfoItem
import com.puxiansheng.www.R
import com.puxiansheng.www.common.url
import com.puxiansheng.www.databinding.FragmentInfoItemBinding
import kotlinx.android.extensions.LayoutContainer

 class InfoListAdapter(private val onItemSelect: ((infoItem: InfoItem?) -> Unit)? = null) : PagedListAdapter<InfoItem, InfoListAdapter.InfoViewHolder>(
    InfoItem.DIFF
) {
     private var dataList: PagedList<InfoItem>? = null
    inner class InfoViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        private val binding = FragmentInfoItemBinding.bind(containerView)

        @SuppressLint("SetTextI18n")
        fun bind(infoItem: InfoItem?) {
            binding.title.text = infoItem?.title
            binding.data.text = infoItem?.date
            binding.pageViews.text = infoItem?.pageViews.toString()
            binding.icon.url(infoItem?.image ?: "")
            binding.root.setOnClickListener {
                onItemSelect?.let { select -> select(infoItem) }
//                val intent = Intent(requireActivity(), InfoDetailActivity::class.java)
//                intent.putExtra("url", infoItem?.url)
//                startActivity(intent)
            }
        }
    }

     override fun submitList(pagedList: PagedList<InfoItem>?) {
         dataList = pagedList!!
         super.submitList(pagedList)
         notifyDataSetChanged()
     }


     fun getDataCount(): Int {
         return dataList?.size ?: 0
     }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = InfoViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.fragment_info_item, parent, false)
    )

    override fun onBindViewHolder(
        holder: InfoViewHolder,
        position: Int
    ) = holder.bind(getItem(position))
}