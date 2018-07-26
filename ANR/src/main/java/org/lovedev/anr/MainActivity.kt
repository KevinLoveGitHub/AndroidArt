package org.lovedev.anr

import android.os.Bundle
import android.os.Environment
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import android.view.View
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun click(view: View) {
        val sdcard = Environment.getExternalStorageDirectory().absolutePath
        val filePath = "$sdcard/logs.txt"
        val file = File(filePath)
        val outputStream = file.outputStream()
        SystemClock.sleep(6000)
        outputStream.close()
    }
}
