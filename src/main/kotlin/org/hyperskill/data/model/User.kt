package org.hyperskill.data

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val userId: String,
    val userName: String,
    val password: String
)
