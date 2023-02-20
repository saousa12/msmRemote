package com.example.msmremote

import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.RECEIVE_SMS)
        != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.RECEIVE_SMS,
            android.Manifest.permission.READ_PHONE_STATE),1234)
        }
        var btn=findViewById<Button>(R.id.button)
        btn.setOnClickListener { finish() }


    }
lateinit var Mp:MediaPlayer
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        if(intent?.getStringExtra("data")?:""=="meaw"){
            Mp=MediaPlayer.create(this,R.raw.cat)
            Mp.start()
        }
        if(intent?.getStringExtra("data")?:""=="tada"){
            Mp=MediaPlayer.create(this,R.raw.tada)
            Mp.start()
        }
    }
}