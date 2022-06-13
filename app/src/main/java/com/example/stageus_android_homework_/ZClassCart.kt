package com.example.byoapplication

class ZClassCart {
    val cartList = mutableListOf<ZClassProductInCart>()
    var priceSum = 0


    fun showCart() {//나중에 카트 내 동일 제품 여러개 출력 가능하게 쉽게 할 수 있습니다. (객체로 만들었기 때문)
        if (cartList.size == 0) {
            println("카트가 비어있습니다.")
        }
        else {
            for (i in 0 until cartList.size) {
                print((i + 1).toString() + "번 - ")
                if (cartList[i].option1 != null) {
                    println(cartList[i].productName + " 세트 ")
                    print(cartList[i].option1 + " with " + cartList[i].option2 + " : ")
                }
                else{
                    print(cartList[i].productName + " : ")
                }
                println(cartList[i].productPrice + "원")
                println("---------------------------------")
            }
            updateIncartPrice()
            println("총합 : " + priceSum + "원")
        }
    }
    fun askEditCart(){ //원래 위에 붙어있음, 위에 것을 따로 쓸 일 있어서 빼냄
        if (cartList.size != 0) {
            print("장바구니를 수정하시겠습니까? 1.예 2.아니오 : ")
            var fixOrNot = 0
            fixOrNot = readLine()!!.toInt()
            if (fixOrNot == 1) {
                editCart()
            } else {
            }
        }
    }

    fun editCart() {
        print("삭제할 장바구니 번호를 입력하세요. : ")
        var fixIndex = 0
        fixIndex = readLine()!!.toInt()
        if ((fixIndex-1 < cartList.size) and (-1 < fixIndex-1) ){
            cartList.removeAt(fixIndex-1)
        }
        updateIncartPrice()
    }

    fun addCart(incartZClassProduct : ZClassProductInCart) {
        cartList.add(incartZClassProduct)
        updateIncartPrice()
        //겨우 두줄인데 빼는게 나을까?
        //새로 생긴함수 딱히 필요는 없지만, 카트메뉴 변경에 따른 카트 내 값의 변화가 atomic하게 됨.
        //다른 개발자가 updateIncartPrice()없이 호출만 하면 됨
    }

    fun updateIncartPrice() {
        var sumTemp = 0
        for (i in 0 until cartList.size) {
            sumTemp += (cartList[i].productPrice.toInt())
        }
        priceSum = sumTemp
    }
}