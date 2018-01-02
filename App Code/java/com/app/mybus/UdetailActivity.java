package com.app.mybus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;
import java.util.regex.Pattern;

public class UdetailActivity extends AppCompatActivity {

    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11;
    String bid,doj,cname;
    int tot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udetail);

        t1=(TextView) findViewById(R.id.textView27);
        t2=(TextView) findViewById(R.id.textView26);
        t3=(TextView) findViewById(R.id.textView23);
        t4=(TextView) findViewById(R.id.textView25);
        t5=(TextView) findViewById(R.id.textView31);
        t6=(TextView) findViewById(R.id.textView30);
        t7=(TextView) findViewById(R.id.textView34);
        t8=(TextView) findViewById(R.id.textView35);
        t9=(TextView) findViewById(R.id.textView38);
        t10=(TextView) findViewById(R.id.textView39);
        t11=(TextView) findViewById(R.id.textView37);

        SharedPreferences p = getApplicationContext().getSharedPreferences("MyBus", Context.MODE_PRIVATE);
        cname=p.getString("uname","");
        Intent intent = getIntent();
        bid = intent.getStringExtra(UReservActivity.EXTRA_BID);
        doj = intent.getStringExtra(UReservActivity.EXTRA_DOJ);

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
            t3.setText(details[2]);
            t4.setText(details[3]);
            t5.setText(details[4]);
            t6.setText(details[5]+"/seat");
            t7.setText(doj);
            t8.setText(details[6]);
            t9.setText(details[7]);
            tot=Integer.parseInt(details[5])*Integer.parseInt(details[7]);
            t10.setText("Total: Rs. "+(tot));

            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            String[] date=doj.split(Pattern.quote("-"));
            int y=Integer.parseInt(date[0]);
            int m=Integer.parseInt(date[1]);
            int d=Integer.parseInt(date[2]);

            if(y>year || (y==year&&m>month) || (y==year&&m==month&&d>day) )
            {
                t11.setText("Cancel");
            }

        }
    }

    public void setcancel(View v)
    {
        ServletInterface.cancel(cname,bid,doj,tot+"");
        Intent in=new Intent(this,UReservActivity.class);
        startActivity(in);
        finish();
    }

}
