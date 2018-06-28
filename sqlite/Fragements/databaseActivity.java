package com.example.jayhind.lecture_app.database.sqlite.Fragements;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.jayhind.lecture_app.R;

public class databaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        Fragment f=new sqliteFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.s_frm_id,f).commit();
    }
}
