package com.example.travel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.squareup.timessquare.CalendarPickerView;

import java.util.Calendar;
import java.util.Date;

public class date extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        Date today=new Date();
        Calendar nextYear=Calendar.getInstance();
        nextYear.add(Calendar.YEAR,1);

        CalendarPickerView datePicker=findViewById(R.id.calen);
        datePicker.init(today,nextYear.getTime()).withSelectedDate(today);

        datePicker.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {

                Calendar calSellect=Calendar.getInstance();
                calSellect.setTime(date);
                String selectDate=""+calSellect.get(Calendar.DAY_OF_MONTH)+"/"+(calSellect.get(Calendar.MONTH)+1)+"/"+calSellect.get(Calendar.YEAR);

                Toast.makeText(date.this, ""+selectDate, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });

    }
}
