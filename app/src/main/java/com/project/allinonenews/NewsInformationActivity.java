package com.project.allinonenews;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class NewsInformationActivity extends AppCompatActivity {

    private String url;
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_information);

        ActionBar ab = getSupportActionBar();
        ab.hide();
        webView = findViewById(R.id.webView);

        url = getIntent().getStringExtra("url");
        webView.setWebViewClient(new WB_Client());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setVerticalScrollBarEnabled(false);
        webView.loadUrl(url);




    }

    private static class WB_Client extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String urls) {
            view.loadUrl(urls);
            return true;
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {

            super.onReceivedError(view, request, error);
            System.out.println("AllInOneNews ERROR: " + error.toString());
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()){
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
