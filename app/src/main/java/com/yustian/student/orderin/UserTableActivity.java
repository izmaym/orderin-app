package com.yustian.student.orderin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class UserTableActivity extends AppCompatActivity implements ListView.OnItemClickListener {
    private ListView listView;
    private String JSON_STRING;

    String id;
    SharedPreferences sharedpreferences;
    String username;
    String empId;
    int acak;

    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_table);

        Random rand = new Random();
        acak = rand.nextInt(50);

        // Session
        sharedpreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        id = sharedpreferences.getString(TAG_ID, null);
        username = sharedpreferences.getString(TAG_USERNAME, null);

        // Mengambil data menu
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        getJSON();
    }

    private void showContact(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,
                String>>();

        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Configuration.TAG_JSON_ARRAY);
            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);

                String id = jo.getString(Configuration.TAG_ID_TABLE);
                String name = jo.getString(Configuration.TAG_NUMBER_TABLE);

                HashMap<String,String> contacts = new HashMap<>();

                contacts.put(Configuration.TAG_ID_TABLE,id);
                contacts.put(Configuration.TAG_NUMBER_TABLE,name);

                list.add(contacts);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                UserTableActivity.this, list, R.layout.activity_user_table_list_view,
                new String[]{Configuration.TAG_ID_TABLE, Configuration.TAG_NUMBER_TABLE},
                new int[]{R.id.name, R.id.number});
        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(UserTableActivity.this,"Menyiapkan Meja","Silahkan Tunggu",false,false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showContact();
            }
            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Configuration.URL_GET_ALL_TABLE);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long
            id) {
        Intent intent = new Intent(this, UserMenuActivity.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        empId = map.get(Configuration.TAG_ID_TABLE).toString();
        intent.putExtra(Configuration.TAG_ID_TABLE, empId);
        intent.putExtra(Configuration.TAG_ID_TRANSACTION, empId+id+acak);
        addContact();
        startActivity(intent);
    }

    private void addContact() {
        class AddContact extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(UserTableActivity.this,"Menambahkan...",
                        "Tunggu...",false,false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(UserTableActivity.this,s,Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Configuration.KEY_ID_TRANSACTION, empId + id + acak);
                params.put(Configuration.KEY_ID_USER, id);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Configuration.URL_ADD_SALES, params);
                return res;
            }
        }
        AddContact ae = new AddContact();
        ae.execute();
    }
}

