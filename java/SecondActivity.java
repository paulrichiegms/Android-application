package com.example.travel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private ListView listView;
    DatabaseReference databaseReference;
    private List<Members> membersList;
    private Adapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        databaseReference= FirebaseDatabase.getInstance().getReference("members");
        membersList=new ArrayList<>();

        adapter=new Adapter(SecondActivity.this,membersList);

        listView=findViewById(R.id.listview);

    }
    protected void  onStart(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Members members=dataSnapshot1.getValue(Members.class);
                    membersList.add(members);
                }
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        super.onStart();
        Toast.makeText(SecondActivity.this, "Successful Booking \n Take ScreenShot", Toast.LENGTH_SHORT).show();
    }
}
