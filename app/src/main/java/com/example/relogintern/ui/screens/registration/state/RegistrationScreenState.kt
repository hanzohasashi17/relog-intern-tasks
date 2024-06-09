package com.example.relogintern.ui.screens.registration.state

import com.example.relogintern.data.model.AuthResponse

data class RegistrationScreenState(
    val authData: AuthResponse? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)
