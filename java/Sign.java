package com.example.travel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class Sign extends AppCompatActivity {





    EditText txtEmail, txtPass, txtCpass;
    Button btn;
    ProgressBar progressBar;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        getSupportActionBar().setTitle("Signup Form");

        txtEmail=(EditText) findViewById(R.id.txtEmail);
        txtPass=(EditText)findViewById(R.id.txtPass);
        txtCpass=(EditText)findViewById(R.id.txtCpass);
        btn=(Button)findViewById(R.id.btn);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);


        auth=FirebaseAuth.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email =txtEmail.getText().toString().trim();
                String pass=txtPass.getText().toString().trim();
                String cpass=txtCpass.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Sign.this, "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(Sign.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(cpass)){
                    Toast.makeText(Sign.this, "Please enter password again here", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(pass.length()<6){
                    Toast.makeText(Sign.this, " too short", Toast.LENGTH_SHORT).show();
                    return;

                }

                progressBar.setVisibility(View.VISIBLE);

                if(pass.equals(cpass)){
                    auth.createUserWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(Sign.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        Toast.makeText(Sign.this, "Authentication Successful", Toast.LENGTH_SHORT).show();


                                    } else {

                                        Toast.makeText(Sign.this, "Authentication Failed", Toast.LENGTH_SHORT).show();

                                    }

                                    // ...
                                }
                            });

                }

            }
        });
    }
}