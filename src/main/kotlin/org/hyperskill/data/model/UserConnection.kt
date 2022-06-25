package org.hyperskill.data.model

import io.ktor.websocket.*
import kotlinx.serialization.Serializable
import java.util.concurrent.atomic.AtomicInteger

@Serializable
data class UserConnection(val user:User,val session:WebSocketSession){
    companion object {
        val lastId = AtomicInteger(0)
    }
    val connectionId = lastId.getAndIncrement()

}