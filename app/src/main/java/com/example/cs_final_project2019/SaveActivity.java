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

        Button backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SaveActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

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
                sendSMS();
            }
        });

        EditText phoneNumView = findViewById(R.id.phoneNumView);
        phoneNumView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneNumView.setText("");
            }
        });
    }

    public boolean checkPermission(String check) {
        int seek = ContextCompat.checkSelfPermission(this, check);
        return (seek == PackageManager.PERMISSION_GRANTED);
    }
}
