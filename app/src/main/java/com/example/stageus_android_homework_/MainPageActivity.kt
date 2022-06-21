package com.example.stageus_android_homework_

import android.app.AlertDialog
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

interface MainInterface{

    fun changeFragment(fragmentNum : Int)
    fun inCartProduct(product : ProductInCartClass)
    fun delCartProduct(index : Int)
}
lateinit var myService: MultiService
var isService = false
var connection = object : ServiceConnection {
    override fun onServiceConnected(className: ComponentName?, service: IBinder?) {
        val binder = service as MultiService.MyBinder
        myService = binder.getService()
        isService = true
        Log.d("result_message","성공여부 : ${isService}")
    }
    override fun onServiceDisconnected(className: ComponentName?) {
        isService = false
    }
}

class MainPageActivity : AppCompatActivity() ,MainInterface{
    override fun changeFragment(fragmentNum : Int) {
        if (fragmentNum == 0) {
            finish()
        }
        else if (fragmentNum == 1) { //main
            val fragmentTemp = MainPageMainFragment()
            supportFragmentManager.beginTransaction().replace(R.id.fragmentBox, fragmentTemp).commit()
        }
        else if(fragmentNum == 2) { //cart
            val fragmentTemp = MainPageCartFragment()//파일명을 가져와야함.
            val bundle = Bundle()
            bundle.putSerializable("cartData",myService.cart)
            fragmentTemp.arguments = bundle
            val fragmentTemp1 = fragmentTemp//파일명을 가져와야함.
            supportFragmentManager.beginTransaction().replace(R.id.fragmentBox, fragmentTemp1).commit()//가져온 프래그먼트를 붙여줍니다. 첫번째는 위치, 두번째는 물건

        }
        else if(fragmentNum == 3) { //payment
            if (myService.cart.priceSum != 0) {
                val fragmentTemp = MainPagePaymentOptionFragment()
                val bundle = Bundle()
                bundle.putSerializable("cartData",myService.cart)
                fragmentTemp.arguments = bundle
                val fragmentTemp1 = fragmentTemp//파일명을 가져와야함.
                supportFragmentManager.beginTransaction().replace(R.id.fragmentBox, fragmentTemp1).commit()
            }
            else{
                emptyCartDialog()
            }
        }
        else {
            val fragmentTemp = MainPageCategoryFragment()//파일명을 가져와야함.
            val bundle = Bundle()
            bundle.putString("indexValue",fragmentNum.toString())
            fragmentTemp.arguments = bundle
            supportFragmentManager.beginTransaction().replace(R.id.fragmentBox, fragmentTemp).commit()
        }
    }
    override fun inCartProduct(product: ProductInCartClass) {
        myService.cart.addCart(product) }
    override fun delCartProduct(index: Int) {
        myService.cart.editCart(index)
    }


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_common_page)
        val fragmentTemp = MainPageMainFragment()//파일명을 가져와야함.
        supportFragmentManager.beginTransaction().add(R.id.fragmentBox, fragmentTemp).commit()//가져온 프래그먼트를 붙여줍니다. 첫번째는 위치, 두번째는 물건

    }

    fun emptyCartDialog(){//dialog make
        val dialogtemp = AlertDialog.Builder(this)
        val dialog = dialogtemp.create()
        val dialogView = layoutInflater.inflate(R.layout.main_emptycart_alert_dialog,null)
        val cashPayButton = dialogView.findViewById<Button>(R.id.cancelBtn)
        dialog.setView(dialogView)
        cashPayButton.setOnClickListener{
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        val intent = Intent(this, MultiService::class.java)
        ContextCompat.startForegroundService(this, intent)
    }

    override fun onRestart() {
        super.onRestart()
        //notification 제거
    }
}


