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
import com.example.byoapplication.ProductDB

class MainPageCategoryFragment():Fragment() {
    var idValue = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        idValue = arguments?.getString("indexValue").toString()

        val view = inflater.inflate(R.layout.main_category_fragment, container, false)
        initEvent(view) // index를 받아와야함
        createView(view)
        return view
    }

    fun initEvent(view: View){
        val backBtn = view.findViewById<Button>(R.id.backBtn)
        backBtn.setOnClickListener{
            val changeInterface = context as MainInterface
            changeInterface.changeFragment(1)
        }
    }
    fun createView(view: View){
        val index = idValue.toInt()
        val layout = view.findViewById<LinearLayout>(R.id.parentLayout)
        val productDB = ProductDB()
        if (index == 4){//세트메뉴
            for (i in 0 until productDB.productArray[0].size) {
                val layoutInflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val customView = layoutInflater.inflate(R.layout.main_category_customview,layout,false)
                val imageView = customView.findViewById<ImageView>(R.id.symbol)
                val text1View = customView.findViewById<TextView>(R.id.text1)
                text1View.text = productDB.productArray[0][i][0] + " 세트"
                val text2View = customView.findViewById<TextView>(R.id.text2)
                text2View.text = (productDB.productArray[0][i][1].toInt()+2000).toString() + "원"

                when (productDB.productArray[0][i][0]) {
                    "와퍼" -> imageView.setImageResource(R.mipmap.whopperset)
                    "치즈와퍼" -> imageView.setImageResource(R.mipmap.cheeseset)
                    "불고기와퍼" -> imageView.setImageResource(R.mipmap.bulgogiset)
                    "새우와퍼" -> imageView.setImageResource(R.mipmap.shrimpset)
                }
                layout.addView(customView)
                customView.setOnClickListener {
                    val changeInterface = context as MainInterface
                    val product = ProductInCartClass()
                    product.productName = productDB.productArray[0][i][0] + "세트"
                    product.productPrice = (productDB.productArray[0][i][1].toInt()+2000).toString()
                    changeInterface.inCartProduct(product)
                }
            }
        }
        else{
            for (i in 0 until productDB.productArray[index-5].size) {
                val layoutInflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val customView = layoutInflater.inflate(R.layout.main_category_customview,layout,false)
                val imageView = customView.findViewById<ImageView>(R.id.symbol)
                val text1View = customView.findViewById<TextView>(R.id.text1)
                text1View.text = productDB.productArray[index-5][i][0]
                val text2View = customView.findViewById<TextView>(R.id.text2)
                text2View.text = productDB.productArray[index-5][i][1] + "원"

                when (productDB.productArray[index-5][i][0]) {
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
                }
                layout.addView(customView)
                customView.setOnClickListener {
                    val changeInterface = context as MainInterface
                    val product = ProductInCartClass()
                    product.productName = productDB.productArray[index-5][i][0]
                    product.productPrice = productDB.productArray[index-5][i][1]
                    changeInterface.inCartProduct(product)
                }
            }
        }
    }
}



