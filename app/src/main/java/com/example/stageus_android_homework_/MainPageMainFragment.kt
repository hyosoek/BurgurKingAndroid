package com.example.stageus_android_homework_

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

class MainPageMainFragment:Fragment() {
    var categoryList = arrayListOf<String>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        categoryList = arguments?.getSerializable("category") as ArrayList<String>
        val view = inflater.inflate(R.layout.main_main_fragment, container, false)
        addCategory(view)
        initEvent(view)
        return view
    }

    fun addCategory(view:View)
    {
        Log.d("result1","Request : ${categoryList}")//실패사유
        val layout = view.findViewById<LinearLayout>(R.id.categoryBtn)
        val layoutInflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        for (i in 0 until categoryList.size) {
            val customView = layoutInflater.inflate(R.layout.main_main_fragment_customview, layout, false)
            val categoryBtn = customView.findViewById<TextView>(R.id.categoryBtn)
            categoryBtn.text = "special "+ categoryList[i].toString()
            layout.addView(customView)
            customView.setOnClickListener{
                val changeInterface = context as MainInterface
                changeInterface.changeFragment(8,categoryList[i])
            }
        }
    }
    fun initEvent(view: View) {
        val cartButton = view.findViewById<Button>(R.id.cartBtn)
        cartButton.setOnClickListener {
            val changeInterface = context as MainInterface
            changeInterface.changeFragment(2,null)
        }
        val payButton = view.findViewById<Button>(R.id.payBtn)
        payButton.setOnClickListener {
            val changeInterface = context as MainInterface
            changeInterface.changeFragment(3,null)
        }
        val backBtn = view.findViewById<Button>(R.id.backBtn)
        backBtn.setOnClickListener {
            activity?.finish()
        }
        val comboCategoryBtn = view.findViewById<Button>(R.id.comboCategory)
        comboCategoryBtn.setOnClickListener {
            val changeInterface = context as MainInterface
            changeInterface.changeFragment(4,null)
        }
        val burgerCategoryBtn = view.findViewById<Button>(R.id.burgerCategory)
        burgerCategoryBtn.setOnClickListener {
            val changeInterface = context as MainInterface
            changeInterface.changeFragment(5,null)
        }
        val drinkCategoryBtn = view.findViewById<Button>(R.id.drinkCategory)
        drinkCategoryBtn.setOnClickListener {
            val changeInterface = context as MainInterface
            changeInterface.changeFragment(6,null)
        }
        val sideCategoryBtn = view.findViewById<Button>(R.id.sideCategory)
        sideCategoryBtn.setOnClickListener {
            val changeInterface = context as MainInterface
            changeInterface.changeFragment(7,null)
        }
        val logInBtn = view.findViewById<Button>(R.id.signInBtn)
        logInBtn.setOnClickListener{
            val changeInterface = context as MainInterface
            changeInterface.changeFragment(9,null)
        }
        val showSalesButton = view.findViewById<Button>(R.id.orderLogBtn)
        showSalesButton.setOnClickListener{
            val changeInterface = context as MainInterface
            changeInterface.changeFragment(10,null)
        }

    }
}
