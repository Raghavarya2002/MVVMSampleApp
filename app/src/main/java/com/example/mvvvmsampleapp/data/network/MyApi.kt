package com.example.mvvvmsampleapp.data.network

import com.example.mvvvmsampleapp.data.network.responses.AuthResponse
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyApi {
    @FormUrlEncoded
    @POST("login")
    suspend fun userLogin( // A suspending function is simply a function that can be  paused and resumed at a later time , so these type of function can execute a long running operation and wait for it to complete without blocking.
        @Field("email") email: String,
        @Field("password") password: String

    ): Response<AuthResponse>


    @FormUrlEncoded
    @POST("signup")
    suspend fun userSignup(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<AuthResponse>


    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): MyApi {

            val okkHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okkHttpClient)
                .baseUrl("https://api.simplifiedcoding.in/course-apis/mvvm/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}