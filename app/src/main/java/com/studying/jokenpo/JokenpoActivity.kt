package com.studying.jokenpo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.studying.jokenpo.databinding.ActivityJokenpoBinding
import com.studying.jokenpo.fragments.JokenpoResultGameFragment

class JokenpoActivity : AppCompatActivity(){

    private lateinit var binding : ActivityJokenpoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJokenpoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHostFragment.navController


    }

}