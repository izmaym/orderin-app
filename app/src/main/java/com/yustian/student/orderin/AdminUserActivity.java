package com.yustian.student.orderin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class AdminUserActivity extends AppCompatActivity implements ListView.OnItemClickListener {
    private ListView listView;
    private String JSON_STRING;

    SharedPreferences sharedpreferences;

    public final static String TAG_USERNAME = "username";
    public final static String TAG_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user);

        sharedpreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);

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
                String id = jo.getString(Configuration.TAG_ID);
                String username = jo.getString(Configuration.TAG_USERNAME);
                HashMap<String,String> contacts = new HashMap<>();
                contacts.put(Configuration.TAG_ID,id);
                contacts.put(Configuration.TAG_USERNAME,username);
                list.add(contacts);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListAdapter adapter = new SimpleAdapter(
                AdminUserActivity.this, list, R.layout.activity_user_list_view,
                new String[]{Configuration.TAG_USERNAME},
                new int[]{R.id.name});
        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(AdminUserActivity.this,"Mengambil Data","Mohon Tunggu...",false,false);
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
                String s = rh.sendGetRequest(Configuration.URL_GET_ALL_USER);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long
            id) {
        Intent intent = new Intent(this, AdminDetailMenuActivity.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String empId = map.get(Configuration.TAG_ID).toString();
        intent.putExtra(Configuration.CON_ID, empId);
        startActivity(intent);
    }
}
