package com.puxiansheng.www.ui.release

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.MenuItem
import com.puxiansheng.www.R
import com.puxiansheng.www.common.drawableTop
import com.puxiansheng.www.databinding.DialogSelectiveFalitiesItemBinding
import kotlinx.android.extensions.LayoutContainer

class ReleaseFacilityAdapter (
    private var list: List<MenuItem>
) : RecyclerView.Adapter<ReleaseFacilityAdapter.MenuItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemHolder =
        MenuItemHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.dialog_selective_falities_item,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MenuItemHolder, position: Int) =
        holder.bind(list[position], holder.adapterPosition)

    fun setMenuData(menuData: MutableSet<MenuItem>) {
        list = menuData.toMutableList()
        notifyDataSetChanged()
    }

    inner class MenuItemHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        val binding: DialogSelectiveFalitiesItemBinding =
            DialogSelectiveFalitiesItemBinding.bind(containerView)

        fun bind(menuItem: MenuItem, adapterPosition: Int) {
            binding.menuText.text = menuItem.text
            binding.menuText.drawableTop(menuItem.icon_enable)
        }
    }

}
