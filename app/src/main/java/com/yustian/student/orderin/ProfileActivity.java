package com.yustian.student.orderin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView txt_username;
    Button btnSearch;
    EditText cari;
    String id, username;
    SharedPreferences sharedpreferences;

    public final static String TAG_USERNAME = "username";
    public final static String TAG_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnSearch = (Button)findViewById(R.id.btnSearch);
        cari = (EditText)findViewById(R.id.cari);
        txt_username = (TextView)findViewById(R.id.txt_username);

        sharedpreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        id = getIntent().getStringExtra(TAG_ID);
        username = getIntent().getStringExtra(TAG_USERNAME);

        txt_username.setText("Selamat Datang, "+username);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            btnSearch.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (cari.getText().toString().equals("")) {
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        i.putExtra(TAG_ID, id);
                        i.putExtra(TAG_USERNAME, username);
                        startActivity(i);
                    } else {
                        Intent i = new Intent(getApplicationContext(), MenuInfoActivity.class);
                        i.putExtra(Konfigurasi.CON_ID, cari.getText().toString());
                        startActivity(i);
                    }
                }
            });
        }
    }
}
