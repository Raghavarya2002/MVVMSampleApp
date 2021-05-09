package com.example.mvvvmsampleapp.ui.auth

import com.example.mvvvmsampleapp.data.db.entities.User

interface AuthListener {

    fun onStarted()
    fun onSuccess(user: User)
    fun onFailure(message: String)

}