package org.hyperskill.data.datasource.impl

import org.hyperskill.data.datasource.ChatDataSource
import org.hyperskill.data.model.ChatRoom
import org.hyperskill.data.model.UserConnection
import org.hyperskill.exceptions.ChatDoesNotExistException
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class ChatDataSourceImp(val db: CoroutineDatabase) : ChatDataSource {

    /**
     * getting collection of chats from MongoDB
     */
    val chats = db.getCollection<ChatRoom>()

    override suspend fun getAllChats(): List<ChatRoom> {
        return chats.find().toList()
    }

    override suspend fun getChatByChatName(chatName: String): ChatRoom? {
        return chats.findOne(ChatRoom::chatName eq chatName)
    }

    override suspend fun getChatByChatId(chatId: String): ChatRoom? {
        return chats.findOne(ChatRoom::chatId eq chatId)
    }

    override suspend fun addNewConnectionToChat(connection: UserConnection, chatName: String) {
        val chat = getChatByChatName(chatName)
        if (chat != null) {
            chat.userConnections += connection
            chats.deleteOne(ChatRoom::chatId eq chat.chatId)
            chats.insertOne(chat)
        } else {
            throw ChatDoesNotExistException()
        }
    }

    override suspend fun findUserConnectionToChat(connectionId: Int, chatName: String): UserConnection? {
        val chat = getChatByChatName(chatName)
        if (chat != null) {
            return chat.getUserConnectionById(connectionId)
        } else {
            throw ChatDoesNotExistException()
        }
    }

    override suspend fun deleteConnectionFromChat(connectionId: Int, chatName: String) {
        val chat = getChatByChatName(chatName)
        if (chat != null) {
            val connectionToDelete = chat.getUserConnectionById(connectionId)
            chat.userConnections.minus(connectionToDelete)
            chats.deleteOne(ChatRoom::chatId eq chat.chatId)
            chats.insertOne(chat)
        } else {
            throw ChatDoesNotExistException()
        }
    }

    override suspend fun addNewChat(chat: ChatRoom) {
        chats.insertOne(chat)
    }

}