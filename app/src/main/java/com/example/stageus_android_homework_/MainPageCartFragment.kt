package com.example.stageus_android_homework_

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
import java.io.Serializable

//메인 액티비티에 들어가는 프래그먼트
class MainPageCartFragment:Fragment() {
    var cart = CartClass()

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
            val text2View = customView.findViewById<TextView>(R.id.text2)
            text2View.text = cart.cartList[i].productPrice + "원"
            when (cart.cartList[i].productName) {
                "와퍼" -> imageView.setImageResource(R.mipmap.whopper)
                "치즈와퍼" -> imageView.setImageResource(R.mipmap.cheesewhopper)
                "불고기와퍼" -> imageView.setImageResource(R.mipmap.bulgogiwhopper)
                "새우와퍼" -> imageView.setImageResource(R.mipmap.shrimpwhopper)
                "콜라" -> imageView.setImageResource(R.mipmap.coke)
                "사이다" -> imageView.setImageResource(R.mipmap.cider)
                "환타" -> imageView.setImageResource(R.mipmap.fanta)
                "감자튀김" -> imageView.setImageResource(R.mipmap.frenchfri)
                "어니언링" -> imageView.setImageResource(R.mipmap.onionring)
                "치즈스틱" -> imageView.setImageResource(R.mipmap.cheesestick)
                "와퍼세트" -> imageView.setImageResource(R.mipmap.whopperset)
                "치즈와퍼세트" -> imageView.setImageResource(R.mipmap.cheeseset)
                "불고기와퍼세트" -> imageView.setImageResource(R.mipmap.bulgogiset)
                "새우와퍼세트" -> imageView.setImageResource(R.mipmap.shrimpset)
            }
            layout.addView(customView)
            customView.setOnClickListener {//삭제하기
                val changeInterface = context as MainInterface
                changeInterface.delCartProduct(i)
                layout.removeView(customView)

                val textView = view.findViewById<TextView>(R.id.cartPrice)
                textView.text = cart.priceSum.toString()
                renewPriceSum(view)
            }
        }
    }
    fun renewPriceSum(view: View){
        val textView = view.findViewById<TextView>(R.id.cartPrice)
        textView.text = cart.priceSum.toString() + "원"
    }

}
