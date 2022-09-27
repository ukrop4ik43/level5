package com.example.myapplication.ui.activity

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.fragments.ProfileFragment
import com.example.myapplication.ui.viewpager.PagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout:TabLayout
    private lateinit var viewPager:ViewPager2
    private var emailVal=""
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getDataFromAuth()
        tabLayout=binding.tabLayout
        viewPager=binding.viewPager
        viewPager.adapter=PagerAdapter(this)
        TabLayoutMediator(tabLayout,viewPager){
            tab,index->tab.text=when(index){
                0 ->"first fragment"
                1 ->"second fragment"
            else -> {throw Resources.NotFoundException("Position not found")}
        }
        }.attach()

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