package com.example.stageus_android_homework_

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

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
