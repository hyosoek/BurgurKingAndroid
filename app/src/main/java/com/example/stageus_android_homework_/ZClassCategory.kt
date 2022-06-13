package com.example.byoapplication

class ZClassCategory(cartTemp : ZClassCart) {
    val productDB = ZProductDB()
    val cart = cartTemp

    fun showCategory(categoryIndex : Int) {
        if (categoryIndex == 4){
            showSetCategory()
        }
        else {
            for (i in 0 until productDB.productArray[categoryIndex-1].size) {
                print((i+1).toString() + ". "+productDB.productArray[categoryIndex-1][i][0] + " ")
                println(productDB.productArray[categoryIndex-1][i][1] + "원")
            }
            print("장바구니에 담으시겠습니까? \n 1.장바구니 담기 2. 돌아가기 : ")
            var askBuyOrNot = 0 //지역변수인데 공공으로 써서 뽑음 - 고민해보기

            askBuyOrNot = readLine()!!.toInt()
            if (askBuyOrNot == 1) {
                selectMenu(categoryIndex)
            } else if (askBuyOrNot == 2) {
            }
        }
    }
    fun showSetCategory(){
        for (i in 0 until productDB.productArray[0].size) {
            print((i+1).toString() + ". "+productDB.productArray[0][i][0] + "세트 ")
            println((productDB.productArray[0][i][1].toInt()+2000).toString() + "원")
        }
        print("장바구니에 담으시겠습니까? \n 1. 장바구니 담기 2. 돌아가기 : ")
        var askBuyOrNot = 0 //지역변수인데 공공으로 써서 뽑음 - 고민해보기
        askBuyOrNot = readLine()!!.toInt()
        if (askBuyOrNot == 1) {
            selectSetMenu()
        } else if (askBuyOrNot == 2) {
        }
    }

    fun selectMenu(categoryIndex : Int) {
        val incartProduct = ZClassProductInCart() // 상품을 묶어줄 객체 - 고민해보기
        print("몇번째 메뉴를 담으시겠습니까? : ")
        var IndexInput = 0 //지역변수인데 공공으로 써서 뽑음 - 고민해보기
        IndexInput = readLine()!!.toInt()
        if (IndexInput - 1 < productDB.productArray[categoryIndex - 1].size) {
            incartProduct.productName = productDB.productArray[categoryIndex - 1][IndexInput-1][0]
            incartProduct.productPrice = productDB.productArray[categoryIndex - 1][IndexInput-1][1]

            cart.addCart(incartProduct)

        }

    }
    fun selectSetMenu() {
        var option1 = 0
        var option2 = 0
        val incartProduct = ZClassProductInCart() // 상품을 묶어줄 객체 - 고민해보기
        print("몇번 세트를 구매하시겠습니까? : ")
        var IndexInput = 0 //지역변수인데 공공으로 써서 뽑음 - 고민해보기
        IndexInput = readLine()!!.toInt()
        if (IndexInput-1 < productDB.productArray[0].size) {
            print("사이드선택 - 1.감자튀김 2.오징어링 3.치즈스틱 : ")
            option1 = readLine()!!.toInt()
            print("음료선택 - 1.콜라 2.사이다 3.환타 : ")
            option2 = readLine()!!.toInt()

            incartProduct.productName = (productDB.productArray[0][IndexInput-1][0])
            incartProduct.productPrice = (productDB.productArray[0][IndexInput-1][1].toInt() + 2000).toString()
            incartProduct.option1 = productDB.productArray[1][option1-1][0]
            incartProduct.option2 = productDB.productArray[2][option2-1][0]

            cart.addCart(incartProduct)
        }
        else{}
    }
}