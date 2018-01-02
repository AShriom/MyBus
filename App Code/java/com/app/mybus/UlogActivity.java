package com.app.mybus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UlogActivity extends AppCompatActivity {

    TextView t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ulog);
        t=(TextView) findViewById(R.id.textView15);
        SharedPreferences p = getApplicationContext().getSharedPreferences("MyBus", Context.MODE_PRIVATE);
        String cname=p.getString("uname","");
        t.setText("Welcome!!! "+cname);
    }

    public void gosearch(View v)
    {
        Intent in=new Intent(this,ReservActivity.class);
        startActivity(in);
    }

    public void resview(View v)
    {
        Intent in=new Intent(this,UReservActivity.class);
        startActivity(in);
    }

    public void ulogout(View v)
    {
        SharedPreferences p = getApplicationContext().getSharedPreferences("MyBus", Context.MODE_PRIVATE);
        SharedPreferences.Editor e = p.edit();
        e.putString("uname", "");
        e.apply();
        Intent in=new Intent(this,MainActivity.class);
        startActivity(in);
        finish();
    }

}
