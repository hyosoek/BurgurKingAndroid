package com.example.stageus_android_homework_

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ActivityIncartProduct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.z_trash_turn_to_dialog)
        incartEvent()
    }

    fun incartEvent() {
        val cancelBtn = findViewById<Button>(R.id.cancel_button)
        cancelBtn.setOnClickListener {
            gotoMain()
        }
        val incartBtn = findViewById<Button>(R.id.incart_button)
        incartBtn.setOnClickListener {
            gotoMain()
        }
    }
    fun gotoMain() {
        val intentMain = Intent(this, InitPageActivity::class.java)
        startActivity(intentMain)
    }

}
