package com.puxiansheng.www.ui.release

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.MapAddress
import com.puxiansheng.logic.bean.MenuItem
import com.puxiansheng.logic.util.GlideImageEngine
import com.puxiansheng.www.databinding.FragmentTransferOrderImgSelectorAddItemBinding
import com.puxiansheng.www.databinding.FragmentTransferOrderImgSelectorItemBinding
import com.puxiansheng.www.ui.main.MainViewModel
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.internal.entity.CaptureStrategy
import kotlinx.android.extensions.LayoutContainer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.logic.util.LiveDataBus
import com.puxiansheng.util.ext.MyScreenUtil
import com.puxiansheng.util.ext.PermissionUtils
import com.puxiansheng.www.common.url
import com.puxiansheng.www.tools.SpUtils
import com.puxiansheng.www.tools.UMengKeys
import com.puxiansheng.www.ui.map.GetLocationActivity
import com.puxiansheng.www.ui.mine.ServiceActivity
import com.puxiansheng.www.ui.order.dialog.*
import com.puxiansheng.www.ui.release.dialog.ReleaseDialog
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.activity_relase_order_transfer_out.*
import kotlinx.android.synthetic.main.activity_relase_order_transfer_out.bt_my_kefu
import kotlinx.android.synthetic.main.activity_relase_order_transfer_out.bt_select_rent
import kotlinx.android.synthetic.main.activity_relase_order_transfer_out.button_back
import kotlinx.android.synthetic.main.activity_relase_order_transfer_out.button_select_area
import kotlinx.android.synthetic.main.activity_relase_order_transfer_out.button_select_industry
import kotlinx.android.synthetic.main.activity_relase_order_transfer_out.button_select_size
import kotlinx.android.synthetic.main.activity_relase_order_transfer_out.edit_user_address
import kotlinx.android.synthetic.main.activity_relase_order_transfer_out.imageSelector
import kotlinx.android.synthetic.main.activity_relase_order_transfer_out.input_description
import kotlinx.android.synthetic.main.activity_relase_order_transfer_out.input_enverment
import kotlinx.android.synthetic.main.activity_relase_order_transfer_out.input_fee
import kotlinx.android.synthetic.main.activity_relase_order_transfer_out.input_name
import kotlinx.android.synthetic.main.activity_relase_order_transfer_out.input_phone
import kotlinx.android.synthetic.main.activity_relase_order_transfer_out.input_reason
import kotlinx.android.synthetic.main.activity_relase_order_transfer_out.input_title
import kotlinx.android.synthetic.main.activity_relase_order_transfer_out.submit
import kotlinx.android.synthetic.main.activity_release_order_transfer_in.*
import java.text.DecimalFormat

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class InsertOrUpdateTransferOutOrderActivity : MyBaseActivity() {
    private val requestCodePermissions = 10
    private val requiredPermissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.CAMERA
    )
    private lateinit var insertOrUpdateTransferOutOrderViewModel: InsertOrUpdateTransferOutOrderViewModel
    private lateinit var appViewModel: MainViewModel
    private var facilist = mutableSetOf<MenuItem>()
    private var indulist = mutableSetOf<MenuItem>()

    override fun getLayoutId(): Int {
        MyScreenUtil.setStateBarStyle(this,true,R.color.bg_order_list,true)
        return R.layout.activity_relase_order_transfer_out
    }

    override fun business() {
        PermissionUtils.requestPermission(this@InsertOrUpdateTransferOutOrderActivity, requiredPermissions)
//        ActivityCompat.requestPermissions(
//            this,
//            requiredPermissions,
//            requestCodePermissions
//        )
        insertOrUpdateTransferOutOrderViewModel =
            ViewModelProvider(this)[InsertOrUpdateTransferOutOrderViewModel::class.java]
        appViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        initView()
        initData()
    }


    private fun initData(){
        lifecycleScope.launch {
            insertOrUpdateTransferOutOrderViewModel.getConfigInfo("api_kf_url")?.let { configInfo ->
                bt_my_kefu.setOnClickListener {
                    val intent = Intent(this@InsertOrUpdateTransferOutOrderActivity, ServiceActivity::class.java)
                    intent.putExtra("title", "我的客服")
                    intent.putExtra("url", configInfo)
                    startActivity(intent)
                    MobclickAgent.onEvent(this@InsertOrUpdateTransferOutOrderActivity, UMengKeys.PAGE_NAME, "ServiceActivity")
                }
            }
        }

    }

    private fun initView() {
        button_back.setOnClickListener {
            onBackPressed()
        }

        insertOrUpdateTransferOutOrderViewModel.contactName =
            SpUtils.get(API.LOGIN_ACTUL_NAME, "").toString()
        insertOrUpdateTransferOutOrderViewModel.contactPhone =
            SpUtils.get(API.LOGIN_ACTUL_PHONE, "").toString()

        input_name.setText(insertOrUpdateTransferOutOrderViewModel.contactName)
        input_phone.setText(insertOrUpdateTransferOutOrderViewModel.contactPhone)

        input_name.addTextChangedListener {
            insertOrUpdateTransferOutOrderViewModel.contactName = it.toString()
        }
        input_phone.addTextChangedListener {
            insertOrUpdateTransferOutOrderViewModel.contactPhone = it.toString()
        }

        submit.setOnClickListener {
            insertOrUpdateTransferOutOrderViewModel.title.let {
                if (it.isEmpty()) {
                    Toast.makeText(this, "请输入发布标题！", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            insertOrUpdateTransferOutOrderViewModel.size.let {
                if (it.isEmpty()) {
                    Toast.makeText(this, "请输入店铺面积！", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            insertOrUpdateTransferOutOrderViewModel.fee.let {
                if (it.isEmpty()) {
                    Toast.makeText(this, "请输入转让费！", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            insertOrUpdateTransferOutOrderViewModel.rent.let {
                if (it.isEmpty()) {
                    Toast.makeText(this, "请输入转让租金！", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            insertOrUpdateTransferOutOrderViewModel.address.let {
                if (it.isEmpty()) {
                    Toast.makeText(this, "请输入详细地址！", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

//            insertOrUpdateTransferOutOrderViewModel.lat.let {
//                if (it == 0.0) {
//                    getLngAndLat(this)
//                    Toast.makeText(this, "需要获取您的定位！", Toast.LENGTH_SHORT).show()
//                    return@setOnClickListener
//                }
//            }
//
//            insertOrUpdateTransferOutOrderViewModel.lng.let {
//                if (it == 0.0) {
//                    Toast.makeText(this, "需要获取您的定位！", Toast.LENGTH_SHORT).show()
//                    return@setOnClickListener
//                }
//            }


            insertOrUpdateTransferOutOrderViewModel.industry.let {
                if (it.isEmpty()) {
                    Toast.makeText(this, "请选择行业类型！", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            insertOrUpdateTransferOutOrderViewModel.area.let {
                if (it.isEmpty()) {
                    Toast.makeText(this, "请选择城市区域！", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            insertOrUpdateTransferOutOrderViewModel.contactName.let {
                if (it.isEmpty()) {
                    Toast.makeText(this, "请输入联系人！", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }
            insertOrUpdateTransferOutOrderViewModel.contactPhone.let {
                if (it.isEmpty()) {
                    Toast.makeText(this, "请输入联系电话！", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }
            insertOrUpdateTransferOutOrderViewModel.submit()
        }

        insertOrUpdateTransferOutOrderViewModel.type = intent.getStringExtra("shopID")

        input_title.addTextChangedListener {
            insertOrUpdateTransferOutOrderViewModel.title = it.toString()
        }

        button_select_industry.setOnClickListener {
            SelectIndustryDialog(onSelectIndustry = { topLevelItem, secondLevelItem ->
                topLevelItem?.let { indulist.add(topLevelItem) }
                secondLevelItem?.let { indulist.add(secondLevelItem) }
                insertOrUpdateTransferOutOrderViewModel.industries.postValue(indulist)
                insertOrUpdateTransferOutOrderViewModel.industry =
                    "${topLevelItem?.menuID ?: 0},${secondLevelItem?.menuID ?: 0}"
                button_select_industry.text =
                    "${topLevelItem?.text ?: "所有行业"} - ${secondLevelItem?.text ?: "所有类型"}"
            }).show(
                supportFragmentManager,
                SelectIndustryDialog::class.java.name
            )
        }

//        button_select_size.setOnClickListener {
//            SelectSizeRangeDialog(
//                onSelectSize = {
//                    it?.let { size ->
//                        button_select_size.text = size.text
//                        insertOrUpdateTransferOutOrderViewModel.size = size.menuID.toString()
//                    }
//                }
//            ).show(supportFragmentManager, SelectSizeRangeDialog::class.java.name)
//        }

//        bt_select_rent.setOnClickListener {
//            SelectRentRangeDialog(onSelectRent = {
//                it?.let { rent ->
//                    bt_select_rent.text = rent.text
//                    insertOrUpdateTransferOutOrderViewModel.rent = rent.menuID.toString();
//                }
//            }).show(
//                supportFragmentManager,
//                SelectRentRangeDialog::class.java.name
//            )
//        }

        button_select_size.addTextChangedListener {
            insertOrUpdateTransferOutOrderViewModel.size = it.toString()
        }

        bt_select_rent.addTextChangedListener {
            insertOrUpdateTransferOutOrderViewModel.rent = it.toString()
        }

        input_fee.addTextChangedListener {
            insertOrUpdateTransferOutOrderViewModel.fee = it.toString()
        }

        edit_user_address.addTextChangedListener {
            insertOrUpdateTransferOutOrderViewModel.address = it.toString()
        }


        button_select_state.setOnClickListener {
            SelectStateRangeDialog(onSelectState = {
                it?.let { state ->
                    button_select_state.text = state
                    insertOrUpdateTransferOutOrderViewModel.state = if (state == "营业中") 1 else 0
                }
            }).show(
                supportFragmentManager,
                SelectStateRangeDialog::class.java.name
            )
        }

        button_select_empty_transfer.setOnClickListener {
            SelectCanEmptyDialog(onSelectCanEmpty = {
                it?.let {
                    button_select_empty_transfer.text = it
                    if (it == "是") {
                        insertOrUpdateTransferOutOrderViewModel.exclusive = 1
                    } else {
                        insertOrUpdateTransferOutOrderViewModel.exclusive = 0
                    }
                }
            }).show(supportFragmentManager, SelectCanEmptyDialog::class.java.name)
        }

        button_select_area.setOnClickListener {
//            SelectAreaDialog(onSelectArea = {
//                it?.let { locationNode ->
//                    insertOrUpdateTransferOutOrderViewModel.saveArea.postValue(locationNode)
//                    button_select_area.text = locationNode.text
//                }
//            }).show(
//                supportFragmentManager,
//                SelectAreaDialog::class.java.name
//            )
            SelectNewAreaDialog(onSelectArea = { topMenuItem, secondMenuItem ->
                button_select_area.text = topMenuItem?.btText
                insertOrUpdateTransferOutOrderViewModel.area =
                    "${topMenuItem?.nodeID ?: 0},${secondMenuItem?.nodeID ?: 0}"
                button_select_area.text =
                    "${topMenuItem?.text ?: "所有城市"} - ${secondMenuItem?.text ?: "所有地区"}"
            }).show(
                supportFragmentManager,
                SelectNewAreaDialog::class.java.name
            )
        }

        LiveDataBus.get().with("Map", MapAddress::class.java)?.observe(this, Observer {
            edit_user_address.setText(it.addressDesc)
            var df = DecimalFormat("#.000");
            insertOrUpdateTransferOutOrderViewModel.address = it.addressDesc
            insertOrUpdateTransferOutOrderViewModel.lat = df.format(it.lat).toDouble()
            insertOrUpdateTransferOutOrderViewModel.lng = df.format(it.lng).toDouble()
        })

        button_select_address.setOnClickListener {
            var location = getLngAndLat(this)
            //  edit_user_address.inputType = InputType.TYPE_NULL
            val intent = Intent(this, GetLocationActivity::class.java)
            intent.putExtra("location", location)
            startActivity(intent)
        }


        //获取设施
        button_select_facilities.setOnClickListener {
            SelectFacilityDialog(onSelectFacilities = {
                it.forEach { menuItem ->
                    facilist.add(menuItem)
                    insertOrUpdateTransferOutOrderViewModel.facilities.postValue(facilist)
                }
                (facilities_list.adapter as ReleaseFacilityAdapter).setMenuData(it.toMutableSet())
                facilities_list.removeAllViews()
                val sb = StringBuilder()
                it.forEach { menuItem ->
                    sb.append(menuItem.menuID).append(",")
                    insertOrUpdateTransferOutOrderViewModel.facility = sb.substring(0, sb.lastIndex)
                }
            }).show(
                supportFragmentManager,
                SelectFacilityDialog::class.java.name
            )

        }

        facilities_list.layoutManager = GridLayoutManager(this, 6)
        facilities_list.adapter = ReleaseFacilityAdapter(arrayListOf())

        insertOrUpdateTransferOutOrderViewModel.selectiveFacilityMenuData.observe(this, Observer {
            it?.takeIf { it.isNotEmpty() }?.let { list ->
                (facilities_list.adapter as ReleaseFacilityAdapter).setMenuData(list.toMutableSet())
            }
        })


        //TODO 2020/6/8 取消限制
//        edit_user_address.setOnClickListener {
//            if (insertOrUpdateTransferOutOrderViewModel.address.isNotEmpty()) {
//                edit_user_address.inputType = InputType.TYPE_CLASS_TEXT
//            } else {
//                edit_user_address.inputType = InputType.TYPE_NULL
//            }
//        }

        edit_user_address.addTextChangedListener {
            if (insertOrUpdateTransferOutOrderViewModel.address.isNotEmpty()) {
                insertOrUpdateTransferOutOrderViewModel.address = it.toString()
            }

        }
        button_input_floor.addTextChangedListener {
            if (it.toString().isNotEmpty()) {
                insertOrUpdateTransferOutOrderViewModel.floor = it.toString().toInt()
            }
        }


//        list获取图片

        imageSelector.layoutManager = GridLayoutManager(this, 3)
        imageSelector.adapter = ImageSelectorAdapter()
        insertOrUpdateTransferOutOrderViewModel.selectedImages.observe(
            this,
            Observer {
                Log.d("---imageicon", " selectedImages Observer= " + it.size)
                (imageSelector.adapter as ImageSelectorAdapter).update(it.toMutableList())
            })


        insertOrUpdateTransferOutOrderViewModel.industries.observe(this, Observer {
            when (it.size) {
                0 -> {
                    insertOrUpdateTransferOutOrderViewModel.industry = "0"
                    button_select_industry.text = "所有行业"
                }
                1 -> {
                    insertOrUpdateTransferOutOrderViewModel.industry =
                        "${it.elementAt(0)?.menuID ?: 0},0"
                    button_select_industry.text = "${it.elementAt(0)?.text ?: "所有行业"} - 所有类型"
                }
                2 -> {
                    insertOrUpdateTransferOutOrderViewModel.industry =
                        "${it.elementAt(0)?.menuID ?: 0},${it.elementAt(1)?.menuID ?: 0}"
                    button_select_industry.text =
                        "${it.elementAt(0)?.text ?: "所有行业"} - ${it.elementAt(1)?.text ?: "所有类型"}"
                }
            }
        })

        insertOrUpdateTransferOutOrderViewModel.isCanEmpty.observe(this, Observer {
            button_select_empty_transfer.text = it
            if (it == "是") {
                insertOrUpdateTransferOutOrderViewModel.exclusive = 1
            } else {
                insertOrUpdateTransferOutOrderViewModel.exclusive = 0
            }
        })


        insertOrUpdateTransferOutOrderViewModel.facilities.observe(this, Observer {
            facilities_list.removeAllViews()
            val sb = StringBuilder()
            it.forEach { menuItem ->
                sb.append(menuItem.menuID).append(",")
                insertOrUpdateTransferOutOrderViewModel.facility = sb.substring(0, sb.lastIndex)
            }
            (facilities_list.adapter as ReleaseFacilityAdapter).setMenuData(it)
        })


        insertOrUpdateTransferOutOrderViewModel.saveArea.observe(this, Observer {
            button_select_area.text = it.btText
            insertOrUpdateTransferOutOrderViewModel.area = it.menuID.toString()
        })


        input_description.addTextChangedListener {
            insertOrUpdateTransferOutOrderViewModel.description = it.toString()
        }

        input_enverment.addTextChangedListener {
            insertOrUpdateTransferOutOrderViewModel.environment = it.toString()
        }

        input_reason.addTextChangedListener {
            insertOrUpdateTransferOutOrderViewModel.reason = it.toString()
        }


        insertOrUpdateTransferOutOrderViewModel.toastMsg.observe(this, Observer {
            Log.d("---submit", "toastMsg = " + it)
//            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            if (it.contains("保存成功")) {
                ReleaseDialog(0, it).show(supportFragmentManager, ReleaseDialog::class.java.name)
            } else if (it.contains("发布成功")) {
                ReleaseDialog(1, it).show(supportFragmentManager, ReleaseDialog::class.java.name)
            } else {
                ReleaseDialog(2, it).show(supportFragmentManager, ReleaseDialog::class.java.name)
            }
        })

        insertOrUpdateTransferOutOrderViewModel.submitResult.observe(this, Observer {
            Log.d("---submit", "submitResult = " + it)
            if (it != API.CODE_SUCCESS) {
//                ReleaseDialog(2).show(supportFragmentManager, ReleaseDialog::class.java.name)
            }
        })


        intent.getStringExtra("shopID").takeIf {
            it.isNotEmpty()
        }?.also {
            lifecycleScope.launch {
                if (it != "null" || it != "0") {
                    insertOrUpdateTransferOutOrderViewModel.requestEditTransferOutOrderDetail(it)
                        ?.let { order ->
                            order.shopID.toString().let { id ->
                                insertOrUpdateTransferOutOrderViewModel.type = id
                            }
                            order.images?.let { images ->
                                insertOrUpdateTransferOutOrderViewModel.selectedImages.postValue(
                                    images.toMutableSet()
                                )
                            }

                            order.title?.let { title ->
                                input_title.setText(title)
                                insertOrUpdateTransferOutOrderViewModel.title = title
                            }


                            order.size.toString().let { size ->
                                button_select_size.setText(size)
                                insertOrUpdateTransferOutOrderViewModel.size = size
                            }

                            order.fee.toString().let { fee ->
                                input_fee.setText(fee)
                                insertOrUpdateTransferOutOrderViewModel.fee = fee
                            }

                            order.rent.toString().let { rent ->
                                bt_select_rent.setText(rent)
                                insertOrUpdateTransferOutOrderViewModel.rent = rent
                            }

                            order.includeFacilities?.let { include ->
                                insertOrUpdateTransferOutOrderViewModel.exclusive = include
                                if (include == 0) {
                                    button_select_empty_transfer.text = "不可空转"
                                } else {
                                    button_select_empty_transfer.text = "可空转"
                                }
                            }

                            order.runningState?.let { runningState ->
                                insertOrUpdateTransferOutOrderViewModel.state = runningState
                                if (runningState == 0) {
                                    button_select_state.text = "已停业"
                                } else {
                                    button_select_state.text = "营业中"
                                }
                            }

                            order.formattedFinalLocationNode?.let { finalLocationNode ->
                                button_select_area.text = finalLocationNode
                            }


                            order.address?.let { addressObj ->
                                edit_user_address.setText(addressObj.addressDetail)
                                insertOrUpdateTransferOutOrderViewModel.address =
                                    addressObj.addressDetail

                                addressObj.locationNodes?.let { locationNodes ->
                                    if (locationNodes.isNotEmpty()) {
                                        insertOrUpdateTransferOutOrderViewModel.area =
                                            locationNodes[locationNodes.size - 1].nodeID.toString()
                                    }
//                                    else insertOrUpdateTransferOutOrderViewModel.area = ""
                                }
                            }

                            order.lng?.let {
                                if (it.isNotEmpty())
                                    insertOrUpdateTransferOutOrderViewModel.lng = it.toDouble()
//                                else getLngAndLat(this@InsertOrUpdateTransferOutOrderActivity)
                            }

                            order.lat?.let {
                                if (it.isNotEmpty())
                                    insertOrUpdateTransferOutOrderViewModel.lat = it.toDouble()
//                                else getLngAndLat(this@InsertOrUpdateTransferOutOrderActivity)
                            }

//                            order.lng?.let {
//                                if (it != 0.0)
//                                    insertOrUpdateTransferOutOrderViewModel.lng = it
//                                else getLngAndLat(this@InsertOrUpdateTransferOutOrderActivity)
//                            }
//
//                            order.lat?.let {
//                                if (it != 0.0)
//                                    insertOrUpdateTransferOutOrderViewModel.lat = it
//                                else getLngAndLat(this@InsertOrUpdateTransferOutOrderActivity)
//                            }

                            order.industry?.let { industry ->
                                insertOrUpdateTransferOutOrderViewModel.industry = industry
                            }

                            order.shopOwnerName.let {
                                input_name.setText(it)
                                insertOrUpdateTransferOutOrderViewModel.contactName = it
                            }

                            order.shopOwnerPhoneNumbr?.let {
                                input_phone.setText(it)
                                insertOrUpdateTransferOutOrderViewModel.contactPhone = it
                            }

                            order.formattedFinalIndustry?.let { finalIndustry ->
                                button_select_industry.text = finalIndustry
                            }

                            order.facilities?.let {
                                facilities_list.removeAllViews()
                                val sb = StringBuilder()
                                it.forEach { menuItem ->
                                    sb.append(menuItem.menuID).append(",")
                                    insertOrUpdateTransferOutOrderViewModel.facility =
                                        sb.substring(0, sb.lastIndex)
                                }
                                (facilities_list.adapter as ReleaseFacilityAdapter).setMenuData(it.toMutableSet())
                            }

                            order.floor?.let {
                                insertOrUpdateTransferOutOrderViewModel.floor = it
                                if (it != 0) {
                                    button_input_floor.setText(it.toString())
                                }
                            }

                            order.description?.let { description ->
                                input_description.setText(description)
                                insertOrUpdateTransferOutOrderViewModel.description = description
                            }

                            order.environment?.let { environment ->
                                input_enverment.setText(environment)
                                insertOrUpdateTransferOutOrderViewModel.environment = environment
                            }

                            order.reason?.let { reason ->
                                input_reason.setText(reason)
                                insertOrUpdateTransferOutOrderViewModel.reason = reason
                            }
                        }
                } else {
                    insertOrUpdateTransferOutOrderViewModel.requestSaveTransferOutOrderDetail()
                        ?.let { order ->
                            order.shopID.toString().let { id ->
                                insertOrUpdateTransferOutOrderViewModel.type = id
                            }
                            order.images?.let { images ->
                                insertOrUpdateTransferOutOrderViewModel.selectedImages.postValue(
                                    images.toMutableSet()
                                )
                            }

                            order.title?.let { title ->
                                input_title.setText(title)
                                insertOrUpdateTransferOutOrderViewModel.title = title
                            }


                            order.size.toString().let { size ->
                                button_select_size.setText(size)
                                insertOrUpdateTransferOutOrderViewModel.size = size
                            }

                            order.fee.toString().let { fee ->
                                input_fee.setText(fee)
                                insertOrUpdateTransferOutOrderViewModel.fee = fee
                            }

                            order.rent.toString().let { rent ->
                                bt_select_rent.setText(rent)
                                insertOrUpdateTransferOutOrderViewModel.rent = rent
                            }

                            order.includeFacilities?.let { include ->
                                insertOrUpdateTransferOutOrderViewModel.exclusive = include
                                if (include == 0) {
                                    button_select_empty_transfer.text = "不可空转"
                                } else {
                                    button_select_empty_transfer.text = "可空转"
                                }
                            }

                            order.runningState?.let { runningState ->
                                insertOrUpdateTransferOutOrderViewModel.state = runningState
                                if (runningState == 0) {
                                    button_select_state.text = "已停业"
                                } else {
                                    button_select_state.text = "营业中"
                                }
                            }

                            order.formattedFinalLocationNode?.let { finalLocationNode ->
                                button_select_area.text = finalLocationNode
                            }


                            order.address?.let { addressObj ->
                                edit_user_address.setText(addressObj.addressDetail)
                                insertOrUpdateTransferOutOrderViewModel.address =
                                    addressObj.addressDetail

                                addressObj.locationNodes?.let { locationNodes ->
                                    if (locationNodes.isNotEmpty()) {
                                        insertOrUpdateTransferOutOrderViewModel.area =
                                            locationNodes[locationNodes.size - 1].nodeID.toString()
                                    }
//                                    else insertOrUpdateTransferOutOrderViewModel.area = ""
                                }
                            }

                            order.lng?.let {
                                insertOrUpdateTransferOutOrderViewModel.lng = it.toDouble()
                            }

                            order.lat?.let {
                                insertOrUpdateTransferOutOrderViewModel.lat = it.toDouble()
                            }

//                            order.lng?.let {
//                                if (it != 0.0)
//                                    insertOrUpdateTransferOutOrderViewModel.lng = it
//                                else getLngAndLat(this@InsertOrUpdateTransferOutOrderActivity)
//                            }
//
//                            order.lat?.let {
//                                if (it != 0.0)
//                                    insertOrUpdateTransferOutOrderViewModel.lat = it
//                                else getLngAndLat(this@InsertOrUpdateTransferOutOrderActivity)
//                            }

                            order.floor?.let {
                                insertOrUpdateTransferOutOrderViewModel.floor = it
                                if (it != 0) {
                                    button_input_floor.setText(it.toString())
                                }
                            }

                            order.industry?.let { industry ->
                                insertOrUpdateTransferOutOrderViewModel.industry = industry
                            }

                            order.shopOwnerName?.let {
                                input_name.setText(it)
                                insertOrUpdateTransferOutOrderViewModel.contactName = it
                            }

                            order.shopOwnerPhoneNumbr?.let {
                                input_phone.setText(it)
                                insertOrUpdateTransferOutOrderViewModel.contactPhone = it
                            }

                            order.formattedFinalIndustry?.let { finalIndustry ->
                                button_select_industry.text = finalIndustry
                            }

                            order.facilities?.let {
                                facilities_list.removeAllViews()
                                val sb = StringBuilder()
                                it.forEach { menuItem ->
                                    sb.append(menuItem.menuID).append(",")
                                    insertOrUpdateTransferOutOrderViewModel.facility =
                                        sb.substring(0, sb.lastIndex)
                                }
                                (facilities_list.adapter as ReleaseFacilityAdapter).setMenuData(it.toMutableSet())
                            }

                            order.floor?.let {
                                insertOrUpdateTransferOutOrderViewModel.floor = it
                                if (it != 0) {
                                    button_input_floor.setText(it.toString())
                                }
                            }

                            order.description?.let { description ->
                                input_description.setText(description)
                                insertOrUpdateTransferOutOrderViewModel.description = description
                            }

                            order.environment?.let { environment ->
                                input_enverment.setText(environment)
                                insertOrUpdateTransferOutOrderViewModel.environment = environment
                            }

                            order.reason?.let { reason ->
                                input_reason.setText(reason)
                                insertOrUpdateTransferOutOrderViewModel.reason = reason
                            }

//                            if (order.shopID.toString().isNullOrEmpty()) {
//                                edit_user_address.inputType = InputType.TYPE_NULL
//                            }
                        }
                }
            }
        }



        API.logoutSignal.observe(this, Observer {
            if (it == API.CODE_REQUIRE_LOGIN ||
                it == API.CODE_AUTO_CODE_INVALID ||
                it == API.CODE_AUTO_CODE_EXPIRED ||
                it == API.CODE_AUTO_CODE_ERROR ||
                it == API.CODE_AUTO_CODE_EMPTY ||
                it == API.CODE_BANNED_USER
            ) {
                launchLoginActivity()
            }
        })
    }

//    @SuppressLint("SetTextI18n")
//    override fun onResume() {
//        super.onResume()
//
//    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            Matisse.obtainPathResult(data).let { list ->
                insertOrUpdateTransferOutOrderViewModel.selectedImages.value?.let {
                    it.addAll(list)
                    insertOrUpdateTransferOutOrderViewModel.selectedImages.postValue(it)
                }
            }
        }
    }

    inner class ImageSelectorAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        private val maxSelectable = 9
        private val viewTypeAdd = 0
        private val viewTypeImage = 1
        private val images = mutableListOf<String>()

        inner class ImageViewHolder(override val containerView: View) :
            RecyclerView.ViewHolder(containerView), LayoutContainer {
            private val binding = FragmentTransferOrderImgSelectorItemBinding.bind(containerView)
            fun bind(url: String) {
                binding.image.url(url)
                binding.delete.setOnClickListener {
                    insertOrUpdateTransferOutOrderViewModel.selectedImages.value?.let {
                        it.remove(url)
                        insertOrUpdateTransferOutOrderViewModel.selectedImages.postValue(it)
                    }
                }
            }
        }

        inner class AddImageViewHolder(override val containerView: View) :
            RecyclerView.ViewHolder(containerView), LayoutContainer {
            init {
                FragmentTransferOrderImgSelectorAddItemBinding.bind(containerView).apply {
                    image.setImageResource(R.mipmap.ic_add_img)
                    image.setOnClickListener {
                        Matisse.from(this@InsertOrUpdateTransferOutOrderActivity)
                            .choose(MimeType.ofImage())
                            .countable(true)
                            .maxSelectable(
                                maxSelectable - (insertOrUpdateTransferOutOrderViewModel.selectedImages.value?.size
                                    ?: 0)
                            )
                            .capture(true)
                            .captureStrategy(
                                CaptureStrategy(
                                    false,
                                    "${this@InsertOrUpdateTransferOutOrderActivity.packageName}.fileProvider"
                                )
                            )
                            .gridExpectedSize(resources.displayMetrics.widthPixels / 3)
                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                            .thumbnailScale(0.85f)
                            .imageEngine(GlideImageEngine())
                            .forResult(100)
                    }
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            when (viewType) {
                viewTypeAdd -> {
                    AddImageViewHolder(
                        LayoutInflater.from(parent.context).inflate(
                            R.layout.fragment_transfer_order_img_selector_add_item,
                            parent,
                            false
                        )
                    )
                }
                else -> {
                    ImageViewHolder(
                        LayoutInflater.from(parent.context).inflate(
                            R.layout.fragment_transfer_order_img_selector_item,
                            parent,
                            false
                        )
                    )
                }
            }

        override fun getItemViewType(position: Int): Int =
            if (position < images.size) viewTypeImage else viewTypeAdd

        override fun getItemCount(): Int = if (images.size <= 5) images.size + 1 else maxSelectable

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if (holder is ImageViewHolder) holder.bind(images[position])
        }

        fun update(images: List<String>) {
            this.images.clear()
            this.images.addAll(images)
            notifyDataSetChanged()
        }
    }


    var latitude = 0.0
    var longitude = 0.0

    @SuppressLint("MissingPermission")
    fun getLngAndLat(context: Context): String {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val providers = locationManager.allProviders
        for (provider in providers) {
            val location = locationManager.getLastKnownLocation(provider)
            if (location != null) {
                latitude = location.latitude
                longitude = location.longitude
                insertOrUpdateTransferOutOrderViewModel.lat = latitude
                insertOrUpdateTransferOutOrderViewModel.lng = longitude

                return "$longitude,$latitude"
            }
        }
        locationManager.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,
            1000,
            0F,
            locationListener
        )
        val location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        if (location != null) {
            latitude = location.latitude
            longitude = location.longitude
            insertOrUpdateTransferOutOrderViewModel.lat = latitude
            insertOrUpdateTransferOutOrderViewModel.lng = longitude
        }
        return "$longitude,$latitude"
    }


    private val locationListener = object : LocationListener {
        override fun onLocationChanged(location: Location?) {
            if (location != null) {
                latitude = location.latitude
                longitude = location.longitude
            }
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        }

        override fun onProviderEnabled(provider: String?) {
        }

        override fun onProviderDisabled(provider: String?) {
        }
    }

//    override fun onResume() {
//        super.onResume()
//        MobclickAgent.onPageStart("InsertOrUpdateTransferOutOrderActivity") //统计页面，"MainScreen"为页面名称，可自定义
//    }
//
//    override fun onPause() {
//        super.onPause()
//        MobclickAgent.onPageEnd("InsertOrUpdateTransferOutOrderActivity")
//    }
}

