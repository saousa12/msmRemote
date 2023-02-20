package com.example.implicitintentexample

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds
import android.provider.MediaStore
import android.widget.Button
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // www
        var btn_www = findViewById<Button>(R.id.button)
        btn_www.isEnabled = false
    if(ActivityCompat.checkSelfPermission(this,Manifest.permission.INTERNET)!=
        PackageManager.PERMISSION_GRANTED) {  //เช็คว่า Permission เปิดไหม ถ้าเปิดอยู่จะข้ามไป
        ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.INTERNET),1235)
    }else{
        //ส่วนที่อินเทอร์เน็ตใช้ได้
        btn_www.isEnabled = true
        btn_www.setOnClickListener {
        var i = Intent(Intent.ACTION_VIEW,Uri.parse("http://www.mju.ac.th"))
        startActivity(i)
    }
    }
    //alarm
    var btn_alarm = findViewById<Button>(R.id.button2)
    if(ActivityCompat.checkSelfPermission(this,"com.android.alarm.permission.SET_ALARM")
        != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(
            this,
            arrayOf("com.android.alarm.permission.SET_ALARM"), 2345)
    }
        btn_alarm.setOnClickListener {
            var i = Intent(AlarmClock.ACTION_SET_TIMER)
            i.putExtra(AlarmClock.EXTRA_LENGTH, 10)
            i.putExtra(AlarmClock.EXTRA_MESSAGE, "Time up!")
            i.putExtra(AlarmClock.EXTRA_SKIP_UI,true) //ซ่อนหน้านับถอยหลัง
            startActivity(i)
    }
        //18.7765824,98.9941224 google map
        var btn_map = findViewById<Button>(R.id.button3)
        btn_map.setOnClickListener {
            var i = Intent(Intent.ACTION_VIEW, Uri.parse("geo:19.2084292,98.8522024"))
            //เช่น ร้านอาหาร
            startActivity(i)
        }
        //phonebook
    if (ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS)
    != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS),1233)
    }
        var btn_phonebook = findViewById<Button>(R.id.button4)
        btn_phonebook.setOnClickListener {
            var i = Intent(Intent.ACTION_PICK,ContactsContract.Contacts.CONTENT_URI)
            //camera
            //var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(i)
        }

    }
}