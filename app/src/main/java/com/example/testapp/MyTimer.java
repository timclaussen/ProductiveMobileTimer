package com.example.testapp;

import android.os.Build;
import android.os.SystemClock;

import androidx.annotation.RequiresApi;

//import java.time;
import java.time.LocalTime;
import java.time.temporal.TemporalField;

//Timer class to provide the functional timer code to the fragments who will use it
public class MyTimer {
    private long initTime;
    private long currTime;
    private long timeTotal;
    private long seconds;
    private long minutes;
    private long hours;
    private LocalTime startTime;
    private LocalTime currentTime;
    public String timeString;

    public void MyTimer()
    {
        initTime = 0;
        currTime = System.nanoTime();
        timeTotal = 0;
        timeString = "0.0";
    }

    //planning:

    //Timer start - reset init time to now, do not change total time

    public void startTimer()
    {

        initTime = System.nanoTime();
    }

    public void updateTime()
    {
        currTime = System.nanoTime();
        timeTotal = (currTime - initTime);
        toReadableTimeString();
    }

    //Timer update time difference
    public long getUpdatedTime()
    {
        updateTime();
        return (currTime - initTime);
    }

    //Get time total
    public long getTotalTime()
    {
        timeTotal = (currTime - initTime);
        return (currTime - initTime);
    }

    public void resetTimers()
    {
        timeTotal = currTime = initTime = 0;
    }

    public void toReadableTimeString()
    {
        StringBuilder sb = new StringBuilder();
        seconds = timeTotal/100000000; //convert nanotime to seconds
        hours = seconds / 3600; //seconds to hours
        seconds = seconds - (hours * 3600); //remove the hours from seconds counter
        minutes = seconds/60; // seconds to minutes
        seconds -= (minutes * 60); // remove minutes from seconds
        sb.append(String.valueOf(hours) + "h"); //start with hours
        sb.append(String.valueOf(minutes) + "m");
        sb.append(String.valueOf(seconds) + "s"); //string is done
        timeString = sb.toString();
    }

}
