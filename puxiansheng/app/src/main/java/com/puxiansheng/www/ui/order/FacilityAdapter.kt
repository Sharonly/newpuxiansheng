package com.puxiansheng.www.ui.order

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.MenuItem
import com.puxiansheng.www.R
import com.puxiansheng.www.common.drawableTop
import com.puxiansheng.www.databinding.DialogSelectiveMenuItemBinding
import kotlinx.android.extensions.LayoutContainer

class FacilityAdapter(
private val facilities: List<MenuItem>
) : RecyclerView.Adapter<FacilityAdapter.FacilityItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FacilityItemViewHolder = FacilityItemViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.dialog_selective_menu_item,
            parent,
            false
        )
    )

    override fun getItemCount(): Int = facilities.size

    override fun onBindViewHolder(
        holder: FacilityItemViewHolder,
        position: Int
    ) {
        holder.bind(facilities[position])
    }

    inner class FacilityItemViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        val binding: DialogSelectiveMenuItemBinding =
            DialogSelectiveMenuItemBinding.bind(containerView)

        fun bind(menuItem: MenuItem) {
            binding.menuText.textSize = 10F
            binding.menuText.text = menuItem.text
            binding.menuText.drawableTop(menuItem.icon_enable)
        }
    }
}