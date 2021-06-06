package com.itsmesou.hgrtapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;

public class Browseimages extends AppCompatActivity {

    private Button browse;
    private  ArrayList<Uri> imageUris= new ArrayList<Uri>();
    ArrayList<String> userpassword =new ArrayList<String>();
    private String textt;

    private static final int PICK_IMAGES_CODE=0;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXTT = "textt";
    private final String KEYY = "mykeyy";
    private final String PREF_KEY = "filename";
    ArrayList<String> sending_password = new ArrayList<String>();

    int position=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browseimages);

        browse=findViewById(R.id.browse);
        sending_password.clear();

        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               pickImageIntent();

            }
        });

    }



    // Save to shared prefference

    /* public boolean saveArray(String[] array, String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName +"_size", array.length);
        for(int i=0;i<array.length;i++)
            editor.putString(arrayName + "_" + i, array[i]);
        return editor.commit();
    } */

    //load from shared prefference

    /* public String[] loadArray(String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
        int size = prefs.getInt(arrayName + "_size", 0);
        String array[] = new String[size];
        for(int i=0;i<size;i++)
            array[i] = prefs.getString(arrayName + "_" + i, null);
        return array;
    } */




    private void  pickImageIntent(){

        Intent intent=new Intent();
        intent.setType("image/*");
        intent.putExtra(intent.EXTRA_ALLOW_MULTIPLE,true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Images"),PICK_IMAGES_CODE);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==PICK_IMAGES_CODE){

            if (resultCode== Activity.RESULT_OK){

                if(data.getClipData()!=null){
                 //picked multiple images
                 int count=data.getClipData().getItemCount(); //Number of images selected
                 if (count== 16){

                    for (int i=0;i<16;i++){

                        Uri imageUri=data.getClipData().getItemAt(i).getUri();
                            imageUris.add(imageUri); //add to list
                            //String str =String.valueOf(imageUris.get(0))

                    }
                     Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                    position=0;


                     // conver Uri to string
                     for (int i=0;i<imageUris.size();i++){

                         userpassword.add(i,String.valueOf(imageUris.get(i)));

                     }



                     // put array to shared prefference
                     SharedPreferences.Editor editor = getSharedPreferences(PREF_KEY, MODE_PRIVATE).edit();
                     for ( int i = 0; i < userpassword.size(); i++) {
                         Log.i("here : ", userpassword.get(i));
                         editor.putString(KEYY + i, userpassword.get(i));
                     }
                     // after pushing store the key size
                     editor.putInt(KEYY + "sizee", userpassword.size());
                     editor.apply();
                     // Getting code

                     int sizee = getSharedPreferences(PREF_KEY, MODE_PRIVATE).getInt(KEYY + "sizee", 0);
                     Log.i("here after : ", String.valueOf(sizee));
                     for (int i = 0; i < sizee; i++) {
                         sending_password.add(getSharedPreferences(PREF_KEY, MODE_PRIVATE).getString(KEYY + i, ""));
                         Log.i("here after : ", getSharedPreferences(PREF_KEY, MODE_PRIVATE).getString(KEYY + i, ""));
                     }
                     Toast.makeText(this, "size is"+sending_password.size(), Toast.LENGTH_SHORT).show();

                     SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                     textt = sharedPreferences.getString(TEXTT, "");





                 }

                 else {
                     Toast.makeText(this, "Please select 8 Images", Toast.LENGTH_SHORT).show();


                 }


                }

                else {

                    //pick single image
                    Uri imageUri=data.getData();
                    imageUris.add(imageUri);
                    Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
                    position =0;
                }

            }

        }
    }
}