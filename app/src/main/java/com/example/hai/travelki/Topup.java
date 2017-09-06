package com.example.hai.travelki;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Topup extends AppCompatActivity {

    int TotalAmount;
    int TopupAmount;
    EditText Topup_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup);

        Topup_layout = (EditText) findViewById(R.id.topup_amt);

        if (getIntent().hasExtra("from dashboard")) {
            TotalAmount = getIntent().getExtras().getInt("from dashboard");
            Topup_layout.setHint("Current Amount is: "+ TotalAmount);
        }

    }
    public void onConfirm(View view){
        TopupAmount = Integer.parseInt(Topup_layout.getText().toString());
        TotalAmount = TotalAmount+TopupAmount;
        Intent replyBalance = new Intent(this, Dashboard.class);
        Intent returnMessage = replyBalance.putExtra("returnMessage", TotalAmount);
        setResult(RESULT_OK, replyBalance);
        finish() ; // close this activity.
    }
}
