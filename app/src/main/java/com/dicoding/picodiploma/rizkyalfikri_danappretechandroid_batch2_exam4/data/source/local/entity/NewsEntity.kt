package com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tb_news")
data class NewsEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    @ColumnInfo
    var category: String,
    @ColumnInfo
    var content: String,
    @ColumnInfo
    var imageUrl: String,
    @ColumnInfo
    var title: String
) : Parcelable