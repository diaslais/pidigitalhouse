package com.nasinha.digitalspace

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var _navController: NavController
    private lateinit var _drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        _navController = findNavController(R.id.navigation_header_container)

        _drawerLayout = findViewById(R.id.drawer_layout)
        _drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

        navClickListener()
    }

    override fun onBackPressed() {
        if (this._drawerLayout.isDrawerOpen(navigationView)) {
            this._drawerLayout.closeDrawer(navigationView)
        } else {
            super.onBackPressed()
        }
    }

    @SuppressLint("RtlHardcoded")
    private fun navClickListener() {
        val navigationView = findViewById<NavigationView>(R.id.navigationView)

        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.itemFavoritosMenu -> {
                    _drawerLayout.closeDrawer(Gravity.LEFT, false)
                    _navController.navigate(R.id.action_explorationFragment_to_favoriteFragment)
                }
                R.id.itemBibliografiaMenu -> {
                    _drawerLayout.closeDrawer(Gravity.LEFT, false)
                    _navController.navigate(R.id.action_explorationFragment_to_bibliografiaFragment)
                }
                R.id.itemChangeNameMenu -> _navController.navigate(R.id.action_explorationFragment_to_changeNameFragment)
                R.id.itemChangeEmailMenu -> _navController.navigate(R.id.action_explorationFragment_to_changeEmailFragment)
                R.id.itemChangePasswordMenu -> _navController.navigate(R.id.action_explorationFragment_to_changePasswordFragment)
                R.id.itemSettingsMenu -> Toast.makeText(this, "Opções", Toast.LENGTH_LONG)
                    .show()
                R.id.itemDevelopersMenu -> Toast.makeText(
                    this,
                    "Desenvolvedores",
                    Toast.LENGTH_LONG
                )
                    .show()
                R.id.itemExitMenu -> {
                    _drawerLayout.closeDrawer(Gravity.LEFT, false)
                    _navController.navigate(R.id.loginFragment)
                }
                else -> Toast.makeText(this, "num deu", Toast.LENGTH_LONG).show()
            }
            true
        }
    }
}