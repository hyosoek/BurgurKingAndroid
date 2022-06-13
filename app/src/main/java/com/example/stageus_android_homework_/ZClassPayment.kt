package com.example.byoapplication

class ZClassPayment(cart_ : ZClassCart, salestemp_ : ZClassSalesTemp) {
    val cart = cart_
    val salestemp = salestemp_
    var resetFlag = false
    var machineCash = 0 //금액 모자라면 갱신해야 하므로

    fun tryPaying() {
        if (cart.cartList.size == 0) {
            println("카트가 비어있습니다!!")
        }
        else {
            var puschaseChoice = 0
            cart.showCart()
            print("결제하시겠습니까? 1.예 2.아니오 :")
            puschaseChoice = readLine()!!.toInt()
            if (puschaseChoice == 1) {
                paying()
            }
            else{}
        }
    }

    fun paying() {
        var howToPay = 0
        print("무엇으로 결제하시겠습니까? 1.현금 2.카드 :")
        howToPay = readLine()!!.toInt()
        if(howToPay == 1){
            payWithCash()
        }
        else if (howToPay == 2){
            payWithCard()
        }
        else{}
    }
    fun payWithCash() {
        var tempMoney = 0
        print("현금을 넣어주세요.(금액 입력),0 입력 시 잔액반환 :")
        tempMoney = readLine()!!.toInt()
        if(tempMoney == 0){
            println("돈을 반환합니다 : " + (machineCash.toString())+"원")
            machineCash = 0
        }
        else {
            machineCash += tempMoney
            if (machineCash >= cart.priceSum) {
                if (machineCash > cart.priceSum) {
                    print("잔액이 반환됩니다 : ")
                    println((machineCash - cart.priceSum).toString() + " 원")
                }

                print("영수증이 필요합니까? 1.예 2.아니오 : ")
                var needReceipt = 0
                needReceipt = readLine()!!.toInt()
                if (needReceipt == 1) {
                    receipt()
                } else {
                }

                println("구매해주셔서 감사합니다. 좋은 하루 되십시오. ")
                salestemp.updateSales(cart)
                resetFlag = true

            } else if (machineCash < cart.priceSum) {
                print("금액이 부족합니다. 모자란 금액 : ")
                println((cart.priceSum - machineCash).toString() + " 원")
                payWithCash()
            }
        }
    }

    fun payWithCard() {
        print("은행을 선택해주십시오 : ")
        var bankname = ""
        bankname = readLine()!!
        if((bankname == "국민") or (bankname == "기업")){
            println("결제 성공!")

            print("영수증이 필요합니까? 1.예 2.아니오 : ")
            var needReceipt = 0
            needReceipt = readLine()!!.toInt()
            if (needReceipt == 1) {
                receipt()
            } else {
            }

            println("구매해주셔서 감사합니다. 좋은 하루 되십시오. ")
            salestemp.updateSales(cart)
            resetFlag = true
        }
        else {
            println("지원하지 않는 카드입니다. 결제가 취소됩니다.")
        }
    }

    fun receipt() {
        println("---------영수증----------")
        cart.showCart()

        println("-----------------------")
    }

}