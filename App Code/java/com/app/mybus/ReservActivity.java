package com.app.mybus;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;



public class ReservActivity extends FragmentActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemClickListener {

    public static final String EXTRA_BID="com.app.mybus.BID";
    public static final String EXTRA_FARE="com.app.mybus.FARE";
    public static final String EXTRA_DOJ="com.app.mybus.DOJ";

    EditText t1,t2;
    TextView t3;
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
        setContentView(R.layout.activity_reserv);

        t1 = (EditText) findViewById(R.id.ressrc);
        t2 = (EditText) findViewById(R.id.resdest);
        t3 = (TextView) findViewById(R.id.dojr);
        l = (ListView) findViewById(R.id.listView);
    }

    public void search(View v)
    {
        if (t1.getText().toString().equals("") || t2.getText().toString().equals("") || t3.getText().toString().equals("Date of Journey (yyyy-mm-dd)"))
        {
            Toast.makeText(getApplicationContext(), " Field(s) empty...!!!", Toast.LENGTH_SHORT).show();
        }
        else
        {

                String k = "";
                k = ServletInterface.reserve(t1.getText().toString(), t2.getText().toString(), t3.getText().toString());

                if(!k.equals("")) {
                    retrieve(k);

                    adapter = new CustomAdapter(this, bid, bname, time, fare, nos);
                    l.setAdapter(adapter);
                    l.setOnItemClickListener(this);
                }

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Intent intent = new Intent(this, PayActivity.class);

        TextView inbid = (TextView) view.findViewById(R.id.bid);
        intent.putExtra(EXTRA_BID, inbid.getText().toString());

        TextView infare = (TextView) view.findViewById(R.id.fare);
        intent.putExtra(EXTRA_FARE, infare.getText().toString());

        TextView indoj = (TextView) findViewById(R.id.dojr);
        intent.putExtra(EXTRA_DOJ, indoj.getText().toString());

        startActivity(intent);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
    {

        String pred="", prem="";
        if(monthOfYear<10)
            prem="0";
        else
            prem="";
        if(dayOfMonth<10)
            pred="0";
        else
            pred="";
        t3.setText(year+"-"+prem+monthOfYear+"-"+pred+dayOfMonth);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}

class CustomAdapter extends ArrayAdapter<String>
{
    Context context;

    String[] bid;
    String[] bname;
    String[] time;
    String[] fare;
    String[] seats;

    CustomAdapter(Context c, String[] bid, String[] bname, String[] time, String[] fare, String[] nos)
    {
        super(c,R.layout.single_row,R.id.bid,bid);
        this.context=c;
        this.bid=bid;
        this.bname=bname;
        this.time=time;
        this.fare=fare;
        this.seats=nos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.single_row,parent,false);

        TextView tvbid = (TextView) row.findViewById(R.id.bid);
        TextView tvbname = (TextView) row.findViewById(R.id.bname);
        TextView tvtime = (TextView) row.findViewById(R.id.time);
        TextView tvfare = (TextView) row.findViewById(R.id.fare);
        TextView tvseats = (TextView) row.findViewById(R.id.seats);

        tvbid.setText(bid[position]);
        tvbname.setText(bname[position]);
        tvtime.setText(time[position]);
        tvfare.setText(fare[position]);
        tvseats.setText(seats[position]);

        return row;
    }

}
