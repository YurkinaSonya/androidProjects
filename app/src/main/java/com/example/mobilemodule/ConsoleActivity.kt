package com.example.mobilemodule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mobilemodule.databinding.ActivityConsoleBinding
import com.example.mobilemodule.databinding.ActivityMainBinding

class ConsoleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bindingConsole = ActivityConsoleBinding.inflate(layoutInflater)
        setContentView(bindingConsole.root)

        val btnBack = bindingConsole.buttonBack
        btnBack.setOnClickListener () {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}