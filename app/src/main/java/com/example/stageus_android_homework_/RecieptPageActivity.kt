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
import com.bumptech.glide.Glide

class RecieptPageActivity(): AppCompatActivity() {
    var cartList = arrayListOf<ProductInCartClass>()
    var priceSum = 0
    override fun onCreate(savedInstanceState: Bundle?){
        cartList = intent.getSerializableExtra("cartData") as ArrayList<ProductInCartClass>
        priceSum = intent.getSerializableExtra("priceSum") as Int
        super.onCreate(savedInstanceState)
        setContentView(R.layout.receipt_show_page)
        Glide.with(this)
            .load(R.mipmap.burgerkinglogo)
            .into(findViewById<ImageView>(R.id.logoimage))

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
        for (i in 0 until cartList.size) {
            val layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val customView = layoutInflater.inflate(R.layout.main_category_customview,layout,false)
            Glide.with(customView)
                .load(cartList[i].productImage)
                .into(customView.findViewById(R.id.symbol))
            val text1View = customView.findViewById<TextView>(R.id.text1)
            text1View.text = cartList[i].productName
            if (cartList[i].option1 != null){
                text1View.text = cartList[i].productName + "\n(" + cartList[i].option1 + ", " + cartList[i].option2 + "로 변경)"
            }
            val text2View = customView.findViewById<TextView>(R.id.text2)
            text2View.text = cartList[i].productPrice + "원"
            layout.addView(customView)
        }
        val textView = findViewById<TextView>(R.id.price)
        textView.text = priceSum.toString() + "원"
    }

}
