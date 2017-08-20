package com.sample.xxx.data.model

import com.google.gson.annotations.SerializedName

data class Photo(
        @SerializedName("albumId")
        val albumId: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("url")
        val url: String
)
