package com.example.mvvvmsampleapp.ui.home.quotes

import androidx.lifecycle.ViewModel
import com.example.mvvvmsampleapp.data.repositories.QuotesRepository
import com.example.mvvvmsampleapp.util.lazyDeferred

class QuotesViewModel(
    repository: QuotesRepository
) : ViewModel() {

    val quotes by lazyDeferred {
        repository.getQuotes()
    }

}