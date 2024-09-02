package com.example.drivinglicenseinfo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.drivinglicenseinfo.databinding.ActivityVehicleInformationBinding
import java.util.Locale

class VehicleInformationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVehicleInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVehicleInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the back button
        binding.backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed() // Updated to use onBackPressedDispatcher
        }

        // Add validation to the vehicle number EditText
        binding.vehicleNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val text = s.toString()
                    .uppercase(Locale.ROOT) // Updated to use Locale.ROOT
                    .replace("[^A-Z0-9]".toRegex(), "")
                if (text != s.toString()) {
                    binding.vehicleNumber.setText(text)
                    binding.vehicleNumber.setSelection(text.length)
                }
                // Limit the length to 10 characters
                if (text.length > 10) {
                    binding.vehicleNumber.setText(text.substring(0, 10))
                    binding.vehicleNumber.setSelection(10)
                }
                binding.vehicleNumberLayout.error = null // Clear any previous error
            }
        })

        // Set up the search button
        binding.searchButton.setOnClickListener {
            val vehicleNumber = binding.vehicleNumber.text.toString().trim().uppercase(Locale.ROOT) // Updated to use Locale.ROOT
            if (vehicleNumber.length == 10) {
                // Proceed with the search
            } else {
                // Show error message
                binding.vehicleNumberLayout.error = "Vehicle number must be 10 characters long"
            }
        }
    }
}
