package com.alliswell.payment;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alliwell.keyboard.Keyboard;
import com.alliwell.keyboard.callback.IResultCallback;


public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    Keyboard keyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        keyboard = findViewById(R.id.keyboard);
        final TextView textView = findViewById(R.id.textView);

        keyboard.setIResultCallback(new IResultCallback() {
            @Override
            public void result(String res) {
                textView.setText(res);
            }

            @Override
            public void clickOk() {
                Toast.makeText(MainActivity.this, textView.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}