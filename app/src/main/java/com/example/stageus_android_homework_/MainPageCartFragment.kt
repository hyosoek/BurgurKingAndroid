package com.example.stageus_android_homework_

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

//메인 액티비티에 들어가는 프래그먼트
class MainPageCartFragment:Fragment() {
    var cart = CartClass()
    val customViewList = mutableListOf<View>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.main_cart_fragment, container, false)
        cart = arguments?.getSerializable("Cart") as CartClass
        initEvent(view)
        makeView(view)
        renewPriceSum(view)
        deleteEvent(view)
        return view
    }
    fun initEvent(view:View){
        val backBtn = view.findViewById<Button>(R.id.backBtn)
        backBtn.setOnClickListener{
            val changeInterface = context as MainInterface
            changeInterface.changeFragment(1)
        }

    }
    fun makeView(view: View){

        val layout = view.findViewById<LinearLayout>(R.id.cartList)
        for (i in 0 until cart.cartList.size) {
            val layoutInflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val customView = layoutInflater.inflate(R.layout.main_category_customview,layout,false)
            val imageView = customView.findViewById<ImageView>(R.id.symbol)
            val text1View = customView.findViewById<TextView>(R.id.text1)
            text1View.text = cart.cartList[i].productName
            if (cart.cartList[i].option1 != null){
                text1View.text = cart.cartList[i].productName + "\n(" + cart.cartList[i].option1 + ", " + cart.cartList[i].option2 + "로 변경)"
            }
            val text2View = customView.findViewById<TextView>(R.id.text2)
            text2View.text = cart.cartList[i].productPrice + "원"
            imageView.setImageResource(cart.cartList[i].productImage!!)

            layout.addView(customView)
            customViewList.add(customView)
        }
    }
    fun deleteEvent(view:View){
        for (i in 0 until customViewList.size) {
            customViewList[i].setOnClickListener{
                deleteDialog(view, view.findViewById<LinearLayout>(R.id.cartList),i)
            }
        }
    }
    fun deleteDialog(view:View,layout:LinearLayout,index:Int){
        val dialogtemp = AlertDialog.Builder(context)
        val dialog = dialogtemp.create()
        val dialogView = layoutInflater.inflate(R.layout.main_cart_inout_dialog,null) // ui재활용
        val warnTextView = dialogView.findViewById<TextView>(R.id.warnText)
        warnTextView.text = "정말로 삭제하시겠습니까?"

        val cancelButton = dialogView.findViewById<Button>(R.id.cancelBtn)
        val cartOutButton = dialogView.findViewById<Button>(R.id.cartInOutBtn)
        cartOutButton.text = "삭제"

        dialog.setView(dialogView)

        cancelButton.setOnClickListener{
            dialog.dismiss()
        }
        cartOutButton.setOnClickListener{
            renewPriceSum(view)
            deleteProduct(view, layout,index)
            dialog.dismiss()
        }
        dialog.show()
    }

    fun deleteProduct(view:View,layout: LinearLayout,index: Int){
        layout.removeView(customViewList[index]) // 이게 1을 받아야되는데, 2를 받았음 근데, 작동은 정상적
        customViewList.removeAt(index)

        val changeInterface = context as MainInterface
        changeInterface.delCartProduct(index)

        renewPriceSum(view)
        deleteEvent(view) // 재등록
    }
    fun renewPriceSum(view: View){
        val textView = view.findViewById<TextView>(R.id.cartPrice)
        textView.text = cart.priceSum.toString() + "원"
    }

}
