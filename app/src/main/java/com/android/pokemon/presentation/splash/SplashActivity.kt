package com.android.pokemon.presentation.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.android.pokemon.databinding.ActivitySplashBinding
import com.android.pokemon.presentation.gallery.GalleryActivity

private const val SPLASH_DELAY = 2000L

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed({
            GalleryActivity.start(this)
            finish()
        }, SPLASH_DELAY)
    }

}
