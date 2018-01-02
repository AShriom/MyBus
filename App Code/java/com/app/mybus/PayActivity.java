package com.app.mybus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PayActivity extends AppCompatActivity {

    EditText t1,t2,t3;
    String doj,bid,fare;
    TextView b,f,d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        t1 = (EditText) findViewById(R.id.paynos);
        t2 = (EditText) findViewById(R.id.accno);
        t3 = (EditText) findViewById(R.id.paypwd);

        Intent intent = getIntent();
        bid = intent.getStringExtra(ReservActivity.EXTRA_BID);
        fare = intent.getStringExtra(ReservActivity.EXTRA_FARE);
        doj = intent.getStringExtra(ReservActivity.EXTRA_DOJ);
        b=(TextView) findViewById(R.id.textView12);
        b.setText("Bus ID: "+bid);
        f=(TextView) findViewById(R.id.textView13);
        f.setText("Fare per seat: "+fare);
        d=(TextView) findViewById(R.id.textView14);
        d.setText("Date of journey: "+doj);
    }

    public void pay(View v)
    {
        if (t1.getText().toString().equals("") || t2.getText().toString().equals("") || t3.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(), " Field(s) empty...!!!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            SharedPreferences p = getApplicationContext().getSharedPreferences("MyBus", Context.MODE_PRIVATE);
            String cname=p.getString("uname","");
            String k = "";
            k = ServletInterface.pay(t1.getText().toString(), t2.getText().toString(), t3.getText().toString(), doj, bid, fare, cname);

            if(valid()) {
                if (k.contains("1")) {
                    Intent in = new Intent(this, ReservActivity.class);
                    Toast.makeText(getApplicationContext(), "Reservation Successful...!!!", Toast.LENGTH_SHORT).show();
                    startActivity(in);
                    finish();
                } else if (k.contains("2")) {
                    Intent in = new Intent(this, ReservActivity.class);
                    Toast.makeText(getApplicationContext(), "Reservation Unsuccessful...!!!", Toast.LENGTH_SHORT).show();
                    startActivity(in);
                    finish();
                } else if (k.contains("0")) {
                    Toast.makeText(getApplicationContext(), " Wrong Account Credentials...!!!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), " Connection problem or Transaction failed...!!!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    public boolean valid()
    {

        String seats=t1.getText().toString();

            try{
                int temp=Integer.parseInt(seats);
            }
            catch(NumberFormatException e)
            {
                Toast.makeText(getApplicationContext(), " enter numbers only...!!!", Toast.LENGTH_SHORT).show();
                return false;
            }



        String accno=t2.getText().toString();
        if(accno.length() == 9)
        {
            try{
                int temp=Integer.parseInt(accno);
            }
            catch(NumberFormatException e)
            {
                Toast.makeText(getApplicationContext(), " account no. can only have numbers...!!!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), " account no. to be exactly 9 digit...!!!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
