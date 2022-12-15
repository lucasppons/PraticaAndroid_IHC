package com.example.pratica_android.parte1_ex1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.buttonSum).setOnClickListener {
            sumValues(it)
        }
    }

    private fun sumValues(view: View) {
        var text1 = findViewById<EditText>(R.id.editTextNumber1).text.toString()
        var text2 = findViewById<EditText>(R.id.editTextNumber2).text.toString()

        var num1 = if (text1.isEmpty()) 0 else text1.toInt()
        var num2 = if (text2.isEmpty()) 0 else text2.toInt()

        findViewById<TextView>(R.id.textViewResult).text = resources.getString(R.string.text_result, num1 + num2)
    }
}
