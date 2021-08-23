package com.mehmetbeken.watchmovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.mehmetbeken.watchmovies.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private val splashScreen=4000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        Handler().postDelayed({

            val intent= Intent(this,WatchMovies::class.java)
            startActivity(intent)
            finish()
        },splashScreen.toLong())
    }
    }

