package com.itsmesou.hgrtapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Phonenumsave extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    EditText enternumber;
    Button savenumberbutton;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonenumsave);


        enternumber = findViewById(R.id.inputmobilenum);
        savenumberbutton = findViewById(R.id.savenumber);
        savenumberbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!enternumber.getText().toString().trim().isEmpty()) {
                    if (enternumber.getText().toString().trim().length() == 10) {
                        saveData();
                    } else {
                        Toast.makeText(Phonenumsave.this, "Enter complete number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Phonenumsave.this, "Enter mobile number", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXT, enternumber.getText().toString().trim());
        editor.apply();
        Toast.makeText(this, "Phone number saved successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Phonenumsave.this, MainActivity.class);
        startActivity(intent);
    }


}
