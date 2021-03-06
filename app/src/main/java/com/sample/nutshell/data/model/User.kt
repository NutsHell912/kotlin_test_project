package com.sample.nutshell.data.model

import com.sample.nutshell.data.network.UserDto

data class User(
        val id: Int,
        val name: String,
        val address: String,
        val email: String
) {
    constructor(userDto: UserDto) : this(
            id = userDto.id,
            name = userDto.name,
            address = userDto.address.street + " " + userDto.address.suite + " "
                    + userDto.address.city,
            email = userDto.email
    )
}
