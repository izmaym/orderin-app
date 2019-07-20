package com.yustian.student.orderin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdminActivity extends AppCompatActivity {
    TextView btn_menu;
    TextView btn_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        btn_menu = (TextView) findViewById(R.id.btn_menu);
        btn_user = (TextView) findViewById(R.id.btn_user);

        btn_menu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(AdminActivity.this, AdminMenuActivity.class);
                startActivity(intent);
            }
        });

        btn_user.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(AdminActivity.this, AdminUserActivity.class);
                startActivity(intent);
            }
        });
    }
}
