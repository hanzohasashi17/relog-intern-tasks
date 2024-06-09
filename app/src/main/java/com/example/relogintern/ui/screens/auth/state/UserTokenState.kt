package com.example.relogintern.ui.screens.auth.state

data class UserTokenState(
    val isLogged: Boolean = false,
    val errorMessage: String? = null
)
