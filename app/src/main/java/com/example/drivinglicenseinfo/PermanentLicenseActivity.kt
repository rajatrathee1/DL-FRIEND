package com.example.drivinglicenseinfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.drivinglicenseinfo.databinding.ActivityPermanentLicenseBinding

class PermanentLicenseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPermanentLicenseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPermanentLicenseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the back button
        binding.backArrowPermanent.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Set the steps text
        binding.permanentLicenseSteps.text = """
            Step 1: Login to Sarathi Website...
            Step 2: Fill Out the Driving License Application...
            Step 3: Pay the Fee and Schedule the Driving Test...
            Step 4: Attend the Driving Test...
            Step 5: Issuance of Driving License...
            (Add detailed steps here)
        """.trimIndent()
    }
}
