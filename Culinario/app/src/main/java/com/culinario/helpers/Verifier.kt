package com.culinario.helpers

object Verifier {
    fun CheckEmail(email: String): Boolean =
        Regex("[A-Za-z0-9-_.]+@[A-Za-z0-9-]+\\.[A-Za-z]{1,3}").matches(email)

}