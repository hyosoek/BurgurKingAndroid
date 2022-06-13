package com.example.stageus_android_homework_

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

//메인 액티비티에 들어가는 프래그먼트
class FragmentCart:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.main_cart_fragment, container, false)
        return view
    }

}
