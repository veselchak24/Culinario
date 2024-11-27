package com.culinario.mvp.models

data class User(
    private val _id: String,
    private var _name: String,
    private var _email: String? = null,
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
            if (value.isNullOrEmpty())
                this._email = null
            else
                if (CheckEmail(value))
                    this._email = value
            else
                throw IllegalArgumentException("Invalid email")
        }

}

private fun CheckEmail(email: String): Boolean =
    Regex("[A-Za-z0-9-_.]+@[A-Za-z0-9-]+\\.[A-Za-z]{1,3}").matches(email)
