package com.example.binaryprefix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class TargetPrefix : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_target_prefix)


        var btn_kB = findViewById<Button>(R.id.button_kiB)
        var btn_MB = findViewById<Button>(R.id.button_MiB)
        var btn_GB = findViewById<Button>(R.id.button_GiB)
        var btn_TB = findViewById<Button>(R.id.button_TiB)
        var i = Intent()
        btn_kB.setOnClickListener {
            i.putExtra("prefix","kiB")
            setResult(RESULT_OK,i); finish()
        }
        btn_MB.setOnClickListener {
            i.putExtra("prefix","MiB")
            setResult(RESULT_OK,i); finish()
        }
        btn_GB.setOnClickListener {
            i.putExtra("prefix","GiB")
            setResult(RESULT_OK,i); finish()
        }
        btn_TB.setOnClickListener {
            i.putExtra("prefix","TiB")
            setResult(RESULT_OK,i); finish()
        }
    }
}