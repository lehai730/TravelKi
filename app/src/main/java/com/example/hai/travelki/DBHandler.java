package com.example.hai.travelki;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.util.Log;
import android.widget.Toast;

import static android.R.attr.tag;

/**
 * Created by Hai on 09-Oct-17.
   Based on tutorials
 https://github.com/buckyroberts/Source-Code-from-Tutorials/blob/master/Android_Beginners/049-054%20SQLite/49%20to%2054%20SQLite.txt
 https://www.youtube.com/watch?v=PrMhjEh2ySk&t=302s
 */

public class DBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "travelki.db";

    public static final String TABLE_TRANSACTION = "touch_event";
    public static final String COLUMN_TRANSACTION_ID = "id";
    public static final String COLUMN_TRANSACTION_STATION_ID = "station_id";
    public static final String COLUMN_TRANSACTION_USER_ID = "user_id";
    public static final String COLUMN_TRANSACTION_TIMESTAMP = "timestamp";
    public static final String COLUMN_TRANSACTION_STATUS = "status";
    public static final String COLUMN_TRANSACTION_CHARGE = "charge";

    public static final String TABLE_STATION = "station";
    public static final String COLUMN_STATION_ID = "id";
    public static final String COLUMN_STATION_NAME = "station_name";
    public static final String COLUMN_STATION_ZONE = "zone";

    public static final String TABLE_USER = "user";
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_USER_NAME = "username";
    public static final String COLUMN_USER_BALANCE = "balance";


    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "DROP TABLE IF EXISTS " + TABLE_USER + ";";
        db.execSQL(query);
        query = "CREATE TABLE " + TABLE_USER + " (" +
                COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " + ", " +
                COLUMN_USER_NAME + " TEXT " + ", " +
                COLUMN_USER_BALANCE + " INTEGER " +
                ");";
        Log.d("dbDebug", query);
        db.execSQL(query);

        query = "DROP TABLE IF EXISTS " + TABLE_STATION;
        db.execSQL(query);
        query = "CREATE TABLE " + TABLE_STATION + " (" +
                COLUMN_STATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " + ", " +
                COLUMN_STATION_NAME + " TEXT " + ", " +
                COLUMN_STATION_ZONE + " INTEGER " +
                ");";
        Log.d("dbDebug", query);
        db.execSQL(query);

        query = "DROP TABLE IF EXISTS " + TABLE_TRANSACTION;
        Log.d("dbDebug", query);
        db.execSQL(query);
        query = "CREATE TABLE " + TABLE_TRANSACTION + " (" +
                COLUMN_TRANSACTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " + ", " +
                COLUMN_TRANSACTION_STATION_ID + " INTEGER " + ", " +
                COLUMN_TRANSACTION_USER_ID + " INTEGER " + ", " +
                COLUMN_TRANSACTION_TIMESTAMP + " DATETIME DEFAULT (DATETIME(CURRENT_TIMESTAMP, 'LOCALTIME')) " + ", " +
                COLUMN_TRANSACTION_STATUS + " TEXT " + ", " +
                COLUMN_TRANSACTION_CHARGE + " INTEGER " + ", " +
                "FOREIGN KEY (" + COLUMN_TRANSACTION_STATION_ID + ") REFERENCES " + TABLE_STATION + "(" + COLUMN_STATION_ID + "), " +
                "FOREIGN KEY (" + COLUMN_TRANSACTION_USER_ID + ") REFERENCES " + TABLE_USER + "(" + COLUMN_USER_ID + ")" +
                ");";
        Log.d("dbDebug", query);
        db.execSQL(query);

        query = "INSERT INTO " + TABLE_USER + " VALUES ( 1, \"hai\", 100 );";
        Log.d("dbDebug", query);
        db.execSQL(query);
        query = "INSERT INTO " + TABLE_USER + " VALUES ( null, \"daniel\", 150 );";
        Log.d("dbDebug", query);
        query = "INSERT INTO " + TABLE_USER + " VALUES ( null, \"sach\", 20 );";
        Log.d("dbDebug", query);
        db.execSQL(query);

        query = "INSERT INTO " + TABLE_TRANSACTION + " (`id`,`station_id`,`user_id`,`status`,`charge`) VALUES (NULL,NULL,1,NULL,NULL);";
        Log.d("dbDebug", query);
        db.execSQL(query);
        query = "INSERT INTO " + TABLE_TRANSACTION + " (`id`,`station_id`,`user_id`,`status`,`charge`) VALUES (NULL,NULL,2,NULL,NULL);";
        Log.d("dbDebug", query);
        db.execSQL(query);

        query = "INSERT INTO " + TABLE_STATION + " (`id`,`station_name`,`zone`) VALUES ( null,\"flinders street\",1);";
        Log.d("dbDebug", query);
        db.execSQL(query);
        query = "INSERT INTO " + TABLE_STATION + " (`id`,`station_name`,`zone`) VALUES ( null,\"southern cross\",1);";
        Log.d("dbDebug", query);
        db.execSQL(query);
        query = "INSERT INTO " + TABLE_STATION + " (`id`,`station_name`,`zone`) VALUES ( null,\"flagstaff\",1);";
        Log.d("dbDebug", query);
        db.execSQL(query);
        query = "INSERT INTO " + TABLE_STATION + " (`id`,`station_name`,`zone`) VALUES ( null,\"melbourne central\",1);";
        Log.d("dbDebug", query);
        db.execSQL(query);
        query = "INSERT INTO " + TABLE_STATION + " (`id`,`station_name`,`zone`) VALUES ( null,\"richmond\",2);";
        Log.d("dbDebug", query);
        db.execSQL(query);
        query = "INSERT INTO " + TABLE_STATION + " (`id`,`station_name`,`zone`) VALUES ( null,\"south yarra\",2);";
        Log.d("dbDebug", query);
        db.execSQL(query);
        query = "INSERT INTO " + TABLE_STATION + " (`id`,`station_name`,`zone`) VALUES ( null,\"carnegie\",2);";
        Log.d("dbDebug", query);
        db.execSQL(query);
        query = "INSERT INTO " + TABLE_STATION + " (`id`,`station_name`,`zone`) VALUES ( null,\"murrumbeena\",2);";
        Log.d("dbDebug", query);
        db.execSQL(query);
        query = "INSERT INTO " + TABLE_STATION + " (`id`,`station_name`,`zone`) VALUES ( null,\"hughesdale\",2);";
        Log.d("dbDebug", query);
        db.execSQL(query);
        query = "INSERT INTO " + TABLE_STATION + " (`id`,`station_name`,`zone`) VALUES ( null,\"oakleigh\",3);";
        Log.d("dbDebug", query);
        db.execSQL(query);
        query = "INSERT INTO " + TABLE_STATION + " (`id`,`station_name`,`zone`) VALUES ( null,\"huntingdale\",3);";
        Log.d("dbDebug", query);
        db.execSQL(query);
        query = "INSERT INTO " + TABLE_STATION + " (`id`,`station_name`,`zone`) VALUES ( null,\"clayton\",3);";
        Log.d("dbDebug", query);
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_STATION);
        onCreate(db);
    }
    //get user balance based on username entered
    public int getBalance(String userName){
        int balance;
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT " + TABLE_USER+"."+COLUMN_USER_BALANCE +
                        " FROM " + TABLE_USER +
                        " WHERE " + TABLE_USER +"."+COLUMN_USER_NAME +" = \""+ userName +"\";";

        //Cursor point to a location in your results
        Cursor c = db.rawQuery(query,null);
        //pointer move to first row
        c.moveToFirst();

        balance = c.getInt(c.getColumnIndex("balance"));

        return balance;
    }

    //update user's balance
    public void updateBalance(int val,String userName){
        SQLiteDatabase db = getWritableDatabase();
        String query = "UPDATE " + TABLE_USER+
                " SET " + COLUMN_USER_BALANCE + " = "+ val+
                " WHERE " + TABLE_USER +"."+COLUMN_USER_NAME +" = \""+ userName +"\";";
        db.execSQL(query);
    }

    //get user ID
    public int getUserID(String userName){
        int userid;
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT " + TABLE_USER+"."+COLUMN_USER_ID +
                " FROM " + TABLE_USER +
                " WHERE " + TABLE_USER +"."+COLUMN_USER_NAME +" = \""+ userName +"\";";

        //Cursor point to a location in your results
        Cursor c = db.rawQuery(query,null);
        //pointer move to first row
        c.moveToFirst();

        userid = c.getInt(c.getColumnIndex(COLUMN_USER_ID));

        return userid;
    }
    //get Station Name using station_id
    public String getStationName(int NFC_id){
        String StationName;
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT " + TABLE_STATION+"."+COLUMN_STATION_NAME +
                " FROM " + TABLE_STATION +
                " WHERE " + TABLE_STATION +"."+COLUMN_STATION_ID +" = "+ NFC_id +";";

        //Cursor point to a location in your results
        Cursor c = db.rawQuery(query,null);
        //pointer move to first row
        c.moveToFirst();

        StationName = c.getString(c.getColumnIndex(COLUMN_STATION_NAME));

        return StationName;
    }

    //get Station Zone number using Station_id
    public int getZoneNum(int NFC_id){
        int ZoneNum;
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT " + TABLE_STATION+"."+COLUMN_STATION_ZONE +
                " FROM " + TABLE_STATION +
                " WHERE " + TABLE_STATION +"."+COLUMN_STATION_ID +" = "+ NFC_id +";";

        //Cursor point to a location in your results
        Cursor c = db.rawQuery(query,null);
        //pointer move to first row
        c.moveToFirst();

        ZoneNum = c.getInt(c.getColumnIndex(COLUMN_STATION_ZONE));

        return ZoneNum;
    }

    //get Station Zone number using station_name
    public int getZoneNum(String station_name){
        int ZoneNum;
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT " + TABLE_STATION+"."+COLUMN_STATION_ZONE +
                " FROM " + TABLE_STATION +
                " WHERE " + TABLE_STATION +"."+COLUMN_STATION_NAME +" = \""+ station_name +"\";";

        //Cursor point to a location in your results
        Cursor c = db.rawQuery(query,null);
        //pointer move to first row
        c.moveToFirst();

        ZoneNum = c.getInt(c.getColumnIndex(COLUMN_STATION_ZONE));

        return ZoneNum;
    }

    //insert new touch event
    public void insertEvent(int station_id, int user_id, String status, int charge) {
        ContentValues event = new ContentValues();
        event.put(COLUMN_TRANSACTION_STATION_ID, station_id);
        event.put(COLUMN_TRANSACTION_USER_ID, user_id);
        event.put(COLUMN_TRANSACTION_STATUS, status);
        event.put(COLUMN_TRANSACTION_CHARGE, charge);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_TRANSACTION, null, event);
        db.close();
    }

    // print out database as a string
    public String showDashHistory(int userID){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_TRANSACTION + " WHERE "+ TABLE_TRANSACTION+"."+COLUMN_TRANSACTION_USER_ID+" = " + userID + " ORDER BY "+ COLUMN_TRANSACTION_TIMESTAMP+ " DESC;";

        //Cursor point to a location in your results
        Cursor c = db.rawQuery(query,null);
        //pointer move to first row
        c.moveToFirst();

        if(c.getString(c.getColumnIndex("station_id"))!= null){
            dbString += c.getString(c.getColumnIndex("id"));
            Log.d("dbDebug", dbString);
            dbString += " ";
            dbString += c.getString(c.getColumnIndex("station_id"));
            Log.d("dbDebug", dbString);
            dbString += " ";
            dbString += c.getString(c.getColumnIndex("user_id"));
            Log.d("dbDebug", dbString);
            dbString += " ";
            dbString += c.getString(c.getColumnIndex("timestamp"));
            Log.d("dbDebug", dbString);
            dbString += " ";
            dbString += c.getString(c.getColumnIndex("status"));
            Log.d("dbDebug", dbString);
            dbString += " ";
            dbString += c.getString(c.getColumnIndex("charge"));
            Log.d("dbDebug", dbString);
            c.moveToNext();
        }
        else {
            dbString = "No History Entry in Database";
        }
        db.close();
        return dbString;
    }

    //test query
    public String testQuery(int userID){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT "+TABLE_USER+"."+COLUMN_USER_NAME+" AS username, " +
                "DATE(" + TABLE_TRANSACTION + "." + COLUMN_TRANSACTION_TIMESTAMP + ") AS date, "+
                "TIME(" + TABLE_TRANSACTION + "." + COLUMN_TRANSACTION_TIMESTAMP + ") AS time, "+
                TABLE_TRANSACTION+"."+COLUMN_TRANSACTION_STATUS+", "+TABLE_STATION+"."+COLUMN_STATION_ZONE+", "+
                TABLE_STATION+"."+COLUMN_STATION_NAME+ ", "+ TABLE_TRANSACTION+"."+COLUMN_TRANSACTION_CHARGE+", "+
                TABLE_USER+"."+COLUMN_USER_BALANCE+
                " FROM "+TABLE_USER+
                " INNER JOIN "+TABLE_TRANSACTION+" ON "+TABLE_USER+"."+COLUMN_USER_ID+" = "+TABLE_TRANSACTION+"."+COLUMN_TRANSACTION_USER_ID+
                " INNER JOIN "+TABLE_STATION+" ON "+TABLE_TRANSACTION+"."+COLUMN_TRANSACTION_STATION_ID+" = "+TABLE_STATION+"."+COLUMN_STATION_ID+
                " WHERE "+TABLE_USER+"."+COLUMN_USER_ID+" = "+userID+
                " ORDER BY TIME DESC";

        //Cursor point to a location in your results
        Cursor c = db.rawQuery(query,null);
        //pointer move to first row
        c.moveToFirst();

        if((c != null)&&(c.getCount()> 0)){
            dbString += "date: ";
            dbString += c.getString(c.getColumnIndex("date"));
            Log.d("dbDebug", dbString);
            dbString += "           time: ";
            dbString += c.getString(c.getColumnIndex("time"));
            Log.d("dbDebug", dbString);
            dbString += "\n Status: ";
            dbString += c.getString(c.getColumnIndex(COLUMN_TRANSACTION_STATUS));
            Log.d("dbDebug", dbString);
            dbString += "\n Station: ";
            dbString += c.getString(c.getColumnIndex(COLUMN_STATION_NAME));
            Log.d("dbDebug", dbString);
            dbString += "    Zone: ";
            dbString += c.getString(c.getColumnIndex(COLUMN_STATION_ZONE));
            Log.d("dbDebug", dbString);
            dbString += "\n Transaction: $";
            dbString += c.getString(c.getColumnIndex(COLUMN_TRANSACTION_CHARGE));
            Log.d("dbDebug", dbString);
            dbString += "     Current Balance: $";
            dbString += c.getString(c.getColumnIndex(COLUMN_USER_BALANCE));
            Log.d("dbDebug", dbString);
            c.moveToNext();
        }
        else {
            dbString = "No History Entry in Database";
        }
        db.close();
        return dbString;
    }

    public String showHistory(int userID){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT "+TABLE_USER+"."+COLUMN_USER_NAME+" AS username, " +
                "DATE(" + TABLE_TRANSACTION + "." + COLUMN_TRANSACTION_TIMESTAMP + ") AS date, "+
                "TIME(" + TABLE_TRANSACTION + "." + COLUMN_TRANSACTION_TIMESTAMP + ") AS time, "+
                TABLE_TRANSACTION+"."+COLUMN_TRANSACTION_STATUS+", "+TABLE_STATION+"."+COLUMN_STATION_ZONE+", "+
                TABLE_STATION+"."+COLUMN_STATION_NAME+ ", "+ TABLE_TRANSACTION+"."+COLUMN_TRANSACTION_CHARGE+", "+
                TABLE_USER+"."+COLUMN_USER_BALANCE+
                " FROM "+TABLE_USER+
                " INNER JOIN "+TABLE_TRANSACTION+" ON "+TABLE_USER+"."+COLUMN_USER_ID+" = "+TABLE_TRANSACTION+"."+COLUMN_TRANSACTION_USER_ID+
                " INNER JOIN "+TABLE_STATION+" ON "+TABLE_TRANSACTION+"."+COLUMN_TRANSACTION_STATION_ID+" = "+TABLE_STATION+"."+COLUMN_STATION_ID+
                " WHERE "+TABLE_USER+"."+COLUMN_USER_ID+" = "+userID+
                " ORDER BY TIME DESC";

        //Cursor point to a location in your results
        Cursor c = db.rawQuery(query,null);
        //pointer move to first row
        c.moveToFirst();

        while (!c.isAfterLast()){
            if((c != null)&&(c.getCount()> 0)){
                dbString += "date: ";
                dbString += c.getString(c.getColumnIndex("date"));
                Log.d("dbDebug", dbString);
                dbString += "           time: ";
                dbString += c.getString(c.getColumnIndex("time"));
                Log.d("dbDebug", dbString);
                dbString += "\n Status: ";
                dbString += c.getString(c.getColumnIndex(COLUMN_TRANSACTION_STATUS));
                Log.d("dbDebug", dbString);
                dbString += "\n Station: ";
                dbString += c.getString(c.getColumnIndex(COLUMN_STATION_NAME));
                Log.d("dbDebug", dbString);
                dbString += "    Zone: ";
                dbString += c.getString(c.getColumnIndex(COLUMN_STATION_ZONE));
                Log.d("dbDebug", dbString);
                dbString += "\n Transaction: $";
                dbString += c.getString(c.getColumnIndex(COLUMN_TRANSACTION_CHARGE));
                Log.d("dbDebug", dbString);
                dbString += "     Current Balance: $";
                dbString += c.getString(c.getColumnIndex(COLUMN_USER_BALANCE));
                Log.d("dbDebug", dbString);
                dbString += "\n\n ";
                c.moveToNext();

            }
            else {
                dbString = "No History Entry in Database";
            }

        }

        db.close();
        return dbString;
    }
}
