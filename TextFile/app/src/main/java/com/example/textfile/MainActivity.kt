package com.example.textfile

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import androidx.core.app.ActivityCompat
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn_resource = findViewById<Button>(R.id.button)
        var btn_assets = findViewById<Button>(R.id.button2)
        var btn_read_sdcard = findViewById<Button>(R.id.button3)
        var btn_write_sdcard = findViewById<Button>(R.id.button4)
        var txt_file = findViewById<EditText>(R.id.editTextTextPersonName)
        var txt_data = findViewById<EditText>(R.id.editTextTextMultiLine)

        btn_resource.setOnClickListener {
        var file = resources.openRawResource(R.raw.genesis01)
            var lines = file.bufferedReader(Charsets.UTF_8).readLines()
            var text = ""
            for(line in lines)text = text + line +"\n"
            txt_data.setText(text)
        }

        btn_assets.setOnClickListener {
        var file = assets.open("textfile/pangram.txt")
            var lines = file.bufferedReader(Charsets.UTF_8).readLines()
            var text = ""
            for(line in lines)text = text + line +"\n"
            txt_data.setText(text)
        }

        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE),1234)
        }

        btn_write_sdcard.setOnClickListener {
            var file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()
                    +"/"+txt_file.text.toString())
            var outputfile = FileOutputStream(file)
            outputfile.write(txt_data.text.toString().toByteArray())
            outputfile.flush() //เพื่อความปลอดภัยให้ save and remove ทุกครั้งเพื่อจัดการข้อมูลที่ยังค้างอยู่
            outputfile.close()
        }

        btn_read_sdcard.setOnClickListener {
            var file = File(Environment.getExternalStorageDirectory()
                .toString()+"/"+txt_file.text.toString())
            var lines = file.bufferedReader(Charsets.UTF_8).readLines()
            var text = ""
            for(line in lines)text = text + line +"\n"
            txt_data.setText(text)
        }
    }
}