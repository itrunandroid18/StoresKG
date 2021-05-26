package it.kimoterru.storesit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import it.kimoterru.storesit.databinding.ActivityMainBinding
import it.kimoterru.storesit.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNav()
    }

    private fun setupBottomNav() {
        val homeFragment = HomeFragment()
        //todo create others later
        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, homeFragment)
            .commit()
        binding.mainBottomNav.setOnNavigationItemSelectedListener {
            val newFragment = when(it.itemId) {
                R.id.home -> homeFragment
                R.id.catalog -> homeFragment //todo change later
                else -> homeFragment //todo change later
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, newFragment)
                .commit()
            true
        }
    }
}