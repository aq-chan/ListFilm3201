package com.example.listfilm3201;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showList(View view){
        Intent intent = new Intent(this, ListFilmActivity.class);
        startActivity(intent);
    }

    public void showIdentity(View view){
        Intent intent = new Intent(this, IdentityActivity.class);
        startActivity(intent);
    }
}