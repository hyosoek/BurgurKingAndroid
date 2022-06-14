package com.example.stageus_android_homework_

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

interface ChangeFragment{
    fun changeFragment(fragmentNum : Int)
}

class MainPageActivity : AppCompatActivity() ,ChangeFragment{
    override fun changeFragment(fragmentNum : Int) {
        if (fragmentNum == 0) {
            finish()
        }
        else if (fragmentNum == 1) { //main
            val fragmentTemp = MainPageMainFragment()
            val bundle = Bundle()
            supportFragmentManager.beginTransaction().replace(R.id.fragmentBox, fragmentTemp).commit()
        }
        else if(fragmentNum == 2) { //cart
            val fragmentTemp = MainPageCartFragment()//파일명을 가져와야함.
            supportFragmentManager.beginTransaction().replace(R.id.fragmentBox, fragmentTemp).commit()
        }
        else if(fragmentNum == 3) { //payment
            val fragmentTemp = MainPagePaymentOptionFragment()
            supportFragmentManager.beginTransaction().replace(R.id.fragmentBox, fragmentTemp).commit()
        }
        else {
            val fragmentTemp = MainPageCategoryFragment()//파일명을 가져와야함.
            val bundle = Bundle()
            bundle.putString("indexValue",fragmentNum.toString())
            fragmentTemp.arguments = bundle
            supportFragmentManager.beginTransaction().replace(R.id.fragmentBox, fragmentTemp).commit()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_common_page)
        val fragmentTemp = MainPageMainFragment()//파일명을 가져와야함.
        supportFragmentManager.beginTransaction().add(R.id.fragmentBox, fragmentTemp).commit()//가져온 프래그먼트를 붙여줍니다. 첫번째는 위치, 두번째는 물건
    }

}


