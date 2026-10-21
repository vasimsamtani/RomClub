package com.example.romclub;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

public class ContactUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        MaterialToolbar toolbar = findViewById(R.id.toolbarContact);
        toolbar.setNavigationOnClickListener(v -> finish());

        MaterialButton btnEmail = findViewById(R.id.btnEmail);
        MaterialButton btnTelegram = findViewById(R.id.btnTelegram);
        MaterialButton btnWebsite = findViewById(R.id.btnWebsite);

        btnEmail.setOnClickListener(view -> {
            try {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"vasim.dev@romclub.app"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "RomClub App Feedback & Feature Request");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Hi Vasim,\n\nI have the following feedback regarding RomClub:\n\nDevice: " + android.os.Build.MODEL);
                startActivity(Intent.createChooser(emailIntent, "Send Email via:"));
            } catch (Exception e) {
                Toast.makeText(this, "No email client found", Toast.LENGTH_SHORT).show();
            }
        });

        btnTelegram.setOnClickListener(view -> {
            try {
                Intent telegramIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/romclub_community"));
                startActivity(telegramIntent);
            } catch (Exception e) {
                Toast.makeText(this, "Could not open Telegram link", Toast.LENGTH_SHORT).show();
            }
        });

        btnWebsite.setOnClickListener(view -> {
            try {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/vasim"));
                startActivity(browserIntent);
            } catch (Exception e) {
                Toast.makeText(this, "Could not open website", Toast.LENGTH_SHORT).show();
            }
        });
    }
}