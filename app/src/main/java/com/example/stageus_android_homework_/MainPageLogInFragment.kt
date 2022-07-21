package com.example.stageus_android_homework_

import android.app.AlertDialog
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

//메인 액티비티에 들어가는 프래그먼트
class MainPageLogInFragment:Fragment() {
    lateinit var retrofit: Retrofit //connection
    lateinit var retrofitHttp: RetrofitService //cursor 역할
    fun initRetrofit(){
        retrofit = RetrofitClient.initRetrofit() //class면 client 뒤에 ()
        retrofitHttp =  retrofit!!.create(RetrofitService::class.java)
    }
    lateinit var myService: CartService
    var isService = false
    var connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName?, service: IBinder?) {
            val binder = service as CartService.MyBinder
            myService = binder.getService()
            isService = true
        }
        override fun onServiceDisconnected(className: ComponentName?) {
            isService = false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.main_log_in_fragment, container, false)
        Intent(context, CartService::class.java).also { intent ->
            activity?.bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
        initEvent(view)
        initRetrofit()
        return view
    }

    fun initEvent(view: View){
        val backBtn = view.findViewById<Button>(R.id.backBtn)
        backBtn.setOnClickListener {
            val changeInterface = context as MainInterface
            changeInterface.changeFragment(1,null)
        }
        val logInBtn = view.findViewById<Button>(R.id.signInBtn)
        logInBtn.setOnClickListener {
            logInTry(view)
        }
        val signUpBtn = view.findViewById<Button>(R.id.signUpBtn)
        signUpBtn.setOnClickListener{
            signUpTry(view)
        }

    }
    fun logInTry(view: View){
        val idInput = view.findViewById<EditText>(R.id.idEditText).text.toString()
        val pwInput = view.findViewById<EditText>(R.id.pwEditText).text.toString()
        retrofitHttp.getAccountLogin(idInput,pwInput)//제이슨으로 받아오기에 담을 객체 바로 생성 가능
            .enqueue(object: Callback<AccountLoginData>{//enqueue가 비동기함수 + 비동기처리 됨 자동으로
            override fun onFailure(call: Call<AccountLoginData>, t: Throwable) {
                Log.d("result","Request Fail ${t}")//실패사유
            }
                override fun onResponse(call: Call<AccountLoginData>, response: Response<AccountLoginData>) {
                    Log.d("result","Request Success")//실패사유
                    if(response.body()!!.success) {
                        Log.d("result","Request Real Success")
                        logInSuccess(view)
                    }
                    else{
                        Log.d("result","Request Fail : ${response.body()!!.message}")//실패사유
                        logInFail(view)
                    }
                }

            })
    }
    fun logInFail(view: View){
        val dialogtemp = AlertDialog.Builder(context)
        val dialog = dialogtemp.create()
        val dialogView = layoutInflater.inflate(R.layout.main_emptycart_alert_dialog,null)
        val cancelBtn = dialogView.findViewById<Button>(R.id.cancelBtn)
        val warnText = dialogView.findViewById<TextView>(R.id.warnText)
        warnText.text = "로그인 실패"
        dialog.setView(dialogView)
        cancelBtn.setOnClickListener{
            dialog.dismiss()
        }
        dialog.show()
    }
    fun logInSuccess(view: View){
        myService.userId = view.findViewById<EditText>(R.id.idEditText).text.toString()
        val changeInterface = context as MainInterface
        changeInterface.changeFragment(1,null)
    }


    fun signUpTry(view:View){
        val idInput = view.findViewById<EditText>(R.id.idEditText).text.toString()
        retrofitHttp.getAccountOverLap(idInput)//제이슨으로 받아오기에 담을 객체 바로 생성 가능
            .enqueue(object: Callback<AccountOverLapData>{//enqueue가 비동기함수 + 비동기처리 됨 자동으로
            override fun onFailure(call: Call<AccountOverLapData>, t: Throwable) {
                Log.d("result","Request Fail ${t}")//실패사유
            }
                override fun onResponse(call: Call<AccountOverLapData>, response: Response<AccountOverLapData>) {
                    Log.d("result","Request Success")
                    if(response.body()!!.success) {
                        Log.d("result","Request Real Success")
                        signUpDialog(view)
                    }
                    else{
                        Log.d("result","Request Fail : ${response.body()!!.message}")//실패사유
                        signUpFailDialog(view)
                    }
                }

            })
    }
    fun signUpDialog(view:View){
        val dialogtemp = AlertDialog.Builder(context)
        val dialog = dialogtemp.create()
        val dialogView = layoutInflater.inflate(R.layout.main_cart_inout_dialog,null)
        val cancelBtn = dialogView.findViewById<Button>(R.id.cancelBtn)
        val makeBtn = dialogView.findViewById<Button>(R.id.cartInOutBtn)
        val warnText = dialogView.findViewById<TextView>(R.id.warnText)
        warnText.text = "사용가능합니다. 생성하시겠습니까?"
        makeBtn.text = "생성"
        dialog.setView(dialogView)
        cancelBtn.setOnClickListener{
            dialog.dismiss()
        }
        makeBtn.setOnClickListener{
            signUpSequence(view)
            dialog.dismiss()
        }
        dialog.show()
    }
    fun signUpFailDialog(view:View){
        val dialogtemp = AlertDialog.Builder(context)
        val dialog = dialogtemp.create()
        val dialogView = layoutInflater.inflate(R.layout.main_emptycart_alert_dialog,null)
        val cancelBtn = dialogView.findViewById<Button>(R.id.cancelBtn)
        val warnText = dialogView.findViewById<TextView>(R.id.warnText)
        warnText.text = "이미 있거나, 불가능한 아이디입니다."
        dialog.setView(dialogView)
        cancelBtn.setOnClickListener{
            dialog.dismiss()
        }
        dialog.show()
    }
    fun signUpSequence(view:View)
    {
        var requestData: HashMap<String,String> = HashMap() //자료형 고정
        requestData["id"] = view.findViewById<EditText>(R.id.idEditText).text.toString()
        requestData["pw"] = view.findViewById<EditText>(R.id.pwEditText).text.toString()
        requestData["name"] = view.findViewById<EditText>(R.id.nameEditText).text.toString()
        requestData["contact"] = view.findViewById<EditText>(R.id.contactEditText).text.toString()

        retrofitHttp.postAccount(requestData)//제이슨으로 받아오기에 담을 객체 바로 생성 가능
            .enqueue(object: Callback<AccountData> {//enqueue가 비동기함수 + 비동기처리 됨 자동으로
            override fun onFailure(call: Call<AccountData>, t: Throwable) {
                Log.d("result","Request Fail ${t}")//실패사유
            }override fun onResponse(call: Call<AccountData>, response: Response<AccountData>) {
                if(response.body()!!.success) {
                    Log.d("result","Request Success")//실패사유
                    view.findViewById<EditText>(R.id.idEditText).getText().clear()
                    view.findViewById<EditText>(R.id.pwEditText).getText().clear()
                    view.findViewById<EditText>(R.id.nameEditText).getText().clear()
                    view.findViewById<EditText>(R.id.contactEditText).getText().clear()
                }
                else{
                    Log.d("result","Request Fail : ${response.body()!!.success}")//실패사유
                }
            }
            })

    }


}



