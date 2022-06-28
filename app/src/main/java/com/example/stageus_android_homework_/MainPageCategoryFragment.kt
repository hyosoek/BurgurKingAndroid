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
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

class MainPageCategoryFragment():Fragment() {
    var idValue = ""

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
            changeInterface.changeFragment(1)
        }
    }
    fun Context.resIdByName(resIdName: String?, resType: String): Int{ //R.id 경로를 int형으로 바꿔주는 함수
        resIdName?.let {
            return resources.getIdentifier(it, resType, packageName)
        }
        throw Resources.NotFoundException()
    }
    fun createView(view: View){
        val index = idValue.toInt()
        val layout = view.findViewById<LinearLayout>(R.id.parentLayout)
        val database = Database()
        val arrayTemp = arrayOf(database.myMenuData.combo_list,database.myMenuData.burger_list,database.myMenuData.drink_list,database.myMenuData.side_list)

        for (i in 0 until arrayTemp[index-4].size) {
            val layoutInflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val customView = layoutInflater.inflate(R.layout.main_category_customview,layout,false)
            Log.d("data12345",arrayTemp[index-4][i].image)
            Glide.with(view)
                .load(context?.resIdByName(arrayTemp[index-4][i].image, "mipmap")!!)
                .into(customView.findViewById(R.id.symbol))

            val text1View = customView.findViewById<TextView>(R.id.text1)
            text1View.text = arrayTemp[index-4][i].name as String
            val text2View = customView.findViewById<TextView>(R.id.text2)
            text2View.text = (arrayTemp[index-4][i].price) + "원"
            layout.addView(customView)

            customView.setOnClickListener {
                if (index == 4){//세트메뉴일 경우
                    cartInDrinkDialog(arrayTemp,index,i)//리턴 값으로 구분해서 실행을 나누면 함수 정형화가 안됩니다.
                }
                else{
                    cartInDialog(arrayTemp[index-4],i)//리턴 값으로 구분해서 실행을 나누면 함수 정형화가 안됩니다.
                }
            }
        }

    }
    fun cartInDrinkDialog(dataArrayList: Array<ArrayList<Database.product>>, index: Int, index2:Int){ //커스텀 뷰로 했어야 하나? 아닌거 같다
        val dialogTemp = AlertDialog.Builder(context)
        val dialog = dialogTemp.create()
        val dialogView = layoutInflater.inflate(R.layout.main_cart_in_combo_option_dialog,null)
        val option1 = dialogView.findViewById<LinearLayout>(R.id.option1)
        val option2 = dialogView.findViewById<LinearLayout>(R.id.option2)
        val option3 = dialogView.findViewById<LinearLayout>(R.id.option3)
        Glide.with(this)
            .load(R.mipmap.coke)
            .into(dialogView.findViewById<ImageView>(R.id.image1))
        Glide.with(this)
            .load(R.mipmap.cider)
            .into(dialogView.findViewById<ImageView>(R.id.image2))
        Glide.with(this)
            .load(R.mipmap.fanta)
            .into(dialogView.findViewById<ImageView>(R.id.image3))

        option1.setOnClickListener{
            dialog.dismiss()
            cartInSideDialog(dataArrayList, index, index2,1)
        }
        option2.setOnClickListener{
            dialog.dismiss()
            cartInSideDialog(dataArrayList, index, index2,2)
        }
        option3.setOnClickListener{
            dialog.dismiss()
            cartInSideDialog(dataArrayList, index, index2,3)
        }
        dialog.setView(dialogView)
        dialog.show()
    }
    fun cartInSideDialog(dataArrayList: Array<ArrayList<Database.product>>,index: Int,index2:Int,drinkOptionIdx:Int){
        val dialogTemp = AlertDialog.Builder(context)
        val dialog = dialogTemp.create()
        val dialogView = layoutInflater.inflate(R.layout.main_cart_in_combo_option_dialog,null)
        //이부분 리팩토링

        Glide.with(this)
            .load(R.mipmap.frenchfri)
            .into(dialogView.findViewById<ImageView>(R.id.image1))
        val textView1 = dialogView.findViewById<TextView>(R.id.text1)
        textView1.text = "감자튀김"

        Glide.with(this)
            .load(R.mipmap.onionring)
            .into(dialogView.findViewById<ImageView>(R.id.image2))
        val textView2 = dialogView.findViewById<TextView>(R.id.text2)
        textView2.text = "어니언링"

        Glide.with(this)
            .load(R.mipmap.cheesestick)
            .into(dialogView.findViewById<ImageView>(R.id.image3))
        val textView3 = dialogView.findViewById<TextView>(R.id.text3)
        textView3.text = "치즈스틱"

        val option1 = dialogView.findViewById<LinearLayout>(R.id.option1)
        val option2 = dialogView.findViewById<LinearLayout>(R.id.option2)
        val option3 = dialogView.findViewById<LinearLayout>(R.id.option3)

        option1.setOnClickListener{
            cartInComboSuccess(dataArrayList,index2,drinkOptionIdx,1)
            dialog.dismiss()
        }
        option2.setOnClickListener{
            cartInComboSuccess(dataArrayList,index2,drinkOptionIdx,2)
            dialog.dismiss()
        }
        option3.setOnClickListener{
            cartInComboSuccess(dataArrayList,index2,drinkOptionIdx,3)
            dialog.dismiss()
        }
        //여기까지
        dialog.setView(dialogView)
        dialog.show()
    }

    fun cartInComboSuccess(dataArrayList: Array<ArrayList<Database.product>>,index:Int,drinkOptionIdx: Int,sideOptionIdx: Int){
        val product = ProductInCartClass()
        product.productName = dataArrayList[0][index].name
        product.productPrice = dataArrayList[0][index].price
        product.productImage = context?.resIdByName(dataArrayList[0][index].image, "mipmap")!!
        product.option1 = dataArrayList[2][drinkOptionIdx-1].name as String
        product.option2 = dataArrayList[3][sideOptionIdx-1].name as String
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



