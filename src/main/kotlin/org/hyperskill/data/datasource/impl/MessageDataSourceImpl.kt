package org.hyperskill.data.datasource.impl

import org.hyperskill.data.datasource.MessageDataSource
import org.hyperskill.data.model.Message
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class MessageDataSourceImpl(val db: CoroutineDatabase) : MessageDataSource {

    val messages = db.getCollection<Message>()

    override suspend fun getAllMessagesByChatId(chatId: String): List<Message> {
        return messages.find(Message::chatId eq chatId).toList()
    }

    override suspend fun insertMessage(message: Message) {
        messages.insertOne(message)
    }

    override suspend fun deleteAllMessagesByChatId(chatId: String) {
        messages.deleteMany(Message::chatId eq chatId)
    }

}