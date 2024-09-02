package com.example.drivinglicenseinfo

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.drivinglicenseinfo.databinding.ActivityLicenseDetailsBinding
import java.util.*

class LicenseDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLicenseDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLicenseDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Back Arrow Click Listener
        binding.backArrow.setOnClickListener {
            finish() // Close the activity and go back to the previous one
        }

        // Set up the DatePickerDialog for the Date of Birth field
        binding.dateOfBirth.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                    binding.dateOfBirth.setText(selectedDate)
                },
                year, month, day
            )
            datePickerDialog.show()
        }

        // Add validation to the license number EditText
        binding.licenseNumber.addTextChangedListener(object : TextWatcher {
            var isFormatting = false

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (isFormatting) return
                isFormatting = true

                val text = s.toString().toUpperCase(Locale.getDefault())
                val formattedText = StringBuilder()

                // Format the input according to "GJ-1234567890123"
                for (i in text.indices) {
                    if (i < 2) { // First 2 characters should be letters
                        if (text[i].isLetter()) {
                            formattedText.append(text[i])
                        } else {
                            Toast.makeText(
                                this@LicenseDetailsActivity,
                                "Only letters are allowed in this part",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else if (i == 2) { // Third character should be '-'
                        if (text[i] != '-') {
                            formattedText.append("-")
                        }
                        formattedText.append(text[i])
                    } else { // Remaining characters should be digits
                        if (text[i].isDigit()) {
                            formattedText.append(text[i])
                        } else {
                            Toast.makeText(
                                this@LicenseDetailsActivity,
                                "Only numbers are allowed in this part",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

                // If the formatted text exceeds the required length, truncate it
                if (formattedText.length > 16) {
                    formattedText.setLength(16)
                    Toast.makeText(
                        this@LicenseDetailsActivity,
                        "License number can't exceed the format 'DL-1234567890123'",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                // Temporarily remove the text watcher to avoid infinite loop
                binding.licenseNumber.removeTextChangedListener(this)
                binding.licenseNumber.setText(formattedText.toString())
                binding.licenseNumber.setSelection(formattedText.length)
                binding.licenseNumber.addTextChangedListener(this)

                isFormatting = false

                // If the maximum length is reached, move focus to the next field
                if (formattedText.length == 16) {
                    binding.dateOfBirth.requestFocus()
                }
            }
        })

        // Handle search button click
        binding.searchButton.setOnClickListener {
            val licenseNumber = binding.licenseNumber.text.toString()
            if (licenseNumber.length < 16) {
                Toast.makeText(
                    this,
                    "License number must be in the format 'DL-1234567890123'",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                // Proceed with search functionality
            }
        }
    }
}
