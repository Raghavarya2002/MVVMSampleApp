package com.example.mvvvmsampleapp

import android.app.Application
import com.example.mvvvmsampleapp.data.db.AppDatabase
import com.example.mvvvmsampleapp.data.network.MyApi
import com.example.mvvvmsampleapp.data.network.NetworkConnectionInterceptor
import com.example.mvvvmsampleapp.data.preferences.PreferenceProvider
import com.example.mvvvmsampleapp.data.repositories.QuotesRepository
import com.example.mvvvmsampleapp.data.repositories.UserRepository
import com.example.mvvvmsampleapp.ui.auth.AuthViewModelFactory
import com.example.mvvvmsampleapp.ui.home.profile.ProfileViewModelFactory
import com.example.mvvvmsampleapp.ui.home.quotes.QuoteViewModelFactory
import com.example.mvvvmsampleapp.ui.home.quotes.QuotesViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from singleton { QuotesRepository(instance(), instance(), instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }
        bind() from provider { QuoteViewModelFactory(instance()) }
    }
}