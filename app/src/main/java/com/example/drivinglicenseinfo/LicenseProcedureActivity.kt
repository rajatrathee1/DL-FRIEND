package com.example.drivinglicenseinfo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.drivinglicenseinfo.databinding.ActivityLicenseProcedureBinding

class LicenseProcedureActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLicenseProcedureBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLicenseProcedureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle back button press
        binding.backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Set click listeners for the options
        binding.optionLearnerLicense.setOnClickListener {
            // Open the Learner's License procedure screen
            val intent = Intent(this, LearningLicenseActivity ::class.java)
            startActivity(intent)
        }

        binding.optionPermanentLicense.setOnClickListener {
            // Open the Permanent License procedure screen
            val intent = Intent(this, PermanentLicenseActivity::class.java)
            startActivity(intent)
        }
    }
}
