package com.example.drivinglicenseinfo

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drivinglicenseinfo.databinding.ActivityDrivingLawsBinding

class DrivingLawsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDrivingLawsBinding
    private val lawsList = mutableListOf(
        DrivingLaw("Penalty for offenses where no penalty is specified", "Rs.500/-"),
        DrivingLaw("Violation of road regulations", "Rs.500 to 1000/-"),
        DrivingLaw("Disobedience of orders of Authority", "Rs.2000/-"),
        DrivingLaw("Traveling without ticket", "Rs.500/-")
        // Add more driving laws here
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrivingLawsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupButtons()

        // Handle back button press using onBackPressedDispatcher
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish() // Close the activity
            }
        })
    }

    private fun setupRecyclerView() {
        val adapter = DrivingLawsAdapter(lawsList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun setupButtons() {
        // Set up the back button functionality
        binding.backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Set up the sort button functionality
        binding.sortButton.setOnClickListener {
            sortLawsByPenalty()
        }
    }

    private fun sortLawsByPenalty() {
        lawsList.sortBy { it.penalty }  // Sort the list by penalty
        binding.recyclerView.adapter?.notifyDataSetChanged()  // Notify the adapter of the change
    }
}
