package com.example.stageus_android_homework_

import android.app.AlertDialog
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
import android.widget.TextView
import androidx.fragment.app.Fragment

//메인 액티비티에 들어가는 프래그먼트
class MainPagePaymentOptionFragment:Fragment() {
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.main_payoption_fragment, container, false)
        initEvent(view)
        val intent = Intent(context, MultiService::class.java)
        activity?.bindService(intent, connection, Context.BIND_AUTO_CREATE)
        cart = arguments?.getSerializable("cartData") as CartClass
        setPaymentPrice(view)
        return view
    }

    fun initEvent(view: View){
        val backBtn = view.findViewById<Button>(R.id.backBtn)
        backBtn.setOnClickListener {
            val changeInterface = context as MainInterface
            changeInterface.changeFragment(1)
        }

        val cashPayButton = view.findViewById<Button>(R.id.cashPayBtn)
        cashPayButton.setOnClickListener {
            val intent = Intent(context, CashPaymentPageActivity::class.java)
            intent.putExtra("cartData", myService.cart)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
            startActivity(intent)
        }
        val cardPayButton = view.findViewById<Button>(R.id.cardPayBtn)
        cardPayButton.setOnClickListener {
            val intent = Intent(context, CardPaymentPageActivity::class.java)
            intent.putExtra("cartData", myService.cart)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
            startActivity(intent)
        }

    }
    fun setPaymentPrice(view: View) {
        val textView = view.findViewById<TextView>(R.id.totalPrice)
        textView.text = cart.priceSum.toString() + "원"
    }

}



