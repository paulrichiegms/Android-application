package com.example.travel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    String[] fromNames,toNames,toNames1,toNames2,toNames3, aDate,aTime,aSeats,aSeats2,aSeats3;

    private Button saveBtn,pbtn,delb,calb;

    private EditText nEdt,pEdt;
    public Spinner spinner,spinner1,spinner2,spinner3,spinner4;

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseReference= FirebaseDatabase.getInstance().getReference("members");

        fromNames=getResources().getStringArray(R.array.From);
        toNames=getResources().getStringArray(R.array.To);
        toNames1=getResources().getStringArray(R.array.To1);
        toNames2=getResources().getStringArray(R.array.To2);
        toNames3=getResources().getStringArray(R.array.To3);
        aDate=getResources().getStringArray(R.array.Date);
        aTime=getResources().getStringArray(R.array.Time);
        aSeats=getResources().getStringArray(R.array.AvailableSeats);
        aSeats2=getResources().getStringArray(R.array.AvailableSeats1);
        aSeats3=getResources().getStringArray(R.array.AvailableSeats2);



        spinner=(Spinner) findViewById(R.id.spin1);
        spinner1=(Spinner)findViewById(R.id.spin2);
        spinner2=(Spinner)findViewById(R.id.spin3);
        spinner3=(Spinner)findViewById(R.id.spin4);
        spinner4=(Spinner)findViewById(R.id.spin5);


        saveBtn=findViewById(R.id.btn);

        pbtn=findViewById(R.id.btnp);
        nEdt=findViewById(R.id.edtname);
        delb=findViewById(R.id.btndl);
        calb=findViewById(R.id.btncal);
        pEdt=findViewById(R.id.edtphone);


        pbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Paypal.class);
                startActivity(intent);
            }
        });
        calb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,date.class);
                startActivity(intent);
            }
        });



        ArrayAdapter<String> adapter=new ArrayAdapter<>(MainActivity.this, R.layout.sample_view,R.id.txtv,fromNames);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               String itemSelect= fromNames[i];


               if(i==0){
                   ArrayAdapter<String> adapter1=new ArrayAdapter<>(MainActivity.this, R.layout.sample_view,R.id.txtv,toNames);
                   spinner1.setAdapter(adapter1);

               }
               if(i==1){
                   ArrayAdapter<String> adapter2=new ArrayAdapter<>(MainActivity.this, R.layout.sample_view,R.id.txtv,toNames1);
                   spinner1.setAdapter(adapter2);

               }
               if(i==2){
                   ArrayAdapter<String> adapter3=new ArrayAdapter<>(MainActivity.this, R.layout.sample_view,R.id.txtv,toNames2);
                   spinner1.setAdapter(adapter3);
               }
               if(i==3){
                   ArrayAdapter<String> adapter4=new ArrayAdapter<>(MainActivity.this, R.layout.sample_view,R.id.txtv,toNames3);
                   spinner1.setAdapter(adapter4);
               }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ArrayAdapter<String> adapter5=new ArrayAdapter<>(MainActivity.this, R.layout.sample_view,R.id.txtv,aDate);
        spinner2.setAdapter(adapter5);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemSelect= aDate[i];


                if(i==0){
                    ArrayAdapter<String> adapter1=new ArrayAdapter<>(MainActivity.this, R.layout.sample_view,R.id.txtv,aSeats);
                    spinner4.setAdapter(adapter1);

                }
                if(i==1){
                    ArrayAdapter<String> adapter2=new ArrayAdapter<>(MainActivity.this, R.layout.sample_view,R.id.txtv,aSeats2);
                    spinner4.setAdapter(adapter2);

                }
                if(i==2){
                    ArrayAdapter<String> adapter3=new ArrayAdapter<>(MainActivity.this, R.layout.sample_view,R.id.txtv,aSeats3);
                    spinner4.setAdapter(adapter3);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> adapter6=new ArrayAdapter<>(MainActivity.this, R.layout.sample_view,R.id.txtv,aTime);
        ArrayAdapter<String> adapter7=new ArrayAdapter<>(MainActivity.this, R.layout.sample_view,R.id.txtv,aSeats);





        spinner3.setAdapter(adapter6);
        spinner4.setAdapter(adapter7);


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String seats=spinner4.getSelectedItem().toString().trim();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                Query query = reference.child("members").orderByChild("seats").equalTo(seats);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {

                            // dataSnapshot is the "issue" node with all children with id 0
                            for (DataSnapshot members : dataSnapshot.getChildren()) {
                                // do something with the individual "issues"
                                Toast.makeText(MainActivity.this, "Seat Taken", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        else{
                            saveData();

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });









            }
        });

        delb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ThirdActivity.class);
                startActivity(intent);

            }
        });

    }

    public void saveData(){


        String from=spinner.getSelectedItem().toString().trim();
        String to=spinner1.getSelectedItem().toString().trim();
        String date=spinner2.getSelectedItem().toString().trim();
        String time=spinner3.getSelectedItem().toString().trim();
        String seats=spinner4.getSelectedItem().toString().trim();

        String phone=pEdt.getText().toString().trim();
        String name=nEdt.getText().toString().trim();

        if (name.matches("")) {
            Toast.makeText(this, "You did not enter a username", Toast.LENGTH_SHORT).show();
            return;
        }
        if(name.matches("")){
            Toast.makeText(this, "You did not enter a phone number", Toast.LENGTH_SHORT).show();
            return;
        }
        if (phone.length()<11 || phone.length()>11){
            Toast.makeText(this, "Enter valid phone number length", Toast.LENGTH_SHORT).show();
            return;
        }


        Random rand = new Random();
        int n = rand.nextInt(50);
        Members members=new Members(name,phone,from,to,date,time,seats);
        databaseReference.child(String.valueOf(n)).setValue(members);
        String key= databaseReference.child(String.valueOf(n)).getKey();
        Toast.makeText(MainActivity.this, "Your Booking ID: "+key, Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, "Successful ", Toast.LENGTH_SHORT).show();


    }

    public void onProcess(View v){

        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:23.8103,90.4125"));
        Intent chooser=Intent.createChooser(intent,"Launch Maps");
        startActivity(chooser);

    }


}
