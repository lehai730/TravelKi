package com.example.hai.travelki;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText UsernameEt, PasswordEt;
    String username, password, type, output;
    Button SigninBtn;
    DBHandler mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SigninBtn = (Button) findViewById(R.id.SignInBtn);
        UsernameEt = (EditText) findViewById(R.id.username);
        PasswordEt = (EditText) findViewById(R.id.password);
        mydb = new DBHandler(this,null,null,1);


    }

    public void OnSignin(View view){
        username = UsernameEt.getText().toString();
        password = PasswordEt.getText().toString();
        type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username, password);
        String output = backgroundWorker.getResult();
        //checkc if login is successful
        //SQL external database tutorial
        //http://www.codebind.com/android-tutorials-and-examples/android-mysql-database-tutorial-android-login-php-mysql/
        https://www.learn2crack.com/2016/04/android-login-registration-php-mysql-server.html

        Toast.makeText(this, "LOGIN AS "+username, Toast.LENGTH_SHORT).show();
        Intent startDashB = new Intent(this,Dashboard.class);
        startDashB.putExtra("from login",username);
        startActivity(startDashB);
        }



}

