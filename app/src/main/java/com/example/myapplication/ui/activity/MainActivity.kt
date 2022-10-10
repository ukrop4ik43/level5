package com.example.myapplication.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {


    private var emailVal=""
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getDataFromAuth()

    }

    private fun getDataFromAuth() {
        val extras = intent.extras
        if (extras != null) {
            emailVal = extras.getString("EXTRA_MESSAGE")!!
        }
    }

    fun sendData(): String? {
        return emailVal
    }
}