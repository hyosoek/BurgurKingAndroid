package com.example.stageus_android_homework_

import android.app.AlertDialog
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide

interface MainInterface{
    fun changeFragment(fragmentNum : Int)
    fun inCartProduct(product : ProductInCartClass)
    fun delCartProduct(index : Int)
}


class MainPageActivity : AppCompatActivity() ,MainInterface{
    lateinit var myService: CartService
    var isService = false
    var connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName?, service: IBinder?) {
            val binder = service as CartService.MyBinder
            myService = binder.getService()
            isService = true
        }
        override fun onServiceDisconnected(className: ComponentName?) {
            isService = false
        }
    }

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
            bundle.putSerializable("cartList",myService.cartList)
            bundle.putInt("priceSum",myService.priceSum)
            fragmentTemp.arguments = bundle
            val fragmentTemp1 = fragmentTemp//파일명을 가져와야함.
            supportFragmentManager.beginTransaction().replace(R.id.fragmentBox, fragmentTemp1).commit()//가져온 프래그먼트를 붙여줍니다. 첫번째는 위치, 두번째는 물건
        }
        else if(fragmentNum == 3) { //payment
            if (myService.priceSum != 0) {
                val fragmentTemp = MainPagePaymentOptionFragment()
                val bundle = Bundle()
                bundle.putInt("priceSum",myService.priceSum)
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
        myService.addCart(product) }

    override fun delCartProduct(index: Int) {
        myService.editCart(index)
    }


    override fun onCreate(savedInstanceState: Bundle?){
        Intent(this, CartService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_common_page)
        Glide.with(this)
            .load(R.mipmap.burgerkinglogo)
            .into(findViewById<ImageView>(R.id.logoimage1))
        val data = Database()
        Log.d("data12345",data.myMenuData.combo_list[0].name)
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
        val intent = Intent(this, CartService::class.java)
        ContextCompat.startForegroundService(this, intent)
    }
}


