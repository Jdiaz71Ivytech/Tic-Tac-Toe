package com.androidatc.tictactoe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)

        val buttonClick1 = findViewById<Button>(R.id.play_Btn)
        buttonClick1.setOnClickListener{
            val intent = Intent(this, other_activity::class.java)
            startActivity(intent)
        }

        val buttonClick2 = findViewById<Button>(R.id.about_Btn)
        buttonClick2.setOnClickListener{
            val intent = Intent(this, howto_activity::class.java)
            startActivity(intent)
        }

        }
    }




