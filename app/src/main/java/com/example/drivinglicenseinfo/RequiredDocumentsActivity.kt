package com.example.drivinglicenseinfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.drivinglicenseinfo.databinding.ActivityRequiredDocumentsBinding

class RequiredDocumentsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRequiredDocumentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRequiredDocumentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the back button
        binding.backArrow.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}
