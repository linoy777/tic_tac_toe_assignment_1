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
        setupBoardButtons()
    }

    private fun handleMove(row: Int, col: Int) {
        // Only let the move happen if the cell is empty and game is active
        if (boardArray[row][col].isEmpty() && isGameActive) {
            boardArray[row][col] = currentMarker
            buttonsArray[row][col].text = currentMarker
    
            // Temporarily just switch players, no win-check yet
            toggleMarker()
        }
    }
    
    private fun toggleMarker() {
        currentMarker = if (currentMarker == "X") "O" else "X"
        statusTextView.text = "$currentMarker Player Turn"
    }

    private fun setupBoardButtons() {
    // Create a 2D array of the 9 buttons
    buttonsArray = arrayOf(
        arrayOf(
            findViewById(R.id.activity_main_button00),
            findViewById(R.id.activity_main_button01),
            findViewById(R.id.activity_main_button02)
        ),
        arrayOf(
            findViewById(R.id.activity_main_button10),
            findViewById(R.id.activity_main_button11),
            findViewById(R.id.activity_main_button12)
        ),
        arrayOf(
            findViewById(R.id.activity_main_button20),
            findViewById(R.id.activity_main_button21),
            findViewById(R.id.activity_main_button22)
        )
    )

    // Assign click listeners to each button
    for (row in 0 until GRID_SIZE) {
        for (col in 0 until GRID_SIZE) {
            buttonsArray[row][col].setOnClickListener {
                handleMove(row, col)
            }
        }
    }
}
    
}
