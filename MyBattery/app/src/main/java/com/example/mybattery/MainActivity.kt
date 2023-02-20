package com.example.mybattery

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.ContentInfoCompat.Flags

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var txt1=findViewById<TextView>(R.id.textView)
        var progress=findViewById<ProgressBar>(R.id.progressBar2)
        progress.max=100
        var receiver=object : Myreceiver() {
            override fun update(batt_value: Float, Usb: Int) {
                progress.progress=batt_value.toInt()
                txt1.text=when(Usb){
                    BatteryManager.BATTERY_STATUS_CHARGING->"Charging"
                    BatteryManager.BATTERY_STATUS_DISCHARGING->"Discharge"
                    BatteryManager.BATTERY_STATUS_FULL->"Full"
                    else->"I don't know"
                }
            }
        }
        registerReceiver(receiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    }
    abstract class Myreceiver:BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
        var level=intent?.getIntExtra(BatteryManager.EXTRA_LEVEL,0)?:0
            var scale=intent?.getIntExtra(BatteryManager.EXTRA_SCALE,1)?:1
            var batt_value=(level/scale.toFloat())*100
            var charging=intent?.getIntExtra(BatteryManager.EXTRA_STATUS,0)?:0
            update(batt_value,charging)
        }
        abstract fun update(batt_value:Float,Usb:Int)
    }
}