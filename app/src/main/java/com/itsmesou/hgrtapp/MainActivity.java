package com.itsmesou.hgrtapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity{

    private ImageButton B1, B2, B3, B4, B5, B6, B7, B8, B9, B10, B11, B12, B13, B14, B15, B16;
    Integer[] images= {R.drawable.m1, R.drawable.m2, R.drawable.m3, R.drawable.m4, R.drawable.m5, R.drawable.m6, R.drawable.m7, R.drawable.m8,
            R.drawable.m9, R.drawable.m10, R.drawable.m11, R.drawable.m12, R.drawable.m13, R.drawable.m14, R.drawable.m15, R.drawable.m1
    };
    Integer[] friends={R.drawable.fr_1,R.drawable.fr_2,R.drawable.fr_3,R.drawable.fr_4,R.drawable.fr_5,R.drawable.fr_6,R.drawable.fr_7,R.drawable.fr_8,
            R.drawable.fr_9,R.drawable.fr_10, R.drawable.fr_11,R.drawable.fr_12,R.drawable.fr_13,R.drawable.fr_14,R.drawable.fr_15,R.drawable.fr_16};



    ArrayList<String> inppass = new ArrayList<String>();
    Integer counter = 0;
    //String outpass[] ={"2131165300"};
    ArrayList<String> outpass= new ArrayList<String>();
    ArrayList<String> temop= new ArrayList<String>();
    String AES ="AES";
    String pa= "morazha";
    Integer tmp[]=images;
    Integer [] current = tmp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        

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

        //outpass.add("2131165310");

        //Each time opening the app the previous array will be flushed.
        inppass.clear();
        temop.add("2131230848");
        temop.add("2131230838");

        // Encrypting content inside output array
        String encout;
        for(i=0;i<temop.size();i++) {
            try {
                encout = encrypt(temop.get(i), pa);
                outpass.add(encout);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


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
            //Toast.makeText(MainActivity.this, "ss"+inppass.get(0)+"and"+outpass.get(0)+id, Toast.LENGTH_SHORT).show();
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
            //Toast.makeText(MainActivity.this, "ss"+inppass.get(0)+"and"+outpass.get(0)+id, Toast.LENGTH_SHORT).show();
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


    public void Check(View view) {
        int l1=inppass.size();
        int l2=outpass.size();
        int l = 0;
        //To fix jimitt guhan raju issue
        int i=0;
        if(l1<l2) {
            while (l1 < l2) {
                if (inppass != null) {
                    inppass.add("DMPJ");
                }
                i++;
                l1 = inppass.size();

            }
        }
        if(l2<l1) {
            while (l2 < l1) {
                if (outpass != null) {
                    outpass.add("DMPJ");
                }
                i++;
                l2 = outpass.size();

            }
        }


        if(l2<l1)
            l=l1;
        if(l1==l2)
            l=l1;

        Integer flag = 0;

        for( i=0;i<l;i++) {
            if (inppass.get(i).compareTo(outpass.get(i))!=0) {
                flag=1;
            }
            }
        if(flag==0){
            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
            inppass.removeAll(inppass);

        }
        else{

            Toast.makeText(MainActivity.this, "Try again", Toast.LENGTH_SHORT).show();
                inppass.removeAll(inppass);


        }

    }


    private String encrypt(String Data, String password)throws Exception {
        SecretKeySpec key =generatekey(password);
        Cipher c = Cipher.getInstance(AES);
        c.init(Cipher.ENCRYPT_MODE,key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = Base64.encodeToString(encVal,Base64.DEFAULT);
        return  encryptedValue;

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
