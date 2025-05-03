package com.culinario.mvp.presenters.user

import com.culinario.mvp.models.User
import com.culinario.mvp.presenters.IPresenter
import com.culinario.repository.user.UserRepository
//import com.culinario.mvp.views.User as UserView

class SelfUserPresenterImpl(
    override val repository: UserRepository,
//    override val view: UserView,
) : IPresenter<User>, SelfUserPresenter {
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