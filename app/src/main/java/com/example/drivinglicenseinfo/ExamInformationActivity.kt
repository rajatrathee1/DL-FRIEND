package com.example.drivinglicenseinfo

import com.example.drivinglicenseinfo.QuestionModel
import com.example.drivinglicenseinfo.QuizModel
import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.drivinglicenseinfo.databinding.ActivityExamInformationBinding

class ExamInformationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExamInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the back button
        binding.backArrow.setOnClickListener {
            // Use onBackPressedDispatcher for handling back button press
            onBackPressedDispatcher.onBackPressed()
        }

        // Set up click listener for the Question Bank card
        binding.cardQuestionBankNew.setOnClickListener {
            val intent = Intent(this, ExamPreparationActivity::class.java)
            startActivity(intent)
        }

        // Set up click listener for the Practice Test card
        binding.cardPracticeTestNew.setOnClickListener {
            val intent = Intent(this, PracticeTestActivity::class.java)
            startActivity(intent)
        }

        // Set up click listener for the Full Exam button
        binding.cardFullExamNew.setOnClickListener {
            val fullExamQuiz = getFullExamQuiz()
            val intent = Intent(this, QuizActivity::class.java)
            QuizActivity.questionModelList = fullExamQuiz.questionList
            QuizActivity.time = fullExamQuiz.time
            startActivity(intent)
        }

        // Set up click listener for the Driving Laws button
        binding.cardDrivingLawsNew.setOnClickListener {
            val intent = Intent(this, DrivingLawsActivity::class.java)
            startActivity(intent)
        }
        binding.cardRequiredDocuments.setOnClickListener {
            val intent = Intent(this, RequiredDocumentsActivity::class.java)
            startActivity(intent)
        }
        binding.cardLicenseProcedure.setOnClickListener {
            val intent = Intent(this, LicenseProcedureActivity::class.java)
            startActivity(intent)
        }

    }

    private fun getFullExamQuiz(): QuizModel {
        // Example Full Exam Quiz (hidden from list)
        val fullExamQuestions = listOf(
            QuestionModel(
                "What should you do when you see a red traffic light?",
                listOf("Speed up", "Slow down", "Stop"),
                "Stop"
            ),
            QuestionModel(
                "How should you handle a pedestrian crossing?",
                listOf("Ignore them", "Stop and let them cross", "Honk and proceed"),
                "Stop and let them cross"
            )
            // Add more mixed questions from different categories here
        )

        return QuizModel(
            id = "full_exam",
            title = "Full Exam",
            subtitle = "Complete Mixed Quiz",
            time = "30", // Set time for full exam
            questionList = fullExamQuestions,
            isHidden = true // Hide from list
        )
    }
}
