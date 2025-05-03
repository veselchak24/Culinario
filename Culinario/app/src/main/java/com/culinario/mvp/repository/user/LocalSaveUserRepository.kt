package com.culinario.repository.user

import android.content.Context
import com.culinario.backend.PROFILE_JSON_FILE_NAME
import com.culinario.mvp.models.User
import com.culinario.repository.IRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.File
import java.lang.reflect.Type


class LocalSaveUserRepository(
    private val context: Context
) : IRepository<User> {
    private val file = File(context.applicationContext.filesDir, PROFILE_JSON_FILE_NAME)
    private var users = mutableListOf<User>()

    init {
        val listType: Type =
            TypeToken.getParameterized(MutableList::class.java, User::class.java).type
        users = Gson().fromJson(file.readText(), listType)

        println(users)
    }

    override fun add(user: User): Boolean {
        val result = users.add(user)
        file.writeText(GsonBuilder().setPrettyPrinting().create().toJson(users))
        return result
    }

    override fun get(id: UInt): User {
        return users.first {
            it.Id.toUInt() == id
        }
    }

    override fun update(tModel: User): Boolean {
        TODO("Not yet implemented")
    }

    override fun delete(id: UInt): Boolean {
        TODO("Not yet implemented")
    }

    fun getAllUsers(): List<User> {
        return users
    }

    fun authenticate(password: String): Boolean {
        TODO("Not yet implemented")
    }

    fun changePassword(oldPassword: String, newPassword: String): Boolean {
        TODO("Not yet implemented")
    }

    fun resetPassword(): Boolean {
        TODO("Not yet implemented")
    }

    fun getProfileData(): Map<String, Any> {
        TODO("Not yet implemented")
    }

    fun updateProfile(newData: Map<String, Any>): Boolean {
        TODO("Not yet implemented")
    }
}