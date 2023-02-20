package com.example.binaryprefix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ShowResult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_result)

        var btn_back = findViewById<Button>(R.id.button_back)
        var text_result = findViewById<TextView>(R.id.result)

        btn_back.setOnClickListener { finish() }

        text_result.text = intent.getStringExtra("result")
    }
}