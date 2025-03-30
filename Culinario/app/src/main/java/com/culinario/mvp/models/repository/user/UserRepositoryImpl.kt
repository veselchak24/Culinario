package com.culinario.mvp.models.repository.user

import com.culinario.backend.ABOUT_RYAN_GOSLING
import com.culinario.mvp.models.User

class UserRepositoryImpl : UserRepository {
    private val users = mutableListOf (
        User(
            "85t6ir7f12v",
            "Ryan Gosling",
            "user@culinario.ru",
            ABOUT_RYAN_GOSLING,
            listOf()
        ),
        User(
            "WaAWgH3212",
            "Валерий Альбертович",
            "valera@yandex.ru",
            "privet, ya Valera.",
            listOf("11111111")
        ),
        User (
            "24DR1EFAwd",
            "Антон Павлович",
            "tosha2x2@gmail.com",
            "Tosha t2x2",
            listOf("11111112")
        )
    )

    override fun addUser(user: User) {
        users.add(user)
    }

    override fun commit() {
        TODO("Not yet implemented")
    }

    override fun getUserById(id: String): User {
        println(id)

        return users.first { user ->
            user.Id == id
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