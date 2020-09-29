package com.alliswell.payment;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alliwell.keyboard.callback.IResultCallback;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new IResultCallback() {
            @Override
            public void result(String res) {
                Toast.makeText(MainActivity.this, res, Toast.LENGTH_SHORT).show();
            }
        };
    }
}