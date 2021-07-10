package com.itsmesou.hgrtapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class manual extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);


        Toast.makeText(this, "User manual ", Toast.LENGTH_SHORT).show();


    }

    public void ok(View view){
        Intent intent = new Intent(manual.this, inpassarraychech.class);
        startActivity(intent);
    }

}