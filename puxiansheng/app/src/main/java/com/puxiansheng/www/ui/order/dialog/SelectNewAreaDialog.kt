package com.puxiansheng.www.ui.order.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.LocationNode
import com.puxiansheng.logic.bean.MenuItem
import com.puxiansheng.www.R
import com.puxiansheng.www.databinding.DialogSelectIndustryBinding
import com.puxiansheng.www.databinding.DialogSelectiveMenuItemBinding
import com.puxiansheng.www.ui.main.MainViewModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class SelectNewAreaDialog(
    private val onSelectArea: (locationNode: MenuItem?, secondLevelItem: MenuItem?) -> Unit
) : DialogFragment() {

    private lateinit var privacyDialogViewModel: SelectNewAreaViewModel

    private lateinit var appModel: MainViewModel
    private lateinit var binding: DialogSelectIndustryBinding
    private var mContext: Context? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        privacyDialogViewModel = ViewModelProvider(this)[SelectNewAreaViewModel::class.java]
        appModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        mContext = context
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
            if(privacyDialogViewModel.selectedTopLevelAreaItem.value!=null && privacyDialogViewModel.selectedSecondLevelAreaItem.value != null) {
                if (privacyDialogViewModel.selectedSecondLevelAreaItem.value?.menuID?.toInt() == 0) {
                    Toast.makeText(mContext, "请选择城市和区域", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                onSelectArea(
                    privacyDialogViewModel.selectedTopLevelAreaItem.value,
                    privacyDialogViewModel.selectedSecondLevelAreaItem.value
                )
                dismiss()
            }else{
                Toast.makeText(mContext, "请选择城市和区域", Toast.LENGTH_SHORT).show()
            }
        }

        title.text = "城市"

        menuListTop.layoutManager = LinearLayoutManager(requireContext())
        menuListTop.adapter = MenuItemListAdapter(mutableListOf(), 0)

        menuListSecond.layoutManager = LinearLayoutManager(requireContext())
        menuListSecond.adapter = MenuItemListAdapter(mutableListOf(), 1)

        privacyDialogViewModel.selectedTopLevelAreaItem.observe(viewLifecycleOwner, Observer {
            privacyDialogViewModel.getNewAreaSecondMenuDataFromRemote(it.menuID.toInt())
        })


        privacyDialogViewModel.selectiveTopLevelAreaData.observe(
            viewLifecycleOwner,
            Observer { it ->
                it?.takeIf { it.isNotEmpty() }?.let { list ->
                    (menuListTop.adapter as MenuItemListAdapter).setMenuData(list)
                }
            })

        privacyDialogViewModel.selectiveSecondLevelAreaData.observe(
            viewLifecycleOwner,
            Observer { it ->
                if (it.isNullOrEmpty()) menuListSecond.visibility =
                    View.GONE else menuListSecond.visibility = View.VISIBLE

                it?.takeIf { it.isNotEmpty() }?.let { list ->
                    (menuListSecond.adapter as MenuItemListAdapter).setMenuData(list)
                }
            })

//        selectMenuViewModel.selectiveSecondLevelIndustryData.observe(
//            viewLifecycleOwner, Observer {
//                if (it.isNullOrEmpty()) menuListSecond.visibility =
//                    View.GONE else menuListSecond.visibility = View.VISIBLE
//
//                it?.takeIf { it.isNotEmpty() }?.let { list ->
//                    (menuListSecond.adapter as SelectIndustryDialog.MenuItemListAdapter).setMenuData(list)
//                }
//            })

        privacyDialogViewModel.selectedTopLevelAreaItemPosition.observe(
            viewLifecycleOwner,
            Observer {
                (menuListTop.adapter as MenuItemListAdapter).notifyDataSetChanged()
            })

        privacyDialogViewModel.selectedSecondLevelAreaItemPosition.observe(
            viewLifecycleOwner,
            Observer {
                (menuListSecond.adapter as MenuItemListAdapter).notifyDataSetChanged()
            })

    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        privacyDialogViewModel.getNewAreaMenuDataFromRemote()
    }

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
                            privacyDialogViewModel.selectedTopLevelAreaItem.postValue(menuItem)
                            privacyDialogViewModel.selectedTopLevelAreaItemPosition.postValue(
                                adapterPosition
                            )
                            privacyDialogViewModel.selectedSecondLevelAreaItemPosition.postValue(
                                -1
                            )
                        }
                        1 -> {
                            privacyDialogViewModel.selectedSecondLevelAreaItem.postValue(
                                menuItem
                            )
                            privacyDialogViewModel.selectedSecondLevelAreaItemPosition.postValue(
                                adapterPosition
                            )
                        }
                    }
                }

                setSelected(false)

                privacyDialogViewModel.selectedTopLevelAreaItemPosition.value?.takeIf { level == 0 }
                    ?.takeIf { it == adapterPosition }?.let {
                        setSelected(true)
                    }

                privacyDialogViewModel.selectedSecondLevelAreaItemPosition.value?.takeIf { level == 1 }
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