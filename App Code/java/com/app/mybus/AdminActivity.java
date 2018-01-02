package com.app.mybus;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.regex.Pattern;

public class AdminActivity extends FragmentActivity implements TimePickerDialog.OnTimeSetListener, AdapterView.OnItemSelectedListener {

    EditText t1,t2,t3,t4,t6,t8;
    String cat="AC";
    TextView t5;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        t1 = (EditText) findViewById(R.id.bid);
        t2 = (EditText) findViewById(R.id.bname);
        t3 = (EditText) findViewById(R.id.src);
        t4 = (EditText) findViewById(R.id.dest);
        t5 = (TextView) findViewById(R.id.time);
        t6 = (EditText) findViewById(R.id.fare);
        t8 = (EditText) findViewById(R.id.seats);

        spinner=(Spinner) findViewById(R.id.spinner);

        ArrayAdapter adapter=ArrayAdapter.createFromResource(this,R.array.category,R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void addbus(View v)
    {
        if (t1.getText().toString().equals("") || t2.getText().toString().equals("") || t3.getText().toString().equals("") || t4.getText().toString().equals("") || t5.getText().toString().equals("Departure Time (hh:mm 24hr)") || t6.getText().toString().equals("") || t8.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(), "Field(s) empty...!!!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(valid())
            {
                String k ;
                k = ServletInterface.addbus(t1.getText().toString(), t2.getText().toString(), t3.getText().toString(), t4.getText().toString(), t5.getText().toString(), t6.getText().toString(), cat, t8.getText().toString());

                if (k.contains("1")) {
                    SharedPreferences p = getApplicationContext().getSharedPreferences("MyBus", Context.MODE_PRIVATE);
                    SharedPreferences.Editor e = p.edit();
                    e.putString("bid", t1.getText().toString());
                    e.apply();
                    Toast.makeText(getApplicationContext(), "Bus Added Successfully...!!!", Toast.LENGTH_SHORT).show();
                } else if (k.contains("0")) {
                    Toast.makeText(getApplicationContext(), "Bus already exists...!!!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Connection Problem... Try Again...!!!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void reset(View v)
    {
        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        t5.setText("Departure Time (hh:mm 24hr)");
        t6.setText("");
        t8.setText("");
    }

    public boolean valid()
    {
        String busid=t1.getText().toString();
        if(busid.length() == 5)
        {
            try{
                int temp=Integer.parseInt(busid);
            }
            catch(NumberFormatException e)
            {
                Toast.makeText(getApplicationContext(), " BId can only have numbers...!!!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), " BId to be exactly 5 digit...!!!", Toast.LENGTH_SHORT).show();
            return false;
        }

        String time=t5.getText().toString();
        if(time.length() == 5)
        {
            try{
                String[] temp=time.split(Pattern.quote(":"));
                int hr=Integer.parseInt(temp[0]);
                int min=Integer.parseInt(temp[1]);
            }
            catch(NumberFormatException e)
            {
                Toast.makeText(getApplicationContext(), " Invalid time...!!!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), " Invalid time...!!!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        String preh="", prem="";
        if(hourOfDay<10)
            preh="0";
        else
            preh="";
        if(minute<10)
            prem="0";
        else
            prem="";
        t5.setText(preh+hourOfDay+":"+prem+minute);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        cat=view.toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
