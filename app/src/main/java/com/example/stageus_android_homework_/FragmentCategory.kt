package com.example.stageus_android_homework_

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.byoapplication.ZProductDB

class FragmentCategory(index : Int):Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.main_category_fragment, container, false)

        createView(view) // index를 받아와야함
        return view
    }
    fun createView(view: View){
        val layout = view.findViewById<LinearLayout>(R.id.parent_layout)
        val productDB = ZProductDB()
        for (i in 0 until productDB.productArray[0].size) {
            productDB.productArray[0][i][0]
            val layoutInflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val customView = layoutInflater.inflate(R.layout.main_category_customview, null)
            val imageView = customView.findViewById<ImageView>(R.id.symbol)
            val text1View = customView.findViewById<TextView>(R.id.text1)
            text1View.text = productDB.productArray[0][i][0]
            val text2View = customView.findViewById<TextView>(R.id.text2)
            text2View.text = productDB.productArray[0][i][1] + "원"

            if (productDB.productArray[0][i][0] == "와퍼"){imageView.setImageResource(R.mipmap.whopper)}
            else if(productDB.productArray[0][i][0] == "치즈와퍼") {imageView.setImageResource(R.mipmap.cheesewhopper)}
            else if (productDB.productArray[0][i][0] == "불고기와퍼"){imageView.setImageResource(R.mipmap.bulgogiwhopper)}
            else if (productDB.productArray[0][i][0] == "새우와퍼"){imageView.setImageResource(R.mipmap.shrimpwhopper)}

            layout.addView(customView)
    }
//    fun createView(view: View){//index를 어떻게 받아오는가?
//        val layout = view.findViewById<LinearLayout>(R.id.parent_layout)
//        val productDB = ZProductDB()
//        for (i in 0 until productDB.productArray[0].size) {
//            productDB.productArray[0][i][0]
//            val layoutInflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//            val customView = layoutInflater.inflate(R.layout.custom_view_category, null)
//            val imageView = customView.findViewById<ImageView>(R.id.symbol)
//            val text1View = customView.findViewById<TextView>(R.id.text1)
//            text1View.text = productDB.productArray[0][i][0]
//            val text2View = customView.findViewById<TextView>(R.id.text2)
//            text2View.text = productDB.productArray[0][i][1] + "원"
//
//            if (productDB.productArray[0][i][0] == "와퍼"){imageView.setImageResource(R.mipmap.whopper)}
//            else if(productDB.productArray[0][i][0] == "치즈와퍼") {imageView.setImageResource(R.mipmap.cheesewhopper)}
//            else if (productDB.productArray[0][i][0] == "불고기와퍼"){imageView.setImageResource(R.mipmap.bulgogiwhopper)}
//            else if (productDB.productArray[0][i][0] == "새우와퍼"){imageView.setImageResource(R.mipmap.shrimpwhopper)}
//
//            layout.addView(customView)
//            val inCartButton = customView.findViewById<ConstraintLayout>(R.id.bg)
//            inCartButton.setOnClickListener {
//                val intent = Intent(context, ActivityIncartProduct::class.java)
//                startActivity(intent)
//            }
//        }
//
//    }

}}



