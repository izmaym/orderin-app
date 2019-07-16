package com.yustian.student.orderin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (username.getText().toString().equals("udin") && password.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Login Sukses", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
                    i.putExtra("username", username.getText().toString());
                    startActivity(i);
                } else if(username.getText().toString().equals("admin") && password.getText().toString().equals("")) {
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.putExtra("username", username.getText().toString());
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "Username atau Password Anda Salah", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
