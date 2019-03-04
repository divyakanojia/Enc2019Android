package com.auribises.enc2019a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// Write a Calculator App !!

public class LayoutsActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn1, btn2, btn3, btn4, btn5, btn6;

    void initViews(){
        btn1 = findViewById(R.id.button8);
        btn2 = findViewById(R.id.button9);
        btn3 = findViewById(R.id.button10);
        btn4 = findViewById(R.id.button12);
        btn5 = findViewById(R.id.button13);
        btn6 = findViewById(R.id.button14);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        initViews();
    }

    @Override
    public void onClick(View v) {


        /*
        if(v == btn1){

        }else if(v == btn2){

        }else if(v == btn3){

        }else{

        }
        */

        int id = v.getId();

        if(id == R.id.button8){

        }else if(id == R.id.button9){

        }else{

        }
    }
}
