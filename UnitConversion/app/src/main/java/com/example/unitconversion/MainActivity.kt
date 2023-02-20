package com.example.unitconversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var txtinput = findViewById<EditText>(R.id.TextNumber)
        var btn1 = findViewById<Button>(R.id.button)
        var btn2 = findViewById<Button>(R.id.button2)
        var txtView: TextView = findViewById(R.id.textView)
        var btn3 = findViewById<Button>(R.id.button3)

        btn1.setOnClickListener {
            var inputnumber = if (txtinput.text.toString().length>0)txtinput.text.toString().toDouble() else 0.0
            txtView.text = " %.2f ".format( inputnumber / 2.54 ) + "Inch"
        }
        btn2.setOnClickListener {
            var inputnumber = if (txtinput.text.toString().length>0)txtinput.text.toString().toDouble() else 0.0
            txtView.text = " %.2f ".format( inputnumber * 2.54 ) + "Inch"
        }
        btn3.setOnClickListener { finish() }
    }
}