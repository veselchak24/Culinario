package com.culinario.mvp.repository

import com.culinario.mvp.models.User
import okhttp3.Response

object UserRepository : IRepository<User> {
    override fun get(id: UInt, outTModel: User): Response {
        TODO("Not yet implemented")
    }

    override fun add(newTModel: User): Response {
        TODO("Not yet implemented")
    }

    override fun update(tModel: User): Response {
        TODO("Not yet implemented")
    }

    override fun delete(id: UInt): Response {
        TODO("Not yet implemented")
    }
}