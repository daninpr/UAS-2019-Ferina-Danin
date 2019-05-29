package com.ferinadanin.projectuas.activities.petugas;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.ferinadanin.projectuas.LoginActivity;
import com.ferinadanin.projectuas.Model.Pasien;
import com.ferinadanin.projectuas.R;
import com.ferinadanin.projectuas.helper.BottomNavigationViewHelper;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class PetugasListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private TextView textViewName;

    // [START define_database_reference]
    private DatabaseReference mDatabase;
    // [END define_database_reference]

    private FirebaseRecyclerAdapter<Pasien, PasienViewHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;
    private ItemClickListener onItemClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petugas_list);

        final String LOG_TAG = PetugasListActivity.class.getSimpleName() ;

        textViewName = findViewById(R.id.textUser);

        SharedPreferences prefs = getSharedPreferences("MyApp", MODE_PRIVATE);
        String username = prefs.getString("USERNAME", "UNKNOWN");

        textViewName.setText(username);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_home:
                        Intent intent0 = new Intent(getApplicationContext(), MainPetugas.class);
                        startActivity(intent0);
                        finish();
                        break;

                    case R.id.ic_list:
                        break;
                    case R.id.ic_logout:
                        showActionDialog();
                        break;
                }
                return false;

            }
            private void showActionDialog() {
                AlertDialog.Builder alert = new AlertDialog.Builder(PetugasListActivity.this);
                alert.setTitle("Perhatian")
                        .setMessage("Apakah Anda yakin untuk logout?")
                        .setPositiveButton("Logout", new DialogInterface.OnClickListener()                 {

                            public void onClick(DialogInterface dialog, int which) {
                                logout(); // Last step. Logout function
                            }
                        }).setNegativeButton("Batal", null);

                AlertDialog alert1 = alert.create();
                alert1.show();
            }
            private void logout() {
                Log.d(LOG_TAG, "Logout");
                startActivity(new Intent(PetugasListActivity.this, LoginActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mRecycler = findViewById(R.id.list_pasien);
        mRecycler.setHasFixedSize(true);

        mManager = new LinearLayoutManager(this);
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);

        // Set up FirebaseRecyclerAdapter with the Query
        Query query = getQuery(mDatabase);

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Pasien>()
                .setQuery(query, Pasien.class)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<Pasien, PasienViewHolder>(options) {
            @Override
            public PasienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new PasienViewHolder(inflater.inflate(R.layout.item_pasien, parent, false));
            }
            @Override
            protected void onBindViewHolder(@NonNull PasienViewHolder holder, int position, @NonNull final Pasien model) {
                holder.bindToPasien(model);
            }
        };

        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);
        mRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = 0;
                Pasien pasien = mAdapter.getItem(i);
                showUpdateDeleteDialog(pasien.getUsername(), pasien.getName());
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Pasien pasien = mAdapter.getItem(i);
        showUpdateDeleteDialog(pasien.getUsername(), pasien.getName());
    }

    private void showUpdateDeleteDialog(String username, String name) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setItemClickListener(ItemClickListener clickListener) {
        onItemClickListener = clickListener;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mAdapter != null) {
            mAdapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAdapter != null) {
            mAdapter.stopListening();
        }
    }

    private Query getQuery(DatabaseReference mDatabase){
        Query query = mDatabase.child("Pasien");
        return query;
    }
}
