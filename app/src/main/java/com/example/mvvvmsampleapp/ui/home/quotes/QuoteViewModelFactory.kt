package com.example.mvvvmsampleapp.ui.home.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvvmsampleapp.data.repositories.QuotesRepository
import com.example.mvvvmsampleapp.data.repositories.UserRepository

@Suppress("UNCHECKED_CAST")
class QuoteViewModelFactory(
    private val repository: QuotesRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuotesViewModel(repository) as T
    }

}