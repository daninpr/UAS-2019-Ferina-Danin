package com.ferinadanin.projectuas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ferinadanin.projectuas.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference users;

    EditText edtMail, edtUsername, edtPassword;
    Button btnSignUp, btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");

        edtMail = findViewById(R.id.edtMail);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final User user = new User(edtUsername.getText().toString(),
                        edtPassword.getText().toString(),
                        edtMail.getText().toString());

                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(user.getUsername()).exists())
                            Toast.makeText(RegisterActivity.this, "username sudah ada", Toast.LENGTH_SHORT).show();
                        else {
                            users.child(user.getUsername()).setValue(user);
                            Toast.makeText(RegisterActivity.this, "berhasil mendaftar", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {


                    }
                });
            }
        });

        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(s);
            }
        });
    }
}
