package com.Appjam.GaCi.global.auth.service

import com.Appjam.GaCi.domain.user.entity.Role
import com.Appjam.GaCi.domain.user.entity.User

data class OAuthAttributes(
    val attributes: Map<String, Any>,
    val nameAttributeKey: String,
    val name: String,
    val email: String,
    val picture: String
) {
    companion object {
        fun of(registrationId: String, userNameAttributeName: String, attributes: Map<String, Any>): OAuthAttributes {
            return ofGoogle(userNameAttributeName, attributes)
        }

        private fun ofGoogle(userNameAttributeName: String, attributes: Map<String, Any>): OAuthAttributes {
            return OAuthAttributes(
                name = attributes["name"] as String,
                email = attributes["email"] as String,
                picture = attributes["picture"] as String,
                attributes = attributes,
                nameAttributeKey = userNameAttributeName
            )
        }
    }

    fun toEntity(): User {
        return User(
            email = email, role = Role.USER, name = name, picture = picture
        )
    }
}
