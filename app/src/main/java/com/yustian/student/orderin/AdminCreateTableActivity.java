package com.yustian.student.orderin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class AdminCreateTableActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextID;
    private EditText editTextTable;

    private Button buttonAdd;
    private Button buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_table);

        editTextID = (EditText) findViewById(R.id.editTextID);
        editTextTable = (EditText) findViewById(R.id.editTextTable);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonView = (Button) findViewById(R.id.buttonView);

        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);
    }

    private void addContact() {
        final String id_meja = editTextID.getText().toString().trim();
        final String no_meja = editTextTable.getText().toString().trim();

        class AddContact extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(AdminCreateTableActivity.this,"Menambahkan...",
                        "Tunggu...",false,false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(AdminCreateTableActivity.this,s,Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Configuration.KEY_ID_TABLE,id_meja);
                params.put(Configuration.KEY_NUMBER_TABLE,no_meja);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Configuration.URL_ADD_TABLE, params);
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
            startActivity(new Intent(this, AdminTableActivity.class));
        }
    }
}
