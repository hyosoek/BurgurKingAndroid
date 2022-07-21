package com.example.stageus_android_homework_

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

//메인 액티비티에 들어가는 프래그먼트
class MainPageOrderLogFragment:Fragment() {
    val customViewList = arrayListOf<View>()
    var cartList = arrayListOf<ProductInCartClass>()
    var priceSum = 0
    lateinit var orderData: MainPageActivity.OrderData


    lateinit var myService: CartService
    var connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName?, service: IBinder?) {
            val binder = service as CartService.MyBinder
            myService = binder.getService()
        }
        override fun onServiceDisconnected(className: ComponentName?) {
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.main_cart_fragment, container, false)
        orderData = arguments?.getSerializable("orderLog") as MainPageActivity.OrderData
        Log.d("hello","${orderData}")
        Intent(context, CartService::class.java).also { intent ->
            activity?.bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
        initEvent(view)
        return view
    }

    fun initEvent(view:View){
        val backBtn = view.findViewById<Button>(R.id.backBtn)
        backBtn.setOnClickListener{
            val changeInterface = context as MainInterface
            changeInterface.changeFragment(1,null)
        }
        makeView(view)
    }
    fun makeView(view: View){

        val layout = view.findViewById<LinearLayout>(R.id.cartList)
        for (i in 0 until orderData.order_list.size) {
            val layoutInflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val customView = layoutInflater.inflate(R.layout.main_category_customview,layout,false)
            val text1View = customView.findViewById<TextView>(R.id.text1)
            text1View.text = orderData.order_list[i].name +" "+ orderData.order_list[i].count.toString() + "개"

            val text2View = customView.findViewById<TextView>(R.id.text2)
            text2View.text = orderData.order_list[i].sum_price.toString() + "원"

//            Glide.with(view)
//                .load(cartList[i].productImage)
//                .into(customView.findViewById(R.id.symbol))
//            if(cartList[i].productURLImage !=null){
//                Glide.with(view)
//                    .load("http://3.39.66.6:3000"+ cartList[i].productURLImage)
//                    .into(customView.findViewById(R.id.symbol))
//            }
            layout.addView(customView)
            customViewList.add(customView)
        }
        val textView = view.findViewById<TextView>(R.id.cartPrice)
        textView.text = orderData.total_price.toString() + "원"
    }


}
