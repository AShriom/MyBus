package com.app.mybus;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class SignupActivity extends FragmentActivity implements DatePickerDialog.OnDateSetListener{

    EditText t1,t2,t3,t4,t5,t6;
    TextView t7;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        t1 = (EditText) findViewById(R.id.suui);
        t2 = (EditText) findViewById(R.id.sufn);
        t3 = (EditText) findViewById(R.id.supw);

        t5 = (EditText) findViewById(R.id.suem);
        t6 = (EditText) findViewById(R.id.sucn);
        t7=(TextView) findViewById(R.id.sudob);
    }

    public void submit(View v)
    {
        if (t1.getText().toString().equals("") || t2.getText().toString().equals("") || t3.getText().toString().equals("") || t5.getText().toString().equals("") || t6.getText().toString().equals("")||t7.getText().toString().equals("Date Of Birth"))
        {
            Toast.makeText(getApplicationContext(), " Field(s) empty...!!!", Toast.LENGTH_SHORT).show();
        }
        else {
            if(valid()) {
                String k = "";
                k = ServletInterface.signup(t1.getText().toString(), t2.getText().toString(), t3.getText().toString(), t7.getText().toString(), t5.getText().toString(), t6.getText().toString());

                if (k.contains("1")) {
                    Intent in = new Intent(this, MainActivity.class);
                    Toast.makeText(getApplicationContext(), "Signup Successful...!!!", Toast.LENGTH_SHORT).show();
                    startActivity(in);
                    finish();
                } else if (k.contains("0")) {
                    Toast.makeText(getApplicationContext(), "Username already exists...!!!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Sign Up Failed... Try Again...!!!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void reset(View v)
    {
        t1.setText("");
        t2.setText("");
        t3.setText("");

        t5.setText("");
        t6.setText("");
        t7.setText("Date Of Birth");
    }


    public boolean valid()
    {



        String uname=t1.getText().toString();
        if(uname.length() >= 5)
        {

        }
        else
        {
            Toast.makeText(getApplicationContext(), " username should be 5 characters or more...!!!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        String pred="", prem="";
        if(monthOfYear<10)
            prem="0";
        else
            prem="";
        if(dayOfMonth<10)
            pred="0";
        else
            pred="";
        t7.setText(year+"-"+prem+monthOfYear+"-"+pred+dayOfMonth);
    }

    public void showDOBPickerDialog(View v) {
        DialogFragment newFragment = new DOBPickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

}
