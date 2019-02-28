package com.auribises.enc2019a;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityOne extends AppCompatActivity implements View.OnClickListener{

    // Reference Variables for the Views
    EditText eTxtName, eTxtPhone;
    Button btnSubmit;

    void initViews(){
        eTxtName = findViewById(R.id.editTextName); // findViewById will create Object for us
        eTxtPhone = findViewById(R.id.editTextPhone);

        btnSubmit = findViewById(R.id.buttonSubmit);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        initViews();
    }

    @Override
    public void onClick(View v) {

        String name = eTxtName.getText().toString();
        String phone = eTxtPhone.getText().toString();

        Intent intent = new Intent(ActivityOne.this, ActivityTwo.class);
        // Sending Data from ActivityOne to ActivityTwo using Intent
        intent.putExtra("keyName",name);
        intent.putExtra("keyPhone",phone);
        startActivity(intent);
    }
}
