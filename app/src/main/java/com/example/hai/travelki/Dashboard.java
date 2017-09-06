package com.example.hai.travelki;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {
    public static final int REQUEST_TOPUP = 2;
    TextView userNameDisp;
    EditText userBalance;
    String userName;
    int testBalance = 100; //initial balance amount
    Button TopUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        userBalance = (EditText) findViewById(R.id.user_bal);
        TopUp = (Button) findViewById(R.id.TopUpBtn);
        userBalance.setText(String.valueOf(testBalance));
        if (getIntent().hasExtra("from login")) {
            userNameDisp = (TextView) findViewById(R.id.username_d);
            userName = getIntent().getExtras().getString("from login");
            userNameDisp.setText(userName);
        }
    }

    //top up button
    public void onTopup(View view) {
        Intent startTopUp = new Intent(this, Topup.class);
        startTopUp.putExtra("from dashboard", testBalance);
        startActivityForResult(startTopUp, REQUEST_TOPUP);

    }
    //get total balance back from top up
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_TOPUP) { // right child and OK result.
            if (data.hasExtra("returnMessage")) {
                testBalance = data.getExtras().getInt("returnMessage");
                userBalance.setText(String.valueOf(testBalance));
            }
        }
    }
}
