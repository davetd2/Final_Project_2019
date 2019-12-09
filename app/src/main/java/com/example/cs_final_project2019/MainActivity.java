package com.example.cs_final_project2019;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.os.Bundle;

import java.util.List;
import java.util.ArrayList;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.LinearLayout;

import android.widget.Toast;
import java.util.Random;
import android.Manifest;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;

import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private List<Password> passwords;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_screen);

        passwords = new ArrayList<>();

        EditText websiteText = (EditText) findViewById(R.id.websiteText);
        EditText usernameText = (EditText) findViewById(R.id.usernameText);
        TextView passwordText = (TextView) findViewById(R.id.pwText);

        Button generateButton = (Button) findViewById(R.id.pwGenerateButton);
        
        shareButton.setEnabled(false);
        if (checkPermission(Manifest.permission.SEND_SMS)) {
            shareButton.setEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REUQEST_CODE);
        }

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String alpha = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                String special = "@%+/\'!#$^?:,(){}[]~-_.";
                Random r = new Random();
                String total;
                int num;
                String password = "";

                boolean bounds = null;
                int lowerBound = lb;
                int upperBound = ub;
                boolean specialChar = null;

                if (specialChar) {
                    total = alpha + special;
                } else {
                    total = alpha;
                }
                num = total.length();
                int stop = 12;
                if (bounds) {
                    stop = upperBound;
                }
                for (int i = 0; i < stop; i++) {
                    password += total.charAt(r.nextInt(num));
                }
                passwordText.setText(password);
                
            }
        });

        websiteText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                websiteText.setText("");
            }
        });

        usernameText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernameText.setText("");
            }
        });

        Button saveButton = (Button) findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String website = websiteText.getText().toString();
                String username = usernameText.getText().toString();

                Password newPass = new Password(website, username, password);
                passwords.add(newPass);
            }
        });

        Button viewButton = findViewById(R.id.viewButton);

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUI();

                Intent intent = new Intent(MainActivity.this, SaveActivity.class);
                startActivity(intent);
            }
        });
    }

    private void updateUI() {
        View saveScreen = getLayoutInflater().inflate(R.layout.second_screen, null);
        LinearLayout savedPasswords = (LinearLayout) saveScreen.findViewById(R.id.passwordsList);
        savedPasswords.removeAllViews();

        for (Password pw : passwords) {
            View passwordChunk = getLayoutInflater().inflate(R.layout.chunk_password, savedPasswords, false);

            String website = pw.getWebsite();
            String username = pw.getUsername();
            String pass = pw.getPassword();

            TextView websiteView = passwordChunk.findViewById(R.id.websiteDisplay);
            websiteView.setText(website);

            TextView usernameView = passwordChunk.findViewById(R.id.userDisplay);
            usernameView.setText(username);

            TextView passView = passwordChunk.findViewById(R.id.passDisplay);
            passView.setText(pass);

            Button removeButton = passwordChunk.findViewById(R.id.removeButton);

            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    passwords.remove(pw);
                    updateUI();
                }
            });

            //add the chunk to the layout
            passwordChunk.setVisibility(View.VISIBLE);
            savedPasswords.addView(passwordChunk);
        }
    }
    
    public void onShare(View v) {
        int phoneNumber = 0;//get phone number
        String password = null;//get password
        String phNumber = Integer.toString(phoneNumber);


        if (phoneNumber < 8 || phoneNumber > 10 ) {
            Toast.makeText(this, "Enter a valid phone number", Toast.LENGTH_SHORT).show();
            return;
        }

        if (checkPermission(Manifest.permission.SEND_SMS)) {
            SmsManager smsmanager = SmsManager.getDefault();
            smsmanager.sendTextMessage(phNumber , null, password, null,null);
            Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "message failed", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkPermission(String check) {
        int seek = ContextCompat.checkSelfPermission(this, check);
        return (seek == PackageManager.PERMISSION_GRANTED);
    }
}
