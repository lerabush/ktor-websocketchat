package org.hyperskill.data.datasource.impl

import org.hyperskill.data.datasource.UserDataSource
import org.hyperskill.data.model.User
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class UserDataSourceImpl(val db: CoroutineDatabase) : UserDataSource {
    val users = db.getCollection<User>()
    override suspend fun getAllUsers(): List<User> {
        return users.find().toList()
    }

    override suspend fun getUserById(userId: String): User? {
        return users.findOne(User::userId eq userId)
    }

    override suspend fun getUserByUsername(username: String): User? {
        return users.findOne(User::userName eq username)
    }

    override suspend fun addNewUser(user: User) {
       users.insertOne(user)
    }

}