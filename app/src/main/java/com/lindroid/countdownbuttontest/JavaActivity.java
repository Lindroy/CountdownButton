package com.lindroid.countdownbuttontest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lindroid.countdownbutton.CountdownButton;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

public class JavaActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);
        final CountdownButton btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSubmit.start();
            }
        });
        btnSubmit.setOnFinishedListener(new Function0<Unit>() {
            @Override
            public Unit invoke() {
                return null;
            }
        });
        btnSubmit.setOnTickListener(new Function1<Integer, Unit>() {
            @Override
            public Unit invoke(Integer interval) {

                return null;
            }
        });


        btnSubmit.setOnCountdownListener(new CountdownButton.OnCountdownListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onTick(int interval) {

            }

            @Override
            public void onFinished() {

            }
        });

        btnSubmit.setOnCountdownListener(new CountdownButton.SimpleOnCountdownListener() {
            @Override
            public void onFinished() {
                super.onFinished();
            }
        });
    }
}
