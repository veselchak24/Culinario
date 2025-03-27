package com.culinario.mvp.models.repository

import com.culinario.backend.ABOUT_RYAN_GOSLING
import com.culinario.mvp.models.User

class UserRepositoryImpl : UserRepository {
    override fun getProfile(id: String): User =
        User(
            "85t6ir7f12v",
            "Ryan Gosling",
            "user@culinario.ru",
            ABOUT_RYAN_GOSLING,
            666,
            2,
            6000
        )

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