package com.androidatc.tictactoe

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val buttons = Array(3) {
        arrayOfNulls<Button>(
            3
        )
    }

    private var playerXTurn = true
    var roundCount = 0

    var playerXPoints = 0
    var playerOPoints = 0

    private var textViewPlayerX: TextView? = null
    private var textViewPlayerO: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonClick = findViewById<Button>(R.id.more_button)
        buttonClick.setOnClickListener{
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        textViewPlayerX = findViewById(R.id.text_view_x)
        textViewPlayerO = findViewById(R.id.text_view_o)

        for (i in 0..2) {
            for (j in 0..2) {

                val buttonID = "button_$i$j"
                val resID = resources.getIdentifier(buttonID, "id", packageName)
                buttons[i][j] = findViewById(resID)
                buttons[i][j]?.setOnClickListener(this)
            }
        }

        val buttonReset = findViewById<Button>(R.id.reset_button)
        buttonReset.setOnClickListener {

            fun onClick(V: View) {
                resetGame()
            }
        }
    }

    override fun onClick(v: View) {


        if ((v as Button).text.toString() != "") {
            return
        }
        if (playerXTurn) {
            v.text = "X"
        }
        else {
            v.text = "O"
        }

        roundCount++

        if (checkForWin()) {
            if (playerXTurn) {
                playerXWins()
            } else {
                playerOWins()
            }
        } else if (roundCount == 9) {
            draw()
        } else {
            playerXTurn = !playerXTurn
        }
    }

    private fun checkForWin(): Boolean {
        val field = Array(3) {
            arrayOfNulls<String>(
                3
            )
        }
        for (i in 0..2) {
            for (j in 0..2) {
                field[i][j] = buttons[i][j]!!.text.toString()
            }
        }
        for (i in 0..2) {
            if (field[i][0] == field[i][1] && field[i][0] == field[i][2] && field[i][0] != "") {
                return true
            }
        }
        for (i in 0..2) {
            if (field[0][i] == field[1][i] && field[0][i] == field[2][i] && field[0][i] != "") {
                return true
            }
        }
        if (field[0][0] == field[1][1] && field[0][0] == field[2][2] && field[0][0] != "") {
            return true
        }
        return field[0][2] == field[1][1] && field[0][2] == field[2][0] && field[0][2] != ""
    }

    private fun playerXWins() {
        playerXPoints++
        Toast.makeText(this, "X Wins!", Toast.LENGTH_SHORT).show()
        updatePointsText();
        resetBoard()
    }
    private fun playerOWins() {
        playerOPoints++
        Toast.makeText(this,"O Wins!", Toast.LENGTH_SHORT).show()
        updatePointsText()
        resetBoard()
    }

    private fun draw() {
        Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show()
        resetBoard()
    }
    private fun updatePointsText() {
        textViewPlayerX?.text = "Player X: $playerXPoints"
        textViewPlayerO?.text = "Player O: $playerOPoints"
    }
    private fun resetBoard() {
        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j]!!.text = ""
            }
        }

        roundCount = 0
        playerXTurn = true
    }

    private fun resetGame() {
        playerXPoints = 0
        playerOPoints = 0
        updatePointsText()
        resetBoard()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)

        outState.putInt("roundCount", roundCount)
        outState.putInt("playerXPoints", playerXPoints)
        outState.putInt("playerOPoints", playerOPoints)
        outState.putBoolean("playerXTurn", playerXTurn)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        roundCount = savedInstanceState.getInt("roundCount")
        playerXPoints = savedInstanceState.getInt("playerXPoints")
        playerOPoints = savedInstanceState.getInt("playerOPoints")
        playerXTurn = savedInstanceState.getBoolean("playerXTurn")

    }
}