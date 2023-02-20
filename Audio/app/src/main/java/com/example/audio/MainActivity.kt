package com.example.audio

import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build.VERSION_CODES.M
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    lateinit var Mp : MediaPlayer //lateinit บอกว่าจะสร้าง object ทีหลัง
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn_resource = findViewById<Button>(R.id.button)
        var btn_play = findViewById<Button>(R.id.button5)
        var btn_assets = findViewById<Button>(R.id.button2)
        var btn_sdcard = findViewById<Button>(R.id.button3)
        var btn_Pause = findViewById<Button>(R.id.button6)
        var btn_stop = findViewById<Button>(R.id.button7)

        //Hello
        btn_resource.setOnClickListener { Mp = MediaPlayer.create(this,R.raw.greeting) }
        btn_play.setOnClickListener { Mp.start() }
        //เสียงอะไรก็ได้สั้น ๆ
        btn_assets.setOnClickListener {
            Mp=MediaPlayer()
            var mp3file=assets.openFd("mp3/iamgroot.mp3")
            Mp.setDataSource(mp3file.fileDescriptor,mp3file.startOffset,mp3file.length)
            Mp.prepare() //คำสั่งในการโหลด
        }
        //ในกรณีเอาไฟล์จากคอมลง sdcard แล้วเล่น หยุดชั่วคราว หยุด /เพลง
    if(ActivityCompat.checkSelfPermission(this,android.Manifest
        .permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
        ActivityCompat.requestPermissions(this,
        arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1234)
        }
        btn_sdcard.setOnClickListener {
            Mp = MediaPlayer(); Mp.setDataSource(
            Environment.getExternalStorageDirectory().path+"/savagelove_bts.mp3"); Mp.prepare()
        }
        btn_Pause.setOnClickListener { Mp.pause() }
        btn_stop.setOnClickListener { Mp.stop() }
        if(ActivityCompat.checkSelfPermission(this,android.Manifest
                .permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.INTERNET),1234)
        }
        var btn_internet = findViewById<Button>(R.id.button4)
        btn_internet.setOnClickListener {
            Mp = MediaPlayer()
            Mp.setDataSource(this, Uri.parse("http://112.121.150.133:9000/stream?type=http&amp;nocache=1590"))
            Mp.prepare()
        }
    }


}