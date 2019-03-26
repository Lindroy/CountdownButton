package com.lindroid.countdownbuttontest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.lindroid.countdownbutton.CountdownButton
import kotlinx.android.synthetic.main.activity_main.*

private val TAG = "CountDownTag"
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /* btnSubmit.apply {
             setOnClickListener {
                 btnSubmit.start()
             }
             setOnStartListener {
                 Log.d(TAG, "倒计时开始")
             }
             setonCancelListener {
                 Log.d(TAG, "倒计时取消")
             }
             setOnTickListener {
                 Log.d(TAG, "倒计时：$it")
             }
             setOnFinishedListener {
                 Log.d(TAG, "倒计时结束")
                 Toast.makeText(this@MainActivity, "倒计时结束", Toast.LENGTH_SHORT).show()
             }
         }*/
        btnSubmit.setOnCountdownListener(object : CountdownButton.SimpleOnCountdownListener() {
            override fun onStart() {
                super.onStart()
                Log.d(TAG, "倒计时开始")
            }

            override fun onCancel() {
                super.onCancel()
                Log.d(TAG, "倒计时取消")
            }

            override fun onTick(interval: Int) {
                super.onTick(interval)
                Log.d(TAG, "倒计时：$interval")
            }

            override fun onFinished() {
                super.onFinished()
                Log.d(TAG, "倒计时结束")
            }
        })
        btnStart.setOnClickListener { btnSubmit.start() }
        btnStop.setOnClickListener { btnSubmit.cancel() }
    }
}
