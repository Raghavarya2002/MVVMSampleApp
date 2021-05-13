package com.example.mvvvmsampleapp.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvvmsampleapp.data.db.AppDatabase
import com.example.mvvvmsampleapp.data.db.entities.Quote
import com.example.mvvvmsampleapp.data.network.MyApi
import com.example.mvvvmsampleapp.data.network.SafeApiRequest
import com.example.mvvvmsampleapp.data.preferences.PreferenceProvider
import com.example.mvvvmsampleapp.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

private val MINIMUM_INTERVAL = 6
class QuotesRepository(

    private val api: MyApi,
    private val db: AppDatabase,
    private val prefs: PreferenceProvider
) : SafeApiRequest() {

    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.observeForever {
            saveQuotes(it)
        }
    }

    suspend fun getQuotes(): LiveData<List<Quote>> {

        return withContext(Dispatchers.IO) {
            fetchQuotes()
            db.getQuoteDao().getQuotes()
        }
    }

    private suspend fun fetchQuotes() {  //fetch the quotes from backend API


        val lastsavedAt = prefs.getlastSavedAt()



        if (lastsavedAt == null || isFetchNeeded(LocalDateTime.parse(lastsavedAt))) {

            val response = apiRequest { api.getQuotes() }
            quotes.postValue(response.quotes)

        }

    }

    private fun isFetchNeeded(savedAt: LocalDateTime): Boolean {

        return ChronoUnit.HOURS.between(savedAt, LocalDateTime.now()) > MINIMUM_INTERVAL

    }

    private fun saveQuotes(quotes: List<Quote>) {

        Coroutines.io {
            prefs.savelastSavedAt(LocalDateTime.now().toString())
            db.getQuoteDao().saveAllQuotes(quotes)
        }

    }

}