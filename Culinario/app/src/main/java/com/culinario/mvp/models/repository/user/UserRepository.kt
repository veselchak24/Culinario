package com.culinario.mvp.models.repository.user

import com.culinario.mvp.models.User

interface UserRepository {
    fun addUser(user: User)

    fun commit()

    fun getUserById(id: String): User

    fun getAllUsers(): List<User>

    fun authenticate(password: String): Boolean

    fun changePassword(oldPassword: String, newPassword: String): Boolean

    fun resetPassword(): Boolean

    fun getProfileData(): Map<String, Any>

    fun updateProfile(newData: Map<String, Any>): Boolean
}