package com.example.stageus_android_homework_

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CashPaymentPageActivity : AppCompatActivity() {
    var cart = CartClass()
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cashpay_payment_page)
        cart = intent.getSerializableExtra("cartData") as CartClass
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
        cart.priceSum
        price.text = "/" + (cart.priceSum).toString() + "원"
    }

}
