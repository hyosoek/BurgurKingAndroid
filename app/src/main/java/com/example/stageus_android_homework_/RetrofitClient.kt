package com.example.stageus_android_homework_

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.*
import kotlin.collections.ArrayList

object RetrofitClient { //class로 만들어도 됨
    //기본설정
    var instance : Retrofit? = null
    fun initRetrofit():Retrofit {
        if (instance == null) {
            instance = Retrofit
                .Builder()
                .baseUrl("http://3.39.66.6:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            //IP나 도메인 입력하고, 뒤에는 json 통신을 하겠다고 명시
        }
        return instance!!
    }
}

data class AccountData(
    var message: String,
    var success: Boolean
)
data class AccountLoginData(
    var message: String,
    var success: Boolean
)
data class AccountOverLapData(
    var message: String,
    var success: Boolean
)
data class CategoryData(
    var message: String,
    var success: Boolean,
    var data: List<Any>
)
data class CategoryMenuData(
    var message: String,
    var success: Boolean,
    var data: List<Any>//?
)
data class OrderData(
    var message: String,
    var success: Boolean,
    var data: List<Any>//?
)
data class GetOrderData(
    var message: String,
    var success: Boolean,
    var data: List<Any>//?
)
interface RetrofitService{
    @POST("/account")
    fun postAccount(//보내는 건 하나밖에 못 보냄
        @Body body: HashMap<String, String>//key value로 이루어진 자료형, hashmap의 발전형이 json
    ):Call<AccountData>

    @GET("/account/login")
    fun getAccountLogin(
        @Query("id") id: String,
        @Query("pw") pw: String
    ) : Call<AccountLoginData>//안에 데이터 클래스

    @GET("/account/overlap")
    fun getAccountOverLap(
        @Query("id") id: String
    ) : Call<AccountOverLapData>//안에 데이터 클래스

    @GET("/category")
    fun getCategory(
        @Query("lang") id: String//lang은 "kr" 혹은 "en"을 보낼 것
    ) : Call<CategoryData>//안에 데이터 클래스

    @GET("/category/menu")
    fun getCategoryMenu(
        @Query("category_name") category_name: String,
        @Query("lang") id: String//lang은 "kr" 혹은 "en"을 보낼 것
    ) : Call<CategoryMenuData>//안에 데이터 클래스

    @POST("/order")
    fun postOrder(//보내는 건 하나밖에 못 보냄
        @Body body: HashMap<String, String>//key value로 이루어진 자료형, hashmap의 발전형이 json
    ):Call<OrderData>

    @GET("/order")
    fun getOrder(
        @Query("id") id: String,
    ) : Call<CategoryMenuData>//안에 데이터 클래스


}