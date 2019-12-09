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
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SEND_SMS}, 1);
        }

        EditText phoneNumView = findViewById(R.id.phoneNumView);
        phoneNumView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneNumView.setText("");
            }
        });
    }

    public void onShare(View v) {
        EditText phoneNumView = findViewById(R.id.phoneNumView);
        String phNumber = phoneNumView.getText().toString();
        int phoneNumber = Integer.parseInt(phNumber);
        String password = getIntent().getStringExtra("password");

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
