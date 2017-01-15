package com.sajorahasan.dailynews;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sajorahasan.dailynews.model.NewsStore;

public class NewsDetailsActivity extends AppCompatActivity {

    private static final String KEY_INDEX = "news_index";
    private WebView webView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Initializing Views
        webView = (WebView) findViewById(R.id.news_details_webView);
        progressBar = (ProgressBar) findViewById(R.id.news_details_progressBar);

        //Receiving index
        int received_index = getIntent().getIntExtra(KEY_INDEX, -1);
        if (received_index != -1) {
            updateNewsDetails(received_index);
        } else {
            Toast.makeText(this, "Sorry incorrect index passed", Toast.LENGTH_SHORT).show();
        }

    }

    public void updateNewsDetails(int index) {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(NewsDetailsActivity.this, "Error in loading webpage", Toast.LENGTH_SHORT).show();
            }
        });

        webView.loadUrl(NewsStore.getArticles().get(index).getUrl());
        getSupportActionBar().setTitle(NewsStore.getArticles().get(index).getTitle());
    }

    public static void launch(Context context, int index) {
        Intent intent = new Intent(context, NewsDetailsActivity.class);
        intent.putExtra(KEY_INDEX, index);
        context.startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
