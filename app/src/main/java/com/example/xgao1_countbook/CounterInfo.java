
/*
 * CounterInfo
 * Version 1.0
 *
 * October 2, 2017
 * Copyright (c) 2017 Team X, CMPUT301, University of Alberta-All Right Reserved.
 *   You may use, distribute, or modify this code under terms and conditions of the Code of Student Behavior at University of Alberta.
 *   You can find a copy of the license in this project. Otherwisw please contant contant@abc.ca.
 */

package com.example.xgao1_countbook;

/*
 * Created by gaoxin on 2017/9/29.
 */
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Date;

public class CounterInfo {
    public String name;
    public int currentValue;
    public int initivalValue;
    public String comment;
    public Date date;
    public String datestring;
    @RequiresApi(api = Build.VERSION_CODES.N)
    public CounterInfo (String name, int currentValue, int initivalValue, String comment){
        this.name = name;
        this.currentValue = currentValue;
        this.initivalValue = initivalValue;
        this.comment = comment;
        date =new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.datestring = dateFormat.format(date);


    }

    public String getName(){return name;}

    public String getDate() {
        return datestring;
    }

    public int currentValue() {return currentValue;}

    public int initivalValue() {return initivalValue;}

    public String getComment() {return comment;}

    @Override
    public String toString(){
        return "Name:" + name +"\n Current Value:"+ currentValue + "\n " +
                "Date:"+datestring+"\n Initival Value: "+ initivalValue+ "\n Comments: "+ comment;
    }

}
