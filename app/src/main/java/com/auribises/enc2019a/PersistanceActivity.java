package com.auribises.enc2019a;

import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class PersistanceActivity extends AppCompatActivity {

    EditText eTxtData;
    Button btnSubmit;

    String data;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    void initViews(){
        eTxtData = findViewById(R.id.editTextData);
        btnSubmit = findViewById(R.id.buttonSubmit);


        sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //data = eTxtData.getText().toString();
                //saveDataInInternalFile();

                //readDataFromInternalFile();

                //saveDataInExternalFile();

                //editor.putString("keyQuote",data);
                //sharedPreferences.edit().apply(); // Save the Data in XML File | Background Thread
                //editor.commit();  // Save the Data in XML File

                //Toast.makeText(PersistanceActivity.this,"Data Saved in SharedPrefs",Toast.LENGTH_LONG).show();
                //eTxtData.setText("");
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persistance);

        initViews();
        //readDataFromInternalFile();
        //readDataFromExternalFile();

        data = sharedPreferences.getString("keyQuote","NA");
        eTxtData.setText(data);
    }

    void saveDataInInternalFile(){
        try{

            FileOutputStream outputStream = openFileOutput("data.txt",MODE_PRIVATE);
            outputStream.write(data.getBytes());
            outputStream.close();

            Toast.makeText(this,"Data Saved in Internal File",Toast.LENGTH_LONG).show();
            eTxtData.setText("");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void readDataFromInternalFile(){
        try{

            FileInputStream inputStream = openFileInput("data.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            data = reader.readLine();
            eTxtData.setText(data);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void saveDataInExternalFile(){
        try{
            String path = Environment.getExternalStorageDirectory().getPath()+"/data.txt";
            FileOutputStream outputStream = new FileOutputStream(path);
            outputStream.write(data.getBytes());
            outputStream.close();

            Toast.makeText(this,"Data Saved in External File",Toast.LENGTH_LONG).show();
            eTxtData.setText("");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void readDataFromExternalFile(){
        try{
            String path = Environment.getExternalStorageDirectory().getPath()+"/data.txt";
            FileInputStream inputStream = new FileInputStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            data = reader.readLine();
            eTxtData.setText(data);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

/*
    From SplashActivity navigate to RegisterActivity (name, phone, password) | Write in SP
    Enter Data in RegisterActivity and save it in SharedPreferences
    Now after save operation is done -> navigate to HomeActivity (Welcome, name) | Read from SP

    Once Registration is done from SplashActivity we should always open HomeActivity


 */