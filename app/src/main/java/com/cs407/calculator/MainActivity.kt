package com.cs407.calculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val numberOne: EditText = findViewById(R.id.numberOne)
        val numberTwo: EditText = findViewById(R.id.numberTwo)
        val addButton: Button = findViewById(R.id.add)
        val subtractButton: Button = findViewById(R.id.minus)
        val multiplyButton: Button = findViewById(R.id.multiply)
        val divideButton: Button = findViewById(R.id.divide)

        // Set click listeners for each button
        addButton.setOnClickListener { performOperation("+", numberOne, numberTwo) }
        subtractButton.setOnClickListener { performOperation("-", numberOne, numberTwo) }
        multiplyButton.setOnClickListener { performOperation("*", numberOne, numberTwo) }
        divideButton.setOnClickListener { performOperation("/", numberOne, numberTwo) }
    }

    private fun performOperation(operator: String, numberOne: EditText, numberTwo: EditText) {
        val num1Text = numberOne.text.toString()
        val num2Text = numberTwo.text.toString()

        if (num1Text.isNotEmpty() && num2Text.isNotEmpty()) {
            val num1 = num1Text.toDouble()
            val num2 = num2Text.toDouble()

            var result = 0.0

            when (operator) {
                "+" -> result = num1 + num2
                "-" -> result = num1 - num2
                "*" -> result = num1 * num2
                "/" -> {
                    if (num2 != 0.0) {
                        result = num1 / num2
                    } else {
                        Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show()
                        return
                    }
                }
            }

            // Create intent to move to ResultActivity and pass the result
            val intent = Intent(this, result_activity::class.java)
            intent.putExtra("RESULT", result.toString())
            startActivity(intent)
        } else Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show()
    }

}