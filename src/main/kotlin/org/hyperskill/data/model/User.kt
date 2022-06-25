package org.hyperskill.data.model

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class User(
    @BsonId
    val userId:String = ObjectId().toString(),
    val userName: String,
    val password: String
)
