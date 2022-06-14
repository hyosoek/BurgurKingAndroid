package com.example.stageus_android_homework_

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class CardPaymentPageActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cardpay_payment_page)
        initEvent()
    }
    fun initEvent() {
        val backBtn = findViewById<Button>(R.id.backBtn)
        backBtn.setOnClickListener {
            finish()
        }
        val payBtn = findViewById<Button>(R.id.payBtn)
        payBtn.setOnClickListener {
            val intentMain = Intent(this,PaymentEndPageActivity::class.java)
            startActivity(intentMain)
            finish()
        }

    }
}
