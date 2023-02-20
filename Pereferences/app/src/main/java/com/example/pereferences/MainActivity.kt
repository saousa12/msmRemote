package com.example.pereferences

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn_load = findViewById<Button>(R.id.button)
        var txt_data = findViewById<EditText>(R.id.editTextTextPersonName)
        var txt_data2 = findViewById<EditText>(R.id.editTextTextPersonName2)
        var btn_save = findViewById<Button>(R.id.button2)
        var btn_exit = findViewById<Button>(R.id.button3)
        var Pref = getSharedPreferences("mypref", Context.MODE_PRIVATE)

        btn_exit.setOnClickListener { finish() }
        btn_save.setOnClickListener {
            var pref_edit = Pref.edit()
            pref_edit.putString("parameter1",txt_data.text.toString())
            pref_edit.putString("parameter2",txt_data2.text.toString())
            pref_edit.commit()
        }
        btn_load.setOnClickListener {
            var pref_all = Pref.all
            txt_data.setText(pref_all.get("parameter1")?.toString()?:"no data")
            txt_data2.setText(pref_all.get("parameter2")?.toString()?:"no data")


        }
    }
}