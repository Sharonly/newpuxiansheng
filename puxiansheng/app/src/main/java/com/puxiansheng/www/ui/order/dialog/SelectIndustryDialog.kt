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
import com.puxiansheng.www.databinding.DialogSelectiveMenuItemBinding
import kotlinx.android.extensions.LayoutContainer

class SelectIndustryDialog(
    private val onSelectIndustry: (topLevelItem: MenuItem?, secondLevelItem: MenuItem?) -> Unit
) : DialogFragment() {
    private lateinit var selectMenuViewModel: SelectIndustryViewModel
    private lateinit var binding: DialogSelectIndustryBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        selectMenuViewModel = ViewModelProvider(this)[SelectIndustryViewModel::class.java]
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
            onSelectIndustry(
                selectMenuViewModel.selectedTopLevelIndustryItem.value,
                selectMenuViewModel.selectedSecondLevelIndustryItem.value
            )
            dismiss()
        }

        btReset.setOnClickListener {

        }

        title.text = "行业"

        menuListTop.layoutManager = LinearLayoutManager(requireContext())
        menuListTop.adapter = MenuItemListAdapter(mutableListOf(), 0)

        menuListSecond.layoutManager = LinearLayoutManager(requireContext())
        menuListSecond.adapter = MenuItemListAdapter(mutableListOf(), 1)

        selectMenuViewModel.selectedTopLevelIndustryItem.observe(viewLifecycleOwner, Observer {
            selectMenuViewModel.getIndustrySecondMenuDataFromRemote(it.menuID.toInt())
        })

        selectMenuViewModel.selectiveTopLevelIndustryData.observe(
            viewLifecycleOwner,
            Observer { it ->
                it?.takeIf { it.isNotEmpty() }?.let { list ->
                    (menuListTop.adapter as MenuItemListAdapter).setMenuData(list)
                }
            })

        selectMenuViewModel.selectiveSecondLevelIndustryData.observe(
            viewLifecycleOwner, Observer {
                if (it.isNullOrEmpty()) menuListSecond.visibility =
                    View.GONE else menuListSecond.visibility = View.VISIBLE

                it?.takeIf { it.isNotEmpty() }?.let { list ->
                    (menuListSecond.adapter as MenuItemListAdapter).setMenuData(list)
                }
            })


        selectMenuViewModel.selectedTopLevelIndustryItemPosition.observe(
            viewLifecycleOwner,
            Observer {
                (menuListTop.adapter as MenuItemListAdapter).notifyDataSetChanged()
            })

        selectMenuViewModel.selectedSecondLevelIndustryItemPosition.observe(
            viewLifecycleOwner,
            Observer {
                (menuListSecond.adapter as MenuItemListAdapter).notifyDataSetChanged()
            })

        selectMenuViewModel.getIndustryMenuDataFromRemote()
    }.root

    inner class MenuItemListAdapter(
        var list: MutableList<MenuItem>,
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

        fun setMenuData(menuData: List<MenuItem>) {
            list = menuData.toMutableList()
            notifyDataSetChanged()
        }

        inner class MenuItemHolder(
            override val containerView: View,
            private val level: Int
        ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
            val binding: DialogSelectiveMenuItemBinding =
                DialogSelectiveMenuItemBinding.bind(containerView)

            fun bind(menuItem: MenuItem, adapterPosition: Int) {
                binding.menuText.text = menuItem.text
                binding.menuText.setOnClickListener {
                    when (level) {
                        0 -> {
                            selectMenuViewModel.selectedTopLevelIndustryItem.postValue(menuItem)
                            selectMenuViewModel.selectedTopLevelIndustryItemPosition.postValue(
                                adapterPosition
                            )
                            selectMenuViewModel.selectedSecondLevelIndustryItemPosition.postValue(
                                -1
                            )
                        }
                        1 -> {
                            selectMenuViewModel.selectedSecondLevelIndustryItem.postValue(
                                menuItem
                            )
                            selectMenuViewModel.selectedSecondLevelIndustryItemPosition.postValue(
                                adapterPosition
                            )
                        }
                    }
                }

                setSelected(false)

                selectMenuViewModel.selectedTopLevelIndustryItemPosition.value?.takeIf { level == 0 }
                    ?.takeIf { it == adapterPosition }?.let {
                        setSelected(true)
                    }

                selectMenuViewModel.selectedSecondLevelIndustryItemPosition.value?.takeIf { level == 1 }
                    ?.takeIf { it == adapterPosition }?.let {
                        setSelected(true)
                    }
            }

            private fun setSelected(isSelected: Boolean) = if (isSelected) {
                binding.menuText.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.appMain
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