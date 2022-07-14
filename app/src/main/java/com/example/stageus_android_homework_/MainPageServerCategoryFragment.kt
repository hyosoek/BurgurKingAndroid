package com.example.stageus_android_homework_

import android.app.AlertDialog
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.res.Resources
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

class MainPageServerCategoryFragment():Fragment() {
    val database = Database()

    lateinit var myService: CartService
    var connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName?, service: IBinder?) {
            val binder = service as CartService.MyBinder
            myService = binder.getService()
        }
        override fun onServiceDisconnected(className: ComponentName?) {
        }
    }

    var menuList = arrayListOf<MainPageActivity.getCategoryMenu>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        menuList = arguments?.getSerializable("menuList") as ArrayList<MainPageActivity.getCategoryMenu>
        Log.d("result","Result Is : ${menuList}")
        val view = inflater.inflate(R.layout.main_category_fragment, container, false)
        initEvent(view) // index를 받아와야함
        Intent(context, CartService::class.java).also { intent ->
            activity?.bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
        createView(view)
        return view
    }

    fun initEvent(view: View){
        val backBtn = view.findViewById<Button>(R.id.backBtn)
        backBtn.setOnClickListener{
            val changeInterface = context as MainInterface
            changeInterface.changeFragment(1,null)
        }
    }
    fun Context.resIdByName(resIdName: String?, resType: String): Int{ //R.id 경로를 int형으로 바꿔주는 함수
        resIdName?.let {
            return resources.getIdentifier(it, resType, packageName)
        }
        throw Resources.NotFoundException()
    }
    fun createView(view: View){
        val layout = view.findViewById<LinearLayout>(R.id.parentLayout)

        Log.d("sex :",":is mine${menuList}")
        for (i in 0 until menuList.size) {
            val layoutInflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val customView = layoutInflater.inflate(R.layout.main_category_customview,layout,false)
            Glide.with(view)
                .load("http://3.39.66.6:3000"+ menuList[i].menu_image)
                .into(customView.findViewById(R.id.symbol))

            val text1View = customView.findViewById<TextView>(R.id.text1)
            text1View.text = menuList[i].menu_name.toString()
            val text2View = customView.findViewById<TextView>(R.id.text2)
            text2View.text = menuList[i].menu_price.toString() + "원"
            layout.addView(customView)

            customView.setOnClickListener {
                cartInDialog(menuList[i])//리턴 값으로 구분해서 실행을 나누면 함수 정형화가 안됩니다.
            }
        }

    }

    fun cartInDialog(menu:MainPageActivity.getCategoryMenu){
        val dialogtemp = AlertDialog.Builder(context)
        val dialog = dialogtemp.create()
        val dialogView = layoutInflater.inflate(R.layout.main_cart_inout_dialog,null)
        val cancelButton = dialogView.findViewById<Button>(R.id.cancelBtn)
        val cartInButton = dialogView.findViewById<Button>(R.id.cartInOutBtn)
        dialog.setView(dialogView)
        cancelButton.setOnClickListener{
            dialog.dismiss()
        }
        cartInButton.setOnClickListener{
            cartInSuccess(menu)
            dialog.dismiss()
        }
        dialog.show()
    }

    fun cartInSuccess(menu:MainPageActivity.getCategoryMenu){
        val changeInterface = context as MainInterface
        val product = ProductInCartClass()
        product.productName = menu.menu_name
        product.productPrice = menu.menu_price.toString()
        product.productURLImage = menu.menu_image
        changeInterface.inCartProduct(product)
    }
}



