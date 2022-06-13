package com.example.byoapplication

class ZClassSalesTemp() {
    var salesArray  = arrayOf(arrayOf("와퍼","0"),
    arrayOf("치즈와퍼", "0"),
    arrayOf("불고기와퍼", "0"),
    arrayOf("새우와퍼", "0"),
    arrayOf("감자튀김", "0"),
    arrayOf("오징어링", "0"),
    arrayOf("치즈스틱", "0"),
    arrayOf("콜라", "0"),
    arrayOf("사이다", "0"),
    arrayOf("환타", "0"))


    fun showSales(){
        println("---------키오스크 동작 동안의 매출----------")
        for (i in 0 until salesArray.size){
            print(salesArray[i][0].toString() + " : ")
            println(salesArray[i][1].toString() +  "개")
        }
    }
    fun updateSales(cart : ZClassCart){ //결제가 완료된 카트를 통해서 데이터를 업데이트합니다.
        for (i in 0 until cart.cartList.size){
            for (j in 0 until salesArray.size){
                if (salesArray[j][0] == cart.cartList[i].productName){
                    salesArray[j][1] = (salesArray[j][1].toInt() + 1).toString()
                }
                if (salesArray[j][0] == cart.cartList[i].option1){
                    salesArray[j][1] = (salesArray[j][1].toInt() + 1).toString()
                }
                if (salesArray[j][0] == cart.cartList[i].option2){
                    salesArray[j][1] = (salesArray[j][1].toInt() + 1).toString()
                }
            }
        }
    }
}