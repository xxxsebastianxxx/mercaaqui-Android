package com.example.mercaaqui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationMenuView: BottomNavigationView  = findViewById(R.id.bottom_navigation)
        val navController = findNavController(R.id.fragment1)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.btnhome,
                R.id.btnfactura
            )
        )

       // setupActionBarWithNavController(navController, appBarConfiguration)

        bottomNavigationMenuView.setupWithNavController(navController)



    }
}