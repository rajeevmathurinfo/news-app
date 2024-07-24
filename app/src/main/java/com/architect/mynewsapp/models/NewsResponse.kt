package com.architect.mynewsapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
) : Parcelable

