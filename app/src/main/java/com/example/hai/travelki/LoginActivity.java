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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SigninBtn = (Button) findViewById(R.id.SignInBtn);
        UsernameEt = (EditText) findViewById(R.id.username);
        PasswordEt = (EditText) findViewById(R.id.password);


    }

    public void OnSignin(View view){
        username = UsernameEt.getText().toString();
        password = PasswordEt.getText().toString();
        type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username, password);

        //checkc if login is successful
        if(output == "login success"){
            Intent startDashB = new Intent(this,Dashboard.class);
            startDashB.putExtra("from login",username);
            startActivity(startDashB);
        }
        Toast.makeText(this, "LOGIN FAILED", Toast.LENGTH_SHORT).show();

    }
}
