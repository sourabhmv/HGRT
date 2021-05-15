package com.itsmesou.hgrtapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class inpassarraychech extends AppCompatActivity {

    ArrayList<String> password= new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inpassarraychech);

       password.clear();
       password.add(0,"2131230880");
       password.add(1,"2131230870");
        Intent intent= new Intent(inpassarraychech.this,MainActivity.class);
        intent.putExtra("string",password);
        startActivity(intent);
    }
}