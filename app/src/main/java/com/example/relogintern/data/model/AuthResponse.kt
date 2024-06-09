package com.example.relogintern.data.model


import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("roles")
    val roles: List<String>,
    @SerializedName("tokenType")
    val tokenType: String,
    @SerializedName("username")
    val username: String
)