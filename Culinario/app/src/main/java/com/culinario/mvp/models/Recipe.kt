package com.culinario.mvp.models

import com.culinario.backend.interfaces.IOnDeserialize
import com.culinario.backend.interfaces.IOnSerialize

/**
 * Временный класс, который будет уничтожен
 */
data class Recipe(var name: String, var likes: Int, var comments: Int, var eachOtherField: Int) : IOnSerialize,
    IOnDeserialize {
    override fun onSerialize() {
        println("Serialized >>> ${toString()}")
    }

    override fun onDeserialize() {
        println("Deserialized >>> ${toString()}")
    }

    override fun toString(): String {
        return "Recipe: $name, likes: $likes, comments: $comments..."
    }
}