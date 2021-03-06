package com.puxiansheng.logic.bean.http

import com.google.gson.annotations.SerializedName
import com.puxiansheng.logic.bean.*

data class HttpRespOrderDetail(
    @SerializedName("result")
    val obj: OrderDetailObject? = null
)


data class OrderDetailObject(
    @SerializedName("id")
    var shopID: Long = 0,

    @SerializedName("title")
    var title: String = "",

    @SerializedName("type")
    var type: String = "",

    @SerializedName("update_time")
    var updateTime: Long = 0,

    @SerializedName("checked_id")
    var checkId: Int = 0,

    @SerializedName("status")
    var status: Int = 0,

    @SerializedName("img_ids")
    var images: List<String>? = null,

    @SerializedName("acreage")
    var size: Double = 0.0,

    @SerializedName("rent")
    var rent: Double = 0.0,

    @SerializedName("rent_unit_id")
    var rentUnitId: Int = 0,

//    @SerializedName("labels")
//    var labels: String = "",

    @SerializedName("category_path_id")
    var industry: String = "",

    @SerializedName("is_top")
    var isTop: Int = 0,


    @SerializedName("is_vip")
    var isVip: Int = 0,


    @SerializedName("is_hot")
    var isHot: Int = 0,

    @SerializedName("is_recommend")
    var isRecommend: Int = 0,

    @SerializedName("is_large_order")
    var isLargeOrder: Int = 0,

    @SerializedName("large_order_img")
    var largeOrderImg: String = "",

//    @SerializedName("view_list_statsus")
//    var viewListStatus:List<MenuItem> ?= null,

    @SerializedName("city_id")
    var city_id: Long = 0,


    @SerializedName("transfer_fee")
    var fee: Double = 0.0,

    @SerializedName("category")
    var formattedIndustry: List<String>? = null,

    @SerializedName("category_end")
    var formattedFinalIndustry: String = "",

    @SerializedName("category_str")
    var categoryStr: String = "",

    @SerializedName("view_acreage_un_prefix")
    var view_acreage_un_prefix: String = "",

    @SerializedName("view_transfer_fee")
    var formattedTransferFee: String = "",

    @SerializedName("floor")
    var floor: Int = 0,

    @SerializedName("view_acreage")
    var formattedSize: String = "",

    @SerializedName("category_acreage")
    var categoryAcreage: String = "",

    @SerializedName("view_category")
    var view_category: String = "",

    @SerializedName("rent_name")
    var rentName: String = "",

    @SerializedName("view_rent")
    var formattedRent: String = "",

    @SerializedName("thum_img")
    var image: String = "",

    @SerializedName("is_activity_selected")
    var shopLabel: String = "",

    @SerializedName("area_obj")
    var address: Address? = null,

    @SerializedName("area_str")
    var formattedLocationNodes: String = "",

    @SerializedName("new_area_str")
    var newAreaStr: String = "",

    @SerializedName("area_point_str")
    var area_point_str: String = "",

    @SerializedName("area_lis")
    var areaLis: List<String>? = null,

    @SerializedName("area_end")
    var formattedFinalLocationNode: String = "",

    @SerializedName("show_area")
    var show_area: String = "",


    @SerializedName("view_opening")
    var viewOpening: String = "",

    @SerializedName("view_can_empty")
    var viewCanEmpty: String = "",

    @SerializedName("is_opening")
    var runningState: Int = 0,

    @SerializedName("lng")
    var lng: String = "",

    @SerializedName("lat")
    var lat: String = "",

    @SerializedName("can_empty")
    var includeFacilities: Int = 0,


    @SerializedName("labels_list")
    //@Expose(serialize = false, deserialize = false)
    @Transient var labelList: List<MenuItem>? = null,

    @SerializedName("demand_ids")//显示有的设施
    var facilities: List<MenuItem>? = null,

    @SerializedName("area_multiple")//显示所有的地区
    var area_multiple: String = "",

    @SerializedName("content")
    var description: String = "",


    @SerializedName("content_url")
    var descriptionUrl: String = "",

    @SerializedName("support_description")
    var environment: String = "",

    @SerializedName("transfer_reason")
    var reason: String = "",

    /*@SerializedName("contact_name")
    var serviceAgentName: String = "",*/

    @SerializedName("kf_phone")
    var serviceAgentPhone: String = "",

    @SerializedName("contact_name")
    var shopOwnerName: String = "",

    @SerializedName("contact_phone")
    var shopOwnerPhoneNumbr: String = "",

    @SerializedName("view_status")
    var state: Order.Companion.State? = null,

//    @SerializedName("view_status")
//    var status: Order.Companion.orderStatus? = null,


    @SerializedName("collect")
    var favorite: Int = 0,

    //formatted data
    @SerializedName("view_time")
    var formattedDate: String = "",

    @SerializedName("day_time")
    var day_time: String = "",

    @SerializedName("view_count")
    var formattedPageViews: Int = 0,

    @SerializedName("is_success")
    var isSuccess: Int = 0,


    @SerializedName("view_rent_un_prefix")
    var view_rent_un_prefix: String = "",


    @SerializedName("view_demand_ids")//显示全部设施
    var formattedFacilities: List<MenuItem>? = null,

    @SerializedName("data_type")
    var data_type: String = "",

    @SerializedName("jump_type")
    var jump_type: Int = 0,

    @SerializedName("jump_view")
    var jump_view: String = "",

    @SerializedName("jump_param")
    var jump_param: String = "",

    @SerializedName("is_update_time")
    var isUpdateTime: Int = 0,

    @SerializedName("data")
    var articles: List<ArticleBean>? = null

)


data class ArticleObject(
    @SerializedName("id")
    var shopID: Long = 0,

    @SerializedName("title")
    var title: String = "",

    @SerializedName("jump_type")
    var jump_type: Int = 0,

    @SerializedName("jump_view")
    var jump_view: String = "",

    @SerializedName("jump_param")
    var jump_param: String = ""

)