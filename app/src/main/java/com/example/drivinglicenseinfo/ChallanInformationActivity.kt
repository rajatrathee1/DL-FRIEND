package com.example.drivinglicenseinfo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.drivinglicenseinfo.databinding.ActivityChallanInformationBinding
import java.util.Locale

class ChallanInformationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChallanInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChallanInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the back button
        binding.backArrow.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Set up the vehicle number input with validation
        binding.vehicleNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    // Convert to uppercase and allow only alphabets and numbers
                    val filteredText = it.toString().uppercase(Locale.getDefault()).filter { char ->
                        char.isLetterOrDigit()
                    }

                    if (filteredText != it.toString()) {
                        binding.vehicleNumber.setText(filteredText)
                        binding.vehicleNumber.setSelection(filteredText.length)
                    }

                    // Limit the length to 10 characters
                    if (filteredText.length > 10) {
                        binding.vehicleNumber.setText(filteredText.substring(0, 10))
                        binding.vehicleNumber.setSelection(10)
                    }
                    }
            }
        })

        // Set up the search button
        binding.searchButton.setOnClickListener {
            val vehicleNumber = binding.vehicleNumber.text.toString().trim()
            if (vehicleNumber.length == 10) {
                // Proceed with the search
            } else {
                // Show error message
                binding.vehicleNumberLayout.error = "Invalid vehicle number format"
            }
        }
    }
}
