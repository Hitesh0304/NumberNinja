package com.hm.numberninja

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GameResultsActivity: AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GameResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_results)
        supportActionBar!!.title = "Game Results"

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val manager = GameResultManager(this)
        val gameResults = manager.getGameResults()

        adapter = GameResultAdapter(gameResults)
        recyclerView.adapter = adapter




    }
}