package org.hyperskill.data

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId


@Serializable
data class Message(
    @BsonId
    val messageId:String,
    val chatId:String,
    val senderId:String,
    val text: String,
    val senderUsername:String,
    val timeStamp:String
)