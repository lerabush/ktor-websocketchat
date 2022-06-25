package org.hyperskill.data.model

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class ChatRoom(
    val chatName: String,
    val adminId: String, @BsonId
    val chatId: String = ObjectId().toString(),
    var userConnections: List<UserConnection> = mutableListOf()
) {
    fun getUserConnectionById(connectionId: Int): UserConnection? {
        for (connection in this.userConnections) {
            if (connection.connectionId == connectionId) return connection
        }
        return null
    }
}
