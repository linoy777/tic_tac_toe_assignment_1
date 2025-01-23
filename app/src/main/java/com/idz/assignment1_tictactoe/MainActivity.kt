package com.idz.assignment1_tictactoe

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

private const val GRID_SIZE = 3

class TicTacToeActivity : AppCompatActivity() {

    private var currentMarker = "X"
    private var isGameActive = true

    // Board state
    private val boardArray = Array(GRID_SIZE) {
        Array(GRID_SIZE) { "" }
    }

    // UI elements
    private lateinit var statusTextView: TextView
    private lateinit var retryButton: Button
    private lateinit var buttonsArray: Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Basic references
        statusTextView = findViewById(R.id.activity_main_text_player_turn)
        retryButton = findViewById(R.id.activity_main_play_again_button)

        // Initial text
        statusTextView.text = "$currentMarker Player Turn"
    }
}
