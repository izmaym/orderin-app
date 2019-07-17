package com.yustian.student.orderin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView username;
    Button btnSearch;
    EditText cari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnSearch = (Button)findViewById(R.id.btnSearch);
        cari = (EditText)findViewById(R.id.cari);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String someVariable = extras.getString("username");
            username = (TextView)findViewById(R.id.username);
            username.setText("Selamat Datang, "+someVariable);

            btnSearch.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (cari.getText().toString().equals("")) {
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
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
