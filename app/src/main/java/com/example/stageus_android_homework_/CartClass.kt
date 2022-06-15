package com.example.stageus_android_homework_

import java.io.Serializable

class CartClass:Serializable {
    val cartList = mutableListOf<ProductInCartClass>()
    var priceSum = 0

    fun editCart(index : Int) { // index로 카트 내 데이터 제거
        if ((index < cartList.size) and (-1 < index) ){
            cartList.removeAt(index)
        }
        updateIncartPrice()
    }

    fun addCart(incartZClassProduct : ProductInCartClass) {
        cartList.add(incartZClassProduct)
        updateIncartPrice()
    }

    fun updateIncartPrice() {
        var sumTemp = 0
        for (i in 0 until cartList.size) {
            sumTemp += (cartList[i].productPrice.toInt())
        }
        priceSum = sumTemp
    }
}