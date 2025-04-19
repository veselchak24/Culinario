package com.culinario.mvp.presenters.user

import com.culinario.mvp.presenters.IPresenter

interface SelfUserPresenter : IPresenter{

    fun authenticate(password: String): Boolean

    fun changePassword(oldPassword: String, newPassword: String): Boolean

    fun resetPassword(): Boolean

    fun getProfileData(): Map<String, Any>

    fun updateProfile(newData: Map<String, Any>): Boolean
}