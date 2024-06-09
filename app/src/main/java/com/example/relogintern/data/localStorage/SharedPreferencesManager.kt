package com.example.relogintern.data.localStorage

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(private val context: Context) {
    private val sharedPref: SharedPreferences by lazy {
        context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    }

    fun saveToken(token: String) {
        with(sharedPref.edit()) {
            if (token.isNotEmpty()) {
                putString("token", "Bearer $token")
                apply()
            }
        }
    }

    fun getToken(): String? {
        return sharedPref.getString("token", null)
    }

    fun deleteToken() {
        with(sharedPref.edit()) {
            remove("token")
            apply()
        }
    }
}