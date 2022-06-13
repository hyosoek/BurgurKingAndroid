package com.example.stageus_android_homework_

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_common_page)

        val fragmentTemp = MainPageMainFragment()//파일명을 가져와야함.
        supportFragmentManager.beginTransaction().replace(R.id.fragmentBox, fragmentTemp).commit()//가져온 프래그먼트를 붙여줍니다. 첫번째는 위치, 두번째는 물건
    }

}
