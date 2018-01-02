package com.app.mybus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.regex.Pattern;

public class AReservActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static final String EXTRA_BID="com.app.mybus.BID";
    public static final String EXTRA_DOJ="com.app.mybus.DOJ";
    public static final String EXTRA_USER="com.app.mybus.USER";


    ListView l;
    String[] bus;
    String[] bid;
    String[] user;
    String[] seats;
    String[] doj;

    ACustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areserv);

        l = (ListView) findViewById(R.id.listView3);
        search();
    }

    public void search()
    {

        String k = "";
        k = ServletInterface.areserve();

        if(!k.equals("")) {
            retrieve(k);

            adapter = new ACustomAdapter(this, bid, user, seats, doj);
            l.setAdapter(adapter);
            l.setOnItemClickListener(this);
        }


    }

    public void retrieve(String data)
    {
        bus = data.split(Pattern.quote(" :: "));
        int n = bus.length;

        bid = new String[n];
        user = new String[n];
        seats= new String[n];
        doj = new String[n];


        for(int i=0;i<n;i++)
        {
            String[] temp=bus[i].split(Pattern.quote(" || "));
            bid[i]=temp[0];
            user[i]=temp[1];
            seats[i]=temp[2];
            doj[i]=temp[3];

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Intent intent = new Intent(this, AdetailActivity.class);

        TextView inbid = (TextView) view.findViewById(R.id.abid);
        intent.putExtra(EXTRA_BID, inbid.getText().toString());

        TextView indoj = (TextView) view.findViewById(R.id.adoj);
        intent.putExtra(EXTRA_DOJ, indoj.getText().toString());

        TextView inuser = (TextView) view.findViewById(R.id.auser);
        intent.putExtra(EXTRA_USER, inuser.getText().toString());

        startActivity(intent);
    }


}

class ACustomAdapter extends ArrayAdapter<String>
{
    Context context;

    String[] bid;
    String[] doj;
    String[] user;
    String[] seats;

    ACustomAdapter(Context c, String[] bid, String[] user, String[] nos, String[] doj)
    {
        super(c,R.layout.asingle_row,R.id.abid,bid);
        this.context=c;
        this.bid=bid;
        this.doj=doj;
        this.user=user;
        this.seats=nos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.asingle_row,parent,false);

        TextView tvbid = (TextView) row.findViewById(R.id.abid);
        TextView tvdoj = (TextView) row.findViewById(R.id.adoj);
        TextView tvseats = (TextView) row.findViewById(R.id.aseats);
        TextView tvuser= (TextView) row.findViewById(R.id.auser);

        tvbid.setText(bid[position]);
        tvuser.setText(user[position]);
        tvdoj.setText(doj[position]);
        tvseats.setText(seats[position]);

        return row;
    }

}





