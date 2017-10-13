package com.example.hai.travelki;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.tech.NfcA;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

public class Dashboard extends AppCompatActivity {
    public static final int REQUEST_TOPUP = 2;
    public static final int REQUEST_TRIPCOST = 3;
    public static final int REQUEST_HISTORY = 4;
    TextView userNameDisp, Cur_history;
    EditText userBalance;
    String userName;
    int testBalance, ZoneOn, ZoneOff; //initial balance amount
    Button TopUp,TripCost,History;
    NfcAdapter nfcAdapter;
    DBHandler mydb;
    boolean touch_state = false;
    //card view implementations throughout the project
    //https://github.com/googlesamples/android-CardView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        userBalance = (EditText) findViewById(R.id.user_bal);
        userBalance.setKeyListener(null);
        TopUp = (Button) findViewById(R.id.TopUpBtn);
        TripCost = (Button) findViewById(R.id.calculate_trip);
        History = (Button)findViewById(R.id.view_history) ;
        Cur_history = (TextView) findViewById(R.id.current_history);
        if (getIntent().hasExtra("from login")) {
            userNameDisp = (TextView) findViewById(R.id.username_d);
            userName = getIntent().getExtras().getString("from login");
            userNameDisp.setText(userName);
        }

        //NFC set up
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (!(nfcAdapter != null && nfcAdapter.isEnabled())) {
            Toast.makeText(this, "Please turn on NFC from settings", Toast.LENGTH_SHORT).show();
            finish();
        }
        //Create database
        mydb = new DBHandler(this,null,null,1);
        int userID = mydb.getUserID(userName);
        printDatabase(userID);
        setBalance();

    }

    //print username from database
    public void printDatabase(int userID){
        String dbString = mydb.testQuery(userID);
        Cur_history.setText(dbString);
    }
    //get balance from username
    public void setBalance(){
        testBalance = mydb.getBalance(userName);
        userBalance.setText(String.valueOf(testBalance));
    }

    //Handle NFC intent
    //http://www.codexpedia.com/android/android-nfc-read-and-write-example/
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.hasExtra(NfcAdapter.EXTRA_TAG)) {
            Parcelable[] parcelable = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if (parcelable != null && parcelable.length > 0) {
                readTextFromMessage((NdefMessage)parcelable[0]);
            }
            else {
                Toast.makeText(this, "No NDEF Messages found.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void readTextFromMessage(NdefMessage ndefMessage) {
        NdefRecord[] ndefRecords = ndefMessage.getRecords();
        if (ndefRecords != null && ndefRecords.length > 0) {
            NdefRecord ndefRecord = ndefRecords[0];
            int NFC_ID = Integer.parseInt(getTextFromNdefRecord(ndefRecord));
            String username = userNameDisp.getText().toString();
            Cur_history.setText(" ");
            touch_state = !touch_state; //toggle touch state
            int userID = mydb.getUserID(userName);
            String station_name = mydb.getStationName(NFC_ID);
            if(touch_state == true) {
                ZoneOn = mydb.getZoneNum(NFC_ID);
                Toast.makeText(this, "touched on at " + station_name, Toast.LENGTH_SHORT).show();

                mydb.insertEvent(NFC_ID, userID,"touched on", 0);
                printDatabase(userID);
            }
            else{
                ZoneOff = mydb.getZoneNum(NFC_ID);
                Toast.makeText(this, "touched off at "+ station_name, Toast.LENGTH_SHORT).show();
                int charge = calculateTrip(ZoneOn,ZoneOff);
                mydb.insertEvent(NFC_ID, userID,"touched off",charge);
                testBalance = testBalance+charge;
                mydb.updateBalance(testBalance,userName);
                setBalance();
                printDatabase(userID);
            }

//            Intent checklistIntent = new Intent(this, Checklist_screen.class);
//            checklistIntent.putExtra("com.aditya.takeoff.USER_ID", username);
//            checklistIntent.putExtra("com.aditya.takeoff.NFC_ID", NFC_ID);
//            startActivity(checklistIntent);
        }
        else {
            Toast.makeText(this, "No NDEF records found", Toast.LENGTH_SHORT).show();
        }
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

    @Override
    protected void onResume() {
        super.onResume();
        enableForegroundDispatchSystem();
    }

    @Override
    protected void onPause() {
        super.onPause();
        disableForegroundDispatchSystem();
    }


    private void enableForegroundDispatchSystem() {
        Intent intent = new Intent(this, Dashboard.class).addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        IntentFilter[] intentFilter = new IntentFilter[]{};
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilter, null);
    }

    private void disableForegroundDispatchSystem() {
        nfcAdapter.disableForegroundDispatch(this);
    }

    public String getTextFromNdefRecord(NdefRecord ndefRecord) {
        String tagContent = null;
        try {
            byte[] payload = ndefRecord.getPayload();
            String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16";
            int languageSize = payload[0] & 0063;
            tagContent = new String(payload, languageSize + 1, payload.length - languageSize - 1, textEncoding);
        }
        catch (UnsupportedEncodingException e) {
            Log.e("getTextFromNdefRecord", e.getMessage(), e);
        }
        return tagContent;
    }


    //top up button
    public void onTopup(View view) {
        Intent startTopUp = new Intent(this, Topup.class);
        startTopUp.putExtra("from dashboard", testBalance);
        startActivityForResult(startTopUp, REQUEST_TOPUP);

    }
    //tripcost button
    public void onTripcost(View view) {
        Intent startTripcost = new Intent(this, TripCost.class);
        startActivityForResult(startTripcost, REQUEST_TRIPCOST);

    }
    //history button
    public void onHistory(View view) {
        Intent startHistory = new Intent(this, History.class);
        startHistory.putExtra("from dashboard", userName);
        startActivityForResult(startHistory, REQUEST_HISTORY);

    }
    //get total balance back from top up
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_TOPUP) { // right child and OK result.
            if (data.hasExtra("returnMessage")) {
                testBalance = data.getExtras().getInt("returnMessage");
                mydb.updateBalance(testBalance,userName);
                userBalance.setText(String.valueOf(testBalance));
            }
        }
    }
}
