package com.culinario.mvp.models.repository

interface UserRepository {
    fun getProfile(id: String) {

    }

    fun authenticate(password: String): Boolean {
        TODO("Реализовать проверку пароля")
    }

    fun changePassword(oldPassword: String, newPassword: String): Boolean {
        TODO("Реализовать смену пароля")
    }

    fun resetPassword(): Boolean {
        TODO("Реализовать сброс пароля")
    }

    fun getProfileData(): Map<String, Any> {
        TODO("Реализовать получение данных профиля")
    }

    fun updateProfile(newData: Map<String, Any>): Boolean {
        TODO("Реализовать обновление профиля")
    }
}
