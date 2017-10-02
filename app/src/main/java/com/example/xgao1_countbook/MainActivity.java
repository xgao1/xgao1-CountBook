/*
 * CounterBook
 * Version 1.0
 *
 * October 2, 2017
 *
 * Copyright (c) 2017 Team X, CMPUT301, University of Alberta-All Right Reserved.
 *   You may use, distribute, or modify this code under terms and conditions of the Code of Student Behavior at University of Alberta.
 *   You can find a copy of the license in this project. Otherwisw please contant contant@abc.ca.
 */

package com.example.xgao1_countbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class MainActivity extends AppCompatActivity {

    public EditText nameText;
    public EditText intvalueText;
    public EditText commentText;
    public EditText curvalueText;
    public TextView Date;
    ArrayList<CounterInfo> Counters = new ArrayList<>();
    public ArrayAdapter<CounterInfo> adapter;
    public ListView list_counter;
    public static final String FILENAME = "file.sav";
    public TextView number;
    int LongClickedItemIndex;
    int size;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addButton = (Button) findViewById(R.id.add);
        list_counter = (ListView) findViewById(R.id.counterView);
        number = (TextView) findViewById(R.id.numberCounter);

        number.setText("Number Counter:" + size);
        addButton.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
                setResult(RESULT_OK);
                Intent intent = new Intent(MainActivity.this, CreateCounterActivity.class);
                startActivity(intent);
           }
        });

        registerForContextMenu(list_counter);
        list_counter.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
                LongClickedItemIndex=position;
                return false;
            }
        });

    }

    public void loadFile() {

        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();
            Counters = gson.fromJson(in, new TypeToken<ArrayList<CounterInfo>>(){}.getType());

            fis.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            Counters = new ArrayList<CounterInfo>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    public void saveFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson =new Gson();
            gson. toJson(Counters, writer);
            writer.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();

        loadFile();
        adapter = new ArrayAdapter<CounterInfo>(this, R.layout.list_item, Counters);
        list_counter.setAdapter(adapter);
        size = Counters.size();

        number.setText("Number Counter:" + size);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Menu");
        menu.add(0, v.getId(), 0, "Edit");
        menu.add(0, v.getId(), 0, "Delete");
        menu.add(0, v.getId(), 0, "View");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        if (item.getTitle().equals("Edit")) {
            Intent i = new Intent(MainActivity.this, EditCounter.class);
            i.putExtra("EditClass", position);


            startActivity(i);
        }
        else if (item.getTitle() == "Delete") {
            Counters.remove(LongClickedItemIndex);
            saveFile();
            list_counter.setAdapter(adapter);
            onStart();

        }
        else if (item.getTitle() == "View") {
            Intent i = new Intent(MainActivity.this, ViewCounter.class);
            i.putExtra("ViewClass", position);
            startActivity(i);
        }
        else {
            return false;
        }
        return true;
    }

    public class CounterListAdapter extends ArrayAdapter<CounterInfo>{
        public CounterListAdapter(){
            super (MainActivity.this, R.layout.list_item, Counters);
        }

    }
}


