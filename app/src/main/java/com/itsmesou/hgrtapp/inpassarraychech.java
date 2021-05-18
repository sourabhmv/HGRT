package com.itsmesou.hgrtapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class inpassarraychech extends AppCompatActivity {

    final Integer[] images = {R.drawable.m1, R.drawable.m2, R.drawable.m3, R.drawable.m4, R.drawable.m5, R.drawable.m6, R.drawable.m7, R.drawable.m8,
            R.drawable.m9, R.drawable.m10, R.drawable.m11, R.drawable.m12, R.drawable.m13, R.drawable.m14, R.drawable.m15, R.drawable.m16};
    final Integer[] friends = {R.drawable.fr_1, R.drawable.fr_2, R.drawable.fr_3, R.drawable.fr_4, R.drawable.fr_5, R.drawable.fr_6, R.drawable.fr_7, R.drawable.fr_8,
            R.drawable.fr_9, R.drawable.fr_10, R.drawable.fr_11, R.drawable.fr_12, R.drawable.fr_13, R.drawable.fr_14, R.drawable.fr_15, R.drawable.fr_16};
    final Integer[] animal = {R.drawable.an_1, R.drawable.an_2, R.drawable.an_3, R.drawable.an_4, R.drawable.an_5, R.drawable.an_6, R.drawable.an_7, R.drawable.an_8,
            R.drawable.an_9, R.drawable.an_10, R.drawable.an_11, R.drawable.an_12, R.drawable.an_13, R.drawable.an_14, R.drawable.an_15, R.drawable.an_16};
    Spinner spinner;
    Integer[] current = images;
    private ImageButton B1, B2, B3, B4, B5, B6, B7, B8, B9, B10, B11, B12, B13, B14, B15, B16;
    private Button login;

    ArrayList<String> password = new ArrayList<String>();
    private final String KEY = "mykey";
    private final String PREF_KEY = "filename";
    ArrayList<String> sending_password = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inpassarraychech);

        password.clear();
       /*password.add(0,"2131230880");
       password.add(1,"2131230870");
        Intent intent= new Intent(inpassarraychech.this,MainActivity.class);
        intent.putExtra("string",password);
        startActivity(intent);*/

        spinner = findViewById(R.id.spinner);
        final String str[] = {"Actors", "Friends", "Animals"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(inpassarraychech.this, android.R.layout.simple_dropdown_item_1line, str);
        spinner.setAdapter(arrayAdapter);
        spinner.setSelection(1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                if ("Actors".equals(spinner.getItemAtPosition(i).toString())) {
                   /* Bundle bundle= getIntent().getExtras();
                    ArrayList<String> arrayList = bundle.getStringArrayList("string");
                    temop.add(0,arrayList.get(0));
                    temop.add(1,arrayList.get(1));*/
                    current = images;
                    setButtonImages(images);
                } else if ("Animals".equals(spinner.getItemAtPosition(i).toString())) {
                    /*Bundle bundle= getIntent().getExtras();
                    ArrayList<String> arrayList = bundle.getStringArrayList("string");
                    temop.add(0,arrayList.get(0));
                    temop.add(1,arrayList.get(1));*/
                    current = animal;
                    setButtonImages(animal);
                } else {
                   /* Bundle bundle= getIntent().getExtras();
                    ArrayList<String> arrayList = bundle.getStringArrayList("string");
                    temop.add(0,arrayList.get(0));
                    temop.add(1,arrayList.get(1));*/
                    current = friends;
                    setButtonImages(friends);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setButtonImages(Integer[] current) {

        B1 = (ImageButton) findViewById(R.id.button1);
        B2 = (ImageButton) findViewById(R.id.button2);
        B3 = (ImageButton) findViewById(R.id.button3);
        B4 = (ImageButton) findViewById(R.id.button4);
        B5 = (ImageButton) findViewById(R.id.button5);
        B6 = (ImageButton) findViewById(R.id.button6);
        B7 = (ImageButton) findViewById(R.id.button7);
        B8 = (ImageButton) findViewById(R.id.button8);
        B9 = (ImageButton) findViewById(R.id.button9);
        B10 = (ImageButton) findViewById(R.id.button10);
        B11 = (ImageButton) findViewById(R.id.button11);
        B12 = (ImageButton) findViewById(R.id.button12);
        B13 = (ImageButton) findViewById(R.id.button13);
        B14 = (ImageButton) findViewById(R.id.button14);
        B15 = (ImageButton) findViewById(R.id.button15);
        B16 = (ImageButton) findViewById(R.id.button16);


        int i = 0;
        B1.setBackgroundResource(current[i]);
        B2.setBackgroundResource(current[i + 1]);
        B3.setBackgroundResource(current[i + 2]);
        B4.setBackgroundResource(current[i + 3]);
        B5.setBackgroundResource(current[i + 4]);
        B6.setBackgroundResource(current[i + 5]);
        B7.setBackgroundResource(current[i + 6]);
        B8.setBackgroundResource(current[i + 7]);
        B9.setBackgroundResource(current[i + 8]);
        B10.setBackgroundResource(current[i + 9]);
        B11.setBackgroundResource(current[i + 10]);
        B12.setBackgroundResource(current[i + 11]);
        B13.setBackgroundResource(current[i + 12]);
        B14.setBackgroundResource(current[i + 13]);
        B15.setBackgroundResource(current[i + 14]);
        B16.setBackgroundResource(current[i + 15]);

    }

    public void Button1(View view) {

        password.add(String.valueOf(current[0].intValue()));
    }

    public void Button2(View view) {

        password.add(String.valueOf(current[1].intValue()));
    }

    public void Button3(View view) {

        password.add(String.valueOf(current[2].intValue()));
    }

    public void Button4(View view) {

        password.add(String.valueOf(current[3].intValue()));
    }

    public void Button5(View view) {

        password.add(String.valueOf(current[4].intValue()));
    }

    public void Button6(View view) {
        password.add(String.valueOf(current[5].intValue()));
    }

    public void Button7(View view) {
        password.add(String.valueOf(current[6].intValue()));
    }

    public void Button8(View view) {
        password.add(String.valueOf(current[7].intValue()));
    }

    public void Button9(View view) {
        password.add(String.valueOf(current[8].intValue()));
    }

    public void Button10(View view) {
        password.add(String.valueOf(current[9].intValue()));
    }

    public void Button11(View view) {
        password.add(String.valueOf(current[10].intValue()));
    }

    public void Button12(View view) {
        password.add(String.valueOf(current[11].intValue()));
    }

    public void Button13(View view) {
        password.add(String.valueOf(current[12].intValue()));
    }

    public void Button14(View view) {
        password.add(String.valueOf(current[13].intValue()));
    }

    public void Button15(View view) {
        password.add(String.valueOf(current[14].intValue()));
    }

    public void Button16(View view) {
        password.add(String.valueOf(current[15].intValue()));
    }

    public void Check(View view) {

        if (password.size() != 0) {
            // Pushing code
        /*SharedPreferences.Editor editor = getSharedPreferences(PREF_KEY, MODE_PRIVATE).edit();
        editor.apply();*/
            SharedPreferences.Editor editor = getSharedPreferences(PREF_KEY, MODE_PRIVATE).edit();
            for (int i = 0; i < password.size(); i++) {
                Log.i("here : ", password.get(i));
                editor.putString(KEY + i, password.get(i));
            }
            // after pushing store the key size
            editor.putInt(KEY + "size", password.size());
            editor.apply();
            // Getting code

            int size = getSharedPreferences(PREF_KEY, MODE_PRIVATE).getInt(KEY + "size", 0);
            Log.i("here after : ", String.valueOf(size));
            for (int i = 0; i < size; i++) {
                sending_password.add(getSharedPreferences(PREF_KEY, MODE_PRIVATE).getString(KEY + i, ""));
                Log.i("here after : ", getSharedPreferences(PREF_KEY, MODE_PRIVATE).getString(KEY + i, ""));
            }

            Intent intent = new Intent(inpassarraychech.this, MainActivity.class);
            startActivity(intent);
            // Toast.makeText(inpassarraychech.this, "Password saved" + password.get(0) + "and" + sending_password.get(0), Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(inpassarraychech.this, "Please select any combination as password", Toast.LENGTH_SHORT).show();
        }
    }


}
