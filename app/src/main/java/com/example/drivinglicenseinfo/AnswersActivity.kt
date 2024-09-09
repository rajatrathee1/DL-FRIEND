package com.example.drivinglicenseinfo

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.drivinglicenseinfo.databinding.ActivityAnswersBinding

class AnswersActivity : AppCompatActivity() {
    // Declare binding object
    private lateinit var binding: ActivityAnswersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize binding
        binding = ActivityAnswersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve the question list, correct count, and passing score from the intent
        val questionList: List<QuestionModel> = intent.getSerializableExtra("questionList") as List<QuestionModel>
        val correctCount = intent.getIntExtra("correctCount", 0)
        val passingScore = intent.getIntExtra("passingScore", 0)

        // Update the UI elements like Passing Score, Your Score
        binding.passingScore.text = "Passing Score: $passingScore"
        binding.yourScore.text = "Your Score: $correctCount"

        // Loop through each question and create views dynamically to display the answers
        for (question in questionList) {
            // Display the question text
            val questionText = TextView(this)
            questionText.text = question.question
            binding.answersLayout.addView(questionText)

            // Display the user's answer
            val userAnswerText = TextView(this)
            if (question.correct == question.userAnswer) {
                userAnswerText.setTextColor(Color.GREEN)
                userAnswerText.text = "Your answer: ${question.userAnswer}"
            } else {
                userAnswerText.setTextColor(Color.RED)
                userAnswerText.text = "Your answer: ${question.userAnswer}"
            }
            binding.answersLayout.addView(userAnswerText)

            // Display the correct answer
            val correctAnswerText = TextView(this)
            correctAnswerText.setTextColor(Color.GREEN)
            correctAnswerText.text = "Correct answer: ${question.correct}"
            binding.answersLayout.addView(correctAnswerText)

            // If the question has an image, display it
            question.optionalImageResource?.let {
                val imageView = ImageView(this)
                imageView.setImageResource(it)
                binding.answersLayout.addView(imageView)
            }
        }

        // Set up the Home button click listener
        binding.homeBtn.setOnClickListener {
            finish() // Navigate back to the home screen
        }

        // Set up the Try Again button click listener
        binding.tryAgainBtn.setOnClickListener {
            finish() // Navigate back to retry the quiz
        }
    }
}
