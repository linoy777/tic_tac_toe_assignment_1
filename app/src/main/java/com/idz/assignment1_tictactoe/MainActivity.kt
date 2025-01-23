package com.idz.assignment1_tictactoe

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

private const val GRID_SIZE = 3

class TicTacToeActivity : AppCompatActivity() {

    // Current player's marker, initially "X"
    private var currentMarker = "X"
    // Indicates if the game is still active (no winner/tie yet)
    private var isGameActive = true

    // 2D array for the board state
    private val boardArray = Array(GRID_SIZE) {
        Array(GRID_SIZE) { "" }
    }

    // UI references
    private lateinit var statusTextView: TextView
    private lateinit var retryButton: Button
    private lateinit var buttonsArray: Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Optional, for edge-to-edge layout
        setContentView(R.layout.activity_main)

        // Apply edge-to-edge insets if you have a container with ID 'main'
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize references
        statusTextView = findViewById(R.id.activity_main_text_player_turn)
        retryButton = findViewById(R.id.activity_main_play_again_button)

        // Prepare board buttons
        setupBoardButtons()

        // Set up the "Play Again" button
        retryButton.setOnClickListener {
            resetGame()
        }

        // Initial message
        statusTextView.text = "$currentMarker Player Turn"
    }


    private fun setupBoardButtons() {
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

        // Assign click listeners to each cell
        for (row in 0 until GRID_SIZE) {
            for (col in 0 until GRID_SIZE) {
                buttonsArray[row][col].setOnClickListener {
                    handleMove(row, col)
                }
            }
        }
    }


    private fun handleMove(row: Int, col: Int) {
        // Place marker only if cell is empty and game is active
        if (boardArray[row][col].isEmpty() && isGameActive) {
            boardArray[row][col] = currentMarker
            buttonsArray[row][col].text = currentMarker

            // Check for a winner or a tie
            if (checkWin()) {
                isGameActive = false
                statusTextView.text = "Player $currentMarker is the WINNER!"
                retryButton.visibility = Button.VISIBLE
            } else if (isBoardFull()) {
                isGameActive = false
                statusTextView.text = "It's a TIE!"
                retryButton.visibility = Button.VISIBLE
            } else {
                // Switch to the other player
                toggleMarker()
            }
        }
    }


    private fun toggleMarker() {
        currentMarker = if (currentMarker == "X") "O" else "X"
        statusTextView.text = "$currentMarker Player Turn"
    }


    private fun checkWin(): Boolean {
        // Check rows and columns
        for (i in 0 until GRID_SIZE) {
            // Row i
            if (boardArray[i][0] == boardArray[i][1] &&
                boardArray[i][1] == boardArray[i][2] &&
                boardArray[i][0].isNotEmpty()
            ) {
                return true
            }
            // Column i
            if (boardArray[0][i] == boardArray[1][i] &&
                boardArray[1][i] == boardArray[2][i] &&
                boardArray[0][i].isNotEmpty()
            ) {
                return true
            }
        }

        // Diagonal (top-left to bottom-right)
        if (boardArray[0][0] == boardArray[1][1] &&
            boardArray[1][1] == boardArray[2][2] &&
            boardArray[0][0].isNotEmpty()
        ) {
            return true
        }
        // Diagonal (top-right to bottom-left)
        if (boardArray[0][2] == boardArray[1][1] &&
            boardArray[1][1] == boardArray[2][0] &&
            boardArray[0][2].isNotEmpty()
        ) {
            return true
        }

        return false
    }


    private fun isBoardFull(): Boolean {
        for (row in 0 until GRID_SIZE) {
            for (col in 0 until GRID_SIZE) {
                if (boardArray[row][col].isEmpty()) {
                    return false
                }
            }
        }
        return true
    }


    private fun resetGame() {
        for (row in 0 until GRID_SIZE) {
            for (col in 0 until GRID_SIZE) {
                boardArray[row][col] = ""
                buttonsArray[row][col].text = ""
            }
        }
        currentMarker = "X"
        isGameActive = true
        statusTextView.text = "$currentMarker Player Turn"
        retryButton.visibility = Button.INVISIBLE
    }
}
