package com.aprass.sportnews

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val title: String,
    val date: String,
    val content: String,
    val image: String,
) : Parcelable
