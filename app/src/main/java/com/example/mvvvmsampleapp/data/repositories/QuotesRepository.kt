package com.example.mvvvmsampleapp.data.repositories

import androidx.lifecycle.MutableLiveData
import com.example.mvvvmsampleapp.data.db.AppDatabase
import com.example.mvvvmsampleapp.data.db.entities.Quote
import com.example.mvvvmsampleapp.data.network.MyApi

class QuotesRepository(

    private val api: MyApi,
    private val db: AppDatabase
) {

    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.observeForever {
            saveQuotes(it)
        }
    }

    private fun saveQuotes(quotes: List<Quote>) {

    }

}