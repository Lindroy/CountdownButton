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
        btnSubmit.setOnClickListener {
            btnSubmit.start()
        }
        btnSubmit.setFinishListener {
            Toast.makeText(this, "倒计时结束", Toast.LENGTH_SHORT).show()
        }
        btnSubmit.apply {
            setOnClickListener {
                btnSubmit.start()
            }
            setOnTickListener {
                Log.e(TAG,"倒计时：$it")
            }
            setFinishListener {
                Toast.makeText(this@MainActivity, "倒计时结束", Toast.LENGTH_SHORT).show()
            }
        }
        btnStart.setOnClickListener { btnSubmit.start() }
        btnStop.setOnClickListener { btnSubmit.stop() }
    }
}
