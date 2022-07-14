package com.example.stageus_android_homework_

import java.io.Serializable

class ProductInCartClass: Serializable {
    lateinit var productName: String
    lateinit var productPrice: String
    var productImage: Int? = null
    var productURLImage: String? = null
    var option1: String? = null
    var option2: String? = null
}