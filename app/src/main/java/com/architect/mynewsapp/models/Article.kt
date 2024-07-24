package com.architect.mynewsapp.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "articles")
data class Article(
    @PrimaryKey val url: String,
    val title: String,
    val description: String? = null,
    val urlToImage: String? = null,
    val publishedAt: String? = null
) : Parcelable

