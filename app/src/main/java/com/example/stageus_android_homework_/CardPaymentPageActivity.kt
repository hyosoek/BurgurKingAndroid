package com.example.stageus_android_homework_

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class CardPaymentPageActivity: AppCompatActivity() {
    lateinit var myService: CartService
    var priceSum = 0
    var connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName?, service: IBinder?) {
            val binder = service as CartService.MyBinder
            myService = binder.getService()
        }
        override fun onServiceDisconnected(className: ComponentName?) {
        }
    }
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cardpay_payment_page)
        priceSum = intent.getSerializableExtra("priceSum") as Int
        Intent(this, CartService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
        imageSet()
        initEvent()
        setPrice()
    }
    fun imageSet()
    {
        Glide.with(this)
            .load(R.mipmap.cardinsert)
            .into(findViewById(R.id.card_image))
    }
    fun initEvent() {
        val backBtn = findViewById<Button>(R.id.backBtn)
        backBtn.setOnClickListener {
            finish()
        }
        val payBtn = findViewById<Button>(R.id.payBtn)
        payBtn.setOnClickListener {
            val intentMain = Intent(this,PaymentEndPageActivity::class.java)
            intentMain.putExtra("cartList", myService.cartList)
            startActivity(intentMain)
            finish()
        }
    }
    fun setPrice() {
        val price = findViewById<TextView>(R.id.totalPrice)
        price.text = priceSum.toString() + "원"
    }
}
