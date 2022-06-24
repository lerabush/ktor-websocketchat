package org.hyperskill.data

import org.hyperskill.data.model.ChatRoom
import org.hyperskill.data.model.UserConnection

interface ChatDataSource {
    suspend fun getAllChats():List<ChatRoom>

    suspend fun getChatByChatName(chatName:String):List<ChatRoom>

    suspend fun addNewConnectionToChat(connection:UserConnection,chatName:String)

    suspend fun deleteConnectionFromChat(connectionId:Int,chatName:String)

    suspend fun addNewChat(chat:ChatRoom)
}