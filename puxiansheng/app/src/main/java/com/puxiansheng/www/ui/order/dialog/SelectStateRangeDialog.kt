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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.MenuItem
import com.puxiansheng.www.R
import com.puxiansheng.www.databinding.DialogSelectIndustryBinding
import com.puxiansheng.www.databinding.DialogSelectStateBinding
import com.puxiansheng.www.databinding.DialogSelectiveMenuItemBinding
import kotlinx.android.extensions.LayoutContainer

class SelectStateRangeDialog(
    private val onSelectState: (stateMenuItem: String?) -> Unit
) : DialogFragment() {
    private lateinit var selectSizeRangeViewModel: SelectSizeRangeViewModel
    private lateinit var binding: DialogSelectStateBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        selectSizeRangeViewModel = ViewModelProvider(this)[SelectSizeRangeViewModel::class.java]
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
    ): View? = DialogSelectStateBinding.inflate(inflater).apply {
        binding = this
        binding.lifecycleOwner = viewLifecycleOwner

        submit.setOnClickListener {
            onSelectState(selectSizeRangeViewModel.selectiveStateItem.value)
            dismiss()
        }

        title.text = "经营状态"

        menuListTop.layoutManager = LinearLayoutManager(requireContext())
        menuListTop.adapter = MenuItemListAdapter(mutableListOf(), 0)

        selectSizeRangeViewModel.selectiveState.observe(
            viewLifecycleOwner,
            Observer { it ->
                it?.takeIf { it.isNotEmpty() }?.let { list ->
                    (menuListTop.adapter as MenuItemListAdapter).setMenuData(list)
                }
            })

        selectSizeRangeViewModel.selectedTopLevelMenuItemPosition.observe(
            viewLifecycleOwner,
            Observer {
                (menuListTop.adapter as MenuItemListAdapter).notifyDataSetChanged()
            })

        selectSizeRangeViewModel.loadStateMenuDate()
    }.root

    inner class MenuItemListAdapter(
        var list: MutableList<String>,
        private val level: Int
    ) : RecyclerView.Adapter<MenuItemListAdapter.MenuItemHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemHolder =
            MenuItemHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.dialog_selective_menu_item,
                    parent,
                    false
                ),
                level
            )

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: MenuItemHolder, position: Int) =
            holder.bind(list[position], holder.adapterPosition)

        fun setMenuData(menuData: List<String>) {
            list = menuData.toMutableList()
            notifyDataSetChanged()
        }

        inner class MenuItemHolder(
            override val containerView: View,
            private val level: Int
        ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
            val binding: DialogSelectiveMenuItemBinding =
                DialogSelectiveMenuItemBinding.bind(containerView)

            fun bind(menuItem: String, adapterPosition: Int) {
                binding.menuText.text = menuItem
                binding.menuText.setOnClickListener {
                    selectSizeRangeViewModel.selectiveStateItem.postValue(menuItem)
                    selectSizeRangeViewModel.selectedTopLevelMenuItemPosition.postValue(
                        adapterPosition
                    )
                }

                setSelected(false)

                selectSizeRangeViewModel.selectedTopLevelMenuItemPosition.value
                    ?.takeIf { it == adapterPosition }?.let {
                        setSelected(true)
                    }
            }

            private fun setSelected(isSelected: Boolean) = if (isSelected) {
                binding.menuText.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.appMainDark
                    )
                )
                binding.root.setBackgroundColor(Color.parseColor("#FAFAFA"))
            } else {
                binding.menuText.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        android.R.color.black
                    )
                )
                binding.root.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }
        }
    }
}