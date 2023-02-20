package com.example.binaryprefix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class InputPrefix : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_prefix)

        var btn_kB = findViewById<Button>(R.id.button_kB)
        var btn_MB = findViewById<Button>(R.id.button_MB)
        var btn_GB = findViewById<Button>(R.id.button_GB)
        var btn_TB = findViewById<Button>(R.id.button_TB)
        var i = Intent()
        btn_kB.setOnClickListener {
            i.putExtra("prefix","kB")
            setResult(RESULT_OK,i); finish()
        }
        btn_MB.setOnClickListener {
            i.putExtra("prefix","MB")
            setResult(RESULT_OK,i); finish()
        }
        btn_GB.setOnClickListener {
            i.putExtra("prefix","GB")
            setResult(RESULT_OK,i); finish()
        }
        btn_TB.setOnClickListener {
            i.putExtra("prefix","TB")
            setResult(RESULT_OK,i); finish()
        }
    }
}