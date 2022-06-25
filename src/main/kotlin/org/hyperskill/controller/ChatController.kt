package org.hyperskill.controller

import io.ktor.websocket.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.hyperskill.data.datasource.ChatDataSource
import org.hyperskill.data.datasource.MessageDataSource
import org.hyperskill.data.model.ChatRoom
import org.hyperskill.data.model.Message
import org.hyperskill.data.model.UserConnection
import org.hyperskill.exceptions.ChatAlreadyExistsException
import org.hyperskill.exceptions.ChatDoesNotExistException
import java.util.concurrent.ConcurrentHashMap

/**
 * Chat messages controller processes incoming requests for actions with chat
 * @property messageDataSource
 */

class ChatController(
    private val messageDataSource: MessageDataSource,
    private val chatDataSource: ChatDataSource
) {

    suspend fun onJoin(userConnection: UserConnection, chatName: String) {
        chatDataSource.addNewConnectionToChat(userConnection, chatName)
    }

    /**
     * Disconnect userConnection from specifiedChat
     * finding connection by its id from chat user connections, closing WebSocketSession
     * and deleting it from chat user connections
     *
     * @param userConnectionId
     * @param chatName
     */
    suspend fun disconnect(userConnectionId: Int, chatName: String) {
        val userConnection = chatDataSource.findUserConnectionToChat(userConnectionId, chatName)
        userConnection?.session?.close()
        chatDataSource.deleteConnectionFromChat(userConnectionId, chatName)
    }

    suspend fun createNewChat(newChatName: String, adminId: String) {
        val chat = chatDataSource.getChatByChatName(newChatName)
        if (chat != null) throw ChatAlreadyExistsException()

        chatDataSource.addNewChat(ChatRoom(adminId = adminId, chatName = newChatName))
    }

    suspend fun getAllChats(): List<ChatRoom> {
        return chatDataSource.getAllChats()
    }

    suspend fun sendMessage(messageText: String, senderName: String, chatId: String) {
        val chat = chatDataSource.getChatByChatId(chatId) ?: throw ChatDoesNotExistException()

        /**
         * Retrieving user connections of the chat to which we want send the message.
         * Then sending encoded message to all sockets of these user connections
         */
        val userConnections = chat.userConnections
        userConnections.forEach { member ->
            /**
             * creating object of Message class
             */
            val message = Message(
                text = messageText,
                senderUsername = senderName,
                chatId = chatId,
                timeStamp = System.currentTimeMillis()
            )
            messageDataSource.insertMessage(message)

            val parsedMessage = Json.encodeToString(message)
            member.session.send(Frame.Text(parsedMessage))
        }

    }

    suspend fun getAllMessagesFromChat(chatId: String): List<Message> {
        return messageDataSource.getAllMessagesByChatId(chatId)
    }

    suspend fun clearChatMessages(chatId: String) {
        messageDataSource.deleteAllMessagesByChatId(chatId)
    }


}