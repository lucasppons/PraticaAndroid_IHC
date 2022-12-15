package com.example.pratica_android.parte1_ex2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.buttonSend).setOnClickListener {
            sendMessage(it)
        }
    }

    private fun sendMessage(view: View) {
        var message = findViewById<EditText>(R.id.editTextMessage).text.toString()

        var next = Intent(this, SecondActivity::class.java)

        next.putExtra("message", message)

        startActivity(next)
    }
}
