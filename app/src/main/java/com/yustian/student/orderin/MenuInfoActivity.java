package com.yustian.student.orderin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MenuInfoActivity extends AppCompatActivity implements
        View.OnClickListener{

    private TextView editTextName;
    private TextView editTextNumber;

    private Button buttonAdd;
    private String id;
    private String meja;
    private String transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_info);

        Intent intent = getIntent();
        id = intent.getStringExtra(Configuration.CON_ID);
        meja = intent.getStringExtra(Configuration.TAG_ID_TABLE);
        transaction = intent.getStringExtra(Configuration.TAG_ID_TRANSACTION);

        editTextName = (TextView) findViewById(R.id.editTextName);
        editTextNumber = (TextView) findViewById(R.id.editTextNumber);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(this);

        getContact();
    }

    private void getContact(){
        class GetContact extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading =
                        ProgressDialog.show(MenuInfoActivity.this,"Mencari...","Silahkan Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showContact(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Configuration.URL_GET,id);
                return s;
            }
        }

        GetContact ge = new GetContact();
        ge.execute();
    }

    private void showContact(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Configuration.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);

            String name = c.getString(Configuration.TAG_NAME);
            String number = c.getString(Configuration.TAG_NUMBER);

            editTextName.setText(name);
            editTextNumber.setText(number);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void addContact() {
        final String name = editTextName.getText().toString().trim();
        final String number = editTextNumber.getText().toString().trim();

        class AddContact extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MenuInfoActivity.this,"Menambahkan...", "Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MenuInfoActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Configuration.KEY_ID_TRANSACTION, transaction);
                params.put(Configuration.KEY_NAME,name);
                params.put(Configuration.KEY_NUMBER,number);
                params.put(Configuration.KEY_ID_TABLE, meja);
                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Configuration.URL_ORDER, params);
                return res;
            }
        }
        AddContact ae = new AddContact();
        ae.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonAdd){
            if(meja.length() > 0) {
                addContact();
            } else {
                Toast.makeText(MenuInfoActivity.this,"Silahkan pilih meja terlebih dahulu",Toast.LENGTH_LONG).show();
            }
        }
    }

}

