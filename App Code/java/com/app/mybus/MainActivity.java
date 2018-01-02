package com.app.mybus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText t1,t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = (EditText) findViewById(R.id.liuname);
        t2 = (EditText) findViewById(R.id.lipwd);
    }

    public void login(View v)
    {
        if (t1.getText().toString().equals("") || t2.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(), " Field(s) empty...!!!", Toast.LENGTH_SHORT).show();
        }
        else {

            String k = "";
            k = ServletInterface.login(t1.getText().toString(), t2.getText().toString());

            if (t1.getText().toString().equals("admin")&&t2.getText().toString().equals("admin")) {
                Intent in = new Intent(this, AlogActivity.class);
                SharedPreferences p = getApplicationContext().getSharedPreferences("MyBus", Context.MODE_PRIVATE);
                SharedPreferences.Editor e = p.edit();
                e.putString("uname", t1.getText().toString());
                e.apply();
                Toast.makeText(getApplicationContext(), "Login Successful...!!!", Toast.LENGTH_SHORT).show();
                startActivity(in);
                finish();
            } else if (k.contains("1")) {

                Intent in = new Intent(this, UlogActivity.class);
                SharedPreferences p = getApplicationContext().getSharedPreferences("MyBus", Context.MODE_PRIVATE);
                SharedPreferences.Editor e = p.edit();
                e.putString("uname", t1.getText().toString());
                e.apply();
                Toast.makeText(getApplicationContext(), "Login Successful...!!!", Toast.LENGTH_SHORT).show();
                startActivity(in);
                finish();
            } else if (k.contains("0")) {
                Toast.makeText(getApplicationContext(), " Wrong Username or Password...!!!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), " Connection problem or Username not registered...!!!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void gosign(View v)
    {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

}
