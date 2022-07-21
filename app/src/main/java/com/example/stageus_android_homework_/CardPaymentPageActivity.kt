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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class CardPaymentPageActivity: AppCompatActivity() {
    lateinit var myService: CartService
    var cartList = arrayListOf<ProductInCartClass>()
    var userId = ""
    var priceSum = 0

    var connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName?, service: IBinder?) {
            val binder = service as CartService.MyBinder
            myService = binder.getService()
        }
        override fun onServiceDisconnected(className: ComponentName?) {
        }
    }
    lateinit var retrofit: Retrofit //connection
    lateinit var retrofitHttp: RetrofitService //cursor 역할

    fun initRetrofit(){
        retrofit = RetrofitClient.initRetrofit() //class면 client 뒤에 ()
        retrofitHttp =  retrofit!!.create(RetrofitService::class.java)
    }

    data class orderData(
        val name: String,
        var count: Int,
        var sum_price: Int
    )

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cardpay_payment_page)
        priceSum = intent.getSerializableExtra("priceSum") as Int
        cartList = intent.getSerializableExtra("cartData") as ArrayList<ProductInCartClass>
        userId = intent.getSerializableExtra("userId") as String

        Intent(this, CartService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
        initRetrofit()
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
            orderLogUpdate()
            finish()
        }

    }
    fun setPrice() {
        val price = findViewById<TextView>(R.id.totalPrice)
        price.text = priceSum.toString() + "원"
    }
    fun orderLogUpdate(){
        var requestData: HashMap<String, Any> = HashMap() //자료형 고정
        var orderList = arrayListOf<orderData>()
        var overLapCheck = false
        var overLapIndex = -1
        var overLapPrice = -1
        for (i in 0 until cartList.size) {
            val temp1 = orderData(cartList[i].productName, 1, cartList[i].productPrice.toInt())
            for (j in 0 until orderList.size) {
                if(orderList[j].name == cartList[i].productName) {
                    overLapCheck = true //중복 있음
                    overLapIndex = j
                    overLapPrice = cartList[i].productPrice.toInt()
                }
            }
            if(overLapCheck == true){//중복 있으면:
                orderList[overLapIndex].count += 1
                orderList[overLapIndex].sum_price += overLapPrice
                overLapIndex = -1
                overLapPrice = -1
                overLapCheck = false
            }
            else {//중복 없으면:
                orderList.add(temp1)
            }
        }

        requestData["id"] = userId
        requestData["order_list"] = orderList
        requestData["total_price"] = priceSum
        Log.d("logSend","Request Fail ${requestData}")//실패사유
        retrofitHttp.postOrder(requestData)//제이슨으로 받아오기에 담을 객체 바로 생성 가능
            .enqueue(object: Callback<OrderData> {
                override fun onFailure(call: Call<OrderData>, t: Throwable) {
                    Log.d("logSend","Request Fail ${t}")//실패사유
                }
                override fun onResponse(call: Call<OrderData>, response: Response<OrderData>) {
                    Log.d("logSend","Request Success")
                    if(response.body()!!.success) {
                        Log.d("logSend","Request Real Success")

                    }
                    else{
                        Log.d("logSend","Request Fail : ${response.body()!!.message}")//실패사유
                    }
                }

            })
    }
}
