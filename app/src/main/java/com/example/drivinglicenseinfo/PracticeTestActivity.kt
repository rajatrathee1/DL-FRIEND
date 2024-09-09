package com.example.drivinglicenseinfo

import com.example.drivinglicenseinfo.PracticeTestQuestionModel
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.drivinglicenseinfo.databinding.ActivityPracticeTestBinding

class PracticeTestActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityPracticeTestBinding
    private var currentQuestionIndex = 0
    private var selectedAnswer = ""

    // Hardcoded questions for demonstration purposes
    private val questionModelList = listOf(
        PracticeTestQuestionModel(
            "Near a pedestrian crossing, when the pedestrians are waiting to cross the road, you should",
            listOf("Sound horn and proceed", "Slow down, sound horn and pass", "Stop the vehicle and wait till the pedestrians cross the road and then proceed"),
            "Stop the vehicle and wait till the pedestrians cross the road and then proceed",
            "Stop, Pedestrians Rule, Wait, and Proceed."
        )
        // Add more questions here
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Enable the back button in the ActionBar
        // Back Arrow Click Listener
        binding.backArrow.setOnClickListener {
            finish() // Close the activity and go back to the previous one
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Practice Test"

        binding.apply {
            btn0.setOnClickListener(this@PracticeTestActivity)
            btn1.setOnClickListener(this@PracticeTestActivity)
            btn2.setOnClickListener(this@PracticeTestActivity)
            nextBtn.setOnClickListener(this@PracticeTestActivity)
            previousBtn.setOnClickListener(this@PracticeTestActivity)
        }

        loadQuestion()
        updateButtonStates()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun loadQuestion() {
        selectedAnswer = ""

        binding.apply {
            questionIndicatorTextview.text = "Question ${currentQuestionIndex + 1}/${questionModelList.size}"
            questionTextview.text = questionModelList[currentQuestionIndex].question
            btn0.text = questionModelList[currentQuestionIndex].options[0]
            btn1.text = questionModelList[currentQuestionIndex].options[1]
            btn2.text = questionModelList[currentQuestionIndex].options[2]
            tipContainer.visibility = View.GONE // Hide the tip container initially
        }

        updateButtonStates()
    }

    private fun updateButtonStates() {
        binding.apply {
            previousBtn.isEnabled = currentQuestionIndex > 0
            nextBtn.isEnabled = currentQuestionIndex < questionModelList.size - 1

            // Set button colors based on their enabled state
            val disabledColor = getColor(R.color.lighter_purple)
            val enabledColor = getColor(R.color.purple)

            previousBtn.setBackgroundColor(if (previousBtn.isEnabled) enabledColor else disabledColor)
            nextBtn.setBackgroundColor(if (nextBtn.isEnabled) enabledColor else disabledColor)
        }
    }

    override fun onClick(view: View?) {
        val clickedBtn = view as Button

        // Reset the background color and text color of all buttons
        binding.apply {
            btn0.setBackgroundColor(getColor(R.color.grey))
            btn0.setTextColor(getColor(R.color.black))

            btn1.setBackgroundColor(getColor(R.color.grey))
            btn1.setTextColor(getColor(R.color.black))

            btn2.setBackgroundColor(getColor(R.color.grey))
            btn2.setTextColor(getColor(R.color.black))
        }

        // Handle next button click
        if (clickedBtn.id == R.id.next_btn) {
            currentQuestionIndex++
            if (currentQuestionIndex < questionModelList.size) {
                loadQuestion()
            }
        } else if (clickedBtn.id == R.id.previous_btn) {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--
                loadQuestion()
            }
        } else {
            // Handle answer button click
            selectedAnswer = clickedBtn.text.toString()
            if (selectedAnswer == questionModelList[currentQuestionIndex].correct) {
                clickedBtn.setBackgroundColor(Color.GREEN)
                clickedBtn.setTextColor(getColor(R.color.white)) // Correct answer
            } else {
                clickedBtn.setBackgroundColor(Color.RED)
                clickedBtn.setTextColor(getColor(R.color.white)) // Wrong answer
            }

            // Show the tip after an answer is selected
            binding.apply {
                tipTextview.text = questionModelList[currentQuestionIndex].tip
                tipContainer.visibility = View.VISIBLE // Make sure the whole container is visible
            }
        }

        updateButtonStates()
    }
}
