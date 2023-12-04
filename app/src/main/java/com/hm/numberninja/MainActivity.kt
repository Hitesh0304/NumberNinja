package com.hm.numberninja

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var addition: Button
    lateinit var subtraction: Button
    lateinit var multiplication: Button
    lateinit var gameResults: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addition = findViewById(R.id.addButton)
        subtraction = findViewById(R.id.subtractionButton)
        multiplication = findViewById(R.id.multiplicationButton)
        gameResults = findViewById(R.id.gameResultsButton)

        addition.setOnClickListener {
            val intent = GameActivity.newIntent(this, GameCategory.ADDITION)
            startActivity(intent)
        }

        subtraction.setOnClickListener {
            val intent = GameActivity.newIntent(this, GameCategory.SUBTRACTION)
            startActivity(intent)
        }

        multiplication.setOnClickListener {
            val intent = GameActivity.newIntent(this, GameCategory.MULTIPLICATION)
            startActivity(intent)
        }

        gameResults.setOnClickListener {
            val intent = Intent(this@MainActivity, GameResultsActivity::class.java)
            startActivity(intent)
        }
    }
}