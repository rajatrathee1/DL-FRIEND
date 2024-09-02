package com.example.drivinglicenseinfo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.drivinglicenseinfo.databinding.ActivityRcbookDetailsBinding
import java.util.*

class RCBookDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRcbookDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRcbookDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the back button
        binding.backArrow.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Add validation to the vehicle number EditText
        binding.vehicleNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val text = s.toString().uppercase(Locale.getDefault())
                    .replace(Regex("[^A-Z0-9]"), "")

                if (text.length <= 10) {
                    // Temporarily remove the text watcher to avoid an infinite loop
                    binding.vehicleNumber.removeTextChangedListener(this)
                    binding.vehicleNumber.setText(text)
                    binding.vehicleNumber.setSelection(text.length)
                    binding.vehicleNumber.addTextChangedListener(this)
                }
            }
        })

        // Handle search button click
        binding.searchButton.setOnClickListener {
            val vehicleNumber = binding.vehicleNumber.text.toString().trim()
            if (vehicleNumber.isNotEmpty() && vehicleNumber.length == 10) {
                // Proceed with search functionality
            } else {
                // Show error message
                binding.vehicleNumberLayout.error = "Invalid vehicle number format"
            }
        }
    }
}
