package com.yustian.student.orderin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeFragment extends Fragment {
    TextView txt_username;
    Button btnSearch, btnMenu;
    EditText cari;
    String id, username;
    SharedPreferences sharedpreferences;

    public final static String TAG_USERNAME = "username";
    public final static String TAG_ID = "id";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(R.layout.fragment_welcome, container, false);

        btnSearch = (Button)view.findViewById(R.id.btnSearch);
        cari = (EditText)view.findViewById(R.id.cari);
        txt_username = (TextView)view.findViewById(R.id.txt_username);

        sharedpreferences = this.getActivity().getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        id = sharedpreferences.getString(TAG_ID, null);
        username = sharedpreferences.getString(TAG_USERNAME, null);

        txt_username.setText("Selamat Datang, "+username);

        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            btnSearch.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if(cari.getText().toString().trim().length() > 0) {
                        Intent i = new Intent(getActivity(), MenuInfoActivity.class);
                        i.putExtra(Configuration.CON_ID, cari.getText().toString());
                        startActivity(i);
                    } else {
                        cari.setHintTextColor(getResources().getColor(R.color.colorRed));
                        Toast.makeText(getActivity() ,"Silahkan ketik apa yang ingin kamu cari", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        return view;
    }
}
