package com.auribises.enc2019a.ui;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.auribises.enc2019a.R;
import com.auribises.enc2019a.adapter.CustomersAdapter;
import com.auribises.enc2019a.listener.OnRecyclerItemClickListener;
import com.auribises.enc2019a.model.Customer;
import com.auribises.enc2019a.model.Util;

import java.util.ArrayList;

public class AllCustomersActivity extends AppCompatActivity implements OnRecyclerItemClickListener{

    ContentResolver resolver;
    RecyclerView recyclerView;

    ArrayList<Customer> customers;

    CustomersAdapter customersAdapter;

    int position;
    Customer customer;

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

            customersAdapter.setOnRecyclerItemClickListener(this);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(customersAdapter);
        }

    }

    void showCustomerDetails(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(customer.name+" Details:");
        builder.setMessage(customer.toString());
        builder.setPositiveButton("Done",null);
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    void deleteCustomerFromDB(){
        String where = Util.COL_ID+" = "+customer.id;
        int i = resolver.delete(Util.CUSTOMER_URI,where,null);
        if(i>0){
            Toast.makeText(this,"Deletion Finished",Toast.LENGTH_LONG).show();
            customers.remove(position);
            customersAdapter.notifyDataSetChanged(); // Refresh Your RecyclerView
        }else{
            Toast.makeText(this,"Deletion Failed",Toast.LENGTH_LONG).show();
        }
    }

    void askForDeletion(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete "+customer.name);
        builder.setMessage("Are You Sure ?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteCustomerFromDB();
            }
        });
        builder.setNegativeButton("Cancel",null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    void showOptions(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String[] items = {"View "+customer.name, "Update "+customer.name, "Delete "+customer.name, "Cancel"};
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which){
                    case 0:
                        showCustomerDetails();
                        break;

                    case 1:

                        Intent intent = new Intent(AllCustomersActivity.this, AddCustomerActivity.class);
                        intent.putExtra("keyCustomer",customer);
                        startActivity(intent);

                        break;

                    case 2:
                        askForDeletion();
                        break;

                }

            }
        });

        //builder.setCancelable(false);

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    @Override
    public void onItemClick(int position) {
        this.position = position;
        customer = customers.get(position);
        //Toast.makeText(this,"You Clicked on Position:"+position,Toast.LENGTH_LONG).show();
        showOptions();
    }
}
