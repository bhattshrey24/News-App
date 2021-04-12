package com.example.newsapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
public class WebViewsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_views);
        Intent intent=getIntent();
setTitle("Article Page");
        if(intent.getParcelableExtra("News Selected")!=null){
            News news=intent.getParcelableExtra("News Selected");
            WebView webView = findViewById(R.id.webViewForArticle);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(news.getUrlToArticle());
        }else{
            SavedNews news=intent.getParcelableExtra("SavedNews Selected");
            WebView webView = findViewById(R.id.webViewForArticle);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(news.getArticleUrl());
        }



    }
}