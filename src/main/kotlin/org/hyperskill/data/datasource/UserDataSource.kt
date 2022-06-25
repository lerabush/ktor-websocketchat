package org.hyperskill.data.datasource

import org.hyperskill.data.model.User

interface UserDataSource {
    suspend fun getAllUsers():List<User>

    suspend fun getUserById(userId:String):User?

    suspend fun getUserByUsername(username:String):User?

    suspend fun addNewUser(user:User)
}