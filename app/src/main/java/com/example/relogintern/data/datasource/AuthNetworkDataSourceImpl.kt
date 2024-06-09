package com.example.relogintern.data.datasource

import com.example.relogintern.data.api.AuthService
import com.example.relogintern.data.model.AuthRequest
import com.example.relogintern.data.model.AuthResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthNetworkDataSourceImpl @Inject constructor(
    private val authService: AuthService
) : AuthNetworkDataSource {

    override suspend fun signUp(body: AuthRequest): AuthResponse {
        return withContext(Dispatchers.IO) {
            authService.signUp(body)
        }
    }

    override suspend fun signIn(body: AuthRequest): AuthResponse {
        return withContext(Dispatchers.IO) {
            authService.signIn(body)
        }
    }

}