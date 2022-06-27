package com.example.stageus_android_homework_

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CashPaymentPageActivity : AppCompatActivity() {
    var priceSum = 0
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cashpay_payment_page)
        priceSum = intent.getSerializableExtra("priceSum") as Int
        initEvent()
        setPrice()
    }
    fun initEvent() {
        val cancelBtn = findViewById<Button>(R.id.cancelBtn)
        cancelBtn.setOnClickListener {
            finish()
        }
    }
    fun setPrice() {
        val price = findViewById<TextView>(R.id.totalPrice)
        price.text = "/" + priceSum.toString() + "원"
    }

}
