package com.puxiansheng.logic.bean

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.puxiansheng.logic.bean.converter.MenuListConverter
import com.puxiansheng.logic.bean.converter.StringListConverter
import kotlinx.android.parcel.Parcelize

@Parcelize
@TypeConverters(MenuListConverter::class, StringListConverter::class)
data class ArticleBean (  @ColumnInfo(name = "_article_id")
                          var shopID: Long = 0,

                          @ColumnInfo(name = "_article_title")
                          var title: String = "",

                          @SerializedName("_article_jump_type")
                          var jump_type: Int = 0,

                          @SerializedName("_article_jump_view")
                          var jump_view: String = "",

                          @SerializedName("_article_jump_param")
                          var jump_param: String = ""

) : Parcelable

