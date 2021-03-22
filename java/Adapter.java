package com.example.travel;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter extends ArrayAdapter<Members> {

    private Activity context;
    private List<Members> memberlist;

    public Adapter(Activity context,List<Members> memberlist) {
        super(context, R.layout.sample_layout, memberlist);
        this.context = context;
        this.memberlist = memberlist;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater=context.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.sample_layout,null,true);
        Members members=memberlist.get(position);
        TextView t6=view.findViewById(R.id.tv6);
        TextView t1=view.findViewById(R.id.tv1);
        TextView t2=view.findViewById(R.id.tv2);
        TextView t3=view.findViewById(R.id.tv3);
        TextView t4=view.findViewById(R.id.tv4);
        TextView t5=view.findViewById(R.id.tv5);
        TextView t7=view.findViewById(R.id.tv7);


        t6.setText("Name: "+members.getName());
        t7.setText("Phone: "+members.getPhone());
        t1.setText("From: "+members.getFrom());
        t2.setText("To: "+members.getTo());

        t3.setText("Date: "+members.getDate());
        t4.setText("Time: "+members.getTime());
        t5.setText("Seat: "+members.getSeats());


        return view;
    }
}
