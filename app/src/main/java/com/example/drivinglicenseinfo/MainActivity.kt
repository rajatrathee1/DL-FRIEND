package com.example.drivinglicenseinfo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drivinglicenseinfo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var quizModelList: MutableList<QuizModel>
    private lateinit var adapter: QuizListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quizModelList = mutableListOf()
        getDataFromFirebase()

        // Set up click listener for the Driving License Details Card
        binding.cardDrivingLicenseDetails.setOnClickListener {
            val intent = Intent(this, LicenseDetailsActivity::class.java)
            startActivity(intent)
        }

        // Set up click listener for the Vehicle Information Card
        binding.cardVehicleInformation.setOnClickListener {
            val intent = Intent(this, VehicleInformationActivity::class.java)
            startActivity(intent)
        }

        // Set up click listener for the RC Book Details Card
        binding.cardRcBookDetails.setOnClickListener {
            val intent = Intent(this, RCBookDetailsActivity::class.java)
            startActivity(intent)
        }

        // Set up click listener for the Challan Information Card
        binding.cardChallanInformation.setOnClickListener {
            val intent = Intent(this, ChallanInformationActivity::class.java)
            startActivity(intent)
        }

        binding.cardVehicleInsurance.setOnClickListener {
            val intent = Intent(this, VehicleInsuranceActivity::class.java)
            startActivity(intent)
        }

        // Set up click listener for the "View All" button in the RTO Exam Tasks section
        binding.viewAllButton.setOnClickListener {
            val intent = Intent(this, ExamInformationActivity::class.java)
            startActivity(intent)
        }

        binding.cardQuestionBank.setOnClickListener {
            val intent = Intent(this, ExamPreparationActivity::class.java)
            startActivity(intent)
        }

        binding.cardPracticeTest.setOnClickListener {
            val intent = Intent(this, PracticeTestActivity::class.java)
            startActivity(intent)
        }

        // Set up click listener for the Full Exam button
        binding.cardFullExam.setOnClickListener {
            val fullExamQuiz = getFullExamQuiz()
            val intent = Intent(this, QuizActivity::class.java)
            QuizActivity.questionModelList = fullExamQuiz.questionList
            QuizActivity.time = fullExamQuiz.time
            startActivity(intent)
        }

        // Set up click listener for the Driving Laws Card
        binding.cardDrivingLaws.setOnClickListener {
            val intent = Intent(this, DrivingLawsActivity::class.java)
            startActivity(intent)
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        // Filter the quiz list to exclude hidden quizzes
        val visibleQuizList = quizModelList.filter { !it.isHidden }
        adapter = QuizListAdapter(visibleQuizList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    @SuppressLint("SuspiciousIndentation")
    private fun getDataFromFirebase() {
        // Dummy data
        val listQuestionModel = mutableListOf<QuestionModel>()
        listQuestionModel.add(QuestionModel("Overtaking when approaching a turn", mutableListOf("Is permissible", "Not Permissible", "Is Permissible with care"), "Is permissible"))
        listQuestionModel.add(QuestionModel("On a road designated as one way", mutableListOf("Parking is prohibited", "Overtaking is prohibited", "Should not drive in reverse gear"), "Should not drive in reverse gear"))
        listQuestionModel.add(QuestionModel("You can overtake a vehicle in front", mutableListOf("From the right side of that vehicle", "Through the left side", "Through the left side, if the road is wide"), "From the right side of that vehicle"))
        quizModelList.add(QuizModel("1", "Quiz 1", "Quiz 1", "20", listQuestionModel))

        // Add the hidden Full Exam quiz
        quizModelList.add(getFullExamQuiz())

        setupRecyclerView()
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
