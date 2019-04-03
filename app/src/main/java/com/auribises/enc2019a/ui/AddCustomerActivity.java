package com.auribises.enc2019a.ui;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.auribises.enc2019a.R;
import com.auribises.enc2019a.model.Customer;
import com.auribises.enc2019a.model.Util;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddCustomerActivity extends AppCompatActivity {

    EditText eTxtName, eTxtPhone, eTxtEmail;
    Button btnSubmit;

    Customer customer;

    ContentResolver resolver;

    boolean updateMode;

    FirebaseUser firebaseUser;
    FirebaseAuth auth;
    FirebaseFirestore db;


    void initViews(){

        resolver = getContentResolver();

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        firebaseUser = auth.getCurrentUser();

        eTxtName = findViewById(R.id.editTextName);
        eTxtPhone = findViewById(R.id.editTextPhone);
        eTxtEmail = findViewById(R.id.editTextEmail);
        btnSubmit = findViewById(R.id.buttonAdd);

        customer = new Customer();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customer.name = eTxtName.getText().toString();
                customer.phone = eTxtPhone.getText().toString();
                customer.email = eTxtEmail.getText().toString();

                //addCustomerInDB();
                if(Util.isInternetConnected(AddCustomerActivity.this)) {
                    saveCustomerInCloudDB();
                }else{
                    // give message
                }

            }
        });


        Intent rcv = getIntent();
        updateMode = rcv.hasExtra("keyCustomer");

        if(updateMode){
            customer = (Customer) rcv.getSerializableExtra("keyCustomer");
            eTxtName.setText(customer.name);
            eTxtPhone.setText(customer.phone);
            eTxtEmail.setText(customer.email);
            btnSubmit.setText("Update Customer");
        }

    }

    void addCustomerInDB(){

        ContentValues values = new ContentValues();
        values.put(Util.COL_NAME,customer.name);
        values.put(Util.COL_PHONE,customer.phone);
        values.put(Util.COL_EMAIL,customer.email);

        if(updateMode){

            String where = Util.COL_ID+" = "+customer.id;
            int i = resolver.update(Util.CUSTOMER_URI,values,where,null);
            if(i>0){
                Toast.makeText(this,"Updation Finished",Toast.LENGTH_LONG).show();
                finish();
            }else{
                Toast.makeText(this,"Updation Failed",Toast.LENGTH_LONG).show();
            }

        }else{
            Uri uri = resolver.insert(Util.CUSTOMER_URI, values);
            Toast.makeText(this,customer.name+" Added in Database: "+uri.getLastPathSegment(),Toast.LENGTH_LONG).show();

            clearFields();
        }
    }

    void saveCustomerInCloudDB(){

        db.collection("users").document(firebaseUser.getUid())
                .collection("customers").add(customer)
                .addOnCompleteListener(this, new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if(task.isComplete()){
                            Toast.makeText(AddCustomerActivity.this,"Customer Saved in DB",Toast.LENGTH_LONG).show();
                            clearFields();
                        }
                    }
                });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        initViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1,101,1,"All Customers");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == 101){
            Intent intent = new Intent(AddCustomerActivity.this, AllCustomersActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    void clearFields(){
        eTxtName.setText("");
        eTxtPhone.setText("");
        eTxtEmail.setText("");

    }
}
