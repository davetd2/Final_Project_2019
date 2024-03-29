package com.example.cs_final_project2019;

import androidx.appcompat.app.AppCompatActivity;

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

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password = "adsfasdfsd";
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
}
