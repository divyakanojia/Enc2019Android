package com.auribises.enc2019a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LocaleDemoActivity extends AppCompatActivity {

    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locale_demo);

        txt = findViewById(R.id.textView6);
        // Dynamically how we can set the String
        txt.setText(getResources().getString(R.string.titleRegister));

    }
}
