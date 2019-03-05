package com.auribises.enc2019a;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView listView;
    ArrayAdapter<String> adapter;

    void initViews(){

        getSupportActionBar().setTitle("NEWS");

        listView = findViewById(R.id.listView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        adapter.add("AAJ TAK"); // 0
        adapter.add("NDTV");    // 1
        adapter.add("CNN IBN"); // 2
        adapter.add("PTC");     // 3
        adapter.add("ZEE NEWS");// 4
        adapter.add("STAR NEWS");//5

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        initViews();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String newsName = adapter.getItem(position);
        Toast.makeText(this,"You Selected "+newsName,Toast.LENGTH_LONG).show();

        Intent intent = new Intent(ListViewActivity.this, WebViewActivity.class);
        startActivity(intent);
    }
}
