package com.example.stageus_android_homework_

import android.app.AlertDialog
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.res.Resources
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

class MainPageCategoryFragment():Fragment() {
    var idValue = ""
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        idValue = arguments?.getString("indexValue").toString()
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
        val categoryIndex = idValue.toInt() //
        val layout = view.findViewById<LinearLayout>(R.id.parentLayout)

        val arrayTemp = arrayOf(database.myMenuData.combo_list,database.myMenuData.burger_list,database.myMenuData.drink_list,database.myMenuData.side_list)

        for (i in 0 until arrayTemp[categoryIndex-4].size) {
            val layoutInflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val customView = layoutInflater.inflate(R.layout.main_category_customview,layout,false)
            Glide.with(view)
                .load(context?.resIdByName(arrayTemp[categoryIndex-4][i].image, "mipmap")!!)
                .into(customView.findViewById(R.id.symbol))

            val text1View = customView.findViewById<TextView>(R.id.text1)
            text1View.text = arrayTemp[categoryIndex-4][i].name as String
            val text2View = customView.findViewById<TextView>(R.id.text2)
            text2View.text = (arrayTemp[categoryIndex-4][i].price) + "원"
            layout.addView(customView)

            customView.setOnClickListener {
                if (categoryIndex == 4){//세트메뉴일 경우
                    cartInOptionDialog(i)//리턴 값으로 구분해서 실행을 나누면 함수 정형화가 안됩니다.
                }
                else{
                    cartInDialog(arrayTemp[categoryIndex-4],i)//리턴 값으로 구분해서 실행을 나누면 함수 정형화가 안됩니다.
                }
            }
        }

    }
    fun cartInOptionDialog(menuIndex:Int){ //커스텀 뷰로 했어야 하나? 아닌거 같다
        val dialogTemp = AlertDialog.Builder(context)
        val dialog = dialogTemp.create()
        val dialogView = layoutInflater.inflate(R.layout.main_cart_in_combo_option_dialog,null)
        val layout = dialogView.findViewById<LinearLayout>(R.id.linearLayout)
        for (i in 0 until database.myMenuData.drink_list.size) {
            val layoutInflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val customView = layoutInflater.inflate(R.layout.main_combo_option_customview,layout,false)
            val textView = customView.findViewById<TextView>(R.id.text1)
            textView.text = database.myMenuData.drink_list[i].name
            Glide.with(customView)
                .load(context?.resIdByName(database.myMenuData.drink_list[i].image, "mipmap")!!)
                .into(customView.findViewById(R.id.image1))
            layout.addView(customView)
            customView.setOnClickListener{
                dialog.dismiss()
                cartInOptionDialog2(menuIndex,i)
            }
        }
        dialog.setView(dialogView)
        dialog.show()
    }
    fun cartInOptionDialog2(menuIndex: Int,drinkIndex: Int){
        val dialogTemp = AlertDialog.Builder(context)
        val dialog = dialogTemp.create()
        val dialogView = layoutInflater.inflate(R.layout.main_cart_in_combo_option_dialog,null)
        val layout = dialogView.findViewById<LinearLayout>(R.id.linearLayout)
        for (i in 0 until database.myMenuData.drink_list.size) {
            val layoutInflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val customView = layoutInflater.inflate(R.layout.main_combo_option_customview,layout,false)
            val textView = customView.findViewById<TextView>(R.id.text1)
            textView.text = database.myMenuData.side_list[i].name
            Glide.with(customView)
                .load(context?.resIdByName(database.myMenuData.side_list[i].image, "mipmap")!!)
                .into(customView.findViewById(R.id.image1))
            layout.addView(customView)

            customView.setOnClickListener{
                dialog.dismiss()
                cartInComboSuccess(menuIndex,drinkIndex,i)
            }
        }
        //여기까지
        dialog.setView(dialogView)
        dialog.show()
    }

    fun cartInComboSuccess(menuIndex:Int, drinkOptionIdx: Int, sideOptionIdx: Int){
        val product = ProductInCartClass()
        product.productName = database.myMenuData.combo_list[menuIndex].name.toString()
        product.productPrice = database.myMenuData.combo_list[menuIndex].price.toString()
        product.productImage = context?.resIdByName(database.myMenuData.combo_list[menuIndex].image, "mipmap")!!
        product.option1 = database.myMenuData.drink_list[drinkOptionIdx].name as String
        product.option2 = database.myMenuData.side_list[sideOptionIdx].name as String
        myService.addCart(product)
    }

    fun cartInDialog(dataArrayList: ArrayList<Database.product>,index: Int){
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
            cartInSuccess(dataArrayList, index)
            dialog.dismiss()
        }
        dialog.show()
    }

    fun cartInSuccess(dataArrayList: ArrayList<Database.product>,index:Int){
        val changeInterface = context as MainInterface
        val product = ProductInCartClass()
        product.productName = dataArrayList[index].name
        product.productPrice = dataArrayList[index].price
        product.productImage = context?.resIdByName(dataArrayList[index].image, "mipmap")!!

        changeInterface.inCartProduct(product)
    }
}



