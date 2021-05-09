package com.example.mvvvmsampleapp.data.repositories

import com.example.mvvvmsampleapp.data.network.MyApi
import com.example.mvvvmsampleapp.data.network.responses.AuthResponse
import retrofit2.Response

class UserRepository {

    suspend fun userLogin(email: String, password: String): Response<AuthResponse> {

        return MyApi().userLogin(email, password)


    }
}