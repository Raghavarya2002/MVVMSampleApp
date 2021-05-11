package com.example.mvvvmsampleapp.ui.home.profile

import androidx.lifecycle.ViewModel
import com.example.mvvvmsampleapp.data.repositories.UserRepository

class ProfileViewModel(
    repository: UserRepository
) : ViewModel() {
    val user = repository.getUser()
}