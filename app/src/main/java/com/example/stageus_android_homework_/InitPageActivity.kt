package com.example.stageus_android_homework_

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NO_USER_ACTION
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class InitPageActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.init_page)
        initEvent()
    }

    fun initEvent() {
        val showMainButton = findViewById<Button>(R.id.showMainButton)
        showMainButton.setOnClickListener {
            val intentMain = Intent(this,MainPageActivity::class.java)
            startActivity(intentMain)
        }
    }

}

