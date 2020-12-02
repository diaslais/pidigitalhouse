package com.renan.digitalspace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.navigation.NavigationView

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

    private fun navClickListener() {
        val navigationView = findViewById<NavigationView>(R.id.navigationView)

        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.itemChangeNameMenu -> _navController.navigate(R.id.action_exploracaoFragment_to_changeNameFragment)
                R.id.itemChangeEmailMenu -> _navController.navigate(R.id.action_exploracaoFragment_to_changeEmailFragment)
                R.id.itemChangePasswordMenu -> _navController.navigate(R.id.action_exploracaoFragment_to_changePasswordFragment)
                R.id.itemFavoritosMenu -> Toast.makeText(this, "Favoritos", Toast.LENGTH_LONG)
                    .show()
                R.id.itemPontuacaoMenu -> Toast.makeText(this, "Pontuacao", Toast.LENGTH_LONG)
                    .show()
                R.id.itemBibliografiaMenu -> Toast.makeText(this, "Bibliografia", Toast.LENGTH_LONG)
                    .show()
                R.id.itemSairMenu -> {
                    _drawerLayout.closeDrawer(Gravity.LEFT, false)
                    _navController.navigate(R.id.loginFragment)
                }
                else -> Toast.makeText(this, "num deu", Toast.LENGTH_LONG).show()
            }
            true
        }
    }
}