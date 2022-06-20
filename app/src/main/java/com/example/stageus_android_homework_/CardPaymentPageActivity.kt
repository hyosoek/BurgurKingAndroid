package com.example.stageus_android_homework_

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CardPaymentPageActivity: AppCompatActivity() {
    var cart = CartClass()
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cardpay_payment_page)
        cart = intent.getSerializableExtra("cartData") as CartClass
        initEvent()
        setPrice()
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
    fun setPrice() {
        val price = findViewById<TextView>(R.id.totalPrice)
        cart.priceSum
        price.text = (cart.priceSum).toString()
    }
}
