package com.ferinadanin.projectuas.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ferinadanin.projectuas.R;
import com.ferinadanin.projectuas.activities.pasien.RegisterPasien;
import com.ferinadanin.projectuas.activities.petugas.RegisterPetugas;


public class PilihRegistrasi extends AppCompatActivity {
    Button regPetugas, regPasien;
    private String m_Text = "";
    private NestedScrollView nestedScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_registrasi);

        regPetugas = findViewById(R.id.btnPetugas);
        regPasien = findViewById(R.id.btnPasien);

        regPetugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegister = new Intent(PilihRegistrasi.this, RegisterPetugas.class);
                startActivity(intentRegister);
            }

        });

        regPasien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegister = new Intent(PilihRegistrasi.this, RegisterPasien.class);
                startActivity(intentRegister);
            }
        });
    }
}
