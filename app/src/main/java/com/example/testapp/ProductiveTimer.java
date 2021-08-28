package com.example.testapp;

import static java.lang.Thread.sleep;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.testapp.databinding.FragmentFirstBinding;
import com.example.testapp.databinding.FragmentProductiveTimerBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;

import com.example.testapp.MyTimer;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductiveTimer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductiveTimer extends Fragment {


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private MyTimer mytimer;

    private FragmentProductiveTimerBinding binding;


    public ProductiveTimer() {
        mytimer = new MyTimer();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ProductiveTimer.
     */
    public static ProductiveTimer newInstance() {
        ProductiveTimer fragment = new ProductiveTimer();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_productive_timer, container, false);
        //Had to add this bit to allow data binding, and inflate creates instance of fragment
        binding = FragmentProductiveTimerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.TextNumberTimer.setText(mytimer.timeString);
        binding.toggleButtonProductive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.toggleButtonProductive.isChecked())
                {
                    //sets toggle to ON
                    timerStart(); //this stops when toggle is unchecked
                }
                //TODO make logging
                //TODO make log time, reset button
            }
        });
    }

    public void timerStart()
    {
        //timer waits 100ms then repeats at 100ms
        Timer timer = new Timer("timer");
        long delay = 100L;
        long delay2= 100L;

        TimerTask taskUpdateAndCheck = new TimerTask() {
            @Override
            public void run() {
                mytimer.updateTime(); //update the variables in mytimer
                binding.TextNumberTimer.setText(mytimer.timeString);
                if(!binding.toggleButtonProductive.isChecked())
                {
                    timer.cancel(); //
                }
            }
        };

        //debug toggle button
        //timerButtonPressed("Start Timer Pushed!");

        //init the time variables in mytimer, start the timer thread from Java Util
        mytimer.startTimer();
        timer.scheduleAtFixedRate(taskUpdateAndCheck, delay, delay2);
        //TODO do I need more to keep this timer going?
    }

    //TODO timer stop
    public void timerStop()
    {
        //debugging toggle button
        timerButtonPressed("Stop Timer Pushed!");
    }

    public void timerButtonPressed(String message)
    {
        Snackbar headsUp = Snackbar.make(this.getView(), message, 1000);
        headsUp.show();
    }
}