package com.app.mybus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AlogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alog);
    }

    public void goadd(View v)
    {
        Intent in=new Intent(this,AdminActivity.class);
        startActivity(in);
    }

    public void viewbus(View v)
    {
        Intent in=new Intent(this,ABusActivity.class);
        startActivity(in);
    }

    public void viewres(View v)
    {
        Intent in=new Intent(this,AReservActivity.class);
        startActivity(in);
    }

    public void alogout(View v)
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

