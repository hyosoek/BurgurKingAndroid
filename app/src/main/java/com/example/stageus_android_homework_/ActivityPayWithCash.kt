package com.example.stageus_android_homework_

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ActivityPayWithCash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cashpay_payment_page)

        val cancelButton = findViewById<Button>(R.id.cancel_button)
        cancelButton.setOnClickListener {
            val intent = Intent(this, InitPageActivity::class.java)
            startActivity(intent)
        }
    }

}
