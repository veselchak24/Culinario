package com.culinario.mvp.models

data class User(
    private val _id: String,
    private var _name: String,
    private var _email: String? = null,
    private var _about: String? = null,
    private var _likesCount: Int,
    private var _recipesCount: Int,
    private var _watchCount: Int
) {
    val Id: String
        get() = this._id;

    var Name: String
        get() = this._name;
        set(value) {
            if (value.isEmpty())
                throw IllegalArgumentException("Name can't be empty")

            this._name = value
        }

    var Email: String?
        get() = this._email;
        set(value) {
            if (value.isNullOrEmpty() || CheckEmail(value))
                throw IllegalArgumentException("Invalid email")

            this._email = value
        }

    var About: String?
        get() = this._about
        set(value) {
            if (value.isNullOrEmpty())
                throw IllegalArgumentException("Invalid \"about\"")

            _about = value
        }

    var LikesCount: Int
        get() = this._likesCount
        set(value) {
            if (value < 0)
                throw IllegalArgumentException("Invalid likes count")

            _likesCount = value
        }

    var RecipeCount: Int
        get() = _recipesCount
        set(value) {
            if (value < 0)
                throw IllegalArgumentException("Invalid recipes count")

            _recipesCount = value
        }

    var WatchCount: Int
        get() = _watchCount
        set(value) {
            if (value < 0)
                throw IllegalArgumentException("Invalid watch count")

            _watchCount = value
        }
}

private fun CheckEmail(email: String): Boolean =
    Regex("[A-Za-z0-9-_.]+@[A-Za-z0-9-]+\\.[A-Za-z]{1,3}").matches(email)
