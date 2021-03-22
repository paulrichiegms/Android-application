package com.example.travel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ThirdActivity extends AppCompatActivity {

    EditText txtdel,name;
    Button btndel;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

//        txtdel=findViewById(R.id.edtcancel);
        btndel=findViewById(R.id.btnfinaldelete);
        name=findViewById(R.id.name);

//        final String id=txtdel.getText().toString().trim();



        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                databaseReference= FirebaseDatabase.getInstance().getReference("members").child(id);
//                databaseReference.child("members").child(id).removeValue();
                queryDeleteName();


            }
        });


    }

    private void queryDeleteName(){
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("members");
        String name1=name.getText().toString().trim();
        if (name1.matches("")) {
            Toast.makeText(this, "You did not enter a Name", Toast.LENGTH_SHORT).show();
            return;
        }
        Query query=databaseReference.orderByChild("name").equalTo(name1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        Members members=snapshot.getValue(Members.class);
                        snapshot.getRef().removeValue();
                        Toast.makeText(ThirdActivity.this, "Booking Cancelled \n We will Contact you for refund", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(ThirdActivity.this, "Name not Found \n Contact Our Office", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void clickexit(View v){
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
