package com.ferinadanin.projectuas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ferinadanin.projectuas.Model.Pasien;
import com.ferinadanin.projectuas.Model.Petugas;
import com.ferinadanin.projectuas.activities.PilihRegistrasi;
import com.ferinadanin.projectuas.activities.pasien.MainPasien;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference petugases, pasiens;

    EditText edtUsername, edtPassword;
    Button btnSignUp, btnSignIn;

    private RadioGroup radioGroup;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        database = FirebaseDatabase.getInstance();
        petugases = database.getReference("Petugas");
        pasiens = database.getReference("Pasien");

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);

        radioGroup = findViewById(R.id.radioGroupL);
        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(selectedId);

        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(edtUsername.getText().toString(), edtPassword.getText().toString());
            }
        });

        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vie) {
                Intent n = new Intent(getApplicationContext(), PilihRegistrasi.class);
                startActivity(n);
            }
        });
    }

    private void signIn(final String username, final String password){
        petugases.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(username).exists()){
                    if (!username.isEmpty()){
                        Petugas login = dataSnapshot.child(username).getValue(Petugas.class);

                        if(login.getPassword().equals(password)){
                            Intent s = new Intent(getApplicationContext(), MainPasien.class);
                            SharedPreferences preferences = getSharedPreferences("MyApp", MODE_PRIVATE);
                            preferences.edit().putString("USERNAME", edtUsername.getText().toString().trim()).commit();
                            startActivity(s);
                            Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(LoginActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        pasiens.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(username).exists()){
                    if (!username.isEmpty()){
                        Pasien login = dataSnapshot.child(username).getValue(Pasien.class);

                        if (login.getPassword().equals(password)){
                            Intent s = new Intent(getApplicationContext(), MainPasien.class);
                            SharedPreferences preferences = getSharedPreferences("MyApp", MODE_PRIVATE);
                            preferences.edit().putString("USERNAME", edtUsername.getText().toString().trim()).commit();
                            startActivity(s);
                            Toast.makeText(LoginActivity.this, "Login Seccess", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(LoginActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
