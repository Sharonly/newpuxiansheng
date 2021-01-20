package com.puxiansheng.logic.bean

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.puxiansheng.logic.bean.converter.ArtcleListConverter
import com.puxiansheng.logic.bean.converter.MenuListConverter
import com.puxiansheng.logic.bean.converter.StringListConverter
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "table_recommend_article")
data class ArticleBean (  @ColumnInfo(name = "_article_id")
                          @SerializedName("id")
                          var shopID: Int = 0,

                          @SerializedName("title")
                          @ColumnInfo(name = "_article_title")
                          var title: String = "",

                          @SerializedName("jump_type")
                          @ColumnInfo(name = "_article_jump_type")
                          var jump_type: Int = 0,

                          @SerializedName("jump_view")
                          @ColumnInfo(name = "_article_jump_view")
                          var jump_view: String = "",

                          @SerializedName("jump_param")
                          @ColumnInfo(name = "_article_jump_param")
                          var jump_param: String = ""

) : Parcelable

