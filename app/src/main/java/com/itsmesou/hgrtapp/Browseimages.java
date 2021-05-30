package com.itsmesou.hgrtapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;

public class Browseimages extends AppCompatActivity {

    private Button browse;
     Uri[] images = {};
     String[] imagess={};
    private static final int PICK_IMAGES_CODE=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browseimages);

        browse=findViewById(R.id.browse);

        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPickImageIntent();


            }
        });

    }



    // Save to shared prefference

    public boolean saveArray(String[] array, String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName +"_size", array.length);
        for(int i=0;i<array.length;i++)
            editor.putString(arrayName + "_" + i, array[i]);
        return editor.commit();
    }

    //load from shared prefference

    public String[] loadArray(String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
        int size = prefs.getInt(arrayName + "_size", 0);
        String array[] = new String[size];
        for(int i=0;i<size;i++)
            array[i] = prefs.getString(arrayName + "_" + i, null);
        return array;
    }




    private void  setPickImageIntent(){

        Intent intent=new Intent();
        intent.setType("images/*");
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
                 if (count== 8){

                    for (int i=0;i<8;i++){

                        Uri imageUri=data.getClipData().getItemAt(i).getUri();
                        images[i]=imageUri; //Add to array
                        imagess[i]=images[i].toString();
                        //saveArray(String[] array, String arrayName, Context mContext)
                        saveArray(imagess,"imagess",getApplicationContext());
                        Toast.makeText(this, "User password saved to shared prefference", Toast.LENGTH_SHORT).show();

                    }


                 }

                 else {
                     Toast.makeText(this, "Please select 8 Images", Toast.LENGTH_SHORT).show();
                     images=null;

                 }


                }

            }

        }
    }
}