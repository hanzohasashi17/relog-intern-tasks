package com.example.relogintern.data.api

import com.example.relogintern.data.model.AuthRequest
import com.example.relogintern.data.model.AuthResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/V1/signup")
    suspend fun signUp(@Body body: AuthRequest): AuthResponse

    @POST("auth/V1/signin")
    suspend fun signIn(@Body body: AuthRequest): AuthResponse
}