package com.aprass.sportnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aprass.sportnews.databinding.ActivityAboutBinding
import com.aprass.sportnews.databinding.ActivityDetailNewsBinding

class About : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAboutBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}