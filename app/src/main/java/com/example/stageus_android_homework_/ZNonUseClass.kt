package com.example.byoapplication //이 패키지가 어디에 포함되어있냐, 즉 폴더 분할하는법
//
class ZNonUseClass {}

//    lateinit var num1: String//코틀린은 변수 선언에 아무것도 들어가지 않는 것을 허용하지 않습니다. 그래서 다른 초기화를 해줘야함, 그리고 typechecker도 해줘야함, 바뀔 값이라 var로 해줍니다.
//    lateinit var num2: String
//
//    //array초기화
//    var array2 = emptyArray<Int>()
//    var list2 = mutableListOf<Int>() //mutableListOf는 크기 변환이 가능하냐는 질문, listOf의 차이점
//    //var list2 = ListOf<Int>()로 하면 add가 안됩니다.
//    var arrayList2 = mutableListOf<Any>() //any는 왠만해선 쓰지 마라 typechecker의미가 없다.
//    //1만개 정도의 데이터를 넣어서 속도 비교를 해봅시다.
//
//    init{ // class 생성자
//    }
//
//    fun printMessage() {
//        print("제목\n")
//        println("더하기 프로그램")
//        print("첫번쪠 숫자 : ")
//        num1 = readLine()!! // !!를 붙여서 null값을 입력받을수 있는 것을 방지해줍니다.
//        print("두번쪠 숫자 : ")
//        num2 = readLine()!!
//
//        print ("더하기 결과는 " + num1!!.toInt() + num2!!.toInt() + "입니다\n") // 여기서 발생되는 narrowing, print 하면 자동으로 string으로 다시 바뀜
//        //문자열 템플릿 식, 다른함수 호출도 가능합니다. $를 붙여줌으로써
//        print ("더하기 결과는  ${num1!!.toInt() + num2!!.toInt()} 입니다.\n")
//
//        print ("곱하기 결과는  ${mul(num1!!.toInt(), num2.toInt())} 입니다.")
//
//        //if문
//        if (num1!!.toInt() == 1) {
//
//        }
//        else if (num1!!.toInt() == 2){
//
//        }
//        else if (num1!!.toInt() == 3) {
//
//        }
//        //swith문입니다. when으로 구성한 것
//        when {
//            num1!!.toInt() == 1 -> {
//
//            }
//            num1!!.toInt() == 2 -> {
//
//            }
//            num1!!.toInt() == 3 -> {
//
//            }
//        }
//
//        //for문입니다.
//        for (index in 0 until 10){  //0부터 9까지 돕니다.
//
//        }
//        for (index in 0 until 10 step 2){ //i++이 2번씩 돕니다.
//
//        }
//        for (index in 9 downTo 0) { //i++이 2번씩 돕니다.
//
//        }
//        for (index in 0..9){//거꾸로는 안됩니다.
//
//        }
//        while () { // 파이썬과 동일
//
//        }
//
//    }
//    fun mul(num1: Int , num2: Int) : Int//이건 반환대한 자료형입니다.
//    { //인트를 보내줘야만 하는 것을 내가 아는거지 프로그램은 모릅니다. 그래서 들어간 것이 type checker입니다.
//        // :Int를 해주고, Int외 값을 넣으면 에러 호출해줍니다.
//        return num1 * num2
//    }


//        var array = arrayOf(1,2,3) //중간의 값을 가져오는게 빠르다. array는 값추가가 안됩니다.
//        var list = listOf(1,2,3) // 중간의 삽입삭제가 필요할때 더 좋음
//        var arrayList = arrayListOf(1,2,3)
//        array2 = arrayOf(1,2,3)
//        list2.add(3)
//        list2.add(4)
//        list2.add(5)
//
//        arrayList2.add(3)
//        arrayList2.add(4)
//        array2[1] = array2[2] //
//
//    }


//fun main() {
//    var tmp = Main1()
//    tmp.printMessage()
//}