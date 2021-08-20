package com.mlamaa.intodemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    final int WEBVIEW_RESULT_ID = 15711;
    static final String TOKEN ="token" ;
    static final String USER_ID="user_id" ;
    static final String USERNAME="username" ;


    TextView tv_token  ;
    TextView tv_user_id ;
    TextView tv_username ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button fab = findViewById(R.id.button);
        tv_token   = findViewById(R.id.token);
        tv_user_id  = findViewById(R.id.user_id);
        tv_username  = findViewById(R.id.username);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Step one: open web view as ActivityForResult
                Intent i = new Intent(MainActivity.this, ClasseraWebView.class);
                startActivityForResult(i, WEBVIEW_RESULT_ID);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
// Step nine: if requestCode os correct and resultCode is ok, we should get the data from intent "data"
        if (requestCode == WEBVIEW_RESULT_ID) {
            if(resultCode == Activity.RESULT_OK){
                String token=data.getStringExtra(TOKEN);
                String user_id=data.getStringExtra(USER_ID);
                String username=data.getStringExtra(USERNAME);
                tv_token.setText("Token: "+token);
                tv_user_id.setText("user_id: "+user_id);
                tv_username.setText("username: "+username);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                tv_token.setText("Invalid credentials");
            }
        }
    } //onActivityResult

}