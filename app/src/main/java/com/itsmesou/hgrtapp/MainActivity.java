package com.itsmesou.hgrtapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.ResourceBundle;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;

    private ImageButton B1, B2, B3, B4, B5, B6, B7, B8, B9, B10, B11, B12, B13, B14, B15, B16;
    private Button login, register;
    final Integer[] images = {R.drawable.m1, R.drawable.m2, R.drawable.m3, R.drawable.m4, R.drawable.m5, R.drawable.m6, R.drawable.m7, R.drawable.m8,
            R.drawable.m9, R.drawable.m10, R.drawable.m11, R.drawable.m12, R.drawable.m13, R.drawable.m14, R.drawable.m15, R.drawable.m16};

    final Integer[] friends = {R.drawable.fr_1, R.drawable.fr_2, R.drawable.fr_3, R.drawable.fr_4, R.drawable.fr_5, R.drawable.fr_6, R.drawable.fr_7, R.drawable.fr_8,
            R.drawable.fr_9, R.drawable.fr_10, R.drawable.fr_11, R.drawable.fr_12, R.drawable.fr_13, R.drawable.fr_14, R.drawable.fr_15, R.drawable.fr_16};

    final Integer[] animal = {R.drawable.an_1, R.drawable.an_2, R.drawable.an_3, R.drawable.an_4, R.drawable.an_5, R.drawable.an_6, R.drawable.an_7, R.drawable.an_8,
            R.drawable.an_9, R.drawable.an_10, R.drawable.an_11, R.drawable.an_12, R.drawable.an_13, R.drawable.an_14, R.drawable.an_15, R.drawable.an_16};




    //array which will be assigned to buttons
    Integer[] current = images;
    ArrayList<String> inppass = new ArrayList<String>();
    Integer counter = 0;
    //String outpass[] ={"2131165300"};
    ArrayList<String> outpass = new ArrayList<String>();
    ArrayList<String> temop = new ArrayList<String>();

    String AES = "AES";
    String pa = "morazha";

    private final String KEY = "mykey";
    private final String PREF_KEY = "filename";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        final String str[] = {"Actors", "Friends", "Animals"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_dropdown_item_1line, str);
        spinner.setAdapter(arrayAdapter);
        spinner.setSelection(1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                if("Actors".equals(spinner.getItemAtPosition(i).toString())) {
                    inppass.clear();
                    temop.clear();
                    outpass.clear();

                    /*for (i = 0; i < arrayList.size(); i++) {
                        temop.add(i, arrayList.get(i));
                    }*/
                    current = images;
                    setButtonImages(images);
                    // Getting code

                    int size = getSharedPreferences(PREF_KEY, MODE_PRIVATE).getInt(KEY + "size", 0);
                    Log.i("here after : ", String.valueOf(size));
                    for (i = 0; i < size; i++) {
                        temop.add(getSharedPreferences(PREF_KEY, MODE_PRIVATE).getString(KEY + i, ""));
                        Log.i("here after : ", getSharedPreferences(PREF_KEY, MODE_PRIVATE).getString(KEY + i, ""));
                    }

                } else if ("Animals".equals(spinner.getItemAtPosition(i).toString())) {
                    inppass.clear();
                    temop.clear();
                    outpass.clear();
                    /*for (i = 0; i < arrayList.size(); i++) {
                        temop.add(i, arrayList.get(i));
                    }*/
                    current = animal;
                    setButtonImages(animal);

                    int size = getSharedPreferences(PREF_KEY, MODE_PRIVATE).getInt(KEY + "size", 0);
                    for (i = 0; i < size; i++) {
                        temop.add(getSharedPreferences(PREF_KEY, MODE_PRIVATE).getString(KEY + i, ""));
                        Log.i("here after : ", getSharedPreferences(PREF_KEY, MODE_PRIVATE).getString(KEY + i, ""));
                    }
                } else {
                    inppass.clear();
                    temop.clear();
                    outpass.clear();
                    Bundle bundle = getIntent().getExtras();
                    /*for (i = 0; i < arrayList.size(); i++) {
                        temop.add(i, arrayList.get(i));
                    }*/
                    current = friends;
                    setButtonImages(friends);
                    int size = getSharedPreferences(PREF_KEY, MODE_PRIVATE).getInt(KEY + "size", 0);
                    Log.i("here after : ", String.valueOf(size));
                    for (i = 0; i < size; i++) {
                        temop.add(getSharedPreferences(PREF_KEY, MODE_PRIVATE).getString(KEY + i, ""));
                        Log.i("here after : ", getSharedPreferences(PREF_KEY, MODE_PRIVATE).getString(KEY + i, ""));
                    }
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
        login = findViewById(R.id.button);
        register = findViewById(R.id.register);

        //Shuffling array
        for (int i = 0; i < current.length; i++) {
            int index = (int) (Math.random() * current.length);
            Integer temp = current[i];
            current[i] = current[index];
            current[index] = temp;
        }

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





        // Encrypting content inside output array

    }

    public void Button1(View view) {
        Integer id=current[0].intValue();
        String strid;
        String encstr;
        strid=String.valueOf(id);
        try {
            encstr=encrypt(strid,pa);
            //add encrypted id into array
            inppass.add(encstr);
            Toast.makeText(MainActivity.this, "ss"+inppass.get(0)+"and"+outpass.get(0)+id, Toast.LENGTH_SHORT).show();
            //Toast.makeText(MainActivity.this, "Button 1" + id, Toast.LENGTH_SHORT).show();
            counter++;
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void Button2(View view) {
        Integer id = current[1].intValue();
        String strid;
        String encstr;
        strid=String.valueOf(id);
        try {
            encstr=encrypt(strid,pa);
            inppass.add(encstr);
            Toast.makeText(MainActivity.this, "ss"+inppass.get(0)+"and"+outpass.get(0)+id, Toast.LENGTH_SHORT).show();
            //Toast.makeText(MainActivity.this, "Button 2" + id, Toast.LENGTH_SHORT).show();
            counter++;
        } catch (Exception e) {
            e.printStackTrace();
        }




    }

    public void Button3(View view) {
        Integer id = current[2].intValue();
        String strid;
        String encstr;
        strid=String.valueOf(id);
        try {
            encstr=encrypt(strid,pa);
            inppass.add(encstr);
            Toast.makeText(MainActivity.this, "ss"+inppass.get(0)+"and"+outpass.get(0)+id, Toast.LENGTH_SHORT).show();
            //Toast.makeText(MainActivity.this, "Button 3" + id, Toast.LENGTH_SHORT).show();
            counter++;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void Button4(View view) {
        Integer id = current[3].intValue();
        String strid;
        String encstr;
        strid=String.valueOf(id);
        try {
            encstr=encrypt(strid,pa);
            inppass.add(encstr);
            Toast.makeText(MainActivity.this, "ss"+inppass.get(0)+"and"+outpass.get(0)+id, Toast.LENGTH_SHORT).show();
            //Toast.makeText(MainActivity.this, "Button 4" + id, Toast.LENGTH_SHORT).show();
            counter++;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void Button5(View view) {
        Integer id = current[4].intValue();
        String strid;
        String encstr;
        strid=String.valueOf(id);
        try {
            encstr=encrypt(strid,pa);
            inppass.add(encstr);
            Toast.makeText(MainActivity.this, "ss"+inppass.get(0)+"and"+outpass.get(0)+id, Toast.LENGTH_SHORT).show();
            //Toast.makeText(MainActivity.this, "Button 5" + id, Toast.LENGTH_SHORT).show();
            counter++;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void Button6(View view) {
        Integer id = current[5].intValue();
        String strid;
        String encstr;
        strid=String.valueOf(id);
        try {
            encstr=encrypt(strid,pa);
            inppass.add(encstr);
            Toast.makeText(MainActivity.this, "ss"+inppass.get(0)+"and"+outpass.get(0)+id, Toast.LENGTH_SHORT).show();
            //Toast.makeText(MainActivity.this, "Button 6" + id, Toast.LENGTH_SHORT).show();
            counter++;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void Button7(View view) {
        Integer id = current[6].intValue();
        String strid;
        String encstr;
        strid=String.valueOf(id);
        try {
            encstr=encrypt(strid,pa);
            inppass.add(encstr);
            Toast.makeText(MainActivity.this, "ss"+inppass.get(0)+"and"+outpass.get(0)+id, Toast.LENGTH_SHORT).show();
            //Toast.makeText(MainActivity.this, "Button 7" + id, Toast.LENGTH_SHORT).show();
            counter++;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void Button8(View view) {
        Integer id = current[7].intValue();
        String strid;
        String encstr;
        strid=String.valueOf(id);
        try {
            encstr=encrypt(strid,pa);
            inppass.add(encstr);
            Toast.makeText(MainActivity.this, "ss"+inppass.get(0)+"and"+outpass.get(0)+id, Toast.LENGTH_SHORT).show();
            //Toast.makeText(MainActivity.this, "Button 8" + id, Toast.LENGTH_SHORT).show();
            counter++;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void Button9(View view) {
        Integer id = current[8].intValue();
        String strid;
        String encstr;
        strid=String.valueOf(id);
        try {
            encstr=encrypt(strid,pa);
            inppass.add(encstr);
            Toast.makeText(MainActivity.this, "ss"+inppass.get(0)+"and"+outpass.get(0)+id, Toast.LENGTH_SHORT).show();
            //Toast.makeText(MainActivity.this, "Button 9" + id, Toast.LENGTH_SHORT).show();
            counter++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Button10(View view) {
        Integer id = current[9].intValue();
        String strid;
        String encstr;
        strid=String.valueOf(id);
        try {
            encstr=encrypt(strid,pa);
            inppass.add(encstr);
            Toast.makeText(MainActivity.this, "ss"+inppass.get(0)+"and"+outpass.get(0)+id, Toast.LENGTH_SHORT).show();
            //Toast.makeText(MainActivity.this, "Button 10" + id, Toast.LENGTH_SHORT).show();
            counter++;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void Button11(View view) {
        Integer id = current[10].intValue();
        String strid;
        String encstr;
        strid=String.valueOf(id);
        try {
            encstr=encrypt(strid,pa);
            inppass.add(encstr);
            Toast.makeText(MainActivity.this, "ss"+inppass.get(0)+"and"+outpass.get(0)+id, Toast.LENGTH_SHORT).show();
            //Toast.makeText(MainActivity.this, "Button 11" + id, Toast.LENGTH_SHORT).show();
            counter++;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void Button12(View view) {
        Integer id = current[11].intValue();
        String strid;
        String encstr;
        strid=String.valueOf(id);
        try {
            encstr=encrypt(strid,pa);
            inppass.add(encstr);
            Toast.makeText(MainActivity.this, "ss"+inppass.get(0)+"and"+outpass.get(0)+id, Toast.LENGTH_SHORT).show();
            //Toast.makeText(MainActivity.this, "Button 12" + id, Toast.LENGTH_SHORT).show();
            counter++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Button13(View view) {
        Integer id = current[12].intValue();
        String strid;
        String encstr;
        strid=String.valueOf(id);
        try {
            encstr=encrypt(strid,pa);
            inppass.add(encstr);
            Toast.makeText(MainActivity.this, "ss"+inppass.get(0)+"and"+outpass.get(0)+id, Toast.LENGTH_SHORT).show();
            //Toast.makeText(MainActivity.this, "Button 13" + id, Toast.LENGTH_SHORT).show();
            counter++;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void Button14(View view) {
        Integer id = current[13].intValue();
        String strid;
        String encstr;
        strid=String.valueOf(id);
        try {
            encstr=encrypt(strid,pa);
            inppass.add(encstr);
            Toast.makeText(MainActivity.this, "ss"+inppass.get(0)+"and"+outpass.get(0)+id, Toast.LENGTH_SHORT).show();
            //Toast.makeText(MainActivity.this, "Button 14" + id, Toast.LENGTH_SHORT).show();
            counter++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Button15(View view) {
        Integer id = current[14].intValue();
        String strid;
        String encstr;
        strid=String.valueOf(id);
        try {
            encstr=encrypt(strid,pa);
            inppass.add(encstr);
            Toast.makeText(MainActivity.this, "ss"+inppass.get(0)+"and"+outpass.get(0)+id, Toast.LENGTH_SHORT).show();
            //Toast.makeText(MainActivity.this, "Button 15" + id, Toast.LENGTH_SHORT).show();
            counter++;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void Button16(View view) {
        Integer id = current[15].intValue();
        String strid;
        String encstr;
        strid=String.valueOf(id);
        try {
            encstr=encrypt(strid,pa);
            inppass.add(encstr);
            Toast.makeText(MainActivity.this, "ss"+inppass.get(0)+"and"+outpass.get(0)+id, Toast.LENGTH_SHORT).show();
            //Toast.makeText(MainActivity.this, "Button 16" + id, Toast.LENGTH_SHORT).show();
            counter++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


     int timer =0;
    public void Check(View view) {
       outpass.clear();
        String encout;
        int i;
        for(i=0;i<temop.size();i++) {
            try {
                encout = encrypt(temop.get(i), pa);
                outpass.add(encout);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (timer == 4) {

            B1.setEnabled(false);
            B2.setEnabled(false);
            B3.setEnabled(false);
            B4.setEnabled(false);
            B5.setEnabled(false);
            B6.setEnabled(false);
            B7.setEnabled(false);
            B8.setEnabled(false);
            B9.setEnabled(false);
            B10.setEnabled(false);
            B11.setEnabled(false);
            B12.setEnabled(false);
            B13.setEnabled(false);
            B14.setEnabled(false);
            B15.setEnabled(false);
            B16.setEnabled(false);
            login.setEnabled(false);



            Toast.makeText(MainActivity.this, "Try after 10 sec", Toast.LENGTH_SHORT).show();
            timer = 0;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {




                    B1.setEnabled(true);
                    B2.setEnabled(true);
                    B3.setEnabled(true);
                    B4.setEnabled(true);
                    B5.setEnabled(true);
                    B6.setEnabled(true);
                    B7.setEnabled(true);
                    B8.setEnabled(true);
                    B9.setEnabled(true);
                    B10.setEnabled(true);
                    B11.setEnabled(true);
                    B12.setEnabled(true);
                    B13.setEnabled(true);
                    B14.setEnabled(true);
                    B15.setEnabled(true);
                    B16.setEnabled(true);
                    login.setEnabled(true);


                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);


                }
            }, 10000);

        }
        else {
            int l1 = inppass.size();
            int l2 = outpass.size();
            int l = 0;
            //To fix jimitt guhan raju issue
             i = 0;
            if (l1 < l2) {
                while (l1 < l2) {
                    inppass.add("DM");
                    l1 = inppass.size();
                    l = l2;
                }


            } else if (l2 < l1) {
                while (l2 < l1) {
                    outpass.add("DM");
                    l2 = outpass.size();
                    l = l2;

                }
            } else {
                l = l2;
            }


            Integer flag = 0;

            for (i = 0; i < l; i++) {
                if (inppass.get(i).compareTo(outpass.get(i)) != 0) {
                    flag = 1;
                }
            }
            if (flag == 0) {
                // Intent intent = new Intent(this, succscr.class);
                timer = 0;
               Intent intent = new Intent(getApplicationContext(), succscr.class);
                startActivity(intent);
                //Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                inppass.clear();


            } else {

                Toast.makeText(MainActivity.this, "Wrong Password!!! Try Again", Toast.LENGTH_SHORT).show();
                inppass.clear();
                timer++;


            }


        }
    }

    public void Register(View view) {
        Toast.makeText(MainActivity.this, "Nee nokkum enn ariya ith aakan korachude pani nd", Toast.LENGTH_SHORT).show();
    }

    private String encrypt(String Data, String password) throws Exception {
        SecretKeySpec key = generatekey(password);
        Cipher c = Cipher.getInstance(AES);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = Base64.encodeToString(encVal, Base64.DEFAULT);
        return encryptedValue;

    }

    private SecretKeySpec generatekey(String password)throws Exception {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = password.getBytes(StandardCharsets.UTF_8);
        digest.update(bytes,0,bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key,"AES");
        return secretKeySpec;


    }

}
