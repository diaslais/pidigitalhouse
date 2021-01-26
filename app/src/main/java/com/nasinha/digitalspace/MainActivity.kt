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
import com.nasinha.digitalspace.utils.DrawerUtils.lockDrawer
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
        lockDrawer(this)

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
                    closeDrawer()
                    _navController.navigate(R.id.action_explorationFragment_to_favoriteFragment)
                }
                R.id.itemBibliografiaMenu -> {
                    closeDrawer()
                    _navController.navigate(R.id.action_explorationFragment_to_bibliographyFragment)
                }
                R.id.itemChangeProfileMenu -> {
                    closeDrawer()
                    _navController.navigate(R.id.action_explorationFragment_to_profileFragment)
                }
                R.id.itemChangePasswordMenu -> {
                    closeDrawer()
                    _navController.navigate(R.id.action_explorationFragment_to_changePasswordFragment)
                }
                R.id.itemDevelopersMenu -> {
                    closeDrawer()
                    _navController.navigate(R.id.action_explorationFragment_to_developerFragment)
                }
                R.id.itemSettingsMenu -> {
                    closeDrawer()
                    _navController.navigate(R.id.action_explorationFragment_to_settingsFragment)
                }
                R.id.itemExitMenu -> {
                    closeDrawer()
                    logout()
                }
                else -> Toast.makeText(this, "num deu", Toast.LENGTH_LONG).show()
            }
            true
        }
    }

    private fun closeDrawer(){
        _drawerLayout.closeDrawer(Gravity.LEFT, false)
    }

    private fun logout() {
        _drawerLayout.closeDrawer(Gravity.LEFT, false)
        viewModel.signOutUser(this)
        _navController.navigate(R.id.action_explorationFragment_to_loginFragment)
    }
}