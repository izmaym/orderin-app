package com.yustian.student.orderin;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
    private Button buttonDelete;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_info);
        Intent intent = getIntent();
        id = intent.getStringExtra(Konfigurasi.CON_ID);
        editTextName = (TextView) findViewById(R.id.editTextName);
        editTextNumber = (TextView) findViewById(R.id.editTextNumber);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);
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
                        ProgressDialog.show(MenuInfoActivity.this,"Fetching...","Wait...",false,false);
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
                String s = rh.sendGetRequestParam(Konfigurasi.URL_GET_CON_MN,id);
                return s;
            }
        }
        GetContact ge = new GetContact();
        ge.execute();
    }
    private void showContact(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String name = c.getString(Konfigurasi.TAG_NAME);
            String number = c.getString(Konfigurasi.TAG_NUMBER);
            editTextName.setText(name);
            editTextNumber.setText(number);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void deleteContact(){
        class DeleteContact extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MenuInfoActivity.this, "Updating...",
                        "Wait...", false, false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MenuInfoActivity.this, s, Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Konfigurasi.URL_DELETE_CON, id);
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
                                Intent(MenuInfoActivity.this,MainActivity.class));
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
        if(v == buttonDelete){
            confirmDeleteContact();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_read) {
            Intent iread = new Intent(this, MainActivity.class);
            startActivity(iread);
            return true;
        } else if (id == R.id.action_create) {
            Intent icreate = new Intent(this, CreateActivity.class);
            startActivity(icreate);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
