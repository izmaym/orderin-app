package com.yustian.student.orderin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MenuFragment extends Fragment {
    private ListView listView;
    private String JSON_STRING;

    String id;
    SharedPreferences sharedpreferences;
    String username;
    String meja;
    String transaction;

    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";
    public static final String TAG_TRANSACTION = "no_transaksi";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(R.layout.fragment_dashboard, container, false);
        listView =(ListView) view.findViewById(R.id.listView);

        // Session
        sharedpreferences = this.getActivity().getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        id = sharedpreferences.getString(TAG_ID, null);
        username = sharedpreferences.getString(TAG_USERNAME, null);
        transaction = getActivity().getIntent().getStringExtra(TAG_TRANSACTION);

        Intent intent = getActivity().getIntent();
        meja = intent.getStringExtra(Configuration.TAG_ID_TABLE);

        getJSON();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                Intent intent = new Intent(getActivity(), MenuInfoActivity.class);
                HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
                String empId = map.get(Configuration.TAG_ID).toString();
                intent.putExtra(Configuration.CON_ID, empId);
                intent.putExtra(Configuration.TAG_ID_TABLE, meja);
                intent.putExtra(Configuration.TAG_ID_TRANSACTION, transaction);
                startActivity(intent);
            }
        });
        return view;
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
                getActivity(), list, R.layout.activity_user_list_view,
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
                loading = ProgressDialog.show(getActivity(),"Menyiapkan Menu","Silahkan Tunggu",false,false);
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
                String s = rh.sendGetRequest(Configuration.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }
}
