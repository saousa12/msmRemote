package com.example.mylauncher

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ทำให้สามารถเลื่อนได้
    var displayList = mutableListOf<String>()
    var package_list = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
    for(item in package_list){
        Log.v("My installed program",item.packageName)
        displayList.add(item.packageName)
    }
    var adapter = ArrayAdapter(this,R.layout.listi_tem,displayList.toTypedArray())
    var listview1 = findViewById<ListView>(R.id.listview1)
        listview1.adapter=adapter

        var text_package = findViewById<TextView>(R.id.textView)
        var text_class = findViewById<TextView>(R.id.textView2)
        var btn_start = findViewById<Button>(R.id.button)
        listview1.setOnItemClickListener { adapterView, view, i, l ->
            text_package.text = listview1.getItemAtPosition(i).toString()
        if(packageManager.getLaunchIntentForPackage(text_package.text.toString())!= null) {
            text_class.text = packageManager.getLaunchIntentForPackage(text_package.text.toString()
            ).toString()
            btn_start.isEnabled =true
        }else{
            text_class.text = ""
            btn_start.isEnabled = false
        }
        }
    btn_start.setOnClickListener { startActivity(packageManager.getLaunchIntentForPackage
        (text_package.text.toString().toString())) }
    }
}