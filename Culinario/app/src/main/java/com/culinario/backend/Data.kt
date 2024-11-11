package com.culinario.backend

import kotlinx.serialization.Serializable

//дата-класс из документации по сериализации (будет уничтожен позже)
@Serializable
data class Data(var a: Int, var b: String)