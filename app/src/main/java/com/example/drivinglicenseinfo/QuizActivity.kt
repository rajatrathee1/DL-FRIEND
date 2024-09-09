package com.example.drivinglicenseinfo

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.drivinglicenseinfo.databinding.ActivityQuizBinding
import com.example.drivinglicenseinfo.databinding.ScoreDialogBinding

class QuizActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        var questionModelList: List<QuestionModel> = listOf()
        var time: String = ""
    }

    lateinit var binding: ActivityQuizBinding
    var currentQuestionIndex = 0
    var selectedAnswer = ""
    var correctCount = 0
    var incorrectCount = 0
    private var countDownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up button listeners
        binding.apply {
            btn0.setOnClickListener(this@QuizActivity)
            btn1.setOnClickListener(this@QuizActivity)
            btn2.setOnClickListener(this@QuizActivity)
            nextBtn.setOnClickListener(this@QuizActivity)
        }

        loadQuestions()
    }

    private fun startTimer() {
        // Cancel any existing timer before starting a new one
        countDownTimer?.cancel()

        countDownTimer = object : CountDownTimer(30 * 1000L, 1000L) {  // 30 seconds per question
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                binding.timerIndicatorTextview.text = String.format("%02d", seconds)
            }

            override fun onFinish() {
                handleTimeExpiry()
            }
        }.start()
    }

    private fun handleTimeExpiry() {
        if (!isFinishing && !isDestroyed) {
            AlertDialog.Builder(this)
                .setMessage("30 seconds have expired. To see the new question press Next.")
                .setCancelable(false)
                .setPositiveButton("Next Question") { _, _ ->
                    moveToNextQuestion()
                }
                .show()
        }
    }

    private fun loadQuestions() {
        selectedAnswer = ""
        binding.nextBtn.isEnabled = false // Disable the next button initially

        if (currentQuestionIndex == questionModelList.size) {
            finishedQuiz()
            return
        }

        // Cancel the previous timer if it exists
        countDownTimer?.cancel()

        binding.apply {
            questionIndicatorTextview.text = "Question ${currentQuestionIndex + 1}/${questionModelList.size}"
            questionProgressIndicator.progress = ((currentQuestionIndex.toFloat() / questionModelList.size.toFloat()) * 100).toInt()
            questionTextview.text = questionModelList[currentQuestionIndex].question
            btn0.text = questionModelList[currentQuestionIndex].options[0]
            btn1.text = questionModelList[currentQuestionIndex].options[1]
            btn2.text = questionModelList[currentQuestionIndex].options[2]

            // Show or hide the road sign image if applicable
            if (questionModelList[currentQuestionIndex].optionalImageResource != null) {
                roadSignImageView.setImageResource(questionModelList[currentQuestionIndex].optionalImageResource!!)
                roadSignImageView.visibility = View.VISIBLE
            } else {
                roadSignImageView.visibility = View.GONE
            }
        }

        // Start the timer for the new question
        startTimer()
    }

    override fun onClick(view: View?) {
        binding.apply {
            // Reset all button background colors to grey before selection
            btn0.setBackgroundColor(getColor(R.color.grey))
            btn1.setBackgroundColor(getColor(R.color.grey))
            btn2.setBackgroundColor(getColor(R.color.grey))
        }

        val clickedBtn = view as Button
        if (clickedBtn.id == R.id.next_btn) {
            // Next button is clicked
            if (selectedAnswer == questionModelList[currentQuestionIndex].correct) {
                correctCount++
            } else {
                incorrectCount++
            }
            binding.correctCount.text = correctCount.toString()
            binding.incorrectCount.text = incorrectCount.toString()

            moveToNextQuestion()
        } else {
            // Option button is clicked, register the selected answer
            selectedAnswer = clickedBtn.text.toString()
            clickedBtn.setBackgroundColor(getColor(R.color.orange))
            binding.nextBtn.isEnabled = true // Enable next button after selecting an answer
        }
    }

    private fun moveToNextQuestion() {
        // Set the user's answer in the QuestionModel for later review
        questionModelList[currentQuestionIndex].userAnswer = selectedAnswer

        // Cancel current timer before moving to the next question
        countDownTimer?.cancel()

        if (currentQuestionIndex < questionModelList.size - 1) {
            currentQuestionIndex++
            loadQuestions()  // This will restart the timer automatically
        } else {
            finishedQuiz() // Handle the quiz completion
        }
    }

    private fun finishedQuiz() {
        val totalQuestions = questionModelList.size
        val percentage = ((correctCount.toFloat() / totalQuestions.toFloat()) * 100).toInt() // Correct percentage calculation

        val dialogBinding = ScoreDialogBinding.inflate(layoutInflater)
        dialogBinding.apply {
            scoreProgressIndicator.progress = percentage
            scoreProgressText.text = "$percentage %"
            if (percentage > 60) {
                scoreTitle.text = "Congrats! You have passed"
                scoreTitle.setTextColor(Color.BLUE)
            } else {
                scoreTitle.text = "Oops! You have failed"
                scoreTitle.setTextColor(Color.RED)
            }
            scoreSubtitle.text = "$correctCount out of $totalQuestions are correct"

            // "See Answers" button setup
            seeAnswersBtn.setOnClickListener {
                val intent = Intent(this@QuizActivity, AnswersActivity::class.java)
                intent.putParcelableArrayListExtra("questionList", ArrayList(questionModelList)) // Passing the questions as Parcelable
                intent.putExtra("correctCount", correctCount)
                intent.putExtra("passingScore", 9) // Example passing score
                startActivity(intent)
            }

            finishBtn.setOnClickListener {
                finish()
            }
        }
        AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .setCancelable(false)
            .show()
    }

    override fun onDestroy() {
        countDownTimer?.cancel() // Make sure to cancel the timer if the activity is destroyed
        super.onDestroy()
    }
}
