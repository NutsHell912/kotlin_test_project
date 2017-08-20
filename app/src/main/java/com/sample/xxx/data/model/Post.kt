package com.sample.xxx.data.model

import com.google.gson.annotations.SerializedName

data class Post(
        @SerializedName("userId")
        val userId: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("body")
        val body: String
)