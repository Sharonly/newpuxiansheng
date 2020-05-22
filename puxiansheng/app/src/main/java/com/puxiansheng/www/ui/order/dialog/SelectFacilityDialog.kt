package com.puxiansheng.www.ui.order.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.LocationNode
import com.puxiansheng.logic.bean.MenuItem
import com.puxiansheng.www.R
import com.puxiansheng.www.common.drawableTop
import com.puxiansheng.www.databinding.DialogSelectIndustryBinding
import com.puxiansheng.www.databinding.DialogSelectiveMenuItemBinding

import kotlinx.android.extensions.LayoutContainer

class SelectFacilityDialog(
    private val onSelectFacilities: (List<MenuItem>) -> Unit
) : DialogFragment() {
    private lateinit var selectMenuViewModel: SelectFacilityViewModel
    private lateinit var binding: DialogSelectIndustryBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        selectMenuViewModel = ViewModelProvider(this)[SelectFacilityViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = true
    }

    override fun onStart() {
        super.onStart()
        dialog?.let {
            it.window?.let { window ->
                window.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                window.setGravity(Gravity.BOTTOM)
                window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DialogSelectIndustryBinding.inflate(inflater).apply {
        binding = this
        binding.lifecycleOwner = viewLifecycleOwner

        submit.setOnClickListener {
            onSelectFacilities(selectMenuViewModel.selectedFacilities)
            dismiss()
        }

        title.text = "选择物业配套"

        menuListTop.layoutManager = GridLayoutManager(requireContext(), 4)
        menuListTop.adapter = MenuItemListAdapter(mutableListOf())

        selectMenuViewModel.selectiveFacilityMenuData.observe(
            viewLifecycleOwner,
            Observer { it ->
                it?.takeIf { it.isNotEmpty() }?.let { list ->
                    (menuListTop.adapter as MenuItemListAdapter).setMenuData(list)
                }
            })

        selectMenuViewModel.selectedFacilityItemPosition.observe(
            viewLifecycleOwner,
            Observer {
                (menuListTop.adapter as MenuItemListAdapter).notifyDataSetChanged()
            })

        selectMenuViewModel.getFacilityMenuData(MenuItem.TYPE.PROPERTY.value())
    }.root

    inner class MenuItemListAdapter(
        var list: List<MenuItem>
    ) : RecyclerView.Adapter<MenuItemListAdapter.MenuItemHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemHolder =
            MenuItemHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.dialog_selective_menu_item,
                    parent,
                    false
                )
            )

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: MenuItemHolder, position: Int) =
            holder.bind(list[position], holder.adapterPosition)

        fun setMenuData(menuData: List<MenuItem>) {
            list = menuData.toMutableList()
            notifyDataSetChanged()
        }

        inner class MenuItemHolder(
            override val containerView: View
        ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
            val binding: DialogSelectiveMenuItemBinding =
                DialogSelectiveMenuItemBinding.bind(containerView)

            fun bind(menuItem: MenuItem, adapterPosition: Int) {
                binding.menuText.text = menuItem.text
                binding.menuText.drawableTop(menuItem.icon_enable)

                binding.root.setOnClickListener {
                    if (selectMenuViewModel.selectedFacilities.contains(menuItem)) {
                        selectMenuViewModel.selectedFacilities.remove(menuItem)
                    } else {
                        selectMenuViewModel.selectedFacilities.add(menuItem)
                    }
                    notifyDataSetChanged()
                }

                if (selectMenuViewModel.selectedFacilities.contains(menuItem))
                    setSelected(true) else setSelected(false)
            }

            private fun setSelected(isSelected: Boolean) = if (isSelected) {
                binding.menuText.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.input_text_blank
                    )
                )
                binding.root.setBackgroundColor(Color.parseColor("#F1F1F1"))
            } else {
                binding.menuText.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                       R.color.text_gray
                    )
                )
                binding.root.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }
        }
    }
}