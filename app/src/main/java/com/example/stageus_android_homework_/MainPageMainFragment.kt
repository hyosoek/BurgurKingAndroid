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
import androidx.fragment.app.Fragment

class MainPageMainFragment:Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.main_main_fragment, container, false)
        initEvent(view)
        return view
    }
    fun initEvent(view: View) {
        val cartButton = view.findViewById<Button>(R.id.cartBtn)
        cartButton.setOnClickListener {
            val changeInterface = context as MainInterface
            changeInterface.changeFragment(2)
        }

        val payButton = view.findViewById<Button>(R.id.payBtn)
        payButton.setOnClickListener {
            val changeInterface = context as MainInterface
            changeInterface.changeFragment(3)
        }
        val backBtn = view.findViewById<Button>(R.id.backBtn)
        backBtn.setOnClickListener {
            activity?.finish()
        }
        val comboCategoryBtn = view.findViewById<Button>(R.id.comboCategory)
        comboCategoryBtn.setOnClickListener {
            val changeInterface = context as MainInterface
            changeInterface.changeFragment(4)
        }
        val burgerCategoryBtn = view.findViewById<Button>(R.id.burgerCategory)
        burgerCategoryBtn.setOnClickListener {
            val changeInterface = context as MainInterface
            changeInterface.changeFragment(5)
        }
        val drinkCategoryBtn = view.findViewById<Button>(R.id.drinkCategory)
        drinkCategoryBtn.setOnClickListener {
            val changeInterface = context as MainInterface
            changeInterface.changeFragment(6)
        }
        val sideCategoryBtn = view.findViewById<Button>(R.id.sideCategory)
        sideCategoryBtn.setOnClickListener {
            val changeInterface = context as MainInterface
            changeInterface.changeFragment(7)
        }

    }
    fun emptyCart(){//dialog make
        val dialog = AlertDialog.Builder(context)
        dialog.setMessage("장바구니가 비어있음")
        dialog.create()
        dialog.show()
//
//        val dialogView = layoutInflater.inflate(R.layout.main_payment_alert_dialog,null)
//        val cashPayButton = dialogView.findViewById<Button>(R.id.cancelBtn)
//        cashPayButton.setOnClickListener {
//            AlertDialog.Builder()
    }

}
