package com.example.stageus_android_homework_

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class InitPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_common_page)

        val fragmentTemp = FragmentMain()
        supportFragmentManager.beginTransaction().replace(R.id.fragmentBox,fragmentTemp).commit()

        val backButton = findViewById<Button>(R.id.backbtn)
        supportFragmentManager.findFragmentByTag("")
        backButton.setOnClickListener {
            val fragmentTemp = FragmentMain()
            supportFragmentManager.beginTransaction().replace(R.id.fragmentBox, fragmentTemp).commit()

        }
    }
}
