package com.example.financeapp

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.widget.Toolbar
import com.example.financeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar'ı ayarla
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Navigation Controller'ı ayarla
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Bottom Navigation'ı ayarla
        setupNavigation()

        // AppBar Configuration'ı ayarla
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.transactionsFragment,
                R.id.analysisFragment,
                R.id.aiCoachFragment
            )
        )

        // ActionBar'ı NavController ile bağla
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun setupNavigation() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav.setupWithNavController(navController)

        // Bottom Navigation item seçildiğinde
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    NavigationUI.onNavDestinationSelected(item, navController)
                    true
                }
                R.id.transactionsFragment -> {
                    NavigationUI.onNavDestinationSelected(item, navController)
                    true
                }
                R.id.analysisFragment -> {
                    NavigationUI.onNavDestinationSelected(item, navController)
                    true
                }
                R.id.aiCoachFragment -> {
                    NavigationUI.onNavDestinationSelected(item, navController)
                    true
                }
                else -> false
            }
        }
    }

    // Up button'u için
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    // Options Menu item'ları seçildiğinde
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item)
    }
}