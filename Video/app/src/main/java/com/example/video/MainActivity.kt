package com.example.video

import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn_resource = findViewById<Button>(R.id.button)
        var btn_sdcard = findViewById<Button>(R.id.button2)
        var btn_internet = findViewById<Button>(R.id.button3)
        var btnPlay = findViewById<Button>(R.id.button4)
        var btnPause = findViewById<Button>(R.id.button5)
        var btnStop = findViewById<Button>(R.id.button6)
        var videoview = findViewById<VideoView>(R.id.videoView)

        btn_resource.setOnClickListener {
            videoview.setVideoURI(Uri.parse("android.resource://"+
            packageName+"/"+R.raw.video2))
        }
        btnPlay.setOnClickListener { videoview.start() }
        btnPause.setOnClickListener { videoview.pause() }
        btnStop.setOnClickListener { videoview.stopPlayback() }

        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.INTERNET)
        != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
            arrayOf(android.Manifest.permission.INTERNET),1234)
        }

        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1234)
        }
        btn_sdcard.setOnClickListener {
            videoview.setVideoURI(Uri.parse("/sdcard/Loituma.3gp"))
        }
        btn_internet.setOnClickListener {
            videoview.setVideoURI(Uri.parse("http://www.drpaween.com/ohm/cs436/mv.mp4"))
        videoview.setMediaController(MediaController(this))
        }
    }
}