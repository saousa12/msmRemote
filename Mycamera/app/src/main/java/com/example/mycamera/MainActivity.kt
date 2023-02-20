package com.example.mycamera

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    var iscapture=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var image1 = findViewById<ImageView>(R.id.imageView)
        var bitmap:Bitmap? =null
        var mutableBitmap:Bitmap
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED)ActivityCompat.requestPermissions(this,
            arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),1234)

        var camera_result = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            result:ActivityResult->
            if(result.resultCode == Activity.RESULT_OK) {
                if (bitmap != null) bitmap?.recycle()
                bitmap = result.data?.extras?.getParcelable<Bitmap>("data")
                mutableBitmap = bitmap!!.copy(Bitmap.Config.ARGB_8888, true)
                var canvas = Canvas(mutableBitmap)
                var paint = Paint(Paint.ANTI_ALIAS_FLAG)
                paint.setColor(Color.rgb(255, 94, 5))
                paint.textSize = resources.displayMetrics.density*8.0F
                var text = "CSMJU"
                var boundary = Rect()
                paint.getTextBounds(text, 0, text.length, boundary)
                canvas.drawText(text,1F,mutableBitmap.height.toFloat(),paint)
                image1.setImageBitmap(mutableBitmap)

                var imageFile = File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DCIM).toString()+"/myfirstcamera.jpg")
                imageFile.createNewFile()
                var bos=ByteArrayOutputStream()
                mutableBitmap.compress(Bitmap.CompressFormat.JPEG,99,bos)
                var fos=FileOutputStream(imageFile)
                fos.write(bos.toByteArray());fos.flush();fos.close()

            }
            }


        image1.setOnClickListener {
            var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            camera_result.launch(i)
        }
    }
}