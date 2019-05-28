package com.ferinadanin.projectuas.activities.petugas;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ferinadanin.projectuas.LoginActivity;
import com.ferinadanin.projectuas.Message;
import com.ferinadanin.projectuas.R;
import com.ferinadanin.projectuas.helper.BottomNavigationViewHelper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class MainPetugas extends AppCompatActivity {
    private TextView dateDisplay, timeDisplay, textViewName, kodeAntri;
    private Calendar calendar;
    private SimpleDateFormat dateFormat1, dateFormat2;
    private String date1, date2;
    Button btnCode;

    FirebaseDatabase database;
    DatabaseReference myRef;

    String dataTitle, dataMessage, dataName;
    EditText title, message, name;

    private static final String LOG_TAG = MainPetugas.class.getSimpleName() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_petugas);

        textViewName = findViewById(R.id.textUser);

        SharedPreferences prefs = getSharedPreferences("MyApp", MODE_PRIVATE);
        String username = prefs.getString("USERNAME", "UNKNOWN");

        textViewName.setText(username);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_home:

                        break;

                    case R.id.ic_list:
//                        Intent intent0 = new Intent(getApplicationContext(), PetugasListActivity.class);
//                        startActivity(intent0);
//                        finish();
                        break;
                    case R.id.ic_logout:
                        showActionDialog();
                        break;
                }
                return false;

            }
            private void showActionDialog() {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainPetugas.this);
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
                startActivity(new Intent(MainPetugas.this, LoginActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });

        dateDisplay = findViewById(R.id.text_date_display);
        timeDisplay = findViewById(R.id.text_time_display);

        calendar = Calendar.getInstance();

        dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
        date1 = dateFormat1.format(calendar.getTime());
        dateDisplay.setText(date1);

        dateFormat2 = new SimpleDateFormat("HH:mm a");
        date2 = dateFormat2.format(calendar.getTime());
        timeDisplay.setText(date2);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("antrian");

        title = findViewById(R.id.title);
        //message = findViewById(R.id.kode);
        name = findViewById(R.id.name_antri);
        title.setText("Antrian TekQue");

        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                if (key.equals("title")) {
                    dataTitle=(String)getIntent().getExtras().get(key);
                }
                if (key.equals("message")) {
                    dataMessage = (String)getIntent().getExtras().get(key);
                }
                if (key.equals("name")) {
                    dataName = (String)getIntent().getExtras().get(key);
                }
            }
            showAlertDialog();
        }

        btnCode = findViewById(R.id.btnCode);
        kodeAntri = findViewById(R.id.kodeAntri);

        btnCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kodeAntri.setText(getRandomString(4));
            }
        });

    }

    public static String getRandomString(int i) {
        final String characters = "1234567890";
        StringBuilder result = new StringBuilder();
        while(i > 0){
            Random rand = new Random();
            result.append(characters.charAt(rand.nextInt(characters.length())));
            i--;
        }
        return result.toString();
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pesan Notifikasi");
        builder.setMessage("Nama : " + dataName + ".\n" + "Kode Antrian " + dataMessage + ", 10 menit lagi");
        builder.setPositiveButton("OK", null);
        builder.show();
    }

    public void subscribeToTopic(View view) {
        FirebaseMessaging.getInstance().subscribeToTopic("notifications");
        Toast.makeText(this, "Subscribed to Notifications", Toast.LENGTH_SHORT).show();
    }

    public void sendMessage(View view) {
        myRef.push().setValue(new Message(title.getText().toString(), kodeAntri.getText().toString(),name.getText().toString()));
        Toast.makeText(this, "Notifikasi Sent", Toast.LENGTH_SHORT).show();
    }

}
