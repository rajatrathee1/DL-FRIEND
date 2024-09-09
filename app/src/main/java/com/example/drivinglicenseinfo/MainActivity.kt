package com.example.drivinglicenseinfo

import com.example.drivinglicenseinfo.QuestionModel
import com.example.drivinglicenseinfo.QuizModel
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
        loadTopicWiseQuizzes() // Load the topic-wise quizzes into the list

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
            val fullExamQuiz = getFullExamQuiz() // Get full exam quiz data
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

        setupRecyclerView() // Set up the RecyclerView for topic-wise quizzes
    }

    private fun setupRecyclerView() {
        // Filter the quiz list to only show topic-wise quizzes (excluding hidden quizzes like Full Exam)
        val visibleQuizList = quizModelList.filter { !it.isHidden }
        adapter = QuizListAdapter(visibleQuizList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun loadTopicWiseQuizzes() {
        // Add the topic-wise quizzes to the quiz list
        val trafficSignsQuiz = QuizModel(
            id = "1",
            title = "Traffic Signs & Road Safety",
            subtitle = "Quiz 1: Traffic Signs & Road Safety",
            time = "20",
            questionList = listOf(
                QuestionModel("What does a STOP sign mean?", listOf("Slow down", "Stop", "Proceed with caution"), "Stop", R.drawable.ic_stop_sign),
                QuestionModel("When driving through a school zone, you should", listOf("Speed up", "Maintain speed", "Slow down and watch for children"), "Slow down and watch for children"),
                QuestionModel("What should you do when you see a red traffic light?", listOf("Speed up", "Slow down", "Stop"), "Stop")
            )
        )

        val drivingLawsQuiz = QuizModel(
            id = "2",
            title = "Driving Laws & Regulations",
            subtitle = "Quiz 2: Driving Laws & Regulations",
            time = "15",
            questionList = listOf(
                QuestionModel("Is it permissible to overtake another vehicle at a turn?", listOf("Yes", "No", "With caution"), "No"),
                QuestionModel("What is the legal alcohol limit for drivers?", listOf("0.02%", "0.05%", "0%"), "0%"),
                QuestionModel("What should you do if an ambulance is approaching?", listOf("Stop and let it pass", "Speed up", "Ignore it"), "Stop and let it pass")
            )
        )

        val vehicleMaintenanceQuiz = QuizModel(
            id = "3",
            title = "Vehicle Maintenance & Emergency Handling",
            subtitle = "Quiz 3: Vehicle Maintenance & Emergency Handling",
            time = "15",
            questionList = listOf(
                QuestionModel("What should you do when driving in fog?", listOf("Turn on high beam", "Use fog lights", "Turn off all lights"), "Use fog lights"),
                QuestionModel("What is the meaning of a flashing yellow traffic light?", listOf("Stop", "Proceed with caution", "Speed up"), "Proceed with caution")
            )
        )

        val parkingQuiz = QuizModel(
            id = "4",
            title = "Parking & Challans",
            subtitle = "Quiz 4: Parking & Challans",
            time = "10",
            questionList = listOf(
                QuestionModel("What is the penalty for illegal parking?", listOf("Fine", "Jail", "Warning"), "Fine"),
                QuestionModel("Can you park on the sidewalk?", listOf("Yes", "No"), "No")
            )
        )

        // Add topic-wise quizzes to the quizModelList
        quizModelList.add(trafficSignsQuiz)
        quizModelList.add(drivingLawsQuiz)
        quizModelList.add(vehicleMaintenanceQuiz)
        quizModelList.add(parkingQuiz)
    }

    private fun getFullExamQuiz(): QuizModel {
        val fullExamQuestions = listOf(
            QuestionModel("What does a STOP sign mean?", listOf("Slow down", "Stop", "Proceed with caution"), "Stop", R.drawable.ic_stop_sign),
            QuestionModel("What should you do when you see a red traffic light?", listOf("Speed up", "Slow down", "Stop"), "Stop"),
            QuestionModel("When driving through a school zone, you should", listOf("Speed up", "Maintain speed", "Slow down and watch for children"), "Slow down and watch for children"),
            QuestionModel("Is it permissible to overtake another vehicle at a turn?", listOf("Yes", "No", "With caution"), "No"),
            QuestionModel("What should you do if an ambulance is approaching?", listOf("Stop and let it pass", "Speed up", "Ignore it"), "Stop and let it pass"),
            QuestionModel("What is the legal alcohol limit for drivers?", listOf("0.02%", "0.05%", "0%"), "0%"),
            QuestionModel("What should you do when driving in fog?", listOf("Turn on high beam", "Use fog lights", "Turn off all lights"), "Use fog lights"),
            QuestionModel("What is the penalty for illegal parking?", listOf("Fine", "Jail", "Warning"), "Fine")
        )

        return QuizModel(
            id = "full_exam",
            title = "Full Exam",
            subtitle = "Complete Mixed Quiz",
            time = "30", // Full Exam duration
            questionList = fullExamQuestions,
            isHidden = true // Hidden from the topic-wise list
        )
    }
}
