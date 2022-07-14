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

class TotalSalesPage(): AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.total_sales_page)
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
    }

}
