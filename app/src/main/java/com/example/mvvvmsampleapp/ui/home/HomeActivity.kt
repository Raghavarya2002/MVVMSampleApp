package com.example.mvvvmsampleapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.mvvvmsampleapp.R
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(toolbar)

        val navController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupWithNavController(nav_view, navController)

        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(

            Navigation.findNavController(this, R.id.fragment),
            drawer_layout


        )
    }
}