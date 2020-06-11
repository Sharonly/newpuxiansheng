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
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.MenuItem
import com.puxiansheng.logic.util.GlideImageEngine
import com.puxiansheng.util.ext.SharedPreferencesUtil
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
import com.puxiansheng.www.common.LiveDataBus
import com.puxiansheng.www.common.drawableTop
import com.puxiansheng.www.common.url
import com.puxiansheng.www.databinding.DialogSelectiveFalitiesItemBinding
import com.puxiansheng.www.ui.map.MapActivity
import com.puxiansheng.www.ui.order.dialog.*
import com.puxiansheng.www.ui.release.dialog.ReleaseDialog
import kotlinx.android.synthetic.main.activity_relase_order_transfer_out.*
import kotlinx.android.synthetic.main.activity_relase_order_transfer_out.bt_select_rent
import kotlinx.android.synthetic.main.activity_relase_order_transfer_out.button_select_area
import kotlinx.android.synthetic.main.activity_relase_order_transfer_out.button_select_industry
import kotlinx.android.synthetic.main.activity_relase_order_transfer_out.button_select_size
import kotlinx.android.synthetic.main.activity_relase_order_transfer_out.edit_user_address
import kotlinx.android.synthetic.main.activity_relase_order_transfer_out.imageSelector
import kotlinx.android.synthetic.main.activity_relase_order_transfer_out.input_description
import kotlinx.android.synthetic.main.activity_relase_order_transfer_out.input_enverment
import kotlinx.android.synthetic.main.activity_relase_order_transfer_out.input_fee
import kotlinx.android.synthetic.main.activity_relase_order_transfer_out.input_reason
import kotlinx.android.synthetic.main.activity_relase_order_transfer_out.input_title
import kotlinx.android.synthetic.main.activity_relase_order_transfer_out.submit
import java.util.*

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class InsertOrUpdateTransferOutOrderActivity : MyBaseActivity() {
    private val requestCodePermissions = 10
    private val requiredPermissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )
    private lateinit var insertOrUpdateTransferOutOrderViewModel: InsertOrUpdateTransferOutOrderViewModel
    private lateinit var appViewModel: MainViewModel
    private var facilist = mutableSetOf<MenuItem>()
    private var indulist = mutableSetOf<MenuItem>()

    override fun getLayoutId(): Int {
        return R.layout.activity_relase_order_transfer_out
    }

    override fun business() {
        insertOrUpdateTransferOutOrderViewModel =
            ViewModelProvider(this)[InsertOrUpdateTransferOutOrderViewModel::class.java]
        appViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        ActivityCompat.requestPermissions(
            this,
            requiredPermissions,
            requestCodePermissions
        )
        initView()
    }


    private fun initView() {
        bt_back.setOnClickListener {
            onBackPressed()
        }

        insertOrUpdateTransferOutOrderViewModel.contactName =
            SharedPreferencesUtil.get(API.LOGIN_ACTUL_NAME, "").toString()
        insertOrUpdateTransferOutOrderViewModel.contactPhone =
            SharedPreferencesUtil.get(API.LOGIN_USER_PHONE, "").toString()

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

            insertOrUpdateTransferOutOrderViewModel.lat.let {
                if (it == 0.0) {
                    getLngAndLat(this)
                    Toast.makeText(this, "需要获取您的定位！", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            insertOrUpdateTransferOutOrderViewModel.lng.let {
                if (it == 0.0) {
                    Toast.makeText(this, "需要获取您的定位！", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }




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
            insertOrUpdateTransferOutOrderViewModel.submit()
        }

        insertOrUpdateTransferOutOrderViewModel.type = intent.getIntExtra("shopID", 0).toString()

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

        button_select_size.setOnClickListener {
            SelectSizeRangeDialog(
                onSelectSize = {
                    it?.let { size ->
                        button_select_size.text = size.text
                        insertOrUpdateTransferOutOrderViewModel.size = size.menuID.toString()
                    }
                }
            ).show(supportFragmentManager, SelectSizeRangeDialog::class.java.name)
        }

        bt_select_rent.setOnClickListener {
            SelectRentRangeDialog(onSelectRent = {
                it?.let { rent ->
                    bt_select_rent.text = rent.text
                    insertOrUpdateTransferOutOrderViewModel.rent = rent.menuID.toString();
                }
            }).show(
                supportFragmentManager,
                SelectRentRangeDialog::class.java.name
            )
        }

        input_fee.addTextChangedListener {
            insertOrUpdateTransferOutOrderViewModel.fee = it.toString()
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
            SelectAreaDialog(onSelectArea = {
                it?.let { locationNode ->
                    insertOrUpdateTransferOutOrderViewModel.saveArea.postValue(locationNode)
                    button_select_area.text = locationNode.text
                }
            }).show(
                supportFragmentManager,
                SelectAreaDialog::class.java.name
            )
        }

        LiveDataBus.get().with("Map", String::class.java)?.observe(this, Observer {
            edit_user_address.setText(it)
            insertOrUpdateTransferOutOrderViewModel.address = it
        })

        button_select_address.setOnClickListener {
            var location = getLngAndLat(this)
            //  edit_user_address.inputType = InputType.TYPE_NULL
            val intent = Intent(this, MapActivity::class.java)
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
                (facilities_list.adapter as ReleaseFacilityAdapter).setMenuData(it)
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
        facilities_list.adapter = ReleaseFacilityAdapter(mutableListOf())

        insertOrUpdateTransferOutOrderViewModel.selectiveFacilityMenuData.observe(this, Observer {
            it?.takeIf { it.isNotEmpty() }?.let { list ->
                (facilities_list.adapter as ReleaseFacilityAdapter).setMenuData(list)
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
            if (insertOrUpdateTransferOutOrderViewModel.floor != 0) {
                insertOrUpdateTransferOutOrderViewModel.floor = it.toString().toInt()
            }
        }


//        list获取图片

        imageSelector.layoutManager = GridLayoutManager(this, 3)
        imageSelector.adapter = ImageSelectorAdapter()
        insertOrUpdateTransferOutOrderViewModel.selectedImages.observe(
            this,
            Observer {
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
        })


        insertOrUpdateTransferOutOrderViewModel.saveArea.observe(this, Observer {
            button_select_area.text = it.text
            insertOrUpdateTransferOutOrderViewModel.area = it.nodeID.toString()
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
                ReleaseDialog(0).show(supportFragmentManager, ReleaseDialog::class.java.name)
            } else if (it.contains("发布成功")) {
                ReleaseDialog(1).show(supportFragmentManager, ReleaseDialog::class.java.name)
            } else {
                ReleaseDialog(2).show(supportFragmentManager, ReleaseDialog::class.java.name)
            }
        })

        insertOrUpdateTransferOutOrderViewModel.submitResult.observe(this, Observer {
            Log.d("---submit", "submitResult = " + it)
            if (it != API.CODE_SUCCESS) {
                ReleaseDialog(2).show(supportFragmentManager, ReleaseDialog::class.java.name)
            }
        })





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

    override fun onResume() {
        super.onResume()
        intent.getIntExtra("shopID", 0).toString().takeIf {
            it.isNotEmpty()
        }?.also {
            lifecycleScope.launch {
                if (it != "null" || it != "0") {
                    insertOrUpdateTransferOutOrderViewModel.requestEditTransferOutOrderDetail(it)
                        ?.let { order ->
                            order.shop?.shopID.toString().let { id ->
                                insertOrUpdateTransferOutOrderViewModel.type = id
                            }
                            order.shop?.images?.let { images ->
                                insertOrUpdateTransferOutOrderViewModel.selectedImages.postValue(
                                    images.toMutableSet()
                                )
                            }

                            order.shop?.title?.let { title ->
                                input_title.setText(title)
                                insertOrUpdateTransferOutOrderViewModel.title = title
                            }


                            order.shop?.size.toString().let { size ->
                                button_select_size.setText(size)
                                insertOrUpdateTransferOutOrderViewModel.size = size
                            }

                            order.shop?.fee.toString().let { fee ->
                                input_fee.setText(fee)
                                insertOrUpdateTransferOutOrderViewModel.fee = fee
                            }

                            order.shop?.rent.toString().let { rent ->
                                bt_select_rent.setText(rent)
                                insertOrUpdateTransferOutOrderViewModel.rent = rent
                            }

                            order.shop?.includeFacilities?.let { include ->
                                insertOrUpdateTransferOutOrderViewModel.exclusive = include
                                if (include == 0) {
                                    button_select_empty_transfer.text = "不可空转"
                                } else {
                                    button_select_empty_transfer.text = "可空转"
                                }
                            }

                            order.shop?.runningState?.let { runningState ->
                                insertOrUpdateTransferOutOrderViewModel.state = runningState
                                if (runningState == 0) {
                                    button_select_state.text = "已停业"
                                } else {
                                    button_select_state.text = "营业中"
                                }
                            }

                            order.shop?.formattedArea?.let { finalLocationNode ->
                                button_select_area.text = finalLocationNode
                            }


                            order.shop?.address?.let { addressObj ->
                                edit_user_address.setText(addressObj.addressDetail)
                                insertOrUpdateTransferOutOrderViewModel.address =
                                    addressObj.addressDetail

                                addressObj.locationNodes?.let { locationNodes ->
                                    if (locationNodes.isNotEmpty()) {
                                        insertOrUpdateTransferOutOrderViewModel.area =
                                            locationNodes[locationNodes.size - 1].nodeID.toString()
                                    } else insertOrUpdateTransferOutOrderViewModel.area = ""
                                }
                            }

                            order.shop?.lng?.let {
                                if (it != 0.0)
                                    insertOrUpdateTransferOutOrderViewModel.lng = it
                                else getLngAndLat(this@InsertOrUpdateTransferOutOrderActivity)
                            }

                            order.shop?.lat?.let {
                                if (it != 0.0)
                                    insertOrUpdateTransferOutOrderViewModel.lat = it
                                else getLngAndLat(this@InsertOrUpdateTransferOutOrderActivity)
                            }

                            order.shop?.industry?.let { industry ->
                                insertOrUpdateTransferOutOrderViewModel.industry = industry
                            }

                            order.shopOwner?.actualName?.let {
                                input_name.setText(it)
                                insertOrUpdateTransferOutOrderViewModel.contactName = it
                            }

                            order.shopOwner?.userPhoneNumber?.let {
                                input_phone.setText(it)
                                insertOrUpdateTransferOutOrderViewModel.contactPhone = it
                            }

                            order.shop?.formattedFinalIndustry?.let { finalIndustry ->
                                button_select_industry.text = finalIndustry
                            }

                            order.shop?.facilities?.let {
                                facilities_list.removeAllViews()
                                val sb = StringBuilder()
                                it.forEach { menuItem ->
                                    sb.append(menuItem.menuID).append(",")
                                    insertOrUpdateTransferOutOrderViewModel.facility =
                                        sb.substring(0, sb.lastIndex)
                                }
                            }

                            order.shop?.floor?.let {
                                insertOrUpdateTransferOutOrderViewModel.floor = it
                                if (it != 0) {
                                    button_input_floor.setText(it.toString())
                                }
                            }

                            order.shop?.description?.let { description ->
                                input_description.setText(description)
                                insertOrUpdateTransferOutOrderViewModel.description = description
                            }

                            order.shop?.environment?.let { environment ->
                                input_enverment.setText(environment)
                                insertOrUpdateTransferOutOrderViewModel.environment = environment
                            }

                            order.shop?.reason?.let { reason ->
                                input_reason.setText(reason)
                                insertOrUpdateTransferOutOrderViewModel.reason = reason
                            }
                        }
                } else {
                    insertOrUpdateTransferOutOrderViewModel.requestSaveTransferOutOrderDetail()
                        ?.let { order ->
                            order.shop?.shopID.toString().let { id ->
                                insertOrUpdateTransferOutOrderViewModel.type = id
                            }
                            order.shop?.images?.let { images ->
                                insertOrUpdateTransferOutOrderViewModel.selectedImages.postValue(
                                    images.toMutableSet()
                                )
                            }

                            order.shop?.title?.let { title ->
                                input_title.setText(title)
                                insertOrUpdateTransferOutOrderViewModel.title = title
                            }


                            order.shop?.size.toString().let { size ->
                                button_select_size.setText(size)
                                insertOrUpdateTransferOutOrderViewModel.size = size
                            }

                            order.shop?.fee.toString().let { fee ->
                                input_fee.setText(fee)
                                insertOrUpdateTransferOutOrderViewModel.fee = fee
                            }

                            order.shop?.rent.toString().let { rent ->
                                bt_select_rent.setText(rent)
                                insertOrUpdateTransferOutOrderViewModel.rent = rent
                            }

                            order.shop?.includeFacilities?.let { include ->
                                insertOrUpdateTransferOutOrderViewModel.exclusive = include
                                if (include == 0) {
                                    button_select_empty_transfer.text = "不可空转"
                                } else {
                                    button_select_empty_transfer.text = "可空转"
                                }
                            }

                            order.shop?.runningState?.let { runningState ->
                                insertOrUpdateTransferOutOrderViewModel.state = runningState
                                if (runningState == 0) {
                                    button_select_state.text = "已停业"
                                } else {
                                    button_select_state.text = "营业中"
                                }
                            }

                            order.shop?.formattedArea?.let { finalLocationNode ->
                                button_select_area.text = finalLocationNode
                            }


                            order.shop?.address?.let { addressObj ->
                                edit_user_address.setText(addressObj.addressDetail)
                                insertOrUpdateTransferOutOrderViewModel.address =
                                    addressObj.addressDetail

                                addressObj.locationNodes?.let { locationNodes ->
                                    if (locationNodes.isNotEmpty()) {
                                        insertOrUpdateTransferOutOrderViewModel.area =
                                            locationNodes[locationNodes.size - 1].nodeID.toString()
                                    } else insertOrUpdateTransferOutOrderViewModel.area = ""
                                }
                            }

                            order.shop?.lng?.let {
                                if (it != 0.0)
                                    insertOrUpdateTransferOutOrderViewModel.lng = it
                                else getLngAndLat(this@InsertOrUpdateTransferOutOrderActivity)
                            }

                            order.shop?.lat?.let {
                                if (it != 0.0)
                                    insertOrUpdateTransferOutOrderViewModel.lat = it
                                else getLngAndLat(this@InsertOrUpdateTransferOutOrderActivity)
                            }

                            order.shop?.industry?.let { industry ->
                                insertOrUpdateTransferOutOrderViewModel.industry = industry
                            }

                            order.shopOwner?.actualName?.let {
                                input_name.setText(it)
                                insertOrUpdateTransferOutOrderViewModel.contactName = it
                            }

                            order.shopOwner?.userPhoneNumber?.let {
                                input_phone.setText(it)
                                insertOrUpdateTransferOutOrderViewModel.contactPhone = it
                            }

                            order.shop?.formattedFinalIndustry?.let { finalIndustry ->
                                button_select_industry.text = finalIndustry
                            }

                            order.shop?.facilities?.let {
                                facilities_list.removeAllViews()
                                val sb = StringBuilder()
                                it.forEach { menuItem ->
                                    sb.append(menuItem.menuID).append(",")
                                    insertOrUpdateTransferOutOrderViewModel.facility =
                                        sb.substring(0, sb.lastIndex)
                                }
                            }

                            order.shop?.floor?.let {
                                insertOrUpdateTransferOutOrderViewModel.floor = it
                                if (it != 0) {
                                    button_input_floor.setText(it.toString())
                                }
                            }

                            order.shop?.description?.let { description ->
                                input_description.setText(description)
                                insertOrUpdateTransferOutOrderViewModel.description = description
                            }

                            order.shop?.environment?.let { environment ->
                                input_enverment.setText(environment)
                                insertOrUpdateTransferOutOrderViewModel.environment = environment
                            }

                            order.shop?.reason?.let { reason ->
                                input_reason.setText(reason)
                                insertOrUpdateTransferOutOrderViewModel.reason = reason
                            }

                            if (order.shop?.shopID.toString().isNullOrEmpty()) {
                                edit_user_address.inputType = InputType.TYPE_NULL
                            }
                        }
                }
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            Log.d("---imageicon", "shop--- imagePath data= " + data)
            Matisse.obtainPathResult(data).let { list ->
                Log.d("---imageicon", "shop--- imagePath list= " + list)
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
                latitude = location.latitude;
                longitude = location.longitude;
            }
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        }

        override fun onProviderEnabled(provider: String?) {
        }

        override fun onProviderDisabled(provider: String?) {
        }
    };


}

