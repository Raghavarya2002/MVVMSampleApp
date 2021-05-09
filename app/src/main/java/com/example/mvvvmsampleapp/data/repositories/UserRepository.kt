package com.example.mvvvmsampleapp.data.repositories

import com.example.mvvvmsampleapp.data.network.MyApi
import com.example.mvvvmsampleapp.data.network.SafeApiRequest
import com.example.mvvvmsampleapp.data.network.responses.AuthResponse
import retrofit2.Response

class UserRepository : SafeApiRequest() {

    suspend fun userLogin(email: String, password: String): AuthResponse {

        return apiRequest { MyApi().userLogin(email, password) }


    }
}