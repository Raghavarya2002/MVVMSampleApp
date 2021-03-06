package com.example.mvvvmsampleapp.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Coroutines {
    fun main(work: suspend (() -> Unit)) = //unit in kotlin is equivalent to void in java
        CoroutineScope(Dispatchers.Main).launch {
            work()
        }

    fun io(work: suspend (() -> Unit)) = //unit in kotlin is equivalent to void in java
        CoroutineScope(Dispatchers.IO).launch {
            work()
        }
}