package com.puxiansheng.www.ui.order.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
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
import com.puxiansheng.logic.bean.LocationNode
import com.puxiansheng.www.R
import com.puxiansheng.www.databinding.*
import com.puxiansheng.www.ui.main.MainViewModel
import kotlinx.android.extensions.LayoutContainer

class MultiAreaDialog(
    var ids: String,
    private val onSelectArea: (areas: ArrayList<LocationNode>) -> Unit
) : DialogFragment() {

    private lateinit var viewModel: SelectNewAreaViewModel
    private lateinit var appModel: MainViewModel
    private lateinit var binding: DialogMultAreaBinding
    private var allCityData: ArrayList<LocationNode>? = arrayListOf()
    private var selectCity: ArrayList<LocationNode>? = arrayListOf()

    private var topAdapter: MenuItemListAdapter? = null
    private var sendAdapter: SecondMenuItemListAdapter? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[SelectNewAreaViewModel::class.java]
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
            getSelectAreaList()?.let { it1 -> onSelectArea(it1) }
            dismiss()

        }

        menuListTop.layoutManager = LinearLayoutManager(requireContext())
        topAdapter = MenuItemListAdapter(mutableListOf(), 0)
        menuListTop.adapter = topAdapter

        menuListSecond.layoutManager = LinearLayoutManager(requireContext())
        sendAdapter = SecondMenuItemListAdapter(mutableListOf(), 1)
        menuListSecond.adapter = sendAdapter

        viewModel.selectiveTopLevelAreaData.observe(
            viewLifecycleOwner,
            Observer { it ->
                it?.takeIf { it.isNotEmpty() }?.let { list ->
                    var areaNumList = ids.split(",")
                    if (areaNumList.isNotEmpty()) {
                        list.forEach { it1 ->
                            var count = 0
                            areaNumList.forEach { num ->
                                if (num == (it1?.nodeID!!.toString())) {
                                    count++
                                }
                            }
                            Log.d(
                                "MultiAreaDialog ,",
                                " locationNode.name =" + it1.text + " count = " + count + " ids = " + ids
                            )
                            var beanlist: ArrayList<LocationNode>? = arrayListOf()
                            var default = LocationNode()
                            default.nodeID = 0
                            default.pId = it1?.nodeID!!
                            default.text = "不限"
                            if (count > 0) {
                                default.isSelect = 1
                            } else {
                                default.isSelect = 0
                            }
                            Log.d(
                                "MultiAreaDialog ,",
                                " locationNode.name =" + it1.text + "  id = " + it1.nodeID
                            )

                            Log.d(
                                "MultiAreaDialog ,",
                                " default.name =" + default.text + "   default - pid = " + default.pId + "  select = " + default.isSelect
                            )
                            beanlist?.add(default)
                            it1.children?.let { it2 -> beanlist?.addAll(it2) }
                            it1.children = beanlist
                            allCityData?.add(it1)
                        }
                    } else {
                        list.forEach { it1 ->
                            var beanlist: ArrayList<LocationNode>? = arrayListOf()
                            var default = LocationNode()
                            default.nodeID = 0
                            default.pId = it1?.nodeID!!
                            default.text = "不限"
                            default.isSelect = 0
                            beanlist?.add(default)
                            it1.children?.let { it2 -> beanlist?.addAll(it2) }
                            it1.children = beanlist
                            allCityData?.add(it1)
                        }
                    }
                    allCityData?.let { it1 ->
                        (menuListTop.adapter as MenuItemListAdapter).setMenuData(
                            it1
                        )
                    }
                    viewModel.selectedTopLevelAreaItemPosition.postValue(0)
                }
            })

        viewModel.selectedTopLevelAreaItemPosition.observe(this@MultiAreaDialog, Observer {
            var secondCityList: ArrayList<LocationNode>? = arrayListOf()
            secondCityList = allCityData?.get(it)?.children as ArrayList<LocationNode>?
            if (secondCityList != null) {
                (menuListSecond.adapter as SecondMenuItemListAdapter).setMenuData(secondCityList)
            }
        })

        viewModel.getMultiAreaMenuDataFromRemote(ids)

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

        fun notifyCount(pid: Int) {
            var count = 0
            var bean: LocationNode? = null
            allCityData?.forEach { it1 ->
                if (it1.nodeID == pid) {
                    bean = it1
                    if (it1.children != null) {
                        it1.children?.forEach { it2 ->
                            if (it2.isSelect == 1) {
                                count += 1
                            }
                        }
                    }
                }
            }
            bean?.children_selected_count = count
            notifyDataSetChanged()
        }

        inner class MenuItemHolder(
            override val containerView: View,
            private val level: Int
        ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
            val binding: DialogSelectiveTopMenuItemBinding =
                DialogSelectiveTopMenuItemBinding.bind(containerView)

            fun bind(menuItem: LocationNode, adapterPosition: Int) {
                binding.menuText.text = menuItem.text
                if(menuItem.isSelect == 0) {
                    if (menuItem.children_selected_count > 0) {
                        binding.num.visibility = View.VISIBLE
                        binding.num.text = menuItem.children_selected_count.toString()
                    } else {
                        binding.num.visibility = View.GONE
                    }
                }else{
                    binding.num.visibility = View.VISIBLE
                    binding.num.text = menuItem.isSelect.toString()
                }
                binding.root.setOnClickListener {
                    viewModel.selectedTopLevelAreaItem.postValue(menuItem)
                    viewModel.selectedTopLevelAreaItemPosition.postValue(adapterPosition)
                    viewModel.selectedSecondLevelAreaItemPosition.postValue(
                        -1
                    )
                }

                viewModel.selectedTopLevelAreaItemPosition.observe(this@MultiAreaDialog, Observer {
                    if (it == adapterPosition) {
                        setSelected(true)
                    } else {
                        setSelected(false)
                    }
                })

            }

            private fun setSelected(isSelected: Boolean) = if (isSelected) {
                binding.root.setBackgroundColor(Color.parseColor("#f8f8f8"))
            } else {

                binding.root.setBackgroundColor(Color.parseColor("#eeeeee"))
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
                if (locationNode.isSelect == 1) {
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
                    binding.root.setBackgroundColor(Color.parseColor("#f8f8f8"))
                }
                binding.statusImg.setOnClickListener {
                    if (locationNode.isSelect == 1) {
                        locationNode.isSelect = 0
                        binding.menuText.setTextColor(
                            ContextCompat.getColor(
                                requireContext(),
                                android.R.color.black
                            )
                        )
                        binding.statusImg.setImageResource(R.mipmap.bg_area_grey_no_select)
                        binding.root.setBackgroundColor(Color.parseColor("#f8f8f8"))
                    } else {
                        if (getSelectAreaList()?.size!! < 10) {
                            clearSomeSelect(adapterPosition)
                            locationNode.isSelect = 1
                            binding.menuText.setTextColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.appMain
                                )
                            )
                            binding.statusImg.setImageResource(R.mipmap.bg_area_orange_select)
                            binding.root.setBackgroundColor(Color.parseColor("#FFFFFF"))
                        } else {
                            Toast.makeText(requireContext(), "最多可选择10个城市哦", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    topAdapter?.notifyCount(locationNode.pId)
                }
            }


            private fun clearSomeSelect(position: Int) {
                if (position == 0) {
                    for (index in 0 until list.size) {
                        var locationNode = list[index]
                        if (index == 0) {
                            locationNode.isSelect = 1
                            Log.d(
                                "MultiAreaDialog ,",
                                " locationNode.name =" + locationNode.text + "   pid = " + locationNode.pId + "  id = " + locationNode.nodeID
                            )
                        } else {
                            locationNode.isSelect = 0
                        }
                    }
                } else {
                    for (index in 0 until list.size) {
                        var locationNode = list[index]
                        if (index == 0) {
                            locationNode.isSelect = 0
                        }
                    }
                }
                notifyDataSetChanged()
            }
        }
    }


    private fun getSelectAreaList(): ArrayList<LocationNode>? {
        selectCity?.clear()
        allCityData?.forEach { it1 ->
            if (it1.children != null) {
                it1.children?.forEach { it2 ->
                    if (it2.isSelect == 1) {
                        if (it2.nodeID == 0) {
                            selectCity?.add(it1)
                        } else {
                            selectCity?.add(it2)
                        }
                        Log.d("MultiAreaDialog", "city  name = " + it2.text)
                    }
                }
            }
        }
        Log.d("MultiAreaDialog", "selectCity size = " + selectCity?.size)
        return selectCity
    }

}