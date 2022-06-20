package com.example.stageus_android_homework_

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

interface MainInterface{
    fun changeFragment(fragmentNum : Int)
    fun inCartProduct(product : ProductInCartClass)
    fun delCartProduct(index : Int)
}

class MainPageActivity : AppCompatActivity() ,MainInterface{
    val cart = CartClass()
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
            val bundle = Bundle()
            bundle.putSerializable("Cart",cart)
            fragmentTemp.arguments = bundle //이 프래그먼트의 아규먼트로 보내는 겁니다.
            val fragmentTemp1 = fragmentTemp//파일명을 가져와야함.
            supportFragmentManager.beginTransaction().replace(R.id.fragmentBox, fragmentTemp1).commit()//가져온 프래그먼트를 붙여줍니다. 첫번째는 위치, 두번째는 물건

        }
        else if(fragmentNum == 3) { //payment
            if (cart.priceSum != 0) {
                val fragmentTemp = MainPagePaymentOptionFragment()
                val bundle = Bundle()
                bundle.putSerializable("Cart", cart)
                fragmentTemp.arguments = bundle //이 프래그먼트의 아규먼트로 보내는 겁니다.
                val fragmentTemp1 = fragmentTemp//파일명을 가져와야함.
                supportFragmentManager.beginTransaction().replace(R.id.fragmentBox, fragmentTemp1)
                    .commit()
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
        cart.addCart(product)
    }
    override fun delCartProduct(index: Int) {
        cart.editCart(index)
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

}


