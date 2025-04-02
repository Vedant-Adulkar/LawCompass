package com.example.lawcompass.View

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabLayoutAdapter(fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {

    private final val ntabs = 4;



    override fun getItemCount(): Int {
       return ntabs;
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0->return Home()
            1->return Lawyers()
            2->return  Community()
            3->return Profile()
            else->return Home()
        }
    }

}