package com.ferinadanin.projectuas.activities.pasien;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ferinadanin.projectuas.LoginActivity;
import com.ferinadanin.projectuas.R;
import com.ferinadanin.projectuas.Model.Pasien;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterPasien extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference pasiens;

    EditText edtName, edtAlamat, edtUsername, edtPassword, edtPhone;
    Button btnSignUp, btnSignIn;
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi_pasien);

        database = FirebaseDatabase.getInstance();
        pasiens = database.getReference("Pasien");

        edtName = findViewById(R.id.edtName);
        edtAlamat = findViewById(R.id.edtAlamat);
        edtPhone = findViewById(R.id.edtPhone);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        radioGroup = findViewById(R.id.radioGroupL);

        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(selectedId);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Pasien pasien = new Pasien(
                        edtName.getText().toString(),
                        edtAlamat.getText().toString(),
                        edtPhone.getText().toString(),
                        edtUsername.getText().toString(),
                        edtPassword.getText().toString(),
                        radioButton.getText().toString()
                        );

                pasiens.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(pasien.getUsername()).exists())
                            Toast.makeText(RegisterPasien.this, "Username Sudah Ada", Toast.LENGTH_SHORT).show();
                        else {
                            pasiens.child(pasien.getUsername()).setValue(pasien);
                            Toast.makeText(RegisterPasien.this, "Berhasil Mendaftar", Toast.LENGTH_SHORT).show();
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
