package com.auribises.enc2019a.ui;

import android.content.ContentResolver;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.auribises.enc2019a.R;
import com.auribises.enc2019a.adapter.CustomersAdapter;
import com.auribises.enc2019a.model.Customer;
import com.auribises.enc2019a.model.Util;

import java.util.ArrayList;

public class AllCustomersActivity extends AppCompatActivity {

    ContentResolver resolver;
    RecyclerView recyclerView;

    ArrayList<Customer> customers;

    CustomersAdapter customersAdapter;

    void initViews(){
        resolver = getContentResolver();
        recyclerView = findViewById(R.id.recyclerView);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_customers);
        initViews();
        fetchCustomersFromDB();
    }

    void fetchCustomersFromDB(){

        String[] projection = {Util.COL_ID, Util.COL_NAME, Util.COL_PHONE, Util.COL_EMAIL};
        Cursor cursor = resolver.query(Util.CUSTOMER_URI, projection, null, null, null);

        if(cursor!=null){

            customers = new ArrayList<>();

            while(cursor.moveToNext()){

                Customer customer = new Customer();
                customer.id = cursor.getInt(cursor.getColumnIndex(Util.COL_ID));
                customer.name = cursor.getString(cursor.getColumnIndex(Util.COL_NAME));
                customer.phone = cursor.getString(cursor.getColumnIndex(Util.COL_PHONE));
                customer.email = cursor.getString(cursor.getColumnIndex(Util.COL_EMAIL));

                customers.add(customer);
            }

            getSupportActionBar().setTitle("Total Customers: "+customers.size());

            customersAdapter = new CustomersAdapter(this,R.layout.list_item,customers);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(customersAdapter);
        }

    }
}
