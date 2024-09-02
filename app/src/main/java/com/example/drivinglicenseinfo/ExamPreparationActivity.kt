package com.example.drivinglicenseinfo

import ExamPreparationPagerAdapter
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.drivinglicenseinfo.databinding.ActivityExamPreparationBinding
import com.google.android.material.tabs.TabLayoutMediator

class ExamPreparationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExamPreparationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamPreparationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the back button
        binding.backArrow.setOnClickListener {
            onBackPressedDispatcher.onBackPressed() // Use onBackPressedDispatcher for handling back press
        }

        // Initialize ViewPager2 with the ExamPreparationPagerAdapter
        val adapter = ExamPreparationPagerAdapter(this)
        binding.viewPager.adapter = adapter

        // Connect TabLayout with ViewPager2
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Questions"
                1 -> "Traffic Signs"
                else -> null
            }
        }.attach()

        // Handle back press in the activity
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish() // Custom behavior for back button press
            }
        })
    }
}
