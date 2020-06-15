package com.puxiansheng.logic.bean

import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.puxiansheng.logic.bean.converter.ArtcleListConverter
import com.puxiansheng.logic.bean.converter.MenuListConverter
import com.puxiansheng.logic.bean.converter.StringListConverter
import com.puxiansheng.logic.bean.http.ArticleObject
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal

@Parcelize
@TypeConverters(MenuListConverter::class, StringListConverter::class,ArtcleListConverter::class)
data class Shop(
    @ColumnInfo(name = "_shop_id")
    var shopID: Long = 0,

    @ColumnInfo(name = "_title")
    var title: String = "",

    @ColumnInfo(name = "_size")
    var size: Double = 0.0,

    @ColumnInfo(name = "_rent")
    var rent: Double = 0.0,

    @ColumnInfo(name = "_fee")
    var fee: Double = 0.0,



    @Embedded
    var address: Address? = null,

    @ColumnInfo(name = "_lng")
    var lng: String ? = null,

    @ColumnInfo(name = "_lat")
    var lat: String ? = null,

    @ColumnInfo(name = "_new_lng")
    var newLng:  Double = 0.0,

    @ColumnInfo(name = "_new_lat")
    var newLat:  Double = 0.0,


    @ColumnInfo(name = "_industry")
    var industry: String = "",

    @ColumnInfo(name = "_running_state")
    var runningState: Int = 0,

    @ColumnInfo(name = "_exclusive")
    var includeFacilities: Int = 0,

    @ColumnInfo(name = "_image")
    var image: String = "",


    @ColumnInfo(name = "_images")
    var images: List<String>? = null,

    @ColumnInfo(name = "_floor")
    var floor: Int = 0,

    @ColumnInfo(name = "_labels")
    var labels: List<MenuItem>? = null,


    @ColumnInfo(name = "_facilities")
    var facilities: List<MenuItem>? = null,

    @ColumnInfo(name = "_allfacilities")
    var allFacilities: List<MenuItem>? = null,

    @ColumnInfo(name = "_description")
    var description: String = "",

    @ColumnInfo(name = "_description_url")
    var descriptionUrl: String = "",

    @ColumnInfo(name = "_environment")
    var environment: String = "",

    @ColumnInfo(name = "_reason")
    var reason: String = "",

    @ColumnInfo(name = "_transfer_type")
    var transferType: Int = 0,

    @ColumnInfo(name = "_is_success")
    var isSuccess: Int = 0,

    @ColumnInfo(name = "_formatted_is_success")
    var formatted_is_success: Int = 0,

    @ColumnInfo(name = "_formatted_date")
    var formattedDate: String = "",

    @ColumnInfo(name = "_formatted_size")
    var formattedSize: String = "",

    @ColumnInfo(name = "_formatted_rent")
    var formattedRent: String = "",

    @ColumnInfo(name = "_formatted_page_views")
    var formattedPageViews: Int = 0,

    @ColumnInfo(name = "_formatted_fee")
    var formattedFee: String = "",

    @ColumnInfo(name = "_formatted_location_nodes")
    var formattedLocationNodes: String = "",

    @ColumnInfo(name = "_formatted_industry")
    var formattedIndustry: String = "",

    @ColumnInfo(name = "_formatted_final_industry")
    var formattedFinalIndustry: String = "",

    @ColumnInfo(name = "_formatted_final_location_node")
    var formattedFinalLocationNode: String = "",

//    @ColumnInfo(name = "_view_demand_ids")
//    var formattedFacilities: List<MenuItem>? = null,

//    @ColumnInfo(name = "formattedFacilities")
//    var formattedFacilities: List<MenuItem>? = null,
    @SerializedName("view_demand_ids")
    var formattedFacilities: List<MenuItem>? = null,

    //TODO 新增
    @ColumnInfo(name = "_rent_view")
    var rentView: String = "",

    //TODO 新增
    @ColumnInfo(name = "_view_opening")
    var viewOpening: String = "",

    @ColumnInfo(name = "_view_can_empty")
    var viewCanEmpty: String = "",

    @ColumnInfo(name = "_is_top")
    var isTop: Int = 0,

    @ColumnInfo(name = "_is_hot")
    var isHot: Int = 0,

    @ColumnInfo(name = "_is_recommend")
    var isRecommend: Int = 0,

    @ColumnInfo(name = "_is_large_order")
    var isLargeOrder: Int = 0,

    @ColumnInfo(name = "_large_order_img")
    var largeOrderImg: String = "",

    @ColumnInfo(name = "_category_acreage")
    var category_acreage:String = "",

    @ColumnInfo(name = "_formatted_area")
    var formattedArea: String = "",

    @ColumnInfo(name = "_is_vip")
    var isVip:Int = 0,

    @ColumnInfo(name = "_data_type")
    var data_type: String = "",

    @ColumnInfo(name = "_jump_type")
    var jump_type: Int = 0,

    @ColumnInfo(name = "_jump_view")
    var jump_view: String = "",

    @ColumnInfo(name = "_jump_param")
    var jump_param: String = ""

//    @ColumnInfo(name = "_article_list")
//    var articles: List<ArticleBean>? = null

) : Parcelable