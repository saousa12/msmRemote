package com.example.binaryprefix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var byteValue:Double=0.0
        var inputprefix = findViewById<Button>(R.id.btn_inputperfix)
        var textinput = findViewById<EditText>(R.id.editTextNumberDecimal)
        var textoutput = findViewById<TextView>(R.id.textView2)
        var btn_convert = findViewById<Button>(R.id.button2)
        var InputPrefixResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result:ActivityResult->if (result.resultCode == RESULT_OK){
              //  textoutput.text=result.data?.getStringExtra("prefix")?:"error"
              var byteValue = when(result.data?.getStringExtra("prefix")){
                    "kB" -> textinput.text.toString().toDouble()* Math.pow(10.0,3.0)
                    "MB" -> textinput.text.toString().toDouble()* Math.pow(10.0,6.0)
                    "GB" -> textinput.text.toString().toDouble()* Math.pow(10.0,9.0)
                    "TB" -> textinput.text.toString().toDouble()* Math.pow(10.0,12.0)
                  else->0.0
                }
                    textoutput.text = DecimalFormat( "#,###,###").format(byteValue)
        }else{
            textoutput.text="no selection"
        }
        }

        inputprefix.setOnClickListener {
            var i = Intent(this,InputPrefix::class.java)
            InputPrefixResult.launch(i)
        }

        var TargetPrefixResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        { result:ActivityResult->if(result.resultCode == RESULT_OK)
        {
            var ans = when(result.data!!.getStringExtra("perfix")){
                "kiB"->(byteValue/Math.pow(2.0,10.0)).toString()+" kiB"
                "MiB"->(byteValue/Math.pow(2.0,20.0)).toString()+" MiB"
                "GiB"->(byteValue/Math.pow(2.0,30.0)).toString()+" GiB"
                "TiB"->(byteValue/Math.pow(2.0,40.0)).toString()+" TiB"
                else->"I don't know"
            }
            var i = Intent(this,ShowResult::class.java)
                i.putExtra("result",ans)
            startActivity(i)
        }else{}
        }


        btn_convert.setOnClickListener {
            var i = Intent(this,TargetPrefix::class.java)
            TargetPrefixResult.launch(i)
        }
    }
}