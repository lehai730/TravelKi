package com.example.hai.travelki;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class History extends AppCompatActivity {

    DBHandler db;
    String username;
    TextView disp_history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        disp_history = (TextView) findViewById(R.id.display_history);
        db = new DBHandler(this,null,null,1);
        if (getIntent().hasExtra("from dashboard")) {
            username = getIntent().getExtras().getString("from dashboard");
        }
        int userID = db.getUserID(username);
        printDatabase(userID);
    }
    public void onReturn(View view){
        Intent replyBalance = new Intent(this, Dashboard.class);
        setResult(RESULT_OK, replyBalance);
        finish() ; // close this activity.
    }
    //print username from database
    public void printDatabase(int userID){
        String dbString = db.showHistory(userID);
        disp_history.setText(dbString);
    }
}
