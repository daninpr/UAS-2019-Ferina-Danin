package com.ferinadanin.projectuas.activities.petugas;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ferinadanin.projectuas.Model.Pasien;
import com.ferinadanin.projectuas.R;


public class PasienViewHolder extends RecyclerView.ViewHolder  {
    public TextView textViewName, textViewAlamat, textViewTelp, textViewUsername, textViewPassword;

    public PasienViewHolder(View itemView) {
        super(itemView);
        textViewName = itemView.findViewById(R.id.textViewName);
        textViewTelp = itemView.findViewById(R.id.textViewTelp);
        textViewAlamat = itemView.findViewById(R.id.textViewAlamat);
        textViewUsername = itemView.findViewById(R.id.textViewUsername);
        textViewPassword = itemView.findViewById(R.id.textViewPassword);
    }

    public void bindToPasien(Pasien pasien){
        textViewName.setText(pasien.name);
        textViewTelp.setText(pasien.telp);
        textViewAlamat.setText(pasien.alamat);
        textViewUsername.setText(pasien.username);
        textViewPassword.setText(pasien.password);
    }
}
