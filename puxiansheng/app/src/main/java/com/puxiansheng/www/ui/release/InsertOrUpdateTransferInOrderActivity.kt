package com.puxiansheng.www.ui.release

import android.util.Log
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.LocationNode
import com.puxiansheng.logic.bean.MenuItem
import com.puxiansheng.util.ext.MyScreenUtil
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.ui.order.dialog.*
import com.puxiansheng.www.ui.release.adapter.MultiAreaAdapter
import com.puxiansheng.www.ui.release.dialog.ReleaseDialog
import kotlinx.android.synthetic.main.activity_release_order_transfer_in.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class InsertOrUpdateTransferInOrderActivity : MyBaseActivity() {
    private lateinit var insertOrUpdateTransferInOrderViewModel: InsertOrUpdateTransferInOrderViewModel
    private var facilist = mutableSetOf<MenuItem>()
    private var areaAdapter: MultiAreaAdapter? = null
    private var areaLists: ArrayList<LocationNode> = arrayListOf()

    override fun getLayoutId(): Int {
        MyScreenUtil.setStateBarStyle(this,true,R.color.color81,true)
        return R.layout.activity_release_order_transfer_in
    }

    override fun business() {
        insertOrUpdateTransferInOrderViewModel =
            ViewModelProvider(this)[InsertOrUpdateTransferInOrderViewModel::class.java]
        initView()
    }


    private fun initView() {
        button_back.setOnClickListener {
            onBackPressed()
        }

        insertOrUpdateTransferInOrderViewModel.contactName =
            SharedPreferencesUtil.get(API.LOGIN_ACTUL_NAME, "").toString()
        insertOrUpdateTransferInOrderViewModel.contactPhone =
            SharedPreferencesUtil.get(API.LOGIN_ACTUL_PHONE, "").toString()

        input_name.setText(insertOrUpdateTransferInOrderViewModel.contactName)
        input_phone.setText(insertOrUpdateTransferInOrderViewModel.contactPhone)

        input_name.addTextChangedListener {
            insertOrUpdateTransferInOrderViewModel.contactName = it.toString()
        }
        input_phone.addTextChangedListener {
            insertOrUpdateTransferInOrderViewModel.contactPhone = it.toString()
        }

        list_facilities.layoutManager = GridLayoutManager(this, 6)
        list_facilities.adapter = ReleaseFacilityAdapter(mutableListOf())

        submit.setOnClickListener {
            insertOrUpdateTransferInOrderViewModel.title.let {
                if (it.isEmpty()) {
                    Toast.makeText(this, "请输入发布标题！", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            insertOrUpdateTransferInOrderViewModel.size.let {
                if (it.isEmpty()) {
                    Toast.makeText(this, "请选择面积！", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            insertOrUpdateTransferInOrderViewModel.rent.let {
                if (it.isEmpty()) {
                    Toast.makeText(this, "请选择租金！", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            insertOrUpdateTransferInOrderViewModel.area.let {
                if (it.isEmpty()) {
                    Toast.makeText(this, "请选择区域！", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }
            Log.d(
                "---submit--",
                " insertOrUpdateTransferInOrderViewModel.area = " + insertOrUpdateTransferInOrderViewModel.area
            )

            insertOrUpdateTransferInOrderViewModel.industry.let {
                if (it.isEmpty()) {
                    Toast.makeText(this, "请选择行业！", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }



            insertOrUpdateTransferInOrderViewModel.contactName.let {
                if (it.isEmpty()) {
                    Toast.makeText(this, "请输入联系人！", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }
            insertOrUpdateTransferInOrderViewModel.contactPhone.let {
                if (it.isEmpty()) {
                    Toast.makeText(this, "请输入联系电话！", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            insertOrUpdateTransferInOrderViewModel.submit()
        }

        input_title.addTextChangedListener {
            insertOrUpdateTransferInOrderViewModel.title = it.toString()
        }


        input_description.addTextChangedListener {
            insertOrUpdateTransferInOrderViewModel.description = it.toString()
        }

        //选择租金范围
        button_select_rent.setOnClickListener {
            SelectRentRangeDialog(onSelectRent = {it,isReset ->
                it?.let { rent ->
                    button_select_rent.setText(rent.text)
                    insertOrUpdateTransferInOrderViewModel.rent = rent.menuID.toString()
                }
            }).show(
                supportFragmentManager,
                SelectRentRangeDialog::class.java.name
            )
        }


        button_select_size.setOnClickListener {
            SelectSizeRangeDialog(onSelectSize = {
                it?.let { size ->
                    button_select_size.text = size.text
                    insertOrUpdateTransferInOrderViewModel.size = size.menuID.toString()
                }

            }).show(supportFragmentManager, SelectSizeRangeDialog::class.java.name)
        }

        //选择行业
        button_select_industry.setOnClickListener {
            SelectIndustryDialog(onSelectIndustry = { topLevelItem, secondLevelItem ->
                insertOrUpdateTransferInOrderViewModel.industry =
                    "${topLevelItem?.menuID ?: 0},${secondLevelItem?.menuID ?: 0}"
                button_select_industry.text =
                    "${topLevelItem?.text ?: "所有行业"} - ${secondLevelItem?.text ?: "所有类型"}"
            }).show(
                supportFragmentManager,
                SelectIndustryDialog::class.java.name
            )
        }
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        area_list.layoutManager = linearLayoutManager
        areaAdapter = MultiAreaAdapter(arrayListOf())
        area_list.adapter = areaAdapter
        areaAdapter?.listener = object : MultiAreaAdapter.OnDeleteListener {
            override fun onDelete(menuItem: LocationNode) {
                areaLists.remove(menuItem)
                if (areaLists.size > 0) {
                    var sb = StringBuilder()
                    areaLists.forEach { item ->
                        if (item.nodeID != 0) {
                            sb.append(item.nodeID).append(",")
                        } else {
                            sb.append(item.pId).append(",")
                        }
                    }
                    insertOrUpdateTransferInOrderViewModel.area = sb.substring(0, sb.lastIndex)
                }else{
                    insertOrUpdateTransferInOrderViewModel.area = ""
                }
            }
        }

        button_select_area.setOnClickListener {
//            SelectAreaDialog(onSelectArea = {
//                it?.let { locationNode ->
//                    button_select_area.text = locationNode.text
//                    insertOrUpdateTransferInOrderViewModel.area = locationNode.nodeID.toString()
//                }
//            }).show(
//                supportFragmentManager,
//                SelectAreaDialog::class.java.name
//            )
//            if (areaLists.size < 10) {
//                SelectNewAreaDialog(onSelectArea = { topMenuItem, secondMenuItem ->
//                button_select_area.text = topMenuItem?.btText
//                    insertOrUpdateTransferInOrderViewModel.area =
//                        "${topMenuItem?.menuID ?: 0},${secondMenuItem?.menuID ?: 0}"
//                button_select_area.text =
//                    "${topMenuItem?.text ?: "所有城市"} - ${secondMenuItem?.text ?: "所有地区"}"
//                    var areaObject = AreaObject(topMenuItem, secondMenuItem)
//                    areaLists.add(areaObject)
//                    areaAdapter?.add(areaObject)
//                }).show(
//                    supportFragmentManager,
//                    SelectNewAreaDialog::class.java.name
//                )

            MultiAreaDialog(insertOrUpdateTransferInOrderViewModel.area, onSelectArea = {
                areaAdapter?.addList(it, true)
                areaLists = it
                val sb = StringBuilder()
                it.forEach { menuItem ->
                    if (menuItem.nodeID != 0) {
                        sb.append(menuItem.nodeID).append(",")
                    } else {
                        sb.append(menuItem.pId).append(",")
                    }
                }
                insertOrUpdateTransferInOrderViewModel.area = sb.substring(0, sb.lastIndex)
            }).show(supportFragmentManager, MultiAreaDialog::class.java.name)

        }

        //获取设施
        bt_select_facilities.setOnClickListener {
            SelectFacilityDialog(onSelectFacilities = {
                it.forEach { menuItem ->
                    facilist.add(menuItem)
                    insertOrUpdateTransferInOrderViewModel.facilities.postValue(facilist)
                }
                (list_facilities.adapter as ReleaseFacilityAdapter).setMenuData(it.toMutableSet())
                list_facilities.removeAllViews()
                val sb = StringBuilder()
                it.forEach { menuItem ->
                    sb.append(menuItem.menuID).append(",")
                    insertOrUpdateTransferInOrderViewModel.facility = sb.substring(0, sb.lastIndex)
                }
            }).show(
                supportFragmentManager,
                SelectFacilityDialog::class.java.name
            )
        }


        insertOrUpdateTransferInOrderViewModel.selectiveFacilityMenuData.observe(this, Observer {
            it?.takeIf { it.isNotEmpty() }?.let { list ->
                (list_facilities.adapter as ReleaseFacilityAdapter).setMenuData(list.toMutableSet())
            }
        })


        insertOrUpdateTransferInOrderViewModel.selectiveAreaMenuData.observe(this, Observer {
            it?.takeIf { it.isNotEmpty() }?.let { list ->
                areaAdapter?.addList(list as ArrayList<LocationNode>, true)
                val sb = StringBuilder()
                it.forEach { menuItem ->
                    if (menuItem.nodeID != 0) {
                        sb.append(menuItem.nodeID).append(",")
                    } else {
                        sb.append(menuItem.pId).append(",")
                    }
                }
                insertOrUpdateTransferInOrderViewModel.area = sb.substring(0, sb.lastIndex)
            }
        })

        input_floor.addTextChangedListener {
            if (it.toString().toInt() != 0) {
                insertOrUpdateTransferInOrderViewModel.floor = it.toString().toInt()
            }
        }

        input_description.addTextChangedListener {
            insertOrUpdateTransferInOrderViewModel.description = it.toString()
        }

        insertOrUpdateTransferInOrderViewModel.toastMsg.observe(this, Observer {
//            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            if (it.contains("保存成功")) {
                ReleaseDialog(0, it).show(supportFragmentManager, ReleaseDialog::class.java.name)
            } else if (it.contains("发布成功")) {
                ReleaseDialog(1, it).show(supportFragmentManager, ReleaseDialog::class.java.name)
            } else {
                ReleaseDialog(2, it).show(supportFragmentManager, ReleaseDialog::class.java.name)
            }
        })

        insertOrUpdateTransferInOrderViewModel.submitResult.observe(this, Observer {
            if (it != API.CODE_SUCCESS) {
//                ReleaseDialog(2,"").show(supportFragmentManager, ReleaseDialog::class.java.name)
            }
        })

        intent?.getStringExtra("shopID").let { shopID ->
            if (shopID != "null" || shopID != "0") {
                lifecycleScope.launch {
                    shopID?.let {
                        insertOrUpdateTransferInOrderViewModel.requestEditTransferInOrderDetail(it)
                            ?.let { order ->
                                //编辑获取
                                order.shopID.toString().let { id ->
                                    insertOrUpdateTransferInOrderViewModel.type = id
                                }

                                order.title?.let { title ->
                                    input_title.setText(title)
                                    insertOrUpdateTransferInOrderViewModel.title = title
                                }

//                                order.rent.toString().let { rent ->
////                                    insertOrUpdateTransferInOrderViewModel.rent = rent
//
//                                }

                                order.rentUnitId.toString().let { rentId ->
                                    insertOrUpdateTransferInOrderViewModel.rent = rentId

                                }



                                order.industry?.let { industry ->
                                    insertOrUpdateTransferInOrderViewModel.industry = industry
                                }

                                order.formattedFinalIndustry?.let { finalIndustry ->
                                    button_select_industry.text = finalIndustry
                                }

                                order.address?.let { addressObj ->
                                    addressObj.locationNodes?.let { locationNodes ->
                                        if (locationNodes.size > 0) {
                                            insertOrUpdateTransferInOrderViewModel.area =
                                                locationNodes[locationNodes.size - 1].nodeID.toString()
                                        } else insertOrUpdateTransferInOrderViewModel.area = ""
                                    }
                                }


                                order.shopOwnerName.let {
                                    input_name.setText(it)
                                    insertOrUpdateTransferInOrderViewModel.contactName = it
                                }

                                order.shopOwnerPhoneNumbr.let {
                                    input_phone.setText(it)
                                    insertOrUpdateTransferInOrderViewModel.contactPhone = it
                                }

                                order.formattedRent?.let { finalLocationNode ->
                                    button_select_rent.text = finalLocationNode
                                }
//                                order.formattedFinalLocationNode?.let { finalLocationNode ->
//                                    button_select_area.text = finalLocationNode
//                                }
                                order.area_multiple?.let { it ->
                                    insertOrUpdateTransferInOrderViewModel.area = it
                                    if(it.isNotEmpty()) {
                                        var areaNumList = it.split(",")
                                        if(areaNumList.isNotEmpty()) {
                                            checkMultiArea(areaNumList, it)
                                        }
                                    }
                                }

                                order.floor?.let {
                                    if (it != 0) {
                                        input_floor.setText(it.toString())
                                    }
                                    insertOrUpdateTransferInOrderViewModel.floor = it
                                }

                                order.facilities?.let {
                                    Log.d("---facilities --", "  it.size = " + it.size)
                                    list_facilities.removeAllViews()
                                    val sb = StringBuilder()
                                    it.forEach { menuItem ->
                                        facilist.add(menuItem)
                                        insertOrUpdateTransferInOrderViewModel.facilities.postValue(
                                            facilist
                                        )
                                        sb.append(menuItem.menuID).append(",")
                                        insertOrUpdateTransferInOrderViewModel.facility =
                                            sb.substring(0, sb.lastIndex)
                                    }
                                    (list_facilities.adapter as ReleaseFacilityAdapter).setMenuData(
                                        it.toMutableSet()
                                    )
                                }

                                order.description?.let { description ->
                                    input_description.setText(description)
                                    insertOrUpdateTransferInOrderViewModel.description = description
                                }

                                order.rentUnitId.toString().let { size ->
                                    insertOrUpdateTransferInOrderViewModel.size = size
                                }

                                order.formattedSize?.let { size ->
                                    button_select_size.setText(size)
                                }
                            }
                    }
                }
            } else {
//                var phone = SharedPreferencesUtil.get(
//                    API.LOGIN_ACTUL_PHONE, ""
//                ).toString()
//                insertOrUpdateTransferInOrderViewModel.contactPhone = phone

            }
        }


//        API.logoutSignal.observe(this, Observer {
//            if (it == API.CODE_REQUIRE_LOGIN ||
//                it == API.CODE_AUTO_CODE_INVALID ||
//                it == API.CODE_AUTO_CODE_EXPIRED ||
//                it == API.CODE_AUTO_CODE_ERROR ||
//                it == API.CODE_AUTO_CODE_EMPTY ||
//                it == API.CODE_BANNED_USER
//            ) {
//                if(appViewModel?.lastFragment is ReleaseFragment){
//                    Navigation.findNavController(
//                        requireActivity(),
//                        R.id.homeNavHost
//                    ).navigate(R.id.action_insertOrUpdateTransferInOrderFragment_to_loginFragment)
//                }
//            }
//        })
    }


//    override fun onResume() {
//        super.onResume()
//        MobclickAgent.onPageStart("InsertOrUpdateTransferInOrderActivity") //统计页面，"MainScreen"为页面名称，可自定义
//    }
//
//    override fun onPause() {
//        super.onPause()
//        MobclickAgent.onPageEnd("InsertOrUpdateTransferInOrderActivity")
//    }


    private fun checkMultiArea(numlist: List<String>, ids: String) {
        Log.d("---checkMultiArea --", "  ids  = " +ids)
        lifecycleScope.launch {
            insertOrUpdateTransferInOrderViewModel.getMultiAreaMenuDataFromRemote(ids = ids)
                ?.let { list ->
                    numlist.forEach { num ->
                        list.forEach { fristCity ->
                            if (num == (fristCity.nodeID.toString())) {
                                Log.d("---checkMultiArea --", "  fristCity name = " +fristCity.text+"   nodeId = "+fristCity.nodeID)
                                areaLists.add(fristCity)
                            } else {
                                fristCity.children?.forEach { secondCity ->
                                    if (num == (secondCity.nodeID.toString())) {
                                        Log.d("---checkMultiArea --", "secondCity name = " +secondCity.text+"   nodeId = "+secondCity.nodeID+"  pid= "+secondCity.pId)
                                        areaLists.add(secondCity)
                                    }
                                }
                            }
                        }
                    }
                    areaAdapter?.addList(areaLists,true)
                }
        }
    }

}