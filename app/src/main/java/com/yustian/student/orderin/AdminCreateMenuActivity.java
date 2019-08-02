package com.yustian.student.orderin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.HashMap;

public class AdminCreateMenuActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextKategori;
    private EditText editTextName;
    private EditText editTextNumber;
    private EditText editTextStok;

    private Button buttonAdd;
    private Button buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_menu);

        editTextKategori = (EditText) findViewById(R.id.editTextKategori);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextNumber = (EditText) findViewById(R.id.editTextNumber);
        editTextStok = (EditText) findViewById(R.id.editTextStok);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonView = (Button) findViewById(R.id.buttonView);

        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);
    }
    private void addContact() {
        final String kategori = editTextKategori.getText().toString().trim();
        final String name = editTextName.getText().toString().trim();
        final String number = editTextNumber.getText().toString().trim();
        final String stok = editTextStok.getText().toString().trim();

        class AddContact extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(AdminCreateMenuActivity.this,"Menambahkan...",
                        "Tunggu...",false,false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(AdminCreateMenuActivity.this,s,Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Configuration.KEY_KATEGORI,kategori);
                params.put(Configuration.KEY_NAME,name);
                params.put(Configuration.KEY_NUMBER,number);
                params.put(Configuration.KEY_STOK, stok);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Configuration.URL_ADD, params);
                return res;
            }
        }
        AddContact ae = new AddContact();
        ae.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonAdd){
            addContact();
        }
        if(v == buttonView){
            startActivity(new Intent(this, AdminMenuActivity.class));
        }
    }
}