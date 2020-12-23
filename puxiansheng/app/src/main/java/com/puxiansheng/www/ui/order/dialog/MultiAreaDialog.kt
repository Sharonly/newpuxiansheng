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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.LocationNode
import com.puxiansheng.www.R
import com.puxiansheng.www.databinding.*
import com.puxiansheng.www.ui.main.MainViewModel
import kotlinx.android.extensions.LayoutContainer

class MultiAreaDialog : DialogFragment() {

    private lateinit var privacyDialogViewModel: SelectAreaViewModel

    private lateinit var appModel: MainViewModel
    private lateinit var binding: DialogMultAreaBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        privacyDialogViewModel = ViewModelProvider(this)[SelectAreaViewModel::class.java]
        appModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
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
    ): View? = DialogMultAreaBinding.inflate(inflater).apply {
        binding = this
        binding.lifecycleOwner = viewLifecycleOwner
        btCancel.setOnClickListener {
            dismiss()
        }

        btSubmit.setOnClickListener {

        }

        menuListTop.layoutManager = LinearLayoutManager(requireContext())
        menuListTop.adapter = MenuItemListAdapter(mutableListOf(), 0)

        menuListSecond.layoutManager = LinearLayoutManager(requireContext())
        menuListSecond.adapter = SecondMenuItemListAdapter(mutableListOf(), 1)

//        privacyDialogViewModel.selectedTopLevelAreaItem.observe(viewLifecycleOwner, Observer {
//            privacyDialogViewModel.getNewAreaSecondMenuDataFromRemote(it.menuID.toInt())
//        })
//
//
//        privacyDialogViewModel.selectiveTopLevelAreaData.observe(
//            viewLifecycleOwner,
//            Observer { it ->
//                it?.takeIf { it.isNotEmpty() }?.let { list ->
//                    (menuListTop.adapter as SelectNewAreaDialog.MenuItemListAdapter).setMenuData(
//                        list
//                    )
//                }
//            })
//
//        privacyDialogViewModel.selectiveSecondLevelAreaData.observe(
//            viewLifecycleOwner,
//            Observer { it ->
//                if (it.isNullOrEmpty()) menuListSecond.visibility =
//                    View.GONE else menuListSecond.visibility = View.VISIBLE
//
//                it?.takeIf { it.isNotEmpty() }?.let { list ->
//                    (menuListSecond.adapter as SelectNewAreaDialog.MenuItemListAdapter).setMenuData(
//                        list
//                    )
//                }
//            })
//
//        privacyDialogViewModel.selectedTopLevelAreaItemPosition.observe(
//            viewLifecycleOwner,
//            Observer {
//                (menuListTop.adapter as SelectNewAreaDialog.MenuItemListAdapter).notifyDataSetChanged()
//            })
//
//        privacyDialogViewModel.selectedSecondLevelAreaItemPosition.observe(
//            viewLifecycleOwner,
//            Observer {
//                (menuListSecond.adapter as SelectNewAreaDialog.MenuItemListAdapter).notifyDataSetChanged()
//            })


    }.root


    inner class MenuItemListAdapter(
        var list: MutableList<LocationNode>,
        private val level: Int
    ) : RecyclerView.Adapter<MenuItemListAdapter.MenuItemHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemHolder =
            MenuItemHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.dialog_selective_top_menu_item,
                    parent,
                    false
                ),
                level
            )

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: MenuItemHolder, position: Int) =
            holder.bind(list[position], holder.adapterPosition)

        fun setMenuData(menuData: List<LocationNode>) {
            list = menuData.toMutableList()
            notifyDataSetChanged()
        }

        inner class MenuItemHolder(
            override val containerView: View,
            private val level: Int
        ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
            val binding: DialogSelectiveTopMenuItemBinding =
                DialogSelectiveTopMenuItemBinding.bind(containerView)

            fun bind(locationNode: LocationNode, adapterPosition: Int) {
                binding.menuText.text = locationNode.text
                binding.menuText.setOnClickListener {
                    privacyDialogViewModel.selectedLocationNode = locationNode
                    privacyDialogViewModel.selectedLocationNodePosition.postValue(adapterPosition)
                }

                setSelected(false)

                privacyDialogViewModel.selectedLocationNodePosition.value
                    ?.takeIf { it == adapterPosition }?.let {
                        setSelected(true)
                    }
            }

            private fun setSelected(isSelected: Boolean) = if (isSelected) {
                binding.root.setBackgroundColor(Color.parseColor("#fff8f8f8"))
            } else {
                binding.root.setBackgroundColor(Color.parseColor("#FFeeeeee"))
            }
        }
    }


    inner class SecondMenuItemListAdapter(
        var list: MutableList<LocationNode>,
        private val level: Int
    ) : RecyclerView.Adapter<SecondMenuItemListAdapter.SecondMenuItemHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecondMenuItemHolder =
            SecondMenuItemHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.dialog_selective_second_menu_item,
                    parent,
                    false
                ),
                level
            )

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: SecondMenuItemHolder, position: Int) =
            holder.bind(list[position], holder.adapterPosition)

        fun setMenuData(menuData: List<LocationNode>) {
            list = menuData.toMutableList()
            notifyDataSetChanged()
        }

        inner class SecondMenuItemHolder(
            override val containerView: View,
            private val level: Int
        ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
            val binding: DialogSelectiveSecondMenuItemBinding =
                DialogSelectiveSecondMenuItemBinding.bind(containerView)

            fun bind(locationNode: LocationNode, adapterPosition: Int) {
                binding.menuText.text = locationNode.text
                binding.menuText.setOnClickListener {
                    privacyDialogViewModel.selectedLocationNode = locationNode
                    privacyDialogViewModel.selectedLocationNodePosition.postValue(adapterPosition)
                }

                setSelected(false)

                privacyDialogViewModel.selectedLocationNodePosition.value
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
                binding.statusImg.setImageResource(R.mipmap.bg_area_orange_select)
                binding.root.setBackgroundColor(Color.parseColor("#FFFFFF"))
            } else {
                binding.menuText.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        android.R.color.black
                    )
                )
                binding.statusImg.setImageResource(R.mipmap.bg_area_grey_no_select)
                binding.root.setBackgroundColor(Color.parseColor("#fff8f8f8"))
            }
        }
    }

}