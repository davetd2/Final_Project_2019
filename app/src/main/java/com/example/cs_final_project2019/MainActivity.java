package com.example.cs_final_project2019;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private List<Password> passwords;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_screen);

        passwords = new ArrayList<>();

        EditText websiteText = findViewById(R.id.websiteText);
        EditText usernameText = findViewById(R.id.usernameText);
        TextView passwordText = findViewById(R.id.pwText);

        Button generateButton = findViewById(R.id.pwGenerateButton);

        RadioButton specialCharButton = findViewById(R.id.specialCharButton);
        EditText maxLength = findViewById(R.id.maxLength);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String alpha = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                String special = "@%+/\'!#$^?:,(){}[]~-_.";
                Random r = new Random();
                String total;
                int num;
                String password = "";

                int upperBound = Integer.parseInt(maxLength.getText().toString());
                boolean specialChar = specialCharButton.isChecked();

                if (specialChar) {
                    total = alpha + special;
                } else {
                    total = alpha;
                }
                num = total.length();
                for (int i = 0; i < upperBound; i++) {
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

        Button textButton = findViewById(R.id.textButton);
        textButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //updateUI();

                Intent intent = new Intent(MainActivity.this, SaveActivity.class);
                startActivity(intent);
            }
        });
    }

    /*private void updateUI() {
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
            savedPasswords.addView(passwordChunk);
        }
    }*/
}
