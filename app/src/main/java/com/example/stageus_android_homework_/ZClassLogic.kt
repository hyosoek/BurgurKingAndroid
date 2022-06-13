package com.example.byoapplication

class ZClassLogic {
    val cart = ZClassCart() // 삭제 안 할 예정
    val category = ZClassCategory(cart) //위와 동일
    val salesTemp = ZClassSalesTemp() //일일매출
    var resetFlag = false //필요없음
    var lorbyState = 0

    init{lorbyLogic()}
    fun lorbyLogic() {
        while (true) {
//            resetFlag = false
            println("안녕하세요 버거킹입니다.")

            while (true) {
                print("1.버거선택 2.사이드선택 3.음료선택 4.세트구매 5.장바구니 6.결제 7.매출확인 10.처음으로돌아가기 : ")
                lorbyState = readLine()!!.toInt()
                if ((lorbyState!!.toInt() == 1) or (lorbyState!!.toInt() == 2) or (lorbyState!!.toInt() == 3) or (lorbyState!!.toInt() == 4 )) {
                    category.showCategory(lorbyState!!.toInt())
                }
                else if (lorbyState!!.toInt() == 5) {
                    cart.showCart()
                    cart.askEditCart()
                }
                else if (lorbyState!!.toInt() == 6) {
                    val payment = ZClassPayment(cart, salesTemp) //데이터 낭비를 피하기 위해서 중간에 생성
                    payment.tryPaying()
                    resetFlag = payment.resetFlag
                }
                else if (lorbyState!!.toInt() == 7) {
                    salesTemp.showSales()
                }
                else if (lorbyState!!.toInt() == 10) {
                    break
                }


            }
            cart.cartList.clear()
        }
    }
}

