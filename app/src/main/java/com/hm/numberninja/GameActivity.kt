package com.hm.numberninja

import android.content.Context
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
    lateinit var buttonSkip: Button

    var correctAnswer = 0
    var userScore = 0
    var userLife = 3

    lateinit var timer: CountDownTimer
    private val startTimerInMillis: Long = 60000
    var timeLeftInMillis: Long = startTimerInMillis

    private lateinit var category: GameCategory

    companion object {
        private const val CATEGORY = "category"

        fun newIntent(context: Context, category: GameCategory): Intent {
            return Intent(context, GameActivity::class.java).apply {
                putExtra(CATEGORY, category.name)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        category = intent.getStringExtra(CATEGORY)?.let { GameCategory.valueOf(it) }!!
        supportActionBar!!.title = category.title

        textScore = findViewById(R.id.scoreTextView)
        textLife = findViewById(R.id.lifeTextView)
        textTime = findViewById(R.id.timeTextView)
        textQuestion = findViewById(R.id.questionTextView)
        editTextAnswer = findViewById(R.id.answerEditText)
        buttonOk = findViewById(R.id.okButton)
        buttonSkip = findViewById(R.id.skipButton)

        continueGame()

        buttonOk.setOnClickListener {

            val input = editTextAnswer.text.toString()
            if (input == "") {
                Toast.makeText(applicationContext, "Please write an answer or click the skip button", Toast.LENGTH_LONG).show()
            }
            else {

                pauseTimer()
                resetTimer()
                val userAnswer = input.toInt()

                if(userAnswer == correctAnswer) {
                    userScore = userScore + 10
                    Toast.makeText(applicationContext, "Awesome! Your answer is correct", Toast.LENGTH_LONG).show()
                    textScore.text = userScore.toString()
                    editTextAnswer.setText("")
                    continueGame()
                } else {
                    userLife--
                    Toast.makeText(applicationContext, "Sorry, you answer is wrong", Toast.LENGTH_LONG).show()
                    textLife.text = userLife.toString()
                    if(userLife == 0) {
                        showResult()
                    } else {
                        editTextAnswer.setText("")
                        continueGame()
                    }
                }
            }
        }

        buttonSkip.setOnClickListener {
            pauseTimer()
            resetTimer()
            editTextAnswer.setText("")
            userLife--
            if(userLife == 0) {
                showResult()
            } else {
                continueGame()
            }
            textLife.text = userLife.toString()
        }
    }

    fun continueGame() {
        val num1 = Random.nextInt(0, 100)
        val num2 = Random.nextInt(0, 100)

        when (category) {
            GameCategory.ADDITION -> {
                textQuestion.text = "$num1 + $num2"
                correctAnswer = num1 + num2
            }

            GameCategory.SUBTRACTION -> {
                textQuestion.text = "$num1 - $num2"
                correctAnswer = num1 - num2
            }

            GameCategory.MULTIPLICATION -> {
                textQuestion.text = "$num1 * $num2"
                correctAnswer = num1 * num2
            }
        }

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

                if(userLife == 0) {
                    showResult()
                } else {
                    continueGame()
                }
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

    fun showResult() {
        Toast.makeText(applicationContext, "Game Over", Toast.LENGTH_LONG).show()
        val intent = ResultActivity.newIntent(this, category)
        intent.putExtra("score", userScore)
        startActivity(intent)
        finish()
    }
}