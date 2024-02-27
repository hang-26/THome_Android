package com.example.thome.start.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.thome.R
import com.example.thome.start.adapter.FragmentPageAdapter
import com.google.android.material.tabs.TabLayout
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class HomeActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: FragmentPageAdapter
    var auth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        tabLayout = findViewById(R.id.tabLayout)
        viewPager2 = findViewById(R.id.ViewPager2)


        adapter = FragmentPageAdapter(supportFragmentManager, lifecycle)
        setViewHome()

    }

    fun setViewHome() {
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.home).setText("Trang chủ"))
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.bells).setText("Quản lý tin"))
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.activity).setText("Xem tin"))
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.account).setText("Tài khoản"))

        viewPager2.adapter = adapter

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab != null){
                    viewPager2.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

        viewPager2.registerOnPageChangeCallback(object  : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })

    }
}