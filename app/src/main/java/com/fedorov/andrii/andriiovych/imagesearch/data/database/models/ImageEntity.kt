package com.fedorov.andrii.andriiovych.imagesearch.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "landscapeUrl") val landscapeUrl: String,
    @ColumnInfo(name = "portraitUrl") val portraitUrl: String
)