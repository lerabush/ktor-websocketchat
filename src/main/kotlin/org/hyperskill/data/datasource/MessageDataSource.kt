package org.hyperskill.data

import org.hyperskill.data.model.Message

interface MessageDataSource {
    suspend fun getAllMessagesByChaId(chatId:String):List<Message>

    suspend fun insertMessage(message: Message)

    suspend fun deleteAllMessagesByChatId(chatId:String)
}