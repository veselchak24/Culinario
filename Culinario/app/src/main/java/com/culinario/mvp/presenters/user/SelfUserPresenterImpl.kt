package com.culinario.mvp.presenters.user

import com.culinario.mvp.models.UserModel
import com.culinario.mvp.views.UserView

class SelfUserPresenterImpl(
    override val model: UserModel,
    override val view: UserView,
) : SelfUserPresenter {
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