package org.hyperskill.data

import kotlinx.serialization.Serializable

@Serializable
data class ChatRoom(
    val chatId:String,
    val chatName:String,
    val adminId: String,
    val chatMembers:List<User>,
)
