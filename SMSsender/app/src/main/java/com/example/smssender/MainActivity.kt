package com.example.smssender

import android.app.Activity
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var txt_msg=findViewById<EditText>(R.id.editTextTextMultiLine)
        var txt_number=findViewById<EditText>(R.id.editTextNumber)
        var btn_send=findViewById<Button>(R.id.button)
        var txt_result=findViewById<TextView>(R.id.textView)
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.SEND_SMS)
        != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.SEND_SMS,
                android.Manifest.permission.READ_PHONE_STATE),123)
        var sendPI =PendingIntent.getBroadcast(this,0, Intent("SMS_SENT"),PendingIntent.FLAG_MUTABLE)
        var deliveredPI =PendingIntent.getBroadcast(this,0,Intent("SMS_DELIVERED"),PendingIntent.FLAG_MUTABLE)

        btn_send.setOnClickListener {
            var sms=SmsManager.getDefault()
            sms.sendTextMessage(txt_number.text.toString(),null,txt_msg.text.toString(),
            sendPI,deliveredPI)
        }
        registerReceiver(SMSSent(), IntentFilter("SMS_SENT"))
        registerReceiver(SMSdelivered(),IntentFilter("SMS_DELIVERED"))
    }
    class SMSSent:BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            when(resultCode){
                Activity.RESULT_OK->Toast.makeText(context,"The SMS is sent",Toast.LENGTH_LONG).show()
                SmsManager.RESULT_ERROR_GENERIC_FAILURE->Toast.makeText(context,"ERROR",Toast.LENGTH_LONG).show()
                SmsManager.RESULT_ERROR_NO_SERVICE->Toast.makeText(context,"No service",Toast.LENGTH_LONG).show()
                SmsManager.RESULT_ERROR_RADIO_OFF->Toast.makeText(context,"Radio off",Toast.LENGTH_LONG).show()
            }
        }
    }
    class SMSdelivered:BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            when(resultCode){
                Activity.RESULT_OK->Toast.makeText(context,"The sms was delivered",Toast.LENGTH_LONG).show()
                else->Toast.makeText(context,"The sms fail!",Toast.LENGTH_LONG).show()
            }
        }
    }
}