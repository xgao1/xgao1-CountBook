/*
 * EditCounter
 * Version 1.0
 *
 * October 2, 2017
 * Copyright (c) 2017 Team X, CMPUT301, University of Alberta-All Right Reserved.
 *   You may use, distribute, or modify this code under terms and conditions of the Code of Student Behavior at University of Alberta.
 *   You can find a copy of the license in this project. Otherwisw please contant contant@abc.ca.
 */

package com.example.xgao1_countbook;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*
 * Created by gaoxin on 2017/9/30.
 */

public class EditCounter extends MainActivity {
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_counter);

        nameText = (EditText) findViewById(R.id.ename);
        intvalueText = (EditText) findViewById(R.id.eintvalue);
        curvalueText = (EditText) findViewById(R.id.ecurvalue);
        commentText = (EditText) findViewById(R.id.ecomment);

        Intent intent = getIntent();
        final int current_pos = intent.getIntExtra("EditClass", 0);


        final Button saveBtn = (Button) findViewById(R.id.esaveBtn);
        final Button reBtn = (Button) findViewById(R.id.ereBtn);
        final Button plus = (Button)  findViewById(R.id.plusBtn);
        final Button minus = (Button)  findViewById(R.id.minusBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                String name = nameText.getText().toString();
                String intvalue = intvalueText.getText().toString();
                String curvalue = curvalueText.getText().toString();
                String comment = commentText.getText().toString();
                try {
                    int i = Integer.parseInt(intvalue);
                    int c = Integer.parseInt(curvalue);
                    if (i < 0 && c < 0) {
                        Toast.makeText(getApplicationContext(),
                                "The interval value cannot be negative",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Counters.set(current_pos, new CounterInfo(name, c, i, comment));
                        Toast.makeText(getApplicationContext(), name +
                                " has been saved!", Toast.LENGTH_SHORT).show();
                        saveFile();
                    }
                } catch (NumberFormatException e) {

                    Toast.makeText(getApplicationContext(),
                            "The interval value must be integer",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        reBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                String name = nameText.getText().toString();
                String intvalue = intvalueText.getText().toString();
                String comment = commentText.getText().toString();
                int i = Integer.parseInt(intvalue);
                Counters.set(current_pos, new CounterInfo(name, i, i, comment));
                curvalueText .setText(String.valueOf(Counters.get(current_pos).currentValue()));
                Toast.makeText(getApplicationContext(), name +
                        " has been saved!", Toast.LENGTH_SHORT).show();
                saveFile();
            }

        });

        plus.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                String name = nameText.getText().toString();
                String intvalue = intvalueText.getText().toString();
                String comment = commentText.getText().toString();
                String curvalue = curvalueText.getText().toString();
                int c  = Integer.parseInt(curvalue);
                c=c+1;
                int i = Integer.parseInt(intvalue);
                Counters.set(current_pos, new CounterInfo(name, c, i, comment));
                curvalueText .setText(String.valueOf(Counters.get(current_pos).currentValue()));
                saveFile();
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                String name = nameText.getText().toString();
                String intvalue = intvalueText.getText().toString();
                String comment = commentText.getText().toString();
                String curvalue = curvalueText.getText().toString();
                int c  = Integer.parseInt(curvalue);
                c=c-1;
                int i = Integer.parseInt(intvalue);
                Counters.set(current_pos, new CounterInfo(name, c, i, comment));
                curvalueText .setText(String.valueOf(Counters.get(current_pos).currentValue()));
                saveFile();
            }
        });
    }
    protected void onStart(){
        super.onStart();
        Intent intent = getIntent();
        int pos = intent.getIntExtra("EditClass",0);
        nameText.setText(Counters.get(pos).getName());
        intvalueText .setText(String.valueOf(Counters.get(pos).initivalValue()));
        curvalueText .setText(String.valueOf(Counters.get(pos).currentValue()));
        commentText.setText(Counters.get(pos).getComment());

        ArrayAdapter<CounterInfo> adapter= new CounterListAdapter();
        list_counter.setAdapter(adapter);
    }
}
