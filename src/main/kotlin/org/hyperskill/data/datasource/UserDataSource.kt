package org.hyperskill.data

import org.hyperskill.data.model.User

interface UserDataSource {
    suspend fun getAllUsers():List<User>

    suspend fun getUserById(userId:String)

    suspend fun addNewUser(user:User)
}