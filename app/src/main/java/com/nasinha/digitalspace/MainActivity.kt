package com.nasinha.digitalspace

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.navigation.NavigationView
import com.nasinha.digitalspace.authentication.viewmodel.AuthenticatorViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var _navController: NavController
    private lateinit var _drawerLayout: DrawerLayout
    private val viewModel: AuthenticatorViewModel by lazy {
        ViewModelProvider(this).get(AuthenticatorViewModel::class.java)
    }

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
                    _navController.navigate(R.id.action_explorationFragment_to_bibliographyFragment)
                }
                R.id.itemChangeNameMenu -> _navController.navigate(R.id.action_explorationFragment_to_changeNameFragment)
                R.id.itemChangeEmailMenu -> _navController.navigate(R.id.action_explorationFragment_to_changeEmailFragment)
                R.id.itemChangePasswordMenu -> _navController.navigate(R.id.action_explorationFragment_to_changePasswordFragment)
                R.id.itemDevelopersMenu -> {
                    _drawerLayout.closeDrawer(Gravity.LEFT, false)
                    _navController.navigate(R.id.action_explorationFragment_to_developerFragment)
                }
                R.id.itemSettingsMenu -> _navController.navigate(R.id.action_explorationFragment_to_settingsFragment)
                R.id.itemExitMenu -> {
                    _drawerLayout.closeDrawer(Gravity.LEFT, false)
                    logout()
                }
                else -> Toast.makeText(this, "num deu", Toast.LENGTH_LONG).show()
            }
            true
        }
    }

    private fun logout() {
        _drawerLayout.closeDrawer(Gravity.LEFT, false)
        viewModel.signOutUser()
        _navController.navigate(R.id.action_explorationFragment_to_loginFragment)
    }
}