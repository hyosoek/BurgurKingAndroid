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

class CardPaymentPageActivity: AppCompatActivity() {
    var cart = CartClass()
    lateinit var myService: MultiService
    var isService = false
    var connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName?, service: IBinder?) {
            val binder = service as MultiService.MyBinder
            myService = binder.getService()
            isService = true
            Log.d("result_message","성공여부 : ${isService}")
        }
        override fun onServiceDisconnected(className: ComponentName?) {
            isService = false
        }
    }
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cardpay_payment_page)
        cart = intent.getSerializableExtra("cartData") as CartClass
        Intent(this, MultiService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
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
            intentMain.putExtra("cartData", myService.cart)
            startActivity(intentMain)
            finish()
        }
    }
    fun setPrice() {
        val price = findViewById<TextView>(R.id.totalPrice)
        cart.priceSum
        price.text = (cart.priceSum).toString() + "원"
    }
}
