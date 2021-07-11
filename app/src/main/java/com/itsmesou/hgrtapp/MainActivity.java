package com.itsmesou.hgrtapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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
import java.util.Collections;
import java.util.List;
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

    // array of uri

    List<Uri> imagesList, friendsList, animalList, userChoiceList, currentList;

    //array which will be assigned to buttons
    Integer[] current = images;
    ArrayList<String> inppass = new ArrayList<String>();
    Integer counter = 0;
    //String outpass[] ={"2131165300"};
    ArrayList<String> outpass = new ArrayList<String>();
    ArrayList<String> temop = new ArrayList<String>();
    ArrayList<String> userpassword = new ArrayList<String>();

    String AES = "AES";
    String pa = "morazha";

    private final String KEY = "mykey";
    private final String KEYY = "mykeyy";
    private final String PREF_KEY = "filename";
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXTT = "textt";
    private String text,textt;

    int timer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // converting int id to uri of all the lists
        imagesList = convertIntToUri(images);
        animalList = convertIntToUri(animal);
        friendsList = convertIntToUri(friends);

        spinner = findViewById(R.id.spinner);
        final String str[] = {"Actors", "Friends", "Animals","User Choice"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_dropdown_item_1line, str);
        spinner.setAdapter(arrayAdapter);
        spinner.setSelection(1);


        //****************************************************************

        userpassword.clear();
        int sizee = getSharedPreferences(PREF_KEY, MODE_PRIVATE).getInt(KEYY + "sizee", 0);
        Log.i("here after : ", String.valueOf(sizee));
        for (int i = 0; i < sizee; i++) {
            userpassword.add(getSharedPreferences(PREF_KEY, MODE_PRIVATE).getString(KEYY + i, ""));
            Log.i("here after : ", getSharedPreferences(PREF_KEY, MODE_PRIVATE).getString(KEYY + i, ""));
        }
        currentList=convertIntoUrii(userpassword);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        textt = sharedPreferences.getString(TEXTT, "");
        // ***************************************************************

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                if ("Actors".equals(spinner.getItemAtPosition(i).toString())) {
                    inppass.clear();
                    temop.clear();
                    outpass.clear();

                    /*for (i = 0; i < arrayList.size(); i++) {
                        temop.add(i, arrayList.get(i));
                    }*/
                    currentList = imagesList;
                    setButtonImages(imagesList);
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
                    currentList = animalList;
                    setButtonImages(animalList);

                    int size = getSharedPreferences(PREF_KEY, MODE_PRIVATE).getInt(KEY + "size", 0);
                    for (i = 0; i < size; i++) {
                        temop.add(getSharedPreferences(PREF_KEY, MODE_PRIVATE).getString(KEY + i, ""));
                        Log.i("here after : ", getSharedPreferences(PREF_KEY, MODE_PRIVATE).getString(KEY + i, ""));
                    }
                }
                else if("Friends".equals(spinner.getItemAtPosition(i).toString())) {
                    inppass.clear();
                    temop.clear();
                    outpass.clear();
                    Bundle bundle = getIntent().getExtras();
                    /*for (i = 0; i < arrayList.size(); i++) {
                        temop.add(i, arrayList.get(i));
                    }*/
                    currentList = friendsList;
                    setButtonImages(friendsList);
                    int size = getSharedPreferences(PREF_KEY, MODE_PRIVATE).getInt(KEY + "size", 0);
                    Log.i("here after : ", String.valueOf(size));
                    for (i = 0; i < size; i++) {
                        temop.add(getSharedPreferences(PREF_KEY, MODE_PRIVATE).getString(KEY + i, ""));
                        Log.i("here after : ", getSharedPreferences(PREF_KEY, MODE_PRIVATE).getString(KEY + i, ""));
                    }
                }

                else {

                    inppass.clear();
                    temop.clear();
                    outpass.clear();
                    Bundle bundle = getIntent().getExtras();
                    /*for (i = 0; i < arrayList.size(); i++) {
                        temop.add(i, arrayList.get(i));
                    }*/

                    int size = getSharedPreferences(PREF_KEY, MODE_PRIVATE).getInt(KEY + "size", 0);
                    // Log.i("here after : ", String.valueOf(size));
                    for (i = 0; i < size; i++) {
                        temop.add(getSharedPreferences(PREF_KEY, MODE_PRIVATE).getString(KEY + i, ""));
                       // Log.i("here after : ", getSharedPreferences(PREF_KEY, MODE_PRIVATE).getString(KEY + i, ""));
                    }



                    if(userpassword.size()==0){
                        Toast.makeText(MainActivity.this, "You have no user password selection", Toast.LENGTH_SHORT).show();
                    }

                    else {
                        currentList=convertIntoUrii(userpassword);
                        setButtonImages(currentList);

                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    // function to convert int array of images to uri array
    public List<Uri> convertIntToUri(Integer[] arrayInt) {
        List<Uri> returnArray = new ArrayList<>();
        for (Integer integer : arrayInt) {
            //addition to the code
            Uri path = Uri.parse("android.resource://com.itsmesou.hgrtapp/" + integer);
            returnArray.add(path);
        }
        return returnArray;
    }

    // function to convert String list to array list
    public List<Uri>convertIntoUrii(List<String> arraystr){
        List<Uri> returnArray1= new ArrayList<>();
        for(String string : arraystr) {
            Uri path = Uri.parse(string);
            Log.i("heree : ", path.toString());
            returnArray1.add(path);
        }
        return returnArray1;
    }


    public void setButtonImages(List<Uri> current) {

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

        //Shuffling list
        /*for (int i = 0; i < current.length; i++) {
            int index = (int) (Math.random() * current.length);
            Integer temp = current[i];
            current[i] = current[index];
            current[index] = temp;
        }*/
        Collections.shuffle(current);

        B1.setImageURI(current.get(0));
        B2.setImageURI(current.get(1));
        B3.setImageURI(current.get(2));
        B4.setImageURI(current.get(3));
        B5.setImageURI(current.get(4));
        B6.setImageURI(current.get(5));
        B7.setImageURI(current.get(6));
        B8.setImageURI(current.get(7));
        B9.setImageURI(current.get(8));
        B10.setImageURI(current.get(9));
        B11.setImageURI(current.get(10));
        B12.setImageURI(current.get(11));
        B13.setImageURI(current.get(12));
        B14.setImageURI(current.get(13));
        B15.setImageURI(current.get(14));
        B16.setImageURI(current.get(15));
        // Encrypting content inside output array

    }

    public void Button1(View view) {
        Integer id = current[0].intValue();
        String strid;
        String encstr;
        strid = String.valueOf(currentList.get(0));
        inppass.add(strid);


    }

    public void Button2(View view) {
        Integer id = current[1].intValue();
        String strid;
        String encstr;
        strid = String.valueOf(currentList.get(1));
        inppass.add(strid);


    }

    public void Button3(View view) {
        Integer id = current[2].intValue();
        String strid;
        String encstr;
        strid = String.valueOf(currentList.get(2));
        inppass.add(strid);

    }

    public void Button4(View view) {
        Integer id = current[3].intValue();
        String strid;
        String encstr;
        strid = String.valueOf(currentList.get(3));
        inppass.add(strid);

    }

    public void Button5(View view) {
        Integer id = current[4].intValue();
        String strid;
        //strid=String.valueOf();
        String encstr;
        strid = String.valueOf(currentList.get(4));
        inppass.add(strid);

    }

    public void Button6(View view) {
        Integer id = current[5].intValue();
        String strid;
        String encstr;
        strid = String.valueOf(currentList.get(5));
        inppass.add(strid);

    }

    public void Button7(View view) {
        Integer id = current[6].intValue();
        String strid;
        String encstr;
        strid = String.valueOf(currentList.get(6));
        inppass.add(strid);
    }

    public void Button8(View view) {
        Integer id = current[7].intValue();
        String strid;
        String encstr;
        strid = String.valueOf(currentList.get(7));
        inppass.add(strid);

    }

    public void Button9(View view) {
        Integer id = current[8].intValue();
        String strid;
        String encstr;
        strid = String.valueOf(currentList.get(8));
        inppass.add(strid);
    }

    public void Button10(View view) {
        Integer id = current[9].intValue();
        String strid;
        String encstr;
        strid = String.valueOf(currentList.get(9));
        inppass.add(strid);

    }

    public void Button11(View view) {
        Integer id = current[10].intValue();
        String strid;
        String encstr;
        strid = String.valueOf(currentList.get(10));
        inppass.add(strid);

    }

    public void Button12(View view) {
        Integer id = current[11].intValue();
        String strid;
        String encstr;
        strid = String.valueOf(currentList.get(11));
        inppass.add(strid);
    }

    public void Button13(View view) {
        Integer id = current[12].intValue();
        String strid;
        String encstr;
        strid = String.valueOf(currentList.get(12));
        inppass.add(strid);

    }

    public void Button14(View view) {
        Integer id = current[13].intValue();
        String strid;
        String encstr;
        strid = String.valueOf(currentList.get(13));
        inppass.add(strid);
    }

    public void Button15(View view) {
        Integer id = current[14].intValue();
        String strid;
        String encstr;
        strid = String.valueOf(currentList.get(14));
        inppass.add(strid);

    }

    public void Button16(View view) {
        Integer id = current[15].intValue();
        String strid;
        String encstr;
        strid = String.valueOf(currentList.get(15));
        inppass.add(strid);
    }



    public void Register(View view) {
        Intent intent = new Intent(MainActivity.this, Forgetactivity.class);
        startActivity(intent);
    }

    public void Check(View view) {
        // calculate outpass
        outpass.clear();
        for (int i = 0; i < temop.size(); i++) {
            outpass.add(String.valueOf(temop.get(i)));
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

        } else {
            int flag = 0;
            if (inppass.size() == outpass.size()) {
                for (int i = 0; i < inppass.size(); i++) {
                    if (!inppass.get(i).equals(outpass.get(i))) {
                        flag = 1;
                        break;
                    }
                }
            } else {
                flag = 1;
            }

            if (flag == 0) {
                timer = 0;
                Intent intent = new Intent(getApplicationContext(), succscr.class);
                startActivity(intent);
                inppass.clear();
            } else {
                Toast.makeText(MainActivity.this, "Wrong Password!!! Try Again", Toast.LENGTH_SHORT).show();
                inppass.clear();
                timer++;
            }
        }
    }



    /* private String encrypt(String Data, String password) throws Exception {
        SecretKeySpec key = generatekey(password);
        Cipher c = Cipher.getInstance(AES);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = Base64.encodeToString(encVal, Base64.DEFAULT);
        return encryptedValue;

    }

    private SecretKeySpec generatekey(String password) throws Exception {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = password.getBytes(StandardCharsets.UTF_8);
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        return secretKeySpec;


    } */

}
