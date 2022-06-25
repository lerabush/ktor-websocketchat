package org.hyperskill.di

import org.hyperskill.data.datasource.MessageDataSource
import org.hyperskill.data.datasource.impl.MessageDataSourceImpl
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val mainModule = module {
    single {
        KMongo.createClient()
            .coroutine
            .getDatabase("websocket_chat")

    }
    single<MessageDataSource> {
        MessageDataSourceImpl(get())
    }

}