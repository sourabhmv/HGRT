package com.itsmesou.hgrtapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Homescreen extends AppCompatActivity {

    //variables
    ImageView logo;
    private Button button;
    TextView wlc, det;
    private final String KEY = "mykey";
    private final String PREF_KEY = "filename";
    ArrayList<String> temop = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.homescreen);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // User manual
        final int[] flaz = {0};

        final SharedPreferences[] sharedPreferences = {getPreferences(MODE_PRIVATE)};
        flaz[0] = sharedPreferences[0].getInt("koinkey", 0);



        //Declaration
        button = findViewById(R.id.loginhs);
        logo=findViewById(R.id.hslogo);
        wlc=findViewById(R.id.wlc);
        det=findViewById(R.id.pwdet);
        // Logo Animation
        logo.animate().translationYBy(300).setDuration(1500).setStartDelay(2000);
        logo.animate().alphaBy(1).setDuration(600).setStartDelay(1500);

        //Button Animation
        button.animate().translationYBy(300).setDuration(1500).setStartDelay(2000);
        button.animate().alphaBy(1).setDuration(600).setStartDelay(1500);

        //Text Animation
        wlc.animate().translationYBy(300).setDuration(1500).setStartDelay(2000);
        det.animate().translationYBy(300).setDuration(1500).setStartDelay(2000);
        wlc.animate().alphaBy(1).setDuration(600).setStartDelay(1500);
        det.animate().alphaBy(1).setDuration(600).setStartDelay(1500);

        //Button operation to open new activity
        button.setOnClickListener(view -> {
            int size = getSharedPreferences(PREF_KEY, MODE_PRIVATE).getInt(KEY + "size", 0);
            for (int i = 0; i < size; i++) {
                temop.add(getSharedPreferences(PREF_KEY, MODE_PRIVATE).getString(KEY + i, ""));
                Log.i("here after : ", getSharedPreferences(PREF_KEY, MODE_PRIVATE).getString(KEY + i, ""));
            }
            if (temop.size() == 0) {
                if( flaz[0] == 0){
                    Intent intent = new Intent(Homescreen.this, manual.class);
                    startActivity(intent);
                    flaz[0]++;

                    sharedPreferences[0] = getPreferences(MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences[0].edit();
                    editor.putInt("koinkey", flaz[0]);
                    editor.commit();
                }
                else {
                    Intent intent = new Intent(Homescreen.this, inpassarraychech.class);
                    startActivity(intent);
                }
            } else {

                Intent intent = new Intent(Homescreen.this, MainActivity.class);
                startActivity(intent);
            }



        });

        }
    }

