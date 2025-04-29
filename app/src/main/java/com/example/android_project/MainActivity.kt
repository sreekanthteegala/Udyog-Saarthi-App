package com.example.android_project
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.example.android_project.DashboardFragment
import com.example.android_project.ComplainFragment
import com.example.android_project.UpdatesFragment
import com.example.android_project.TestsFragment



class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_main)


        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Udyog Saarthi"





        // Drawer
        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout,
            R.string.open, R.string.close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener(this)

        // Bottom nav
        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_nav)
        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_dashboard -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, DashboardFragment())
                        .commit()
                    true
                }
                R.id.nav_complain -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ComplainFragment())
                        .commit()
                    true
                }
                R.id.nav_updates -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, UpdatesFragment())
                        .commit()
                    true
                }
                R.id.nav_tests -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, TestsFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }

        // load default
        bottomNav.selectedItemId = R.id.nav_dashboard
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if ((item.itemId) == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerLayout.closeDrawer(GravityCompat.START)
        when (item.itemId) {
            R.id.drawer_about_us -> startActivity(Intent(this, AboutUsActivity::class.java))
            R.id.drawer_contact_us -> startActivity(Intent(this, ContactUsActivity::class.java))
            R.id.drawer_settings   -> startActivity(Intent(this, SettingsActivity::class.java))
            R.id.drawer_search     -> startActivity(Intent(this, SearchActivity::class.java))
            R.id.drawer_profile    -> startActivity(Intent(this, ProfileActivity::class.java))
        }
        Toast.makeText(this, "Clicked ${item.title}", Toast.LENGTH_SHORT).show()
        return true
    }
}
