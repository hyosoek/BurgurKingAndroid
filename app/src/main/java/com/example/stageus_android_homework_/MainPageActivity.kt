package com.example.stageus_android_homework_

import android.app.AlertDialog
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.Serializable

interface MainInterface{
    fun changeFragment(fragmentNum: Int, categoryName: String?)
    fun inCartProduct(product : ProductInCartClass)
    fun delCartProduct(index : Int)
}

class MainPageActivity : AppCompatActivity() ,MainInterface{
    lateinit var myService: CartService
    var isService = false
    var connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName?, service: IBinder?) {
            val binder = service as CartService.MyBinder
            myService = binder.getService()
            isService = true
        }
        override fun onServiceDisconnected(className: ComponentName?) {
            isService = false
        }
    }
    var categoryList = arrayListOf<String>()
    var menuList = arrayListOf<getCategoryMenu>()

    lateinit var retrofit: Retrofit //connection
    lateinit var retrofitHttp: RetrofitService //cursor 역할
    fun initRetrofit(){
        retrofit = RetrofitClient.initRetrofit() //class면 client 뒤에 ()
        retrofitHttp =  retrofit!!.create(RetrofitService::class.java)

    }
    data class getCategoryMenu(
        val menu_name: String,
        val menu_price: Int,
        val menu_image: String
    ) : Serializable

    data class OrderData(
        val seq: Int,
        val id: String,
        val total_price: Int,
        val order_list: ArrayList<OrderCount>
    ) : Serializable

    data class OrderCount(
        val count: Int,
        val name : String,
        val sum_price: Int
    )

    override fun changeFragment(fragmentNum: Int, categoryName: String?) {
        if (fragmentNum == 0) {
            finish()
        }
        else if (fragmentNum == 1) { //main
            val fragmentTemp = MainPageMainFragment()
            val bundle = Bundle()
            Log.d("result:","category:${categoryList}")
            bundle.putSerializable("category",categoryList)
            fragmentTemp.arguments = bundle
            supportFragmentManager.beginTransaction().replace(R.id.fragmentBox, fragmentTemp).commit()
        }
        else if(fragmentNum == 2) { //cart
            val fragmentTemp = MainPageCartFragment()//파일명을 가져와야함.
            val bundle = Bundle()
            bundle.putSerializable("cartList",myService.cartList)
            bundle.putInt("priceSum",myService.priceSum)
            fragmentTemp.arguments = bundle
            val fragmentTemp1 = fragmentTemp//파일명을 가져와야함.
            supportFragmentManager.beginTransaction().replace(R.id.fragmentBox, fragmentTemp1).commit()//가져온 프래그먼트를 붙여줍니다. 첫번째는 위치, 두번째는 물건
        }
        else if(fragmentNum == 3) { //payment
            if (myService.priceSum != 0) {
                val fragmentTemp = MainPagePaymentOptionFragment()
                val bundle = Bundle()
                bundle.putInt("priceSum",myService.priceSum)
                fragmentTemp.arguments = bundle
                val fragmentTemp1 = fragmentTemp//파일명을 가져와야함.
                supportFragmentManager.beginTransaction().replace(R.id.fragmentBox, fragmentTemp1).commit()
            }
            else{
                emptyCartDialog()
            }
        }
        else if(fragmentNum == 8){
            val fragmentTemp = MainPageServerCategoryFragment()//파일명을 가져와야함.
            retrofitHttp.getCategoryMenu(categoryName!!,"kr")//제이슨으로 받아오기에 담을 객체 바로 생성 가능
                .enqueue(object: Callback<CategoryMenuData> {//enqueue가 비동기함수 + 비동기처리 됨 자동으로
                override fun onFailure(call: Call<CategoryMenuData>, t: Throwable) {}
                    override fun onResponse(call: Call<CategoryMenuData>, response: Response<CategoryMenuData>) {
                        if(response.body()!!.success) {
                            menuList.clear()
                            for (i in 0 until response.body()!!.data.size) {
                                val gson = Gson()
                                val myJson = gson.toJson(response.body()!!.data[i])
                                val myGsonData = gson.fromJson(myJson, getCategoryMenu::class.java)
                                menuList.add(myGsonData)
                            }
                            val bundle = Bundle()
                            Log.d("result","Origin Result Is : ${menuList}")
                            bundle.putSerializable("menuList",menuList)
                            fragmentTemp.arguments = bundle
                            supportFragmentManager.beginTransaction().replace(R.id.fragmentBox, fragmentTemp).commit()
                        }
                    }
                })

        }
        else if(fragmentNum == 9){
            if(myService.userId == ""){
                val fragmentTemp = MainPageLogInFragment()
                val fragmentTemp1 = fragmentTemp//파일명을 가져와야함.
                supportFragmentManager.beginTransaction().replace(R.id.fragmentBox, fragmentTemp1).commit()
            }
            else{
                logOutDialog()
            }
        }
        else if(fragmentNum == 10)//주문 기록 확인하기
        {
            if(myService.userId != ""){
                val fragmentTemp = MainPageOrderLogFragment()

                retrofitHttp.getOrder(myService.userId)//제이슨으로 받아오기에 담을 객체 바로 생성 가능
                    .enqueue(object: Callback<GetOrderData> {//enqueue가 비동기함수 + 비동기처리 됨 자동으로
                    override fun onFailure(call: Call<GetOrderData>, t: Throwable) {}
                        override fun onResponse(call: Call<GetOrderData>, response: Response<GetOrderData>) {
                            if(response.body()!!.success) {
                                Log.d("logData","${response.body()!!.data}")
                                val gson = Gson()
                                val myJson = gson.toJson(response.body()!!.data[response.body()!!.data.size-1])
                                val myGsonData = gson.fromJson(myJson, OrderData::class.java)
                                val bundle = Bundle()
                                bundle.putSerializable("orderLog", myGsonData)
                                fragmentTemp.arguments = bundle
                                supportFragmentManager.beginTransaction().replace(R.id.fragmentBox, fragmentTemp).commit()
                            }
                        }
                    })
            }
            else {
                noLogInWarnDialog()
            }
        }
        else {
            val fragmentTemp = MainPageCategoryFragment()//파일명을 가져와야함.
            val bundle = Bundle()
            bundle.putString("indexValue",fragmentNum.toString())
            fragmentTemp.arguments = bundle
            supportFragmentManager.beginTransaction().replace(R.id.fragmentBox, fragmentTemp).commit()
        }
    }
    override fun inCartProduct(product: ProductInCartClass) {
        myService.addCart(product) }

    override fun delCartProduct(index: Int) {
        myService.editCart(index)
    }


    override fun onCreate(savedInstanceState: Bundle?){
        categoryList = intent.getSerializableExtra("category") as ArrayList<String>
        Intent(this, CartService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
        initRetrofit()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_common_page)
        Glide.with(this)
            .load(R.mipmap.burgerkinglogo)
            .into(findViewById<ImageView>(R.id.logoimage1))
        val fragmentTemp = MainPageMainFragment()//파일명을 가져와야함.
        val bundle = Bundle()
        Log.d("result:","category:${categoryList}")
        bundle.putSerializable("category",categoryList)
        fragmentTemp.arguments = bundle
        supportFragmentManager.beginTransaction().add(R.id.fragmentBox, fragmentTemp).commit()//가져온 프래그먼트를 붙여줍니다. 첫번째는 위치, 두번째는 물건

    }

    fun emptyCartDialog(){//dialog make
        val dialogtemp = AlertDialog.Builder(this)
        val dialog = dialogtemp.create()
        val dialogView = layoutInflater.inflate(R.layout.main_emptycart_alert_dialog,null)
        val cashPayButton = dialogView.findViewById<Button>(R.id.cancelBtn)
        dialog.setView(dialogView)
        cashPayButton.setOnClickListener{
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        val intent = Intent(this, CartService::class.java)
        ContextCompat.startForegroundService(this, intent)
    }

    fun logOutDialog(){
        Log.d("result","Request : ${myService.userId}")//실패사유
        val dialogtemp = AlertDialog.Builder(this)
        val dialog = dialogtemp.create()
        val dialogView = layoutInflater.inflate(R.layout.main_cart_inout_dialog,null)
        val cancelBtn = dialogView.findViewById<Button>(R.id.cancelBtn)
        val logOutBtn = dialogView.findViewById<Button>(R.id.cartInOutBtn)
        val warnText = dialogView.findViewById<TextView>(R.id.warnText)
        warnText.text = "로그인 중입니다.\n로그아웃하시겠습니까?"
        logOutBtn.text = "로그아웃"
        dialog.setView(dialogView)
        cancelBtn.setOnClickListener{
            dialog.dismiss()
        }
        logOutBtn.setOnClickListener{
            myService.userId = ""
            dialog.dismiss()
        }
        dialog.show()
    }

    fun noLogInWarnDialog(){
        val dialogtemp = AlertDialog.Builder(this)
        val dialog = dialogtemp.create()
        val dialogView = layoutInflater.inflate(R.layout.main_emptycart_alert_dialog,null)
        val cancelBtn = dialogView.findViewById<Button>(R.id.cancelBtn)
        val warnText = dialogView.findViewById<TextView>(R.id.warnText)
        warnText.text = "로그인된 아이디가 없습니다!"
        dialog.setView(dialogView)
        cancelBtn.setOnClickListener{
            dialog.dismiss()
        }
        dialog.show()

    }
}


