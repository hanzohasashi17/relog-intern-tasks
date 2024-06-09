package com.example.relogintern.data.repository

import com.example.relogintern.data.datasource.AuthNetworkDataSourceImpl
import com.example.relogintern.data.datasource.AuthRepository
import com.example.relogintern.data.model.AuthRequest
import com.example.relogintern.data.model.AuthResponse
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authNetworkDataSourceImpl: AuthNetworkDataSourceImpl,
) : AuthRepository {

    override suspend fun signUp(body: AuthRequest): AuthResponse {
        return authNetworkDataSourceImpl.signUp(body)
    }

    override suspend fun signIn(body: AuthRequest): AuthResponse {
        return authNetworkDataSourceImpl.signIn(body)
    }
}