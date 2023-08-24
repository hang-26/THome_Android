package com.example.thome.start.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.thousework.fragment.AccountFragment
import com.example.thousework.fragment.ActivityFragment
import com.example.thousework.fragment.HomeFragment
import com.example.thousework.fragment.NoticeFragment


class FragmentPageAdapter( fragmentManager: FragmentManager, lifecycle: Lifecycle)
:FragmentStateAdapter(fragmentManager, lifecycle){


    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0){
            HomeFragment()
        }
        else if (position == 1){
            NoticeFragment()
        }
        else if (position == 2){
            ActivityFragment()
        }
        else AccountFragment()

    }

}