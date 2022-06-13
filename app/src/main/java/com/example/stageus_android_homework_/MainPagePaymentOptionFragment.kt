package com.example.stageus_android_homework_

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

//메인 액티비티에 들어가는 프래그먼트
class MainPagePaymentOptionFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.main_payoption_fragment, container, false)
        payEvent(view)
        return view
    }
    fun payEvent(view: View){
        val cashPayButton = view.findViewById<Button>(R.id.cash_pay_button)
        cashPayButton.setOnClickListener {
            val intent = Intent(context, CashPaymentPage::class.java)
            startActivity(intent)
            }

        val cardPayButton = view.findViewById<Button>(R.id.card_pay_button)
        cardPayButton.setOnClickListener {
            val fragmentTemp = CardPaymentPage()
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragmentBox, fragmentTemp)?.commit()
        }

    }


}
