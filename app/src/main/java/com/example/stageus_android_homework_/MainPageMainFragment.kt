package com.example.stageus_android_homework_

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class MainPageMainFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.main_main_fragment, container, false)
        mainEvent(view)
        return view
    }
    fun mainEvent(view: View){
        val comboCategoryBtn = view.findViewById<Button>(R.id.comboCategory)
        comboCategoryBtn.setOnClickListener {
            categoryEvent(1)
        }
        val burgerCategoryBtn = view.findViewById<Button>(R.id.burgerCategory)
        burgerCategoryBtn.setOnClickListener {
            categoryEvent(2)
        }
        val drinkCategoryBtn = view.findViewById<Button>(R.id.drinkCategory)
        drinkCategoryBtn.setOnClickListener {
            categoryEvent(3)
        }
        val sideCategoryBtn = view.findViewById<Button>(R.id.sideCategory)
        sideCategoryBtn.setOnClickListener {
            categoryEvent(4)
        }

        val cartButton = view.findViewById<Button>(R.id.cartBtn)
        cartButton.setOnClickListener {
            val fragmentTemp = MainPageCartFragment()
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragmentBox, fragmentTemp)?.commit()
        }

        val payButton = view.findViewById<Button>(R.id.payBtn)
        payButton.setOnClickListener {
            val fragmentTemp = MainPagePaymentOptionFragment()
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragmentBox, fragmentTemp)?.commit()
        }

        val backBtn = view.findViewById<Button>(R.id.backBtn)
        backBtn.setOnClickListener {
            activity?.finish()
        }
    }

    fun categoryEvent(index : Int){
        val fragmentTemp = MainPageCategoryFragment(index)
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragmentBox, fragmentTemp)?.commit()
    }


}
