package com.lindroid.countdownbuttontest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

private val TAG = "CountDownTag"
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSubmit.apply {
            setOnClickListener {
                btnSubmit.start()
            }
            setOnStartListener {
                Log.d(TAG, "倒计时开始")
            }
            setOnStopListener {
                Log.d(TAG, "倒计时取消")
            }
            setOnTickListener {
                Log.d(TAG, "倒计时：$it")
            }
            setOnFinishedListener {
                Log.d(TAG, "倒计时结束")
                Toast.makeText(this@MainActivity, "倒计时结束", Toast.LENGTH_SHORT).show()
            }
        }
        btnStart.setOnClickListener { btnSubmit.start() }
        btnStop.setOnClickListener { btnSubmit.stop() }
    }
}
