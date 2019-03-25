package com.lindroid.countdownbuttontest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

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
    }
}
