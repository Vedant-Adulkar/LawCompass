package com.example.lawcompass.View

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.lawcompass.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: TabLayoutAdapter
    private lateinit var ViewPager2:ViewPager2
    private lateinit var TabLayout:TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        TabLayout = findViewById(R.id.mytablayout)
        ViewPager2 = findViewById(R.id.myview)
        ViewPager2.adapter = TabLayoutAdapter(this)

        TabLayoutMediator(TabLayout,ViewPager2){
            tab,idx -> when(idx){
                0->tab.text = "Home"
                1->tab.text = "Lawyers"
                2->tab.text = "Community"
                3->tab.text = "Profile"

            }
        }.attach()
    }
}