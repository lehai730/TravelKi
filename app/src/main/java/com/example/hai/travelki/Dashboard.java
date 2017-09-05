package com.example.hai.travelki;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {
    TextView userNameDisp;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        if (getIntent().hasExtra("from login")) {
            userNameDisp = (TextView) findViewById(R.id.username_d);
            userName = getIntent().getExtras().getString("from login");
            userNameDisp.setText(userName);
        }
    }
}