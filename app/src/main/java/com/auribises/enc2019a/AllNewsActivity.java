package com.auribises.enc2019a;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

public class AllNewsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    //ListView listView;
    //GridView listView;

    RecyclerView recyclerView;

    ArrayList<News> newsList;
    NewsAdapter newsAdapter;
    NewsRecyclerAdapter recyclerAdapter;

    void initViews(){
        //listView = findViewById(R.id.listView);
        recyclerView = findViewById(R.id.recyclerView);

        newsList = new ArrayList<>();


        News news1 = new News(R.drawable.category,"NDTV","https://www.ndtv.com/");
        News news2 = new News(R.drawable.contact,"CNN","https://edition.cnn.com/");
        News news3 = new News(R.drawable.music,"AAJ TAK","https://aajtak.intoday.in/");
        News news4 = new News(R.drawable.folder,"ZEE NEWS","https://zee.india.com");
        News news5 = new News(R.drawable.music,"PTC","https://ptc.india.com");
        News news6 = new News(R.drawable.contact,"CNN","https://cnn.india.com");
        News news7 = new News(R.drawable.pb,"AAJ TAK","https://aaj.india.com");

        newsList.add(news1); //0
        newsList.add(news2);
        newsList.add(news3);
        newsList.add(news4);
        newsList.add(news5); //4
        newsList.add(news6);
        newsList.add(news7);

        //newsList.add(new News(R.drawable.category,"NDTV","https://ndtv.india.com"));

        //newsAdapter = new NewsAdapter(this, R.layout.list_item, newsList);
        //listView.setAdapter(newsAdapter);

        //listView.setOnItemClickListener(this);

        recyclerAdapter = new NewsRecyclerAdapter(this, R.layout.list_item, newsList);

        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //recyclerView.setLayoutManager(linearLayoutManager);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);

        //StaggeredGridLayoutManager | Explore

        recyclerView.setAdapter(recyclerAdapter);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_news);
        initViews();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        News news = newsList.get(position);
        Intent intent = new Intent(AllNewsActivity.this, WebViewActivity.class);
        intent.putExtra("keyNews",news);
        startActivity(intent);
    }
}
