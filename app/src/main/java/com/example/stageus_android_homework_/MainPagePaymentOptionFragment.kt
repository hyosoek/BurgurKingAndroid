package com.example.stageus_android_homework_

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

//메인 액티비티에 들어가는 프래그먼트
class MainPagePaymentOptionFragment:Fragment() {
    var cart = CartClass()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.main_payoption_fragment, container, false)
        cart = arguments?.getSerializable("Cart") as CartClass
        initEvent(view)
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
                intent.putExtra("cartData", cart)
                startActivity(intent)
        }
        val cardPayButton = view.findViewById<Button>(R.id.cardPayBtn)
        cardPayButton.setOnClickListener {
                val intent = Intent(context, CardPaymentPageActivity::class.java)
                intent.putExtra("cartData", cart)
                startActivity(intent)
        }

    }
    fun setPaymentPrice(view: View) {
        val textView = view.findViewById<TextView>(R.id.totalPrice)
        textView.text = cart.priceSum.toString() + "원"
    }

}



