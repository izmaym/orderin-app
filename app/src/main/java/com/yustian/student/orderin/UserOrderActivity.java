package com.yustian.student.orderin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class UserOrderActivity extends AppCompatActivity implements ListView.OnItemClickListener {
    private ListView listView;
    private String JSON_STRING;

    String id;
    SharedPreferences sharedpreferences;
    String username;
    String meja;
    String transaction;
    int total;

    TextView txt_total;

    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";
    public static final String TAG_TRANSACTION = "no_transaksi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_order);

        txt_total = (TextView)findViewById(R.id.txt_total);

        // Session
        sharedpreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        id = getIntent().getStringExtra(Configuration.TAG_ID_TABLE);
        username = getIntent().getStringExtra(TAG_USERNAME);
        transaction = getIntent().getStringExtra(TAG_TRANSACTION);

        Intent intent = getIntent();
        meja = intent.getStringExtra(Configuration.TAG_ID_TABLE);

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

                String id = jo.getString(Configuration.TAG_ID);
                String name = jo.getString(Configuration.TAG_NAME);
                String number = jo.getString(Configuration.TAG_NUMBER);

                //total = total + Integer.parseInt(number);

                HashMap<String,String> contacts = new HashMap<>();

                contacts.put(Configuration.TAG_ID,id);
                contacts.put(Configuration.TAG_NAME,name);
                contacts.put(Configuration.TAG_NUMBER,number);

                list.add(contacts);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                UserOrderActivity.this, list, R.layout.activity_user_order_list_view,
                new String[]{Configuration.TAG_NAME, Configuration.TAG_NUMBER},
                new int[]{R.id.name, R.id.number});
        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(UserOrderActivity.this,"Menyiapkan Pesanan Anda","Silahkan Tunggu",false,false);
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
                String s = rh.sendGetRequest(Configuration.URL_GET_ALL_ORD_USER+"?no_transaksi="+transaction);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
        //txt_total.setText(String.valueOf(total));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long
            id) {
        Intent intent = new Intent(this, MenuInfoActivity.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String empId = map.get(Configuration.TAG_ID).toString();
        intent.putExtra(Configuration.CON_ID, empId);
        intent.putExtra(Configuration.TAG_ID_TABLE, meja);
        intent.putExtra(Configuration.TAG_ID_TRANSACTION, transaction);
        startActivity(intent);
    }
}