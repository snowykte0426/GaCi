package com.Appjam.GaCi.global.auth.dto

import com.Appjam.GaCi.domain.user.entity.User
import java.io.Serializable

data class SessionUser(
    val name: String, val email: String, val picture: String?
) : Serializable {
    constructor(user: User) : this(
        name = user.name ?: "", email = user.email, picture = user.picture
    )
}