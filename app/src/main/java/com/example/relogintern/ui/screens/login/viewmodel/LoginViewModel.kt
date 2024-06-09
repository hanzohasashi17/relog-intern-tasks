package com.example.relogintern.ui.screens.login.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.relogintern.data.localStorage.SharedPreferencesManager
import com.example.relogintern.data.model.AuthRequest
import com.example.relogintern.data.repository.AuthRepositoryImpl
import com.example.relogintern.ui.screens.registration.state.RegistrationScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepositoryImpl: AuthRepositoryImpl,
    private val sharedPreferencesManager: SharedPreferencesManager
): ViewModel() {
    private val _loginScreenState = MutableStateFlow(RegistrationScreenState())
    val loginScreenState = _loginScreenState.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginScreenState.update { it.copy(isLoading = true, error = null) }
            try {
                val authUser = authRepositoryImpl.signIn(body = AuthRequest(email, password))
                _loginScreenState.update { it.copy(isLoading = false, authData = authUser) }
                sharedPreferencesManager.saveToken(authUser.accessToken)
            } catch (e: Exception) {
                _loginScreenState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }
}