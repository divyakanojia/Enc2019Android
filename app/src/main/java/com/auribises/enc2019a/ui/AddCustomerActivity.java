package com.auribises.enc2019a.ui;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
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

public class AddCustomerActivity extends AppCompatActivity {

    EditText eTxtName, eTxtPhone, eTxtEmail;
    Button btnSubmit;

    Customer customer;

    ContentResolver resolver;

    void initViews(){

        resolver = getContentResolver();

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

                addCustomerInDB();
            }
        });


    }

    void addCustomerInDB(){

        ContentValues values = new ContentValues();
        values.put(Util.COL_NAME,customer.name);
        values.put(Util.COL_PHONE,customer.phone);
        values.put(Util.COL_EMAIL,customer.email);

        Uri uri = resolver.insert(Util.CUSTOMER_URI, values);
        Toast.makeText(this,customer.name+" Added in Database: "+uri.getLastPathSegment(),Toast.LENGTH_LONG).show();

        clearFields();
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
