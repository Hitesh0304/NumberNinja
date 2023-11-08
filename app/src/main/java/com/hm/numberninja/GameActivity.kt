package com.hm.numberninja

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

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
            continueGame()
            editTextAnswer.setText("")
        }
    }

    fun continueGame() {
        val num1 = Random.nextInt(0, 100)
        val num2 = Random.nextInt(0, 100)

        textQuestion.text = "$num1 + $num2"

        correctAnswer = num1 + num2
    }
}