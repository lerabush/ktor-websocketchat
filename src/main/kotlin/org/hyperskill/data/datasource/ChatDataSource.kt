package org.hyperskill.data.datasource

import org.hyperskill.data.model.ChatRoom
import org.hyperskill.data.model.UserConnection

/**
 * Chat data source - for managing queries to MongoDB for working with chat collection
 *
 */
interface ChatDataSource {
    suspend fun getAllChats():List<ChatRoom>

    /**
     * Get chat by chat name
     *
     * @param chatName
     * @return existing chat or ChatRoom = null if the specified chat doesn't exist
     */
    suspend fun getChatByChatName(chatName:String): ChatRoom?

    suspend fun getChatByChatId(chatId:String):ChatRoom?

    /**
     * Add new user connection to the chat
     *
     * @param connection
     * @param chatName
     */
    suspend fun addNewConnectionToChat(connection:UserConnection,chatName:String)

    /**
     * Delete user connection from the chat
     *
     * @param connectionId
     * @param chatName
     */
    suspend fun deleteConnectionFromChat(connectionId:Int,chatName:String)

    /**
     * Creating new chat room
     *
     * @param chat
     */
    suspend fun addNewChat(chat:ChatRoom)
    suspend fun findUserConnectionToChat(connectionId: Int, chatName: String): UserConnection?
}