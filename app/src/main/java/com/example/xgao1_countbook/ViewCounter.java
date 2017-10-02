
/*
 * ViewCounter
 * Version 1.0
 *
 * October 2, 2017
 * Copyright (c) 2017 Team X, CMPUT301, University of Alberta-All Right Reserved.
 *   You may use, distribute, or modify this code under terms and conditions of the Code of Student Behavior at University of Alberta.
 *   You can find a copy of the license in this project. Otherwisw please contant contant@abc.ca.
 */

package com.example.xgao1_countbook;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/*
 * Created by gaoxin on 2017/9/30.
 */

public class ViewCounter extends MainActivity{
    CounterInfo x;
    TextView details;
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_counter);
        details = (TextView) findViewById(R.id.View);
        Intent intent = getIntent();
        final int current_pos = intent.getIntExtra("ViewClass", 0);


    }
    protected void onStart() {
        super.onStart();
        int current_pos;
        Intent intent = getIntent();
        current_pos = intent.getIntExtra("ViewClass", 0);
        details.setText(Counters.get(current_pos).toString());
    }
}
