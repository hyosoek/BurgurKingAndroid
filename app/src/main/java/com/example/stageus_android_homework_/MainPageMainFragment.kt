package com.example.stageus_android_homework_

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class MainPageMainFragment:Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.main_main_fragment, container, false)
        mainEvent(view)
        return view
    }
    fun mainEvent(view: View){
        val cartButton = view.findViewById<Button>(R.id.cartBtn)
        cartButton.setOnClickListener {
            val changeInterface = context as ChangeFragment
            changeInterface.changeFragment(4)
        }

        val payButton = view.findViewById<Button>(R.id.payBtn)
        payButton.setOnClickListener {
        }

        val backBtn = view.findViewById<Button>(R.id.backBtn)
        backBtn.setOnClickListener {
            activity?.finish()
        }
    }
}
