package com.aprass.sportnews

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.aprass.sportnews.databinding.ActivitySplashScreenBinding


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // create view binding
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)

        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            val i = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(i)
            finish()
        }, 2000)
    }
}