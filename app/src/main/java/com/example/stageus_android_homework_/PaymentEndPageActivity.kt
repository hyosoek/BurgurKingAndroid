package com.example.stageus_android_homework_

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PaymentEndPageActivity(): AppCompatActivity() {
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
        setContentView(R.layout.paymentend_show_page)
        Intent(this, MultiService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
        val cartData = intent.getSerializableExtra("cartData")as CartClass
        cart = cartData
        initEvent()
    }
    fun initEvent(){
        val endBtn = findViewById<Button>(R.id.endBtn)
        endBtn.setOnClickListener {
            val i = Intent(this, InitPageActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(i)
        }
        val receiptBtn = findViewById<Button>(R.id.receiptBtn)
        receiptBtn.setOnClickListener {
            val intentMain = Intent(this,RecieptPageActivity::class.java)
            intentMain.putExtra("cartData", myService.cart)
            startActivity(intentMain)
            finish()
        }

    }
}
