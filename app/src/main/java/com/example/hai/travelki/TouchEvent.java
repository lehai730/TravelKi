package com.example.hai.travelki;

/**
 * Created by Hai on 09-Oct-17.
 */

public class TouchEvent {
    private String id;
    private String username;
    private String date;
    private String time;
    private String touch_status;
    private String zone;
    private String station_name;
    private String charge;
    private String balance;

    public TouchEvent(String cId, String cUsername, String cDate, String cTime, String cTouch_status, String cZone, String cStation_name, String cTransaction, String cBalance){
        id = cId;
        username = cUsername;
        date = cDate;
        time = cTime;
        touch_status = cTouch_status;
        zone = cZone;
        station_name = cStation_name;
        charge = cTransaction;
        balance = cBalance;
    }



    public String getBalance() {return balance;}
    public String getDate() {return date;}
    public String getId() {return id;}
    public String getStation_name() {return station_name;}
    public String getTime() {return time;}
    public String getTouch_status() {return touch_status;}
    public String getTransaction() {return charge;}
    public String getUsername() {return username;}
    public String getZone() {return zone;}

}
