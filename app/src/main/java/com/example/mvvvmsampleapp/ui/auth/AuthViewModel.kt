package com.example.mvvvmsampleapp.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mvvvmsampleapp.data.repositories.UserRepository
import com.example.mvvvmsampleapp.util.ApiExceptions
import com.example.mvvvmsampleapp.util.Coroutines

class AuthViewModel : ViewModel() {

    var email : String? = null
    var password  : String?= null
    var authListener : AuthListener? = null


    fun onLoginButtonClick(view : View) {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid Credentials")


            return
        }

        Coroutines.main {
            try {


                val authResponse = UserRepository().userLogin(email!!, password!!)

                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)


            } catch (e: ApiExceptions) {
                authListener?.onFailure(e.message!!)
            }


        }


    }

}