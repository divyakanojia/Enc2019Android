package com.auribises.enc2019a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {


    WebView webView;

    void initViews(){

        //getSupportActionBar().setTitle("Amazon");
        getSupportActionBar().hide();

        webView = findViewById(R.id.webView);

        //WebViewClient client = new WebViewClient();
        //webView.setWebViewClient(client);
        webView.setWebViewClient(new WebViewClient());

        webView.getSettings().setJavaScriptEnabled(true);

        //webView.loadUrl("https://www.google.co.in");
        //webView.loadUrl("https://www.amazon.in/");
        webView.loadUrl("https://www.ndtv.com/");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        initViews();
    }
}
