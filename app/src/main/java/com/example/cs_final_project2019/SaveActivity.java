package com.example.cs_final_project2019;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.util.Log;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class SaveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_screen);

        Button sendButton = findViewById(R.id.sendButton);

        sendButton.setEnabled(false);
        if (checkPermission(Manifest.permission.SEND_SMS)) {
            sendButton.setEnabled(true);
        } else {
            ActivityCompat.requestPermissions(SaveActivity.this, new String[] {Manifest.permission.SEND_SMS}, 1);
        }

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText phoneNumView = findViewById(R.id.phoneNumView);
                String phoneNo = phoneNumView.getText().toString();
                String sms = getIntent().getStringExtra("password");

                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, sms, null, null);
                    Toast.makeText(getApplicationContext(), "SMS Sent!",
                            Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            "SMS failed, please try again later!",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }


    public boolean checkPermission(String check) {
        int seek = ContextCompat.checkSelfPermission(this, check);
        return (seek == PackageManager.PERMISSION_GRANTED);
    }
}
