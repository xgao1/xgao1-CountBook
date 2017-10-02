/*
 * CreaterCounter
 * Version 1.0
 *
 * October 2, 2017
 * Copyright (c) 2017 Team X, CMPUT301, University of Alberta-All Right Reserved.
 *   You may use, distribute, or modify this code under terms and conditions of the Code of Student Behavior at University of Alberta.
 *   You can find a copy of the license in this project. Otherwisw please contant contant@abc.ca.
 */

package com.example.xgao1_countbook;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/*
 * Created by gaoxin on 2017/9/25.
 */

public class CreateCounterActivity extends MainActivity {


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_counter);

        nameText = (EditText) findViewById(R.id.name);
        intvalueText = (EditText) findViewById(R.id.intvalue);
        commentText = (EditText) findViewById(R.id.comment);


        final Button saveButton = (Button) findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v){
                setResult(RESULT_OK);
                String nameTxt = nameText.getText().toString();
                String intvalueTxt = intvalueText.getText().toString();

                if (nameTxt.length() == 0 || intvalueTxt.length() == 0) {
                    Toast.makeText(getApplicationContext(),
                            "The name and interval value cannot be empty",
                            Toast.LENGTH_SHORT).show();
                }
                try {
                    int i = Integer.parseInt(intvalueTxt);

                    if (i < 0) {
                        Toast.makeText(getApplicationContext(),
                                "The interval value cannot be negative",
                                Toast.LENGTH_SHORT).show();
                    }
                    else{
                        addCounter(nameTxt, i, i, commentText.getText().toString());
                        Toast.makeText(getApplicationContext(), nameTxt +
                                " has been added!", Toast.LENGTH_SHORT).show();
                        saveFile();
                    }
                } catch(NumberFormatException e) {

                    Toast.makeText(getApplicationContext(),
                            "The interval value must be integer",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addCounter(String name, int currentValue, int initivalValue, String comment ){
        Counters.add(new CounterInfo(name, currentValue, initivalValue, comment));
    }


}

