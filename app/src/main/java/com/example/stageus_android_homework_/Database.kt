package com.example.stageus_android_homework_

import com.google.gson.Gson


class Database {
    val data = "{"+
            "'combo_list':["+
            "{'name' : '와퍼세트','price' : '6500','image' : whopperset},"+
            "{'name' : '치즈와퍼세트','price' : '7000','image' : cheeseset},"+
            "{'name' : '불고기와퍼세트','price' : '7000','image' : bulgogiset},"+
            "{'name' : '새우와퍼세트','price' : '7500','image' : shrimpset}],"+
            "'burger_list':[{'name' : '와퍼','price' : '4500','image' : whopper},"+
            "{'name' : '치즈와퍼','price' : '5000','image' : cheesewhopper},"+
            "{'name' : '불고기와퍼','price' : '5000','image' : bulgogiwhopper},"+
            "{'name' : '새우와퍼','price' : '5500','image' : shrimpwhopper}],"+
            "'drink_list':[{'name' : '콜라','price' : '1000','image' : coke},"+
            "{'name' : '사이다','price' : '1000','image' : cider},"+
            "{'name' : '환타','price' : '1000','image' : fanta}],"+
            "'side_list':[{'name' : '감자튀김','price' : '2000','image' : frenchfri},"+
            "{'name' : '어니언링','price' : '3000','image' : onionring},"+
            "{'name' : '치즈스틱','price' : '2000','image' : cheesestick}]"+ "}"

    val gson = Gson()//위와 동일한 초기화 하지만, 추가 옵션 불가능
    data class menuList( //코틀린의 자체 용법이고 gson이 아님
        val combo_list: ArrayList<product>,
        val burger_list: ArrayList<product>,
        val drink_list: ArrayList<product>,
        val side_list: ArrayList<product>,
    )
    data class product(
        val name: String,
        val price: String,
        val image: String
    )
    val myMenuData = gson.fromJson(data,menuList::class.java)

//    val whopperSet = product("와퍼세트","6500",R.mipmap.whopperset)
//    val cheeseSet = product("치즈와퍼세트","7000",R.mipmap.cheeseset)
//    val bulgogiSet = product("불고기와퍼세트","7000",R.mipmap.bulgogiset)
//    val shrimpSet = product("새우와퍼세트","7500",R.mipmap.shrimpset)
//    val whopper = product("와퍼","4500",R.mipmap.whopper)
//    val cheeseWhopper = product("치즈와퍼","5000",R.mipmap.cheesewhopper)
//    val bulgogiWhopper = product("불고기와퍼","5000",R.mipmap.bulgogiwhopper)
//    val shrimpWhopper = product("새우와퍼","5500",R.mipmap.shrimpwhopper)
//    val coke = product("콜라","1000",R.mipmap.coke)
//    val cider = product("사이다","1000",R.mipmap.cider)
//    val fanta = product("환타","1000",R.mipmap.fanta)
//    val frenchFri = product("감자튀김","2000",R.mipmap.frenchfri)
//    val onionRing = product("어니언링","3000",R.mipmap.onionring)
//    val cheeseStick = product("치즈스틱","2000",R.mipmap.cheesestick) //    val comboArray = arrayListOf<product>(whopperSet, cheeseSet, bulgogiSet,shrimpSet)
//    val burgerArray = arrayListOf<product>(whopper, cheeseWhopper, bulgogiWhopper,shrimpWhopper)
//    val drinkArray = arrayListOf<product>(coke, cider, fanta)
//    val sideArray = arrayListOf<product>(frenchFri, onionRing, cheeseStick)

}


//    var comboList = ArrayList<product>()
//    var burgerList = ArrayList<product>()
//    var drinkList = ArrayList<product>()
//    var sideList = ArrayList<product>()

//    fun init(){
//        comboList.add(whopperSet)
//        comboList.add(cheeseSet)
//        comboList.add(bulgogiSet)
//        comboList.add(shrimpSet)
//        burgerList.add(whopper)
//        burgerList.add(cheeseWhopper)
//        burgerList.add(bulgogiWhopper)
//        burgerList.add(shrimpWhopper)
//        drinkList.add(coke)
//        drinkList.add(cider)
//        drinkList.add(fanta)
//        sideList.add(frenchFri)
//        sideList.add(onionRing)
//        sideList.add(cheeseStick)
//    }

//    var data = "{"+
//        "'combo_list':["+
//            "{'name' : '와퍼세트','price' : '6500','image' : R.mipmap.cheeseset},"+
//        "{'name' : '치즈와퍼세트','price' : '7000','image' : R.mipmap.cheeseset},"+
//        "{'name' : '불고기와퍼세트','price' : '7000','image' : R.mipmap.cheeseset},"+
//        "{'name' : '새우와퍼세트','price' : '7500','image' : R.mipmap.cheeseset}],"+
//        "'burger_list':[{'name' : '와퍼','price' : '4500','image' : R.mipmap.cheeseset},"+
//        "{'name' : '치즈와퍼','price' : '5000','image : R.mipmap.cheeseset},"+
//        "{'name' : '불고기와퍼','price' : '5000','image' : R.mipmap.cheeseset},"+
//        "{'name' : '새우와퍼','price' : '5500','image' : R.mipmap.cheeseset}],"+
//        "'drink_list':[{'name' : '콜라','price' : '1000','image' : R.mipmap.cheeseset},"+
//        "{'name' : '사이다','price' : '1000','image' : R.mipmap.cheeseset},"+
//        "{'name' : '환타','price' : '1000','image' : R.mipmap.cheeseset}],"+
//        "'side_list':[{'name' : '감자튀김','price' : '2000','image' : R.mipmap.cheeseset},"+
//        "{'name' : '어니언링','price' : '3000','image' : R.mipmap.cheeseset},"+
//        "{'name' : '치즈스틱','price' : '2000','image' : R.mipmap.cheeseset}]"+ "}"
//}