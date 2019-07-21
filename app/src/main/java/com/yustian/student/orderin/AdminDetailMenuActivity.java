package com.yustian.student.orderin;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class AdminDetailMenuActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editTextKategori;
    private EditText editTextName;
    private EditText editTextNumber;
    private EditText editTextStok;
    private Button buttonUpdate;
    private Button buttonDelete;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_detail_menu);
        Intent intent = getIntent();

        id = intent.getStringExtra(Configuration.CON_ID);
        Toast.makeText(getApplicationContext() ,id, Toast.LENGTH_LONG).show();
        editTextKategori = (EditText) findViewById(R.id.editTextKategori);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextNumber = (EditText) findViewById(R.id.editTextNumber);
        editTextStok = (EditText) findViewById(R.id.editTextStok);

        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        getContact();
    }
    private void getContact(){
        class GetContact extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading =
                        ProgressDialog.show(AdminDetailMenuActivity.this,"Fetching...","Wait...",false,false);
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
    private void updateContact(){
        final String kategori = editTextKategori.getText().toString().trim();
        final String name = editTextName.getText().toString().trim();
        final String number = editTextNumber.getText().toString().trim();
        final String stok = editTextStok.getText().toString().trim();

        class UpdateContact extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading =
                        ProgressDialog.show(AdminDetailMenuActivity.this,"Updating...","Wait...",false,false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(AdminDetailMenuActivity.this,s,Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Configuration.KEY_ID,id);
                hashMap.put(Configuration.KEY_KATEGORI,kategori);
                hashMap.put(Configuration.KEY_NAME,name);
                hashMap.put(Configuration.KEY_NUMBER,number);
                hashMap.put(Configuration.KEY_STOK, stok);
                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Configuration.URL_UPDATE,hashMap);
                return s;
            }
        }
        UpdateContact ue = new UpdateContact();
        ue.execute();
    }

    private void deleteContact(){
        class DeleteContact extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(AdminDetailMenuActivity.this, "Updating...",
                        "Wait...", false, false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(AdminDetailMenuActivity.this, s, Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Configuration.URL_DELETE, id);
                return s;
            }
        }
        DeleteContact de = new DeleteContact();
        de.execute();
    }
    private void confirmDeleteContact(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah Kamu Yakin Ingin Menghapus Contact ini?");
                alertDialogBuilder.setPositiveButton("Ya",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                deleteContact();
                                startActivity(new
                                        Intent(AdminDetailMenuActivity.this,AdminMenuActivity.class));
                            }
                        });
        alertDialogBuilder.setNegativeButton("Tidak",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    @Override
    public void onClick(View v) {
        if(v == buttonUpdate){
            updateContact();
        }
        if(v == buttonDelete){
            confirmDeleteContact();
        }
    }
}