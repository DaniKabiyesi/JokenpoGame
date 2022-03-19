package com.studying.jokenpo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.studying.jokenpo.databinding.ActivityJokenpoBinding
import com.studying.jokenpo.databinding.ActivityQuickScreenBinding

class QuickScreenActivity : AppCompatActivity() {

    lateinit var binding: ActivityQuickScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuickScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startNicknameScreen()
    }


    private fun startNicknameScreen(){
        goToNextScreen()
    }

    private fun setNextScreen() {
        val intent = Intent(
            this@QuickScreenActivity, JokenpoActivity::class.java
        )
        startActivity(intent)
    }

    private fun goToNextScreen(){
        Handler().postDelayed({
            setNextScreen()
            finish()
        }, 1600)
    }


}