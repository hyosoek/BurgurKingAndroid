package com.example.stageus_android_homework_

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.NotificationCompat

class MultiService :Service() {
    val SC = "myService"
    val cart = CartClass() //
    val iBinder = MyBinder()
    var nc: Notification? = null


    override fun onBind(intent: Intent): IBinder {
        return iBinder
    }

    inner class MyBinder : Binder() {
        fun getService(): MultiService = this@MultiService
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Notification()
        nc = NotificationCompat.Builder(this, SC).setContentTitle("버거킹앱 실행했습니다.").setSmallIcon(R.mipmap.ic_launcher_round).build()
        startForeground(1, nc)

        return super.onStartCommand(intent, flags, startId)
    }

    fun Notification() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val nc = NotificationChannel(SC, "My Service Channel", NotificationManager.IMPORTANCE_DEFAULT)
            val nm = getSystemService(NotificationManager::class.java)
            nm.createNotificationChannel(nc)
        }
        else {
            Toast.makeText(this, "알림을 실행할 수 없음", Toast.LENGTH_LONG).show()
        }
    }


    //여기까진 - notification


}
