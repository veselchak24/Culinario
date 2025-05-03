package com.culinario.repository.user

import com.culinario.repository.IRepository
import com.culinario.mvp.models.User

object UserRepository : IRepository<User> {
    override fun get(id: UInt): User {
        TODO("Not yet implemented")
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

    override fun add(newTModel: User): Boolean {
        TODO("Not yet implemented")
    }

    override fun update(tModel: User): Boolean {
        TODO("Not yet implemented")
    }

    override fun delete(id: UInt): Boolean {
        TODO("Not yet implemented")
    }
}