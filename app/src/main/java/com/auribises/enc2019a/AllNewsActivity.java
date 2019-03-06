package com.auribises.enc2019a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class AllNewsActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<News> newsList;
    NewsAdapter newsAdapter;

    void initViews(){
        listView = findViewById(R.id.listView);

        newsList = new ArrayList<>();


        News news1 = new News(R.drawable.category,"NDTV","https://ndtv.india.com");
        News news2 = new News(R.drawable.contact,"CNN","https://cnn.india.com");
        News news3 = new News(R.drawable.music,"AAJ TAK","https://aaj.india.com");
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

        newsAdapter = new NewsAdapter(this, R.layout.list_item, newsList);
        listView.setAdapter(newsAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_news);
        initViews();
    }
}
