package com.app.mybus;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class ABusActivity extends AppCompatActivity {

    public static final String EXTRA_BID="com.app.mybus.BID";
    public static final String EXTRA_FARE="com.app.mybus.FARE";
    public static final String EXTRA_DOJ="com.app.mybus.DOJ";


    ListView l;
    String[] bus;
    String[] bid;
    String[] bname;
    String[] time;
    String[] fare;
    String[] nos;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abus);

        l = (ListView) findViewById(R.id.listView4);
        search();
    }

    public void search()
    {


            String k = "";
            k = ServletInterface.abus();

            if(!k.equals("")) {
                retrieve(k);

                adapter = new CustomAdapter(this, bid, bname, time, fare, nos);
                l.setAdapter(adapter);

            }

        }


    public void retrieve(String data)
    {
        bus = data.split(Pattern.quote(" :: "));
        int n = bus.length;

        bid = new String[n];
        bname= new String[n];
        time = new String[n];
        fare = new String[n];
        nos = new String[n];

        for(int i=0;i<n;i++)
        {
            String[] temp=bus[i].split(Pattern.quote(" || "));
            bid[i]=temp[0];
            bname[i]=temp[1];
            time[i]=temp[2];
            fare[i]=temp[3];
            nos[i]=temp[4];
        }
    }




}

