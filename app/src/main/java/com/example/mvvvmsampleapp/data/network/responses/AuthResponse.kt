package com.example.mvvvmsampleapp.data.network.responses

import com.example.mvvvmsampleapp.data.db.entities.User

data class AuthResponse(
    val isSuccessful: Boolean?,
    val message: String?,
    val user: User?

)