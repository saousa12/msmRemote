package com.example.msmremote

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.util.Log

class MySMSReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
    Log.v("my program","found! SMS")
        var message = Telephony.Sms.Intents.getMessagesFromIntent(intent)[0]
        Log.v("my program",message.displayMessageBody)
        var i=Intent(context,MainActivity::class.java)
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        if(message.displayMessageBody=="meaw"){
            i.putExtra("data","meaw")
            context.startActivity(i)
            context.startActivity(i)
        }
        if(message.displayMessageBody=="tada"){
            i.putExtra("data","tada")
            context.startActivity(i)
            context.startActivity(i)
        }

    }
}