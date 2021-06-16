package com.itsmesou.hgrtapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.NoSuchPaddingException;

import Utils.MyEncrypter;

public class Startfileencryption extends AppCompatActivity {

     private static final String FILE_NAME_ENC="aswinsmile_enc";
    private static final String FILE_NAME_DEC="aswinsmile_dec.png";
     Button btnenc,btndec;
     File MyDir;
     ImageView imageView;
    ArrayList<String> userpassword = new ArrayList<String>();
    private final String PREF_KEY = "filename";
    private final String KEYY = "mykeyy";
    String my_key="dmpjmenusrsouasw"; // 16 char
    String my_spec_key="srsouasamzdidhri";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startfileencryption);

        btnenc=findViewById(R.id.btnencrypt);
        btndec=findViewById(R.id.btndecrypt);
        imageView=findViewById(R.id.imageview);

        // init path
        MyDir=new File(Environment.getExternalStorageDirectory().toString()+"/Saved_images");

        Dexter.withActivity(this)
                .withPermissions(new String[]
                        {Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                        })
                .withListener(new MultiplePermissionsListener() {



                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        btndec.setEnabled(true);
                        btnenc.setEnabled(true);

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                        Toast.makeText(Startfileencryption.this, "You should enable permission", Toast.LENGTH_SHORT).show();
                    }
                }).check();

                 btndec.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {

                         File outputFileDec = new File(MyDir,FILE_NAME_DEC);
                         File encFile = new File(MyDir,FILE_NAME_ENC);
                         try {
                             MyEncrypter.decryptToFile(my_key,my_spec_key,new FileInputStream(encFile),
                                     new FileOutputStream(outputFileDec));

                             // Seting image view
                             imageView.setImageURI(Uri.fromFile(outputFileDec));
                             outputFileDec.delete();
                             Toast.makeText(Startfileencryption.this, "Decrypt", Toast.LENGTH_SHORT).show();


                         } catch (IOException e) {
                             e.printStackTrace();
                         } catch (NoSuchAlgorithmException e) {
                             e.printStackTrace();
                         } catch (InvalidKeyException e) {
                             e.printStackTrace();
                         } catch (InvalidAlgorithmParameterException e) {
                             e.printStackTrace();
                         } catch (NoSuchPaddingException e) {
                             e.printStackTrace();
                         }
                     }
                 });

                 btnenc.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {

                         Toast.makeText(Startfileencryption.this, "Button pressed", Toast.LENGTH_SHORT).show();
                         //convert to bitmap
                         Drawable drawable= ContextCompat.getDrawable(Startfileencryption.this,R.drawable.aswinsmile);
                         BitmapDrawable bitmapDrawable=(BitmapDrawable)drawable;
                         Bitmap bitmap=bitmapDrawable.getBitmap();
                         ByteArrayOutputStream stream= new ByteArrayOutputStream();
                         bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                         InputStream is=new  ByteArrayInputStream(stream.toByteArray());

                         //  create file
                         File outputfileEnc=new File(MyDir,FILE_NAME_ENC);

                         try {
                             MyEncrypter.encryptToFile(my_key,my_spec_key,is,new FileOutputStream(outputfileEnc));
                             Toast.makeText(Startfileencryption.this, "Encrypted", Toast.LENGTH_SHORT).show();
                         } catch (IOException e) {
                             e.printStackTrace();
                         } catch (NoSuchAlgorithmException e) {
                             e.printStackTrace();
                         } catch (InvalidKeyException e) {
                             e.printStackTrace();
                         } catch (InvalidAlgorithmParameterException e) {
                             e.printStackTrace();
                         } catch (NoSuchPaddingException e) {
                             e.printStackTrace();
                         }


                     }
                 });

        userpassword.clear();
        int sizee = getSharedPreferences(PREF_KEY, MODE_PRIVATE).getInt(KEYY + "sizee", 0);
        Log.i("here after : ", String.valueOf(sizee));
        for (int i = 0; i < sizee; i++) {
            userpassword.add(getSharedPreferences(PREF_KEY, MODE_PRIVATE).getString(KEYY + i, ""));
            Log.i("here after : ", getSharedPreferences(PREF_KEY, MODE_PRIVATE).getString(KEYY + i, ""));
        }

    }




    public void Resetcustom(View view) {
        if(userpassword.size()==0){

            Toast.makeText(this, "You have no saved custom password", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(Startfileencryption.this, Browseimages.class);
            startActivity(intent);
        }
    }
}