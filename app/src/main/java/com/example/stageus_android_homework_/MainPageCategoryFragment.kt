package com.example.stageus_android_homework_

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
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
        for (i in 0 until productDB.productArray[index-4].size) {
            val layoutInflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val customView = layoutInflater.inflate(R.layout.main_category_customview,layout,false)
            val imageView = customView.findViewById<ImageView>(R.id.symbol)
            imageView.setImageResource(productDB.productArray[index-4][i][2] as Int)
            val text1View = customView.findViewById<TextView>(R.id.text1)
            text1View.text = productDB.productArray[index-4][i][0] as String
            val text2View = customView.findViewById<TextView>(R.id.text2)
            text2View.text = (productDB.productArray[index-4][i][1] as String) + "원"
            layout.addView(customView)

            customView.setOnClickListener {
                if (index == 4){//세트메뉴일 경우
                    cartInDrinkDialog(productDB,index,i)//리턴 값으로 구분해서 실행을 나누면 함수 정형화가 안됩니다.
                }
                else{
                    cartInDialog(productDB,index,i)//리턴 값으로 구분해서 실행을 나누면 함수 정형화가 안됩니다.
                }
            }
        }

    }
    fun cartInDrinkDialog(productDB: ProductDB,index: Int,index2:Int){
        val dialogTemp = AlertDialog.Builder(context)
        val dialog = dialogTemp.create()
        val dialogView = layoutInflater.inflate(R.layout.main_cart_in_combo_option_dialog,null)
        val option1 = dialogView.findViewById<LinearLayout>(R.id.option1)
        val option2 = dialogView.findViewById<LinearLayout>(R.id.option2)
        val option3 = dialogView.findViewById<LinearLayout>(R.id.option3)

        option1.setOnClickListener{
            dialog.dismiss()
            cartInSideDialog(productDB, index, index2,1)
        }
        option2.setOnClickListener{
            dialog.dismiss()
            cartInSideDialog(productDB, index, index2,2)
        }
        option3.setOnClickListener{
            dialog.dismiss()
            cartInSideDialog(productDB, index, index2,3)
        }
        dialog.setView(dialogView)
        dialog.show()
    }
    fun cartInSideDialog(productDB: ProductDB,index: Int,index2:Int,drinkOptionIdx:Int){
        val dialogTemp = AlertDialog.Builder(context)
        val dialog = dialogTemp.create()
        val dialogView = layoutInflater.inflate(R.layout.main_cart_in_combo_option_dialog,null)

        val option1 = dialogView.findViewById<LinearLayout>(R.id.option1)
        val option2 = dialogView.findViewById<LinearLayout>(R.id.option2)
        val option3 = dialogView.findViewById<LinearLayout>(R.id.option3)

        option1.setOnClickListener{
            dialog.dismiss()
            cartInSideDialog(productDB, index, index2,1)
        }
        option2.setOnClickListener{
            dialog.dismiss()
            cartInSideDialog(productDB, index, index2,2)
        }
        option3.setOnClickListener{
            dialog.dismiss()
            cartInSideDialog(productDB, index, index2,3)
        }
        dialog.setView(dialogView)
        dialog.show()
    }

    fun cartInComboSuccess(productDB: ProductDB,index: Int,index2:Int,option1:String,option2:String){
        val changeInterface = context as MainInterface
        val product = ProductInCartClass()
        product.productName = productDB.productArray[index-4][index2][0] as String
        product.productPrice = productDB.productArray[index-4][index2][1] as String
        changeInterface.inCartProduct(product)
    }

    fun cartInDialog(productDB: ProductDB,index: Int,index2:Int){
        val dialogtemp = AlertDialog.Builder(context)
        val dialog = dialogtemp.create()
        val dialogView = layoutInflater.inflate(R.layout.main_cart_in_dialog,null)
        val cancelButton = dialogView.findViewById<Button>(R.id.cancelBtn)
        val cartInButton = dialogView.findViewById<Button>(R.id.cartInBtn)
        dialog.setView(dialogView)
        cancelButton.setOnClickListener{
            dialog.dismiss()
        }
        cartInButton.setOnClickListener{
            cartInSuccess(productDB, index, index2)
            dialog.dismiss()
        }
        dialog.show()
    }

    fun cartInSuccess(productDB: ProductDB,index: Int,index2:Int){
        val changeInterface = context as MainInterface
        val product = ProductInCartClass()
        product.productName = productDB.productArray[index-4][index2][0] as String
        product.productPrice = productDB.productArray[index-4][index2][1] as String
        changeInterface.inCartProduct(product)
    }
}



