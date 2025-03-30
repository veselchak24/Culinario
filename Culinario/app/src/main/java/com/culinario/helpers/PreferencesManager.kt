package com.culinario.helpers

import android.content.Context
import android.content.SharedPreferences


class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("Preferences", Context.MODE_PRIVATE)

    fun saveData(key: String, value: String) {
        val editor = sharedPreferences.edit()

        editor.putString(key, value)
        editor.apply()
    }

    fun hasKey(key: String): Boolean {
        return sharedPreferences.contains(key)
    }

    fun getData(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }
}