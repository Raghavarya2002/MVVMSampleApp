package com.example.mvvvmsampleapp.data.network.responses

import com.example.mvvvmsampleapp.data.db.entities.Quote

data class QuotesResponse(
    val isSuccessful: Boolean,
    val quotes: List<Quote>
)