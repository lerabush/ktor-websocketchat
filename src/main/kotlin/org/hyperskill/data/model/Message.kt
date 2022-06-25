package org.hyperskill.data.model

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId


@Serializable
data class Message(
    @BsonId
    val messageId:String = ObjectId().toString(),
    val chatId:String,
    val text: String,
    val senderUsername:String,
    val timeStamp:Long
)