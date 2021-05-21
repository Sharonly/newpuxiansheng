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
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.LocationNode
import com.puxiansheng.www.R
import com.puxiansheng.www.databinding.DialogSelectIndustryBinding
import com.puxiansheng.www.databinding.DialogSelectiveMenuItemBinding
import com.puxiansheng.www.tools.SpUtils
import com.puxiansheng.www.ui.main.MainViewModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class SelectAreaDialog(
    private val onSelectArea: (locationNode: LocationNode?,isReset:Boolean) -> Unit
) : DialogFragment() {

    private lateinit var privacyDialogViewModel: SelectAreaViewModel

    private lateinit var appModel: MainViewModel
    private lateinit var binding: DialogSelectIndustryBinding
    var isReset = true

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
    ): View? = DialogSelectIndustryBinding.inflate(inflater).apply {
        binding = this
        binding.lifecycleOwner = viewLifecycleOwner

        submit.setOnClickListener {
            onSelectArea(privacyDialogViewModel.selectedLocationNode,isReset)
            dismiss()
        }

        btReset.setOnClickListener {
            isReset = true
        }

        title.text = "城市"

        menuListTop.layoutManager = LinearLayoutManager(requireContext())
        menuListTop.adapter = MenuItemListAdapter(mutableListOf(), 0)

        privacyDialogViewModel.selectiveAreaData.observe(
            viewLifecycleOwner,
            Observer { it ->
                it?.takeIf { it.isNotEmpty() }?.let { list ->
                    (menuListTop.adapter as MenuItemListAdapter).setMenuData(list)
                }
            })

        privacyDialogViewModel.selectedLocationNodePosition.observe(viewLifecycleOwner, Observer {
            (menuListTop.adapter as MenuItemListAdapter).notifyDataSetChanged()
        })
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        privacyDialogViewModel.requestRemoteCitiesByParentID(SpUtils.get(API.USER_CITY_ID,0).toString())
    }

    inner class MenuItemListAdapter(
        var list: MutableList<LocationNode>,
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

        fun setMenuData(menuData: List<LocationNode>) {
            list = menuData.toMutableList()
            notifyDataSetChanged()
        }

        inner class MenuItemHolder(
            override val containerView: View,
            private val level: Int
        ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
            val binding: DialogSelectiveMenuItemBinding =
                DialogSelectiveMenuItemBinding.bind(containerView)

            fun bind(locationNode: LocationNode, adapterPosition: Int) {
                binding.menuText.text = locationNode.text
                binding.menuText.setOnClickListener {
                    privacyDialogViewModel.selectedLocationNode = locationNode
                    privacyDialogViewModel.selectedLocationNodePosition.postValue(adapterPosition)
                    isReset = false
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