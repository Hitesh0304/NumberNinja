package com.hm.numberninja

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GameResultManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("GameResults", Context.MODE_PRIVATE)

    fun saveGameResult(gameResult: GameResult) {
        val gameResults = getGameResults()
        gameResults.add(gameResult)

        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(gameResults)

        editor.putString("gameResults", json)
        editor.apply()
    }

    fun getGameResults(): ArrayList<GameResult> {
        val gson = Gson()
        val json = sharedPreferences.getString("gameResults", "")

        val type = object : TypeToken<ArrayList<GameResult>>() {}.type
        return gson.fromJson(json, type) ?: ArrayList()
    }
}
