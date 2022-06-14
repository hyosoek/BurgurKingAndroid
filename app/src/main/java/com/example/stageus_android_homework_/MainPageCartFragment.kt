package com.example.stageus_android_homework_

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

//메인 액티비티에 들어가는 프래그먼트
class MainPageCartFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.main_cart_fragment, container, false)
        initEvent(view)
        return view
    }
    fun initEvent(view:View){
        val backBtn = view.findViewById<Button>(R.id.backBtn)
        backBtn.setOnClickListener{
            val changeInterface = context as ChangeFragment
            changeInterface.changeFragment(1)
        }
    }
}
