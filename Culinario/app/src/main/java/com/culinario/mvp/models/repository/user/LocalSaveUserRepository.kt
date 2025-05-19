package com.culinario.mvp.models.repository.user

import android.content.Context
import com.culinario.backend.PROFILE_JSON_FILE_NAME
import com.culinario.mvp.models.User
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.File
import java.lang.reflect.Type


class LocalSaveUserRepository (
    private val context: Context
) : UserRepository {
    private val file = File(context.applicationContext.filesDir, PROFILE_JSON_FILE_NAME)
    private var users = mutableListOf<User>()

    init {
        val listType: Type = TypeToken.getParameterized(MutableList::class.java, User::class.java).type
        users = Gson().fromJson(file.readText(),listType)
    }

    override fun addUser(user: User) {
        users.add(user)
    }

    override fun commit() {
        file.writeText(GsonBuilder().setPrettyPrinting().create().toJson(users))
    }

    override fun getUserById(id: String): User {
        return users.first {
            it.id == id
        }
    }

    override fun getAllUsers(): List<User> {
        return users
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