package com.itsmesou.hgrtapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Startfileencryption extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startfileencryption);
    }

    public void Resetcustom(View view) {
        Intent intent = new Intent(Startfileencryption.this, Browseimages.class);
        startActivity(intent);
    }
}