package com.puxiansheng.logic.data.order

import android.util.Log
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.HttpRecommendOrder
import com.puxiansheng.logic.bean.http.HttpSuccessVideo
import com.puxiansheng.logic.bean.http.RecommendSuccessVideoList
import com.puxiansheng.logic.bean.http.*
import com.puxiansheng.util.http.APIResp
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.METHOD
import com.puxiansheng.util.http.buildRequest

class RemoteOrderRepository {
    /**
     * submit a new simple transfer-in order to remote server.
     * @param phone
     * @param area
     * */
    fun submitSimpleTransferInOrder(
        phone: String,
        area: String,
        code:String
    ): APIRst<APIResp<HttpRespSubmitOrder>> = buildRequest(
        url = API.SUBMIT_SIMPLE_TRANSFER_IN_ORDER,
        fieldMap = mutableMapOf(
            "phone" to phone,
            "area" to area,
            "code" to code
        ).also {
            it["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = it,
                method = "POST"
            )
        }
    ).let {
        API.call(it)
    }

    /**
     * submit a new simple transfer-out order to remote server.
     * @param phone
     * @param area
     * */
    fun submitSimpleTransferOutOrder(
        phone: String,
        area: String,
        code:String
    ): APIRst<APIResp<HttpRespSubmitOrder>> = buildRequest(
        url = API.SUBMIT_SIMPLE_TRANSFER_OUT_ORDER,
        fieldMap = mutableMapOf(
            "phone" to phone,
            "area" to area,
            "code" to code
        ).also {
            it["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = it,
                method = "POST"
            )
        }
    ).let {
        API.call(it)
    }

    /**
     * submit a new transfer-out order to remote server.
     * */
    fun submitTransferOutOrder(
        type: String = "",
        title: String = "",
        industry: String = "",
        size: String = "",
        rent: String = "",
        fee: String = "",
        area: String = "",
        address: String = "",
        lng: Double? = null,
        lat: Double? = null,
        exclusive: Int = 0,
        state: Int = 0,
        floor: Int = 0,
        //
        images: String? = null,
        contactName: String = "",
        contactPhone: String = "",
        description: String? = null,
        environment: String? = null,
        facility: String? = null,
        reason: String? = null,
        label: String? = null
    ): APIRst<APIResp<HttpRespSubmitOrder>> = buildRequest(
        url = API.SUBMIT_TRANSFER_OUT_ORDER,
        fieldMap = mutableMapOf(
            "id" to type,
            "title" to title,
            "category_path_id" to industry,
            "acreage" to size,
            "rent" to rent,
            "transfer_fee" to fee,
            "city_path_id" to area,
            "address" to address,
            "can_empty" to exclusive.toString(),
            "is_opening" to state.toString(),
            "rent_id" to "1",
            "contact_name" to contactName,
            "contact_phone" to contactPhone
        ).also { map ->
            images?.let {
                if (it != "") {
                    map["img_ids"] = images
                }
            }

            lat?.let {
                map["lat"] = lat.toString()
            }

            lng?.let {
                map["lng"] = lng.toString()
            }

            description?.let {
                map["content"] = description
            }

            environment?.let {
                map["support_description"] = environment
            }

            facility?.let {
                map["demand_ids"] = facility
            }

            reason?.let {
                map["transfer_reason"] = reason
            }

            label?.let {
                map["labels"] = label
            }

            floor?.let {
                map["floor"] = floor.toString()
            }

            map["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = map,
                method = "POST"
            )
        }
    ).let {
        API.call(it)
    }

    /**
     * submit a new transfer-in order to remote server.
     * */
    fun submitTransferInOrder(
        type: String = "",
        title: String = "",
        size: String = "",
        rent: String = "",
        industry: String = "",
        area: String = "",
        contactName: String = "",
        contactPhone: String = "",
        facility: String? = null,
        description: String? = null,
        floor: Int = 0
    ): APIRst<APIResp<HttpRespSubmitOrder>> = buildRequest(
        url = API.SUBMIT_TRANSFER_IN_ORDER,
        fieldMap = mutableMapOf(
            "id" to type,
            "title" to title,
            "category_path_id" to industry,
            "area_multiple" to area,
            "rent_id" to rent,
            "acreage_id" to size,
            "contact_name" to contactName,
            "contact_phone" to contactPhone
        ).also { map ->
            facility?.let {
                map["demand_ids"] = facility
            }

            floor?.let {
                map["floor"] = floor.toString()
            }
            description?.let {
                map["content"] = description
            }

            map["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = map,
                method = "POST"
            )
        }
    ).let {
        API.call(it)
    }

    /**
     * request a list of transfer-out orders from remote server base on page key.
     * */
    fun getTransferSuccessFromRemote(
        title: String,
        industry: String,
        size: String,
        area: String,
        sortBy: String,
        sortType: String,
        rent: String,
        page: Int,
        hot: Int?,
        top: Int?,
        recommend: Int?,
        city: String?
    ): APIRst<APIResp<HttpRespOrders>> = buildRequest(
        url = API.GET_REMOTE_TRANSFER_SUCCESS_ORDERS,
        fieldMap = mutableMapOf(
            "title" to title,
            "industry_path" to industry,
            "acreage" to size,
            "area" to area,
            "sort_field" to sortBy,
            "sort" to sortType.toString(),
            "page" to page.toString(),
            "rent" to rent
        ).also { map ->
            city?.let {
                map["city"] = city
            }
            hot?.let {
                map["hot"] = it.toString()
            }
            top?.let {
                map["top"] = it.toString()
            }
            recommend?.let {
                map["recommend"] = it.toString()
            }
            map["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = map,
                method = "GET"
            )
        },
        method = METHOD.GET
    ).let {
        API.call(it)
    }

   fun getTransferSuccessVideoFromRemote(
       title: String,
       industry: String,
       size: String,
       area: String,
       sortBy: String,
       sortType: String,
       rent: String,
       page: Int,
       city: String?
   ): APIRst<APIResp<HttpSuccessVideo>> = buildRequest(
       url = API.GET_REMOTE_TRANSFER_SUCCESS_VIDEO,
       fieldMap = mutableMapOf(
           "title" to title,
           "industry_path" to industry,
           "acreage" to size,
           "area" to area,
           "sort_field" to sortBy,
           "sort" to sortType.toString(),
           "page" to page.toString(),
           "rent" to rent
       ).also { map ->
           map["sign"] = API.sign(
               signatureToken = API.currentSignatureToken,
               fieldMap = map,
               method = "GET"
           )
       },
       method = METHOD.GET
   ).let {
       Log.d("---video","getTransferSuccessVideoFromRemote it = "+it)
       API.call(it)
   }


    /**
     * request a list of transfer-out orders from remote server base on page key.
     * */
    fun getTransferOutOrdersFromRemote(
        title: String,
        industry: String,
        size: String,
        area: String,
        sortBy: String,
        sortType: String,
        rent: String,
        page: Int,
        hot: Int?,
        top: Int?,
        recommend: Int?,
        city: String?
    ): APIRst<APIResp<HttpRespOrders>> = buildRequest(
        url = API.GET_REMOTE_TRANSFER_OUT_ORDERS,
        fieldMap = mutableMapOf(
            "title" to title,
            "industry_path" to industry,
            "acreage" to size,
            "area" to area,
            "sort_field" to sortBy,
            "sort" to sortType.toString(),
            "page" to page.toString(),
            "rent" to rent
        ).also { map ->
            city?.let {
                map["city"] = city
            }
            hot?.let {
                map["hot"] = it.toString()
            }
            top?.let {
                map["top"] = it.toString()
            }
            recommend?.let {
                map["recommend"] = it.toString()
            }
            map["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = map,
                method = "GET"
            )
        },
        method = METHOD.GET
    ).let {

        API.call(it)
    }

    /**
     * request a list of transfer-in orders from remote server base on page key.
     * */
    fun getTransferInOrdersFromRemote(
        title: String,
        industry: String,
        size: String,
        area: String,
        rent: String,
        sortBy: String,
        sortType: String,
        page: Int,
        hot: Int?,
        top: Int?,
        recommend: Int?,
        city: String? = null
    ): APIRst<APIResp<HttpRespOrders>> = buildRequest(
        url = API.GET_REMOTE_TRANSFER_IN_ORDERS,
        fieldMap = mutableMapOf(
            "title" to title,
            "industry_path" to industry,
            "acreage" to size,
            "rent" to rent,
            "area" to area,
            "sort_field" to sortBy,
            "sort" to sortType.toString(),
            "page" to page.toString()
        ).also { map ->
            city?.let {
                map["city"] = city
            }
            hot?.let {
                map["hot"] = it.toString()
            }
            top?.let {
                map["top"] = it.toString()
            }
            recommend?.let {
                map["recommend"] = it.toString()
            }
            map["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = map,
                method = "GET"
            )
        },
        method = METHOD.GET
    ).let {
        API.call(it)
    }

    fun getEditTransferOutOrderDetailFromRemote(
        shopID: String
    ): APIRst<APIResp<HttpRespOrderDetail>> = buildRequest(
        url = API.GET_EDIT_TRANSFER_OUT_ORDER,
        fieldMap = mutableMapOf(
            "id" to shopID
        ).also { map ->
            map["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = map,
                method = "GET"
            )
        },
        method = METHOD.GET
    ).let {
        API.call(it)
    }

    fun getSaveTransferOutOrderDetailFromRemote(
    ): APIRst<APIResp<HttpRespOrderDetail>> = buildRequest(
        url = API.GET_SAVE_TRANSFER_OUT_ORDER,
        fieldMap = mutableMapOf<String, String>().also { map ->
            map["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = map,
                method = "GET"
            )
        },
        method = METHOD.GET
    ).let {
        API.call(it)
    }


    fun getTransferOutOrderDetailFromRemote(
        shopID: String
    ): APIRst<APIResp<HttpRespOrderDetail>> = buildRequest(
        url = API.GET_REMOTE_TRANSFER_OUT_ORDER,
        fieldMap = mutableMapOf(
            "id" to shopID
        ).also { map ->

            map["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = map,
                method = "GET"
            )
        },
        method = METHOD.GET
    ).let {
        API.call(it)
    }

    fun getUserLikeShopFromRemote(
        city: String? = null,
        shopID: String,
        page:Int
    ): APIRst<APIResp<HttpRecommendOrder>> = buildRequest(
        url = API.GET_USER_LIKE_SHOP,
        fieldMap = mutableMapOf(
            "id" to shopID,"page" to page.toString()
        ).also { map ->
            city?.let {
                map["city_id"] = city
            }
            map["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = map,
                method = "GET"
            )
        },
        method = METHOD.GET
    ).let {
        Log.d("---recommend--", " it = " + it)
        API.call(it)
    }



    fun getSuccessVideoDetailFromRemote(
        shopID: String
    ): APIRst<APIResp<HttpSuccessVideoInfo>> = buildRequest(
        url = API.GET_REMOTE_SUCCESS_VIDEO_DETAILS,
        fieldMap = mutableMapOf(
            "id" to shopID
        ).also { map ->

            map["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = map,
                method = "GET"
            )
        },
        method = METHOD.GET
    ).let {
        API.call(it)
    }

    fun getSuccessVideoRecommendFromRemote(
        city: String? = null,
        shopID: String
    ): APIRst<APIResp<RecommendSuccessVideoList>> = buildRequest(
        url = API.GET_SUCCESS_VIDEO_RECOMMEND_SHOP,
        fieldMap = mutableMapOf(
            "id" to shopID
        ).also { map ->
            city?.let {
                map["city_id"] = city
            }
            map["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = map,
                method = "GET"
            )
        },
        method = METHOD.GET
    ).let {
        API.call(it)
    }



    fun getEditTransferInOrderDetailFromRemote(
        shopID: String
    ): APIRst<APIResp<HttpRespOrderDetail>> = buildRequest(
        url = API.GET_EDIT_TRANSFER_IN_ORDER,//编辑
        fieldMap = mutableMapOf(
            "id" to shopID
        ).also { map ->
            map["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = map,
                method = "GET"
            )
        },
        method = METHOD.GET
    ).let {
        API.call(it)
    }

    fun getTransferInOrderDetailFromRemote(
        shopID: String
    ): APIRst<APIResp<HttpRespOrderDetail>> = buildRequest(
        url = API.GET_REMOTE_TRANSFER_IN_ORDER,//编辑
        fieldMap = mutableMapOf(
            "id" to shopID
        ).also { map ->
            map["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = map,
                method = "GET"
            )
        },
        method = METHOD.GET
    ).let {
        API.call(it)
    }

    fun getMineTransferOutOrdersFromRemote(
        page: Int,
        city: String? = null
    ): APIRst<APIResp<HttpRespOrders>> = buildRequest(
        url = API.GET_MINE_TRANSFER_OUT_ORDERS,
        fieldMap = mutableMapOf(
            "page" to page.toString()
        ).also { map ->
            city?.let {
                map["city"] = city
            }
            map["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = map,
                method = "GET"
            )
        },
        method = METHOD.GET
    ).let {
        API.call(it)
    }

    fun getMineTransferInOrdersFromRemote(
        page: Int,
        city: String? = null
    ): APIRst<APIResp<HttpRespOrders>> = buildRequest(
        url = API.GET_MINE_TRANSFER_IN_ORDERS,
        fieldMap = mutableMapOf(
            "page" to page.toString()
        ).also { map ->
            city?.let {
                map["city"] = city
            }
            map["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = map,
                method = "GET"
            )
        },
        method = METHOD.GET
    ).let {
        API.call(it)
    }

    fun getRecommendedTransferOutOrdersFromRemote(
        page: Int,
        city: String? = null
    ): APIRst<APIResp<HttpRespOrders>> = buildRequest(
        url = API.GET_REMOTE_TRANSFER_OUT_ORDERS,
        fieldMap = mutableMapOf(
            "recommend" to "1",
            "page" to page.toString()
        ).also { map ->
            city?.let {
                map["city"] = city
            }
            map["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = map,
                method = "GET"
            )
        },
        method = METHOD.GET
    ).let {
        API.call(it)
    }

    fun getHomeRecommendedTransferOutOrdersFromRemote(
        page: Int,
        city: String? = null
    ): APIRst<APIResp<HttpRespOrders>> = buildRequest(
        url = API.GET_RECOMMEND_TRANSFER_OUT_ORDER,
        fieldMap = mutableMapOf(
            "page" to page.toString()
        ).also { map ->
            city?.let {
                map["city"] = city
            }
            map["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = map,
                method = "GET"
            )
        },
        method = METHOD.GET
    ).let {
        API.call(it)
    }


//    fun getHomeRecommendedTransferOutOrdersFromRemote(
//        page: Int,
//        city: String? = null
//    ): APIRst<APIResp<HttpRespOrders>> = buildRequest(
//        url = API.GET_RECOMMEND_TRANSFER_OUT_ORDER,
//        fieldMap = mutableMapOf(
//            "page" to page.toString()
//        ).also { map ->
//            city?.let {
//                map["city"] = city
//            }
//            map["sign"] = API.sign(
//                signatureToken = API.currentSignatureToken,
//                fieldMap = map,
//                method = "GET"
//            )
//        },
//        method = METHOD.GET
//    ).let {
//        Log.d("---home--","RecommendedTransferOut  it= "+it )
//        API.call(it)
//    }

    fun getRecommendedTransferInOrdersFromRemote(
        page: Int,
        city: String? = null
    ): APIRst<APIResp<HttpRespOrders>> = buildRequest(
        url = API.GET_REMOTE_TRANSFER_IN_ORDERS,
        fieldMap = mutableMapOf(
            "page" to page.toString()
        ).also { map ->
            city?.let {
                map["city"] = city
            }
            map["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = map,
                method = "GET"
            )
        },
        method = METHOD.GET
    ).let {
        API.call(it)
    }

    fun getHomeRecommendedTransferInOrdersFromRemote(
        page: Int,
        city: String? = null
    ): APIRst<APIResp<HttpRespOrders>> = buildRequest(
        url = API.GET_RECOMMEND_TRANSFER_IN_ORDER,
        fieldMap = mutableMapOf(
            "page" to page.toString()
        ).also { map ->
            city?.let {
                map["city"] = city
            }
            map["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = map,
                method = "GET"
            )
        },
        method = METHOD.GET
    ).let {
        API.call(it)
    }

    fun deleteTransferOutOrderFromRemote(
        shopID: String
    ): APIRst<APIResp<HttpRespEmpty>> = buildRequest(
        url = API.DELETE_TRANSFER_OUT_ORDER,
        fieldMap = mutableMapOf(
            "id" to shopID
        ).also { map ->
            map["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = map,
                method = "GET"
            )
        },
        method = METHOD.GET
    ).let {
        API.call(it)
    }

    fun deleteTransferInOrderFromRemote(
        shopID: String
    ): APIRst<APIResp<HttpRespEmpty>> = buildRequest(
        url = API.DELETE_TRANSFER_IN_ORDER,
        fieldMap = mutableMapOf(
            "id" to shopID
        ).also { map ->
            map["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = map,
                method = "GET"
            )
        },
        method = METHOD.GET
    ).let {
        API.call(it)
    }


    fun deleteFavorTransferOutOrderFromRemote(
        shopID: String
    ): APIRst<APIResp<HttpRespEmpty>> = buildRequest(
        url = API.DELETE_FAVOR_ORDER,
        fieldMap = mutableMapOf(
            "id" to shopID,
            "type" to "0"
        ).also { map ->
            map["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = map,
                method = "GET"
            )
        },
        method = METHOD.GET
    ).let {
        API.call(it)
    }


    fun refreshShopFromRemote(
        shopID: String,
        type: String
    ): APIRst<APIResp<HttpRespEmpty>> = buildRequest(
        url = API.REFRESH_SHOP,
        fieldMap = mutableMapOf(
            "id" to shopID,
            "data_type" to type
        ).also {
            it["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = it,
                method = "GET"
            )
        },
        method = METHOD.GET
    ).let {
        Log.e("---updata", " it = " + it)
        API.call(it)
    }

    fun deleteFavorTransferInOrderFromRemote(
        shopID: String
    ): APIRst<APIResp<HttpRespEmpty>> = buildRequest(
        url = API.DELETE_FAVOR_ORDER,
        fieldMap = mutableMapOf(
            "id" to shopID, "type" to "1"
        ).also { map ->
            map["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = map,
                method = "GET"
            )
        },
        method = METHOD.GET
    ).let {
        API.call(it)
    }


    fun deleteHistroyTransferOutOrderFromRemote(
    ): APIRst<APIResp<HttpRespEmpty>> = buildRequest(
        url = API.DELETE_HISTORY_ORDER,
        fieldMap = mutableMapOf(
            "type" to "0"
        ).also { map ->
            map["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = map,
                method = "GET"
            )
        },
        method = METHOD.GET
    ).let {
        API.call(it)
    }

    fun deleteHistroyTransferInOrderFromRemote(
    ): APIRst<APIResp<HttpRespEmpty>> = buildRequest(
        url = API.DELETE_HISTORY_ORDER,
        fieldMap = mutableMapOf(
            "type" to "1"
        ).also { map ->
            map["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = map,
                method = "GET"
            )
        },
        method = METHOD.GET
    ).let {
        API.call(it)
    }


    fun getFavoriteTransferOutOrdersFromRemote(
        page: Int
    ): APIRst<APIResp<HttpRespOrders>> = buildRequest(
        url = API.GET_FAVORITE,
        fieldMap = mutableMapOf(
            "p" to page.toString(),
            "type" to "0"
        ).also {
            it["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = it,
                method = "GET"
            )
        },
        method = METHOD.GET
    ).let {
        API.call(it)
    }

    fun getFavoriteTransferInOrdersFromRemote(
        page: Int
    ): APIRst<APIResp<HttpRespOrders>> = buildRequest(
        url = API.GET_FAVORITE,
        fieldMap = mutableMapOf(
            "p" to page.toString(),
            "type" to "1"
        ).also {
            it["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = it,
                method = "GET"
            )
        },
        method = METHOD.GET
    ).let {
        API.call(it)
    }

    fun getTransferOutOrdersBrowsingHistoryFromRemote(
        page: Int
    ): APIRst<APIResp<HttpRespOrders>> = buildRequest(
        url = API.GET_HISTORY,
        fieldMap = mutableMapOf(
            "p" to page.toString(),
            "type" to "0"
        ).also {
            it["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = it,
                method = "GET"
            )
        },
        method = METHOD.GET
    ).let {
        API.call(it)
    }

    fun getTransferInOrdersBrowsingHistoryFromRemote(
        page: Int
    ): APIRst<APIResp<HttpRespOrders>> = buildRequest(
        url = API.GET_HISTORY,
        fieldMap = mutableMapOf(
            "p" to page.toString(),
            "type" to "1"
        ).also {
            it["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = it,
                method = "GET"
            )
        },
        method = METHOD.GET
    ).let {
        API.call(it)
    }


    fun getPublicFromRemote(
    ): APIRst<APIResp<HttpRespReleaseOrders>> = buildRequest(
        url = API.GET_USER_PULISHED,
        fieldMap = mutableMapOf<String, String>().also {
            it["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = it,
                method = "GET"
            )
        },
        method = METHOD.GET
    ).let {
        API.call(it)
    }

    fun getProcessingFromRemote(
    ): APIRst<APIResp<HttpRespReleaseOrders>> = buildRequest(
        url = API.GET_USER_PROCESSING,
        fieldMap = mutableMapOf<String, String>().also {
            it["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = it,
                method = "GET"
            )
        },
        method = METHOD.GET
    ).let {
        API.call(it)
    }

    fun getSoldOutFromRemote(
    ): APIRst<APIResp<HttpRespReleaseOrders>> = buildRequest(
        url = API.GET_USER_SOLD_OUT,
        fieldMap = mutableMapOf<String, String>().also {
            it["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = it,
                method = "GET"
            )
        },
        method = METHOD.GET
    ).let {
        Log.d("---public--", "getSoldOutFromRemote it = " + it)
        API.call(it)
    }

    fun getFastTransferNum(): APIRst<APIResp<HttpCardFastObject>> = buildRequest(
    url = API.GET_FAST_TRANSFER_NUM,
    fieldMap = mutableMapOf<String, String>().also {
        it["sign"] = API.sign(
            signatureToken = API.currentSignatureToken,
            fieldMap = it,
            method = "GET"
        )
    },
    method = METHOD.GET
    ).let {
        API.call(it)
    }


    fun getFastMineTransferFromRemote(): APIRst<APIResp<HttpRespReleaseOrders>> = buildRequest(
        url = API.GET_FAST_MINE_TRANSFER_NUM,
        fieldMap = mutableMapOf<String, String>().also {
            it["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = it,
                method = "GET"
            )
        },
        method = METHOD.GET
    ).let {
        Log.d("---public--", "getSoldOutFromRemote it = " + it)
        API.call(it)
    }

}