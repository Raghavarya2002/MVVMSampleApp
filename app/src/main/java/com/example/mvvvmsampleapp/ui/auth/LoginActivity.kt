package com.example.mvvvmsampleapp.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvvmsampleapp.R
import com.example.mvvvmsampleapp.data.db.AppDatabase
import com.example.mvvvmsampleapp.data.db.entities.User
import com.example.mvvvmsampleapp.data.network.MyApi
import com.example.mvvvmsampleapp.data.network.NetworkConnectionInterceptor
import com.example.mvvvmsampleapp.data.repositories.UserRepository
import com.example.mvvvmsampleapp.databinding.ActivityLoginBinding
import com.example.mvvvmsampleapp.ui.home.HomeActivity
import com.example.mvvvmsampleapp.util.hide
import com.example.mvvvmsampleapp.util.show
import com.example.mvvvmsampleapp.util.snackbar
import com.example.mvvvmsampleapp.util.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() , AuthListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val api = MyApi(networkConnectionInterceptor)
        val db = AppDatabase(this)
        val repository = UserRepository(api, db)
        val factory = AuthViewModelFactory(repository)

        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel

        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if (user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })

    }

    override fun onStarted() {
        progress_bar.show()

    }

    override fun onSuccess(user: User) {
        progress_bar.hide()


    }


    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackbar(message)

    }
}