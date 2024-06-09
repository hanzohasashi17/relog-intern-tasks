package com.example.relogintern.data.datasource

import com.example.relogintern.data.model.AuthRequest
import com.example.relogintern.data.model.AuthResponse

interface AuthRepository {
    suspend fun signUp(body: AuthRequest): AuthResponse

    suspend fun signIn(body: AuthRequest): AuthResponse
}