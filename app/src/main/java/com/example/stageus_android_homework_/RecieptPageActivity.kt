package com.example.stageus_android_homework_

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RecieptPageActivity(): AppCompatActivity() {
    var cart = CartClass()
    override fun onCreate(savedInstanceState: Bundle?){
        val cartData = intent.getSerializableExtra("cartData")as CartClass
        cart = cartData
        super.onCreate(savedInstanceState)
        setContentView(R.layout.receipt_show_page)
        makeView()
        initEvent()
    }
    fun initEvent(){
        val closeBtn = findViewById<Button>(R.id.closeBtn)
        closeBtn.setOnClickListener {
            val i = Intent(this, InitPageActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(i)
        }

    }
    fun makeView(){
        val layout = findViewById<LinearLayout>(R.id.linearLayout)
        for (i in 0 until cart.cartList.size) {
            val layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val customView = layoutInflater.inflate(R.layout.main_category_customview,layout,false)
            val imageView = customView.findViewById<ImageView>(R.id.symbol)
            val text1View = customView.findViewById<TextView>(R.id.text1)
            text1View.text = cart.cartList[i].productName
            if (cart.cartList[i].option1 != null){
                text1View.text = cart.cartList[i].productName + "\n(" + cart.cartList[i].option1 + ", " + cart.cartList[i].option2 + "로 변경)"
            }
            val text2View = customView.findViewById<TextView>(R.id.text2)
            text2View.text = cart.cartList[i].productPrice + "원"
            imageView.setImageResource(cart.cartList[i].productImage!!)
            layout.addView(customView)
        }
        val textView = findViewById<TextView>(R.id.price)
        textView.text = cart.priceSum.toString() + "원"
    }

}
