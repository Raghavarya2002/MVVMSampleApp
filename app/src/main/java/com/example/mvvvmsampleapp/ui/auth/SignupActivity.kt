package com.example.mvvvmsampleapp.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.example.mvvvmsampleapp.R
import com.example.mvvvmsampleapp.data.db.entities.User
import com.example.mvvvmsampleapp.databinding.ActivitySignupBinding
import com.example.mvvvmsampleapp.ui.home.HomeActivity
import com.example.mvvvmsampleapp.util.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignupActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory: AuthViewModelFactory by instance()

    private lateinit var binding: ActivitySignupBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)




        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if (user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })

        binding.buttonSignUp.setOnClickListener {
            userSignup()
        }

    }

    private fun userSignup() {
        val name = binding.editTextName.text.toString().trim()
        val email = binding.editTextEmail.text.toString().trim()
        val password = binding.editTextPassword.text.toString().trim()
        val password1 = binding.editTextPasswordConfirm.text.toString().trim()

        //todo add input validations
        lifecycleScope.launch {
            val authResponse = viewModel.userSignup(name, email, password)
            try {
                val authResponse = viewModel.userSignup(name, email, password)
                if (authResponse.user != null) {
                    viewModel.saveLoggedInUser(authResponse.user)
                } else {
                    binding.root.snackbar(authResponse.message!!)
                }
            } catch (e: ApiExceptions) {
                e.printStackTrace()
            } catch (e: NoInternetException) {
                e.printStackTrace()
            }

        }

    }


}