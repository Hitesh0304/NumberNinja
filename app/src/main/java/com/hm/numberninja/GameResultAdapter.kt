package com.hm.numberninja

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat

class GameResultAdapter(private val gameResults: List<GameResult>) :
    RecyclerView.Adapter<GameResultAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val scoreTextView: TextView = itemView.findViewById(R.id.scoreTextView)
        val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        val categoryTextView: TextView = itemView.findViewById(R.id.categoryTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_game_result, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val gameResult = gameResults[position]

        holder.scoreTextView.text = "Score: ${gameResult.score}"
        holder.dateTextView.text = "Date: ${gameResult.date}"
        holder.categoryTextView.text = "Category: ${gameResult.category}"
    }

    override fun getItemCount(): Int {
        return gameResults.size
    }
}