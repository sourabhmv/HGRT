package com.itsmesou.hgrtapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class succscr extends AppCompatActivity {


    //Declaration
    TextView sctext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succscr);




        //Definition
        sctext=findViewById(R.id.sctext);

        sctext.animate().translationYBy(300).setDuration(1500).setStartDelay(2000);
        sctext.animate().alphaBy(1).setDuration(600).setStartDelay(1500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(succscr.this, Startfileencryption.class);
                startActivity(intent);

            }
        },3000);

    }
}