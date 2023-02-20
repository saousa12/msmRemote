package com.example.helloactivity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.v("My Program","This is onCreate")
    }

    override fun onResume() {
        super.onResume()
        Log.v("My Program","This is onResume")

    }
}