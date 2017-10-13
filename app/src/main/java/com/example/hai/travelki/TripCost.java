package com.example.hai.travelki;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
//Spinner view example
//https://www.mkyong.com/android/android-spinner-drop-down-list-example/
public class TripCost extends AppCompatActivity {
    private Spinner spinnerFrom, spinnerTo;
    private Button btnCalculate;
    private EditText tripCost;
    private DBHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_cost);
        db = new DBHandler(this,null,null,1);
        addItemsOnSpinnerFrom();
        addItemsOnSpinnerTo();
        addListenerOnButton();
        tripCost = (EditText) findViewById(R.id.cost_result);
        tripCost.setKeyListener(null);
    }

    //add list item to spinner1
    public void addItemsOnSpinnerFrom() {

        spinnerFrom = (Spinner) findViewById(R.id.spinner);
        List<String> list = new ArrayList<String>();
        list.add("flinders street");
        list.add("southern cross");
        list.add("flagstaff");
        list.add("melbourne central");
        list.add("richmond");
        list.add("south yarra");
        list.add("carnegie");
        list.add("murrumbeena");
        list.add("hughesdale");
        list.add("oakleigh");
        list.add("huntingdale");
        list.add("clayton");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(dataAdapter);
    }

    //add list item to spinner2
    public void addItemsOnSpinnerTo() {

        spinnerTo = (Spinner) findViewById(R.id.spinner2);
        List<String> list = new ArrayList<String>();
        list.add("flinders street");
        list.add("southern cross");
        list.add("flagstaff");
        list.add("melbourne central");
        list.add("richmond");
        list.add("south yarra");
        list.add("carnegie");
        list.add("murrumbeena");
        list.add("hughesdale");
        list.add("oakleigh");
        list.add("huntingdale");
        list.add("clayton");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTo.setAdapter(dataAdapter);
    }

    //get the selected dropdown list value when button calculate is clicked
    public void addListenerOnButton() {

        //spinnerFrom = (Spinner) findViewById(R.id.spinner);
        //spinnerTo = (Spinner) findViewById(R.id.spinner2);
        btnCalculate = (Button) findViewById(R.id.calculate_trip);

        btnCalculate.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String stationfrom = spinnerFrom.getSelectedItem().toString();
                String stationto = spinnerTo.getSelectedItem().toString();
                int Zoneon = db.getZoneNum(stationfrom);
                int Zoneoff = db.getZoneNum(stationto);
                int costval = calculateTrip(Zoneon, Zoneoff);
                tripCost.setText(String.valueOf(costval));
            }

        });
    }
    //calculate fare based on trip
    public int calculateTrip(int ZoneOn, int ZoneOff){
        int costval = 0;
        if (ZoneOn == 1 && ZoneOff == 1){
            costval = -2;
        }
        else if(ZoneOn == 2 && ZoneOff == 2) {
            costval = -2;
        }
        else if(ZoneOn == 3 && ZoneOff == 3) {
            costval = -2;
        }
        else if ((ZoneOn == 1 && ZoneOff == 2) || (ZoneOn == 2 && ZoneOff == 1)){
            costval = -4;
        }
        else if ((ZoneOn == 1 && ZoneOff == 3) || (ZoneOn == 3 && ZoneOff == 1)){
            costval = -6;
        }
        else if ((ZoneOn == 2 && ZoneOff == 3) || (ZoneOn == 3 && ZoneOff == 2)){
            costval = -4;
        }
        return costval;
    }

    //button to return to dashboard
    public void onReturn(View view){
        Intent finish = new Intent(this, Dashboard.class);
        setResult(RESULT_OK, finish);
        finish() ; // close this activity.
    }
}
