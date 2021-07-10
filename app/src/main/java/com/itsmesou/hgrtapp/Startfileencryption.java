package com.itsmesou.hgrtapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
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
import java.net.URISyntaxException;
import java.nio.channels.FileChannel;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.NoSuchPaddingException;

import Utils.MyEncrypter;

public class Startfileencryption extends AppCompatActivity {

    private  ArrayList<Uri> imageUris= new ArrayList<Uri>();
     private static  String FILE_NAME_ENC="image";
    private static  String FILE_NAME_DEC="image";
     Button btnenc,btndec;
     File MyDir,MyDir2;
     ImageView imageView;
    ArrayList<String> userpassword = new ArrayList<String>();
    ArrayList<String> browsedimges = new ArrayList<String>();
    private final String PREF_KEY = "filename";
    private final String KEYY = "mykeyy";
    String my_key="dmpjmenusrsouasw"; // 16 char
    String my_spec_key="srsouasamzdidhri";
    private static final int PICK_IMAGES_CODE=0;
    int position=0;
    Context context;
    int count;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startfileencryption);

        btnenc=findViewById(R.id.btnencrypt);
        btndec=findViewById(R.id.btndecrypt);



        // init path

        MyDir=new File(Environment.getExternalStorageDirectory().toString()+"/Encrypted_images");
        MyDir2=new File(Environment.getExternalStorageDirectory().toString()+"/Decrypted_images");






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

                         SharedPreferences sharedPreferences ;
                         int flag2;

                         for(int i=0;i<count;i++) {

                             sharedPreferences = getPreferences(MODE_PRIVATE);
                             flag2 = sharedPreferences.getInt("key2", 1);

                             FILE_NAME_DEC="image"+flag2+".png";
                             FILE_NAME_ENC="image"+flag2;
                             flag2++;
                             sharedPreferences = getPreferences(MODE_PRIVATE);
                             SharedPreferences.Editor editor = sharedPreferences.edit();
                             editor.putInt("key2", flag2);
                             editor.commit();


                             File outputFileDec = new File(MyDir, FILE_NAME_DEC);
                             File encFile = new File(MyDir, FILE_NAME_ENC);
                             try {
                                 MyEncrypter.decryptToFile(my_key, my_spec_key, new FileInputStream(encFile),
                                         new FileOutputStream(outputFileDec));

                                 // Seting image view
                                 // outputFileDec.delete();

                                 // Move to decrypted folder

                                 copyOrMoveFile(outputFileDec, MyDir2, false);


                                 Toast.makeText(Startfileencryption.this, "Decrypted", Toast.LENGTH_SHORT).show();
                                 outputFileDec.delete();


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

    // broadcast

    public void callBroadCast() {
        if (Build.VERSION.SDK_INT >= 14) {
            Log.e("-->", " >= 14");
            MediaScannerConnection.scanFile(this, new String[]{Environment.getExternalStorageDirectory().toString()}, null, new MediaScannerConnection.OnScanCompletedListener() {
                /*
                 *   (non-Javadoc)
                 * @see android.media.MediaScannerConnection.OnScanCompletedListener#onScanCompleted(java.lang.String, android.net.Uri)
                 */
                public void onScanCompleted(String path, Uri uri) {
                    Log.e("ExternalStorage", "Scanned " + path + ":");
                    Log.e("ExternalStorage", "-> uri=" + uri);
                }
            });
        } else {
            Log.e("-->", " < 14");
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,
                    Uri.parse("file://" + Environment.getExternalStorageDirectory())));
        }
    }

    // End of broadcast



    // Select images to encrypt
    public void browse(View view) {
        imageUris.clear();
        pickImageIntent();

    }

    //Functions for image browsing start


    private void  pickImageIntent(){

        Intent intent=new Intent();
        intent.setType("image/*");
        intent.putExtra(intent.EXTRA_ALLOW_MULTIPLE,true);
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(Intent.createChooser(intent,"Select Images"),PICK_IMAGES_CODE);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        btnenc.setOnClickListener(new View.OnClickListener() {
            private Object RandomStringUtils;

            @Override
            public void onClick(View v) {

                // Create folder if it doesnt exists

                String folder_main = "Encrypted_images";
                String folder_main2 = "Decrypted_images";

                File f = new File(Environment.getExternalStorageDirectory(), folder_main);
                if (!f.exists()) {
                    f.mkdirs();

                }


                f = new File(Environment.getExternalStorageDirectory(), folder_main2);
                if (!f.exists()) {
                    f.mkdirs();

                }


                //convert to bitmap
                // Drawable drawable= ContextCompat.getDrawable(Startfileencryption.this,R.drawable.aswinsmile);
                //BitmapDrawable bitmapDrawable=(BitmapDrawable)drawable;

                // Bitmap bitmap=bitmapDrawable.getBitmap();
                if(imageUris.size()==0){
                    Toast.makeText(context, "You have no selection", Toast.LENGTH_SHORT).show();
                }
                else {
                    context = getApplicationContext();
                    Bitmap bitmap = null;
                    int flag=1;
                    SharedPreferences sharedPreferences;



                    for (int i = 0; i < count; i++) {
                    try {

                         sharedPreferences = getPreferences(MODE_PRIVATE);
                        flag = sharedPreferences.getInt("key1", 1);




                            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), imageUris.get(i));
                            // path

                            Uri uri = imageUris.get(i);
                            Context context = Startfileencryption.this;
                            String name = uri.getLastPathSegment();

                            String file_dj_path = RealPathUtil.getRealPath(getApplicationContext(), uri);
                            Log.i("Real path", file_dj_path);
                            File fdelete = new File(file_dj_path);
                            if (fdelete.exists()) {
                                if (fdelete.delete()) {
                                    Log.i("here file deleted", file_dj_path);
                                    callBroadCast();
                                } else {
                                    Log.i("here file not deleted", file_dj_path);
                                }
                            } else {
                                Log.i("path", file_dj_path);
                            }


                        } catch(IOException e){
                            Log.i("here try", "Error");
                            e.printStackTrace();
                        }

                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        InputStream is = new ByteArrayInputStream(stream.toByteArray());

                        //  create file
                        FILE_NAME_DEC="image"+flag+".png";
                        FILE_NAME_ENC="image"+flag;
                        File outputfileEnc = new File(MyDir, FILE_NAME_ENC);
                        flag++;
                        sharedPreferences = getPreferences(MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("key1", flag);
                        editor.commit();

                        try {
                            MyEncrypter.encryptToFile(my_key, my_spec_key, is, new FileOutputStream(outputfileEnc));
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




                }

            }
        });





        if(requestCode==PICK_IMAGES_CODE){

            if (resultCode== Activity.RESULT_OK){

                if(data.getClipData()!=null){
                    //picked multiple images
                     count=data.getClipData().getItemCount(); //Number of images selected


                        for (int i=0;i<count;i++){

                            Uri imageUri=data.getClipData().getItemAt(i).getUri();
                            imageUris.add(imageUri); //add to list
                            //String str =String.valueOf(imageUris.get(0))

                        }
                        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                        position=0;

                }

                else {

                    //pick single image
                    Uri imageUri=data.getData();
                    count=1;
                    imageUris.add(imageUri);
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                    position =0;
                }

            }

        }
    }


    //*********************************************************

    @SuppressLint("NewApi")
    public static String getFilePath(Context context, Uri uri) throws URISyntaxException {
        String selection = null;
        String[] selectionArgs = null;
        // Uri is different in versions after KITKAT (Android 4.4), we need to
        if (Build.VERSION.SDK_INT >= 19 && DocumentsContract.isDocumentUri(context.getApplicationContext(), uri)) {
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                uri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("image".equals(type)) {
                    uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                selection = "_id=?";
                selectionArgs = new String[]{
                        split[1]
                };
            }
        }
        if ("content".equalsIgnoreCase(uri.getScheme())) {


            if (isGooglePhotosUri(uri)) {
                return uri.getLastPathSegment();
            }

            String[] projection = {
                    MediaStore.Images.Media.DATA
            };
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver()
                        .query(uri, projection, selection, selectionArgs, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    //********************************************************


    // function for image browsing end





    // Move to decrypted folder function

    private void copyOrMoveFile(File file, File dir,boolean isCopy) throws IOException {
        File newFile = new File(dir, file.getName());
        FileChannel outChannel = null;
        FileChannel inputChannel = null;
        try {
            outChannel = new FileOutputStream(newFile).getChannel();
            inputChannel = new FileInputStream(file).getChannel();
            inputChannel.transferTo(0, inputChannel.size(), outChannel);
            inputChannel.close();
            if(!isCopy)
                file.delete();
        } finally {
            if (inputChannel != null) inputChannel.close();
            if (outChannel != null) outChannel.close();
        }
    }

}