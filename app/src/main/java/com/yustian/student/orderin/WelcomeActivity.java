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
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {
    TextView txt_username;
    Button btnSearch, btnMenu;
    EditText cari;
    String id, username;
    SharedPreferences sharedpreferences;

    public final static String TAG_USERNAME = "username";
    public final static String TAG_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnSearch = (Button)findViewById(R.id.btnSearch);
        btnMenu = (Button)findViewById(R.id.btnMenu);
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
                    if(cari.getText().toString().trim().length() > 0) {
                        Intent i = new Intent(getApplicationContext(), MenuInfoActivity.class);
                        i.putExtra(Configuration.CON_ID, cari.getText().toString());
                        startActivity(i);
                    } else {
                        cari.setHintTextColor(getResources().getColor(R.color.colorRed));
                        Toast.makeText(getApplicationContext() ,"Silahkan ketik apa yang ingin anda cari", Toast.LENGTH_LONG).show();
                    }
                }
            });
            btnMenu.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(), UserActivity.class);
                        i.putExtra(TAG_ID, id);
                        i.putExtra(TAG_USERNAME, username);
                        startActivity(i);
                }
            });
        }
    }
}
