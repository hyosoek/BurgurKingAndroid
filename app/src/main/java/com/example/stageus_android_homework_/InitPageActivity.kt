package com.example.stageus_android_homework_

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NO_USER_ACTION
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.Serializable

class InitPageActivity : AppCompatActivity() {
    lateinit var retrofit: Retrofit //connection
    lateinit var retrofitHttp: RetrofitService //cursor 역할
    var categoryList = arrayListOf<String>()

    fun initRetrofit(){
        retrofit = RetrofitClient.initRetrofit() //class면 client 뒤에 ()
        retrofitHttp =  retrofit!!.create(RetrofitService::class.java)

    }
    data class getCategory(
        val seq: String,
        val category_name: String,
        val lang: String
    )

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.init_page)
        setImage()
        initRetrofit()
        retrofitHttp.getCategory("kr")//제이슨으로 받아오기에 담을 객체 바로 생성 가능
            .enqueue(object: Callback<CategoryData> {//enqueue가 비동기함수 + 비동기처리 됨 자동으로
            override fun onFailure(call: Call<CategoryData>, t: Throwable) {}
                override fun onResponse(call: Call<CategoryData>, response: Response<CategoryData>) {
                    if(response.body()!!.success) {
                        categoryList.clear()
                        for (i in 0 until response.body()!!.data.size) {
                            val gson = Gson()
                            val myJson = gson.toJson(response.body()!!.data[i])
                            val myGsonData = gson.fromJson(myJson, getCategory::class.java)
                            categoryList.add(myGsonData.category_name)
                        }
                    }
                    else{
                        Log.d("result","Request Fail : ${response.body()!!.success}")//실패사유
                    }
                }

            })
        initEvent()
    }
    fun setImage(){
        Glide.with(this)
            .load(R.mipmap.burgerkinglogo)
            .into(findViewById(R.id.imageView))
    }

    fun initEvent() {
        val showMainButton = findViewById<Button>(R.id.signInBtn)
        showMainButton.setOnClickListener {
            val intent = Intent(this, MainPageActivity::class.java)
            intent.putExtra("category", categoryList)
            startActivity(intent)
        }
        val showSalesButton = findViewById<Button>(R.id.salesBtn)
        showSalesButton.setOnClickListener{
            val intentSales = Intent(this,TotalSalesPage::class.java)
            startActivity(intentSales)
        }
    }

}

