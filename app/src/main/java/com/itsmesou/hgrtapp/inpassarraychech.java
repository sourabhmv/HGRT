package com.itsmesou.hgrtapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class inpassarraychech extends AppCompatActivity {

    // array of integer
    final Integer[] images = {R.drawable.m1, R.drawable.m2, R.drawable.m3, R.drawable.m4, R.drawable.m5, R.drawable.m6, R.drawable.m7, R.drawable.m8,
            R.drawable.m9, R.drawable.m10, R.drawable.m11, R.drawable.m12, R.drawable.m13, R.drawable.m14, R.drawable.m15, R.drawable.m16};
    final Integer[] friends = {R.drawable.fr_1, R.drawable.fr_2, R.drawable.fr_3, R.drawable.fr_4, R.drawable.fr_5, R.drawable.fr_6, R.drawable.fr_7, R.drawable.fr_8,
            R.drawable.fr_9, R.drawable.fr_10, R.drawable.fr_11, R.drawable.fr_12, R.drawable.fr_13, R.drawable.fr_14, R.drawable.fr_15, R.drawable.fr_16};
    final Integer[] animal = {R.drawable.an_1, R.drawable.an_2, R.drawable.an_3, R.drawable.an_4, R.drawable.an_5, R.drawable.an_6, R.drawable.an_7, R.drawable.an_8,
            R.drawable.an_9, R.drawable.an_10, R.drawable.an_11, R.drawable.an_12, R.drawable.an_13, R.drawable.an_14, R.drawable.an_15, R.drawable.an_16};
    final Integer[] userchoice = {};
    String AES = "AES";
    String pa = "morazha";

    ArrayList<String> outpass = new ArrayList<String>();

    // array of uri
    List<Uri> imagesList, friendsList, animalList, userChoiceList, currentList;

    Spinner spinner;
    //Integer[] current = images;
    private ImageButton B1, B2, B3, B4, B5, B6, B7, B8, B9, B10, B11, B12, B13, B14, B15, B16;
    private Button login;

    ArrayList<String> password = new ArrayList<String>();
    private final String KEY = "mykey";
    private final String PREF_KEY = "filename";
    ArrayList<String> sending_password = new ArrayList<String>();

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inpassarraychech);

        // converting int id to uri of all the lists
        imagesList = convertIntToUri(images);
        animalList = convertIntToUri(animal);
        friendsList = convertIntToUri(friends);

        password.clear();
       /*password.add(0,"2131230880");
       password.add(1,"2131230870");
        Intent intent= new Intent(inpassarraychech.this,MainActivity.class);
        intent.putExtra("string",password);
        startActivity(intent);*/

        spinner = findViewById(R.id.spinner);
        final String str[] = {"Actors", "Friends", "Animals", "User Choice"};
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
                    currentList = imagesList;
                    setButtonImages(imagesList);
                } else if ("Animals".equals(spinner.getItemAtPosition(i).toString())) {
                    /*Bundle bundle= getIntent().getExtras();
                    ArrayList<String> arrayList = bundle.getStringArrayList("string");
                    temop.add(0,arrayList.get(0));
                    temop.add(1,arrayList.get(1));*/
                    currentList = animalList;
                    setButtonImages(animalList);
                } else if ("Friends".equals(spinner.getItemAtPosition(i).toString())) {
                   /* Bundle bundle= getIntent().getExtras();
                    ArrayList<String> arrayList = bundle.getStringArrayList("string");
                    temop.add(0,arrayList.get(0));
                    temop.add(1,arrayList.get(1));*/
                    currentList = friendsList;
                    setButtonImages(friendsList);

                } else {
                    if (userchoice.length == 0) {
                        setButtonImages(friendsList);
                        currentList=friendsList;
                        Toast.makeText(inpassarraychech.this, "You need to select Images first", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(inpassarraychech.this, Browseimages.class);
                        startActivity(intent);

                    } else {
                        // need to take image form the user
                        currentList = userChoiceList;
                        setButtonImages(userChoiceList);

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
    }

    public void Button1(View view) {

        password.add(String.valueOf(currentList.get(0)));
    }

    public void Button2(View view) {

        password.add(String.valueOf(currentList.get(1)));
    }

    public void Button3(View view) {

        password.add(String.valueOf(currentList.get(2)));
    }

    public void Button4(View view) {

        password.add(String.valueOf(currentList.get(3)));
    }

    public void Button5(View view) {

        password.add(String.valueOf(currentList.get(4)));
    }

    public void Button6(View view) {
        password.add(String.valueOf(currentList.get(5)));
    }

    public void Button7(View view) {
        password.add(String.valueOf(currentList.get(6)));
    }

    public void Button8(View view) {
        password.add(String.valueOf(currentList.get(7)));
    }

    public void Button9(View view) {
        password.add(String.valueOf(currentList.get(8)));
    }

    public void Button10(View view) {
        password.add(String.valueOf(currentList.get(9)));
    }

    public void Button11(View view) {
        password.add(String.valueOf(currentList.get(10)));
    }

    public void Button12(View view) {
        password.add(String.valueOf(currentList.get(11)));
    }

    public void Button13(View view) {
        password.add(String.valueOf(currentList.get(12)));
    }

    public void Button14(View view) {
        password.add(String.valueOf(currentList.get(13)));
    }

    public void Button15(View view) {
        password.add(String.valueOf(currentList.get(14)));
    }

    public void Button16(View view) {
        password.add(String.valueOf(currentList.get(15)));
    }

    public void Check(View view) {
       Toast.makeText(this, password.get(0), Toast.LENGTH_SHORT).show();

        if (password.size() != 0) {

            outpass.clear();
            String encout;
            int i;
            for (i = 0; i < password.size(); i++) {
                try {
                    encout = encrypt(password.get(i), pa);
                    outpass.add(encout);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }



            // Pushing code
        /*SharedPreferences.Editor editor = getSharedPreferences(PREF_KEY, MODE_PRIVATE).edit();
        editor.apply();*/

            SharedPreferences.Editor editor = getSharedPreferences(PREF_KEY, MODE_PRIVATE).edit();
            for ( i = 0; i < password.size(); i++) {
                Log.i("here : ", password.get(i));
                editor.putString(KEY + i, password.get(i));
            }
            // after pushing store the key size
            editor.putInt(KEY + "size", password.size());
            editor.apply();
            // Getting code

            int size = getSharedPreferences(PREF_KEY, MODE_PRIVATE).getInt(KEY + "size", 0);
            Log.i("here after : ", String.valueOf(size));
            for (i = 0; i < size; i++) {
                sending_password.add(getSharedPreferences(PREF_KEY, MODE_PRIVATE).getString(KEY + i, ""));
                Log.i("here after : ", getSharedPreferences(PREF_KEY, MODE_PRIVATE).getString(KEY + i, ""));
            }

            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            text = sharedPreferences.getString(TEXT, "");

            if (text.isEmpty()) {

                Intent intent = new Intent(inpassarraychech.this, Phonenumsave.class);
                startActivity(intent);
                // Toast.makeText(inpassarraychech.this, "Password saved" + password.get(0) + "and" + sending_password.get(0), Toast.LENGTH_SHORT).show();
            } else {

                Intent intent = new Intent(inpassarraychech.this, MainActivity.class);
                startActivity(intent);
            }

        }
        else {

            Toast.makeText(inpassarraychech.this, "Please select any combination as password", Toast.LENGTH_SHORT).show();
        }
    }

    public void Clear(View view) {
        password.clear();
        Toast.makeText(this, "Current selection has been cleard", Toast.LENGTH_LONG).show();
    }

    private String encrypt(String Data, String password) throws Exception {
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


    }


}
