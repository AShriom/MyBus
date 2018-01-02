package com.app.mybus;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class UReservActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static final String EXTRA_BID="com.app.mybus.BID";
    public static final String EXTRA_DOJ="com.app.mybus.DOJ";


    ListView l;
    String[] bus;
    String[] bid;
    String[] seats;
    String[] doj;

    UCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ureserv);

        l = (ListView) findViewById(R.id.listView2);
        search();
    }

    public void search()
    {
        SharedPreferences p = getApplicationContext().getSharedPreferences("MyBus", Context.MODE_PRIVATE);
        String cname=p.getString("uname","");
            String k = "";
            k = ServletInterface.ureserve(cname);

            if(!k.equals("")) {
                retrieve(k);

                adapter = new UCustomAdapter(this, bid, seats, doj);
                l.setAdapter(adapter);
                l.setOnItemClickListener(this);
            }


    }

    public void retrieve(String data)
    {
        bus = data.split(Pattern.quote(" :: "));
        int n = bus.length;

        bid = new String[n];
        seats= new String[n];
        doj = new String[n];


        for(int i=0;i<n;i++)
        {
            String[] temp=bus[i].split(Pattern.quote(" || "));
            bid[i]=temp[0];
            seats[i]=temp[1];
            doj[i]=temp[2];

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Intent intent = new Intent(this, UdetailActivity.class);

        TextView inbid = (TextView) view.findViewById(R.id.ubid);
        intent.putExtra(EXTRA_BID, inbid.getText().toString());

        TextView indoj = (TextView) view.findViewById(R.id.udoj);
        intent.putExtra(EXTRA_DOJ, indoj.getText().toString());

        startActivity(intent);
    }


}

class UCustomAdapter extends ArrayAdapter<String>
{
    Context context;

    String[] bid;
    String[] doj;
    String[] seats;

    UCustomAdapter(Context c, String[] bid, String[] nos, String[] doj)
    {
        super(c,R.layout.usingle_row,R.id.ubid,bid);
        this.context=c;
        this.bid=bid;
        this.doj=doj;
        this.seats=nos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.usingle_row,parent,false);

        TextView tvbid = (TextView) row.findViewById(R.id.ubid);
        TextView tvdoj = (TextView) row.findViewById(R.id.udoj);
        TextView tvseats = (TextView) row.findViewById(R.id.useats);

        tvbid.setText(bid[position]);
        tvdoj.setText(doj[position]);
        tvseats.setText(seats[position]);

        return row;
    }

}








