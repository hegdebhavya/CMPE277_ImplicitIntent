package com.example.cmpe277_implicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;

//BH Start
import android.app.Activity;
import android.view.Menu;
//BH End


import static android.content.ContentValues.TAG;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button urlButton = findViewById(R.id.urlButton);
        final EditText urlText =  findViewById(R.id.urlText);
        urlButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String url =  urlText.getText().toString();
                if (url.length() > 0) {
                    try {
                        Log.v(TAG,"Url typed is:" + url);
                        if(! (url.startsWith("http://") || url.startsWith("https://"))) {
                            url = "http://" + url;
                        }
                        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(myIntent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(getApplicationContext(), "Please install a Web Browser", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter a valid URL", Toast.LENGTH_LONG).show();
                }
            }
        });

        //to invoke PhoneCall
        final Button ringButton = findViewById(R.id.ringButton);
        final EditText ringText =  findViewById(R.id.ringText);

        ringButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String phoneNumber =  ringText.getText().toString();
                if (phoneNumber.length() > 0 &&
                        ((!phoneNumber.startsWith("+1") && phoneNumber.length() == 10) ||
                                (phoneNumber.startsWith("+1") && phoneNumber.length() == 12) )) {
                    Log.v(TAG,"PhoneNumber typed is:" + phoneNumber);
                    if (!phoneNumber.startsWith("+1") )
                        phoneNumber = "+1" + phoneNumber;
                    phoneNumber="tel:"+phoneNumber;
                    try {
                        Intent myIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber));
                        startActivity(myIntent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(getApplicationContext(), "Please install a Phone App",  Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter a valid Phone Number", Toast.LENGTH_LONG).show();
                }
            }
        });

        final Button smsButton = findViewById(R.id.smsButton);
        final EditText smsText =  findViewById(R.id.smsText);
        smsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String smsContent =  smsText.getText().toString();
                String phoneNumber =  ringText.getText().toString();


                        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                        smsIntent.setData(Uri.parse("smsto:"));


                        smsIntent.putExtra("address"  , phoneNumber);
                smsIntent.putExtra("sms_body"  , "Test ");
                try {
                        startActivity(smsIntent);
                        finish();
                    Log.i("Finished sending SMS...", "");
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(getApplicationContext(), "Please install a SMS App",  Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
        });

    }

    public void finishApp(View v) {
        MainActivity.this.finish();
    }
}