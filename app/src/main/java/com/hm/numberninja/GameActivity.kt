package com.hm.numberninja

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.Locale
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    lateinit var textScore: TextView
    lateinit var textLife: TextView
    lateinit var textTime: TextView

    lateinit var textQuestion: TextView
    lateinit var editTextAnswer: EditText

    lateinit var buttonOk: Button
    lateinit var buttonNext: Button

    var correctAnswer = 0
    var userScore = 0
    var userLife = 3

    lateinit var timer: CountDownTimer
    private val startTimerInMillis: Long = 60000
    var timeLeftInMillis: Long = startTimerInMillis

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        supportActionBar!!.title = "Addition"

        textScore = findViewById(R.id.scoreTextView)
        textLife = findViewById(R.id.lifeTextView)
        textTime = findViewById(R.id.timeTextView)
        textQuestion = findViewById(R.id.questionTextView)
        editTextAnswer = findViewById(R.id.answerEditText)
        buttonOk = findViewById(R.id.okButton)
        buttonNext = findViewById(R.id.nextButton)

        continueGame()

        buttonOk.setOnClickListener {

            val input = editTextAnswer.text.toString()
            if (input == "") {
                Toast.makeText(applicationContext, "Please write an answer or click the next button", Toast.LENGTH_LONG).show()
            }
            else {

                pauseTimer()
                val userAnswer = input.toInt()

                if(userAnswer == correctAnswer) {
                    userScore = userScore + 10
                    textQuestion.text = "Awesome! Your answer is correct"
                    textScore.text = userScore.toString()
                } else {
                    userLife--
                    textQuestion.text = "Sorry, you answer is wrong"
                    textLife.text = userLife.toString()
                }
            }
        }

        buttonNext.setOnClickListener {
            pauseTimer()
            resetTimer()
            editTextAnswer.setText("")

            if(userLife == 0) {
                Toast.makeText(applicationContext, "Game Over", Toast.LENGTH_LONG).show()
                val intent = Intent(this@GameActivity, ResultActivity::class.java)
                intent.putExtra("score", userScore)
                startActivity(intent)
                finish()
            }
            else {
                continueGame()
            }
        }
    }

    fun continueGame() {
        val num1 = Random.nextInt(0, 100)
        val num2 = Random.nextInt(0, 100)

        textQuestion.text = "$num1 + $num2"

        correctAnswer = num1 + num2

        startTimer()
    }

    fun startTimer() {
        timer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateTimerText()
            }

            override fun onFinish() {
                pauseTimer()
                resetTimer()
                updateTimerText()

                userLife--
                textLife.text = userLife.toString()
                textQuestion.text = "Sorry, your time is up!"
            }

        }.start()
    }

    fun updateTimerText() {
        val remainingTime: Int = (timeLeftInMillis/1000).toInt()
        textTime.text = String.format(Locale.getDefault(),"%02d", remainingTime)
    }

    fun pauseTimer() {
        timer.cancel()
    }

    fun resetTimer() {
        timeLeftInMillis = startTimerInMillis
        updateTimerText()
    }
}