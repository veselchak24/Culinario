package com.culinario.mvp.models

data class User(
    private val id: String,
    private var name: String,
    private var email: String? = null,
    private var about: String? = null,
    private var recipesId: List<String>? = null
) {
    val Id: String
        get() = this.id;

    var Name: String
        get() = this.name;
        set(value) {
            if (value.isEmpty())
                throw IllegalArgumentException("Name can't be empty")

            this.name = value
        }

    var Email: String?
        get() = this.email;
        set(value) {
            if (value.isNullOrEmpty() || CheckEmail(value))
                throw IllegalArgumentException("Invalid email")

            this.email = value
        }

    var About: String?
        get() = this.about
        set(value) {
            if (value.isNullOrEmpty())
                throw IllegalArgumentException("Invalid \"about\"")

            about = value
        }

    val RecipesId: List<String>?
        get() = recipesId
}

private fun CheckEmail(email: String): Boolean =
    Regex("[A-Za-z0-9-_.]+@[A-Za-z0-9-]+\\.[A-Za-z]{1,3}").matches(email)
