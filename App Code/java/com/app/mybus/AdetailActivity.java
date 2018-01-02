package com.app.mybus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.regex.Pattern;

public class AdetailActivity extends AppCompatActivity {

    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11;
    String bid,doj,cname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adetail);

        t1=(TextView) findViewById(R.id.adbid);
        t2=(TextView) findViewById(R.id.adbname);
        t3=(TextView) findViewById(R.id.aduser);
        t4=(TextView) findViewById(R.id.adsrc);
        t5=(TextView) findViewById(R.id.addest);
        t6=(TextView) findViewById(R.id.adcat);
        t7=(TextView) findViewById(R.id.adfare);
        t8=(TextView) findViewById(R.id.addate);
        t9=(TextView) findViewById(R.id.adtime);
        t10=(TextView) findViewById(R.id.adbseats);
        t11=(TextView) findViewById(R.id.adtfare);

        Intent intent = getIntent();
        bid = intent.getStringExtra(AReservActivity.EXTRA_BID);
        doj = intent.getStringExtra(AReservActivity.EXTRA_DOJ);
        cname=intent.getStringExtra(AReservActivity.EXTRA_USER);
        getdetail();
    }

    public void getdetail()
    {
        String k = "";
        k = ServletInterface.udetail(cname,bid,doj);

        if(!k.equals(""))
        {
            String[] details=k.split(Pattern.quote(" :: "));
            t1.setText(details[0]);
            t2.setText(details[1]);
            t3.setText("user: "+cname);
            t4.setText(details[2]);
            t5.setText(details[3]);
            t6.setText(details[4]);
            t7.setText("Rs."+details[5]+"/seat");
            t8.setText(doj);
            t9.setText(details[6]);
            t10.setText("Seats: "+details[7]);
            int tot=Integer.parseInt(details[5])*Integer.parseInt(details[7]);
            t11.setText("Total: Rs. "+(tot));

        }
    }





}
