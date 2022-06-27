package com.example.stageus_android_homework_

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class PaymentEndPageActivity(): AppCompatActivity() {
    var cartList = arrayListOf<ProductInCartClass>()
    lateinit var myService: CartService
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
        setContentView(R.layout.paymentend_show_page)
        Intent(this, CartService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
        Glide.with(this)
            .load(R.mipmap.burgerkinglogo)
            .into(findViewById<ImageView>(R.id.logoimage))

        cartList = intent.getSerializableExtra("cartList") as ArrayList<ProductInCartClass>
        initEvent()
    }
    fun initEvent(){
        val endBtn = findViewById<Button>(R.id.endBtn)
        endBtn.setOnClickListener {
            val i = Intent(this, InitPageActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(i)
            myService.delNotification()
            myService.cartClear()
        }
        val receiptBtn = findViewById<Button>(R.id.receiptBtn)
        receiptBtn.setOnClickListener {
            val intentMain = Intent(this,RecieptPageActivity::class.java)
            intentMain.putExtra("cartData", myService.cartList)
            intentMain.putExtra("priceSum", myService.priceSum)
            startActivity(intentMain)
            finish()
            myService.delNotification()
            myService.cartClear()

        }

    }
}
