package com.culinario.mvp.models.repository.user

import android.content.Context
import com.culinario.backend.PROFILE_JSON_FILE_NAME
import com.culinario.mvp.models.User
import com.google.gson.Gson
import java.io.File

class LocalSaveUserRepository (
    private val context: Context
) : UserRepository {
    private val file = File(context.applicationContext.filesDir, PROFILE_JSON_FILE_NAME)
    private var users = mutableListOf<User>()

    init {
        if (!file.exists()) {
            file.createNewFile()
            file.writeText("[ ]")
        }
    }

    fun addUser(user: User) {
        users.add(user)

        file.writeText(Gson().toJson(users))
    }

    override fun getProfile(id: String): User {
        return users.first {
            it.Id == id
        }
    }

    override fun authenticate(password: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun changePassword(oldPassword: String, newPassword: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun resetPassword(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getProfileData(): Map<String, Any> {
        TODO("Not yet implemented")
    }

    override fun updateProfile(newData: Map<String, Any>): Boolean {
        TODO("Not yet implemented")
    }
}