package com.auribises.enc2019a;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityTwo extends AppCompatActivity implements View.OnClickListener{

    // Reference Variables for the Views
    EditText eTxtName, eTxtPhone;
    Button btnBack;

    void initViews(){
        eTxtName = findViewById(R.id.editTextName); // findViewById will create Object for us
        eTxtPhone = findViewById(R.id.editTextPhone);

        btnBack = findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(this);

        //Intent rcv = getIntent();
        //String name = rcv.getStringExtra("keyName");
        //String phone = rcv.getStringExtra("keyPhone");

        /*Bundle rcvBun = rcv.getBundleExtra("keyBundle");
        String name = rcvBun.getString("keyName");
        String phone = rcvBun.getString("keyPhone");
        int age = rcvBun.getInt("keyAge",0);*/

        //Person person = (Person)rcv.getSerializableExtra("keyPerson");

        //eTxtName.setText(person.name);
        //eTxtPhone.setText(person.phone);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        initViews();
    }

    @Override
    public void onClick(View v) {

        String name = eTxtName.getText().toString();
        String phone = eTxtPhone.getText().toString();

        Intent data = new Intent(); // Empty Intent just to hold data
        data.putExtra("keyName",name);
        data.putExtra("keyPhone",phone);

        setResult(201,data);

        finish(); // destroy the Activity
    }
}
