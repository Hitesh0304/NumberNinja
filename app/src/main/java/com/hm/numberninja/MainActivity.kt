package com.hm.numberninja

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

    lateinit var addition: Button
    lateinit var subtraction: Button
    lateinit var multiplication: Button
    lateinit var gameResults: Button
    lateinit var theme: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addition = findViewById(R.id.addButton)
        subtraction = findViewById(R.id.subtractionButton)
        multiplication = findViewById(R.id.multiplicationButton)
        gameResults = findViewById(R.id.gameResultsButton)
        theme = findViewById(R.id.themeButton)

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

        updateThemeButtonText()

        theme.setOnClickListener {
            animateThemeChange()
            toggleTheme()
        }
    }

    private fun updateThemeButtonText() {
        val currentMode = AppCompatDelegate.getDefaultNightMode()
        val buttonText = if (currentMode == AppCompatDelegate.MODE_NIGHT_YES) {
            "Light Mode"
        } else {
           "Dark Mode"
        }
        theme.text = buttonText
    }

    private fun toggleTheme() {
        val newMode = if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            AppCompatDelegate.MODE_NIGHT_NO
        } else {
            AppCompatDelegate.MODE_NIGHT_YES
        }

        AppCompatDelegate.setDefaultNightMode(newMode)
    }

    private fun animateThemeChange() {
        val colorFrom = if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            resources.getColor(R.color.dark_background)
        } else {
            resources.getColor(R.color.light_background)
        }

        val colorTo = if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            resources.getColor(R.color.light_background)
        } else {
            resources.getColor(R.color.dark_background)
        }

        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
        colorAnimation.duration = 500 // milliseconds
        colorAnimation.interpolator = AccelerateDecelerateInterpolator()

        colorAnimation.addUpdateListener { animator ->
            val color = animator.animatedValue as Int
            findViewById<View>(android.R.id.content).setBackgroundColor(color)
        }

        colorAnimation.start()
    }
}