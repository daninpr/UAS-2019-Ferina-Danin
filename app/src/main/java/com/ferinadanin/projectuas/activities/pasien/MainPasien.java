package com.ferinadanin.projectuas.activities.pasien;

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
import android.widget.TextView;


import com.ferinadanin.projectuas.LoginActivity;
import com.ferinadanin.projectuas.R;
import com.ferinadanin.projectuas.helper.BottomNavigationViewHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainPasien extends AppCompatActivity {
    private TextView dateDisplay, timeDisplay, textViewName;
    private Calendar calendar;
    private SimpleDateFormat dateFormat1, dateFormat2;
    private String date1, date2;

    private static final String LOG_TAG = MainPasien.class.getSimpleName() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pasien);

        textViewName = findViewById(R.id.textUser);

        SharedPreferences prefs = getSharedPreferences("MyApp", MODE_PRIVATE);
        String username = prefs.getString("USERNAME", "UNKNOWN");

        textViewName.setText(username);

        dateDisplay = findViewById(R.id.text_date_display);
        timeDisplay = findViewById(R.id.text_time_display);

        calendar = Calendar.getInstance();

        dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
        date1 = dateFormat1.format(calendar.getTime());
        dateDisplay.setText(date1);

        dateFormat2 = new SimpleDateFormat("HH:mm a");
        date2 = dateFormat2.format(calendar.getTime());
        timeDisplay.setText(date2);

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

                    case R.id.ic_logout:
                        showActionDialog();
                        break;
                }
                return false;

            }
            private void showActionDialog() {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainPasien.this);
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
                startActivity(new Intent(MainPasien.this, LoginActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });
    }
}
