package com.example.stageus_android_homework_

import java.io.Serializable

class ProductInCartClass:Serializable {
    lateinit var productName: String
    lateinit var productPrice: String
    var option1: String? = null
    var option2: String? = null
}