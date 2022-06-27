package com.example.stageus_android_homework_

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.os.Parcelable
import android.widget.Toast
import androidx.core.app.NotificationCompat
import java.io.Serializable

class CartService :Service(),Serializable{
    var cartList = arrayListOf<ProductInCartClass>()
    var priceSum = 0

    fun editCart(index : Int) { // index로 카트 내 데이터 제거
        if ((index < cartList.size) and (-1 < index) ){
            cartList.removeAt(index)
        }
        updateIncartPrice()
    }

    fun addCart(incartZClassProduct : ProductInCartClass) {
        cartList.add(incartZClassProduct)
        updateIncartPrice()
    }

    fun updateIncartPrice() {
        var sumTemp = 0
        for (i in 0 until cartList.size) {
            sumTemp += (cartList[i].productPrice.toInt())
        }
        priceSum = sumTemp
    }
    ///cartPart

    val SC = "myService"
    val iBinder = MyBinder()

    override fun onBind(intent: Intent): IBinder {
        return iBinder
    }

    inner class MyBinder : Binder() {
        fun getService(): CartService = this@CartService
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        notification()
        return super.onStartCommand(intent, flags, startId)
    }

    var noti : Notification? = null
    var notiManager: NotificationManager? = null


    fun notification() {
        if(priceSum != 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val intentMainLanding = Intent(this, MainPageActivity::class.java)
                val pendingIntent = PendingIntent.getActivity(this, 0, intentMainLanding, 0)

                if (notiManager == null) {
                    notiManager =
                        this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                }
                val builder = NotificationCompat.Builder(this, "service_channel")

                builder.setContentTitle(
                    StringBuilder(resources.getString(R.string.app_name)).append(
                        " 총 금액은 : ${priceSum}"
                    ).toString()
                )
                    .setTicker(
                        StringBuilder(resources.getString(R.string.app_name)).append("service is running")
                            .toString()
                    )
                    .setContentText("Touch to open") //
                    .setSmallIcon(R.mipmap.burgerkinglogo)
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .setWhen(0)
                    .setOnlyAlertOnce(true)
                    .setContentIntent(pendingIntent) //누를시 이동할 액티비티
                    .setOngoing(true)

                noti = builder.build()
                startForeground(1, noti)
            }
        }
    }

    fun delNotification(){
        notiManager?.cancel(1)
        notiManager?.cancelAll()
        stopForeground(true)
    }

    fun cartClear(){
        cartList = arrayListOf<ProductInCartClass>()
        updateIncartPrice()
    }

}
