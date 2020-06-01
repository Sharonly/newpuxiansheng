package com.puxiansheng.logic.bean.http

import com.google.gson.annotations.SerializedName
import com.puxiansheng.logic.bean.BannerImage

data class HttpRespBannerImages(
    @SerializedName("result")
    val banners:List<BannerImage>? = null
)


data class HttpRespBanner(
    @SerializedName("result")
    val bannerImg:BannerImage? = null
)