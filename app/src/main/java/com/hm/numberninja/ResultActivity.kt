package com.hm.numberninja

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    lateinit var result: TextView
    lateinit var playAgain: Button
    lateinit var exit: Button

    companion object {
        private const val CATEGORY = "category"

        fun newIntent(context: Context, category: GameCategory): Intent {
            return Intent(context, ResultActivity::class.java).apply {
                putExtra(CATEGORY, category.name)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        result = findViewById(R.id.resultTextView)
        playAgain = findViewById(R.id.againButton)
        exit = findViewById(R.id.exitButton)

        val score = intent.getIntExtra("score", 0)
        result.text = "Your score: " + score

        playAgain.setOnClickListener {
            val category = intent.getStringExtra(ResultActivity.CATEGORY)?.let { GameCategory.valueOf(it) }!!
            val intent = GameActivity.newIntent(this, category)
            startActivity(intent)
        }

        exit.setOnClickListener {
            val intent = Intent(this@ResultActivity, MainActivity::class.java)
            startActivity(intent)
            //close the old activity
            finish()
        }
    }
}