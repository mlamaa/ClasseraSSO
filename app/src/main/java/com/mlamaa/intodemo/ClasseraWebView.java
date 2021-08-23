package com.mlamaa.intodemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ClasseraWebView extends AppCompatActivity {


    String ClasseraURL="https://me.classera.com/users/login_webview" +
        "";
String QuesryStringDelimiter=":";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        WebView myWebView = (WebView) findViewById(R.id.webview);

        WebViewClient ClasseraWebClient = new WebViewClient(){
            // Step three: monitor the redirection
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url=request.getUrl().toString();
                if( url.startsWith(ClasseraURL)  && !url.equals(ClasseraURL) ){
                    try {
                        // Step four: parse URL to get data from splitQuery
                        Map<String, String> resultMap=splitQuery( url);
                        // Step five: append data to intent and set it as result
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra(MainActivity.USERNAME,resultMap.get(MainActivity.USERNAME));
                        returnIntent.putExtra(MainActivity.TOKEN,resultMap.get(MainActivity.TOKEN));
                        returnIntent.putExtra(MainActivity.USER_ID,resultMap.get(MainActivity.USER_ID));
                        // Step six: set intent result code as OK
                        setResult(Activity.RESULT_OK,returnIntent);
                    }
                    catch (Exception ex){
                        // Step Seven: set intent result code as cancelled if any exception happend
                        setResult(Activity.RESULT_CANCELED);
                    }
                    // Step eight: finish the webview
                    finish();
                }
                return super.shouldOverrideUrlLoading(view, request);
            }
        };

        myWebView.setWebViewClient(ClasseraWebClient);
        // Step tow: open the web view with classera URL
        myWebView.loadUrl(ClasseraURL);
    }

    public Map<String, String> splitQuery(String url) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new LinkedHashMap<String, String>();

        String[] pairs = url.split("/");
        for (String pair : pairs) {
            if (pair.contains(QuesryStringDelimiter) == false)
                continue;
            int idx = pair.indexOf(QuesryStringDelimiter);
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return query_pairs;
    }
}