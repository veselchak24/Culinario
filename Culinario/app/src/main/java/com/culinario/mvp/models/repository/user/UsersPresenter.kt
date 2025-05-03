package com.culinario.mvp.presenters.user

import com.culinario.mvp.models.User
import com.culinario.mvp.presenters.IPresenter
import com.culinario.mvp.repository.UserRepository
import com.culinario.mvp.views.UserView

class UsersPresenter(
    override val repository: UserRepository,
    override val view: UserView,
) : IPresenter<User> {
    override fun loadData(id: UInt) {
        TODO("Not yet implemented")
    }

    override fun saveData(data: User) {
        TODO("Not yet implemented")
    }

}