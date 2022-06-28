package com.example.byoapplication

import com.example.stageus_android_homework_.R
import com.google.gson.Gson

class ProductDB {
    val comboArray = arrayOf(arrayOf("와퍼세트", "6500", R.mipmap.whopperset),
        arrayOf("치즈와퍼세트", "7000",R.mipmap.cheeseset),
        arrayOf("불고기와퍼세트", "7000",R.mipmap.bulgogiset),
        arrayOf("새우와퍼세트", "7500",R.mipmap.shrimpset))
    var burgerArrary = arrayOf(arrayOf("와퍼", "4500",R.mipmap.whopper),
        arrayOf("치즈와퍼", "5000",R.mipmap.cheesewhopper),
        arrayOf("불고기와퍼", "5000",R.mipmap.bulgogiwhopper),
        arrayOf("새우와퍼", "5500",R.mipmap.shrimpwhopper))
    var drinkArrary = arrayOf(arrayOf("콜라", "1000",R.mipmap.coke),
        arrayOf("사이다", "1000",R.mipmap.cider),
        arrayOf("환타", "1000",R.mipmap.fanta))
    var sideArrary = arrayOf(arrayOf("감자튀김", "2000",R.mipmap.frenchfri),
        arrayOf("어니언링", "3000",R.mipmap.onionring),
        arrayOf("치즈스틱", "2000",R.mipmap.cheesestick))

    val productArray = arrayOf(comboArray,burgerArrary,drinkArrary,sideArrary)

}