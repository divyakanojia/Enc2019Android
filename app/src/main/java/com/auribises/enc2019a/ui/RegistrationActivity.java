package com.auribises.enc2019a.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.auribises.enc2019a.HomeActivity;
import com.auribises.enc2019a.R;
import com.auribises.enc2019a.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener{

    EditText eTxtName, eTxtEmail, eTxtPassword;
    Button btnRegister;

    User user;

    // To Access FirebaseAuth WebService
    FirebaseAuth auth;

    ProgressDialog progressDialog;


    void initViews(){
        eTxtName = findViewById(R.id.editTextName);
        eTxtEmail = findViewById(R.id.editTextEmail);
        eTxtPassword = findViewById(R.id.editTextPassword);
        btnRegister = findViewById(R.id.buttonRegister);
        btnRegister.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");
        progressDialog.setCancelable(false);

        user = new User();

        auth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initViews();
    }

    @Override
    public void onClick(View v) {

        //Get the data from UI and put it into User Object
        user.name = eTxtName.getText().toString();
        user.email = eTxtEmail.getText().toString();
        user.password = eTxtPassword.getText().toString();

        registerUser();
    }

    void registerUser(){

        progressDialog.show();

        auth.createUserWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isComplete()){
                            Toast.makeText(RegistrationActivity.this,user.name+ "Registered Sucessfully", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();

                            Intent intent = new Intent(RegistrationActivity.this, HomeActivity.class);
                            startActivity(intent);

                        }
                    }
                });

    }


}
