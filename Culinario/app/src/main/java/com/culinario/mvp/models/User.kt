package com.culinario.mvp.models

data class User(
    private val _id: String,
    private var _name: String,
    private var _email: String? = null,
    private var _about: String? = null,
    private var _recipesId: List<String>? = null
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

    val RecipesId: List<String>?
        get() = _recipesId
}

private fun CheckEmail(email: String): Boolean =
    Regex("[A-Za-z0-9-_.]+@[A-Za-z0-9-]+\\.[A-Za-z]{1,3}").matches(email)
