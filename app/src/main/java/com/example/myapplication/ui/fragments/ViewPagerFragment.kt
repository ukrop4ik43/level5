package com.example.myapplication.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.ui.viewpager.PagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)
        val fragmentList = arrayListOf<Fragment>(FirstFragment(), SecondFragment())

        val adapter =
            PagerAdapter(fragmentList,
                requireActivity().supportFragmentManager,
                lifecycle)
        val viewPager=view.findViewById<ViewPager2>(R.id.viewPager)
        viewPager.adapter=adapter
        val tabLayout: TabLayout = view.findViewById(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager,) { tab, position ->
            val tabNames = listOf("Profile", "List of contacts")
            tab.text = tabNames[position]
        }.attach()
        return view
    }


}