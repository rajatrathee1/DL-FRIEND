package com.example.drivinglicenseinfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.drivinglicenseinfo.databinding.ActivityLearningLicenseBinding

class LearningLicenseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLearningLicenseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLearningLicenseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the back button
        binding.backArrowLearning.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Set the steps text
        binding.learningLicenseSteps.text = """
            Step 1: Register on the Sarathi Website...
            Step 2: Fill Out the Application Form...
            Step 3: Submit the Form...
            Step 4: Book an Appointment for LL Test...
            Step 5: Take the LL Test...
            (Add detailed steps here)
        """.trimIndent()
    }
}
