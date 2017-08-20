package com.sample.nutshell.data.network

import com.google.gson.annotations.SerializedName

data class UserDto(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("address")
        val address: AdressDto
)

data class AdressDto(
        @SerializedName("street")
        val street: String,
        @SerializedName("suite")
        val suite: String,
        @SerializedName("city")
        val city: String

)