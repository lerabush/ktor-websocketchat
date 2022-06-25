package org.hyperskill.data.datasource

import org.hyperskill.data.model.Message

/**
 * Message data source - for managing queries to MongoDB for working with message collection
 *
 */
interface MessageDataSource {

    /**
     * Get all messages by chat id from the chat
     *
     * @param chatId
     * @return all messages from the specified chat
     */
    suspend fun getAllMessagesByChatId(chatId:String):List<Message>

    /**
     * Adding new message from some user to the message collection
     *
     * @param message
     */
    suspend fun insertMessage(message: Message)

    /**
     * Delete all messages by chat id - can be done by an owner of the chat
     *
     * @param chatId
     */
    suspend fun deleteAllMessagesByChatId(chatId:String)
}