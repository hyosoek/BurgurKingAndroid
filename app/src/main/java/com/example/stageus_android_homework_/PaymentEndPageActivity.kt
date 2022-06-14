package com.example.stageus_android_homework_

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PaymentEndPageActivity(): AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.paymentend_show_page)
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
            startActivity(intentMain)
            finish()
        }

    }
}
