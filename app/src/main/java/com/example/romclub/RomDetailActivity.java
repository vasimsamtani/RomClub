package com.example.romclub;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

public class RomDetailActivity extends AppCompatActivity {

    public static final String EXTRA_ROM = "extra_rom_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rom_detail);

        RomModel rom;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            rom = getIntent().getSerializableExtra(EXTRA_ROM, RomModel.class);
        } else {
            rom = (RomModel) getIntent().getSerializableExtra(EXTRA_ROM);
        }
        if (rom == null) {
            Toast.makeText(this, "Failed to load ROM data", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(rom.getTitle());
        toolbar.setNavigationOnClickListener(v -> finish());

        ((TextView) findViewById(R.id.tvDetailTitle)).setText(rom.getTitle());
        ((TextView) findViewById(R.id.tvDetailMaintainer)).setText("Maintained by " + rom.getMaintainer());
        ((TextView) findViewById(R.id.tvDetailTypeBadge)).setText(rom.getType());
        ((TextView) findViewById(R.id.tvDetailCategory)).setText(rom.getCategory());
        ((TextView) findViewById(R.id.tvDetailAndroid)).setText("Android " + rom.getAndroidVersion());
        ((TextView) findViewById(R.id.tvDetailCodename)).setText(rom.getDeviceCodename());
        ((TextView) findViewById(R.id.tvDetailSize)).setText(rom.getFileSize());
        ((TextView) findViewById(R.id.tvDetailGapps)).setText(rom.isGappsIncluded() ? "Included" : "Vanilla");
        ((TextView) findViewById(R.id.tvDetailChangelog)).setText(rom.getChangelog());
        ((TextView) findViewById(R.id.tvDetailSha256)).setText(rom.getSha256());

        // Resource link rows
        setupLinkRow(findViewById(R.id.layoutWebLink), findViewById(R.id.tvDetailWebUrl),
                findViewById(R.id.btnCopyWebUrl), findViewById(R.id.btnOpenWebUrl), "Project Website", rom.getWebsiteUrl());

        setupLinkRow(findViewById(R.id.layoutGithubLink), findViewById(R.id.tvDetailGithubUrl),
                findViewById(R.id.btnCopyGithubUrl), findViewById(R.id.btnOpenGithubUrl), "GitHub Source", rom.getGithubUrl());

        setupLinkRow(findViewById(R.id.layoutSupportLink), findViewById(R.id.tvDetailSupportUrl),
                findViewById(R.id.btnCopySupportUrl), findViewById(R.id.btnOpenSupportUrl), "Support Link", rom.getSupportUrl());

        setupLinkRow(findViewById(R.id.layoutDownloadLink), findViewById(R.id.tvDetailDownloadUrl),
                findViewById(R.id.btnCopyDownloadUrl), null, "Download Link", rom.getDownloadUrl());

        findViewById(R.id.btnCopySha256).setOnClickListener(v -> copyTextToClipboard("SHA256 Checksum", rom.getSha256()));
        findViewById(R.id.btnDetailDownload).setOnClickListener(v -> openWebUrl(rom.getDownloadUrl()));
    }

    private void setupLinkRow(View layout, TextView tvUrl, ImageButton btnCopy, ImageButton btnOpen, String label, String url) {
        if (url != null && !url.trim().isEmpty()) {
            layout.setVisibility(View.VISIBLE);
            tvUrl.setText(url);
            if (btnCopy != null) btnCopy.setOnClickListener(v -> copyTextToClipboard(label, url));
            if (btnOpen != null) btnOpen.setOnClickListener(v -> openWebUrl(url));
        } else {
            layout.setVisibility(View.GONE);
        }
    }

    private void openWebUrl(String url) {
        if (url == null || url.trim().isEmpty()) return;
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        } catch (Exception e) {
            Toast.makeText(this, "Could not open link", Toast.LENGTH_SHORT).show();
        }
    }

    private void copyTextToClipboard(String label, String text) {
        if (text == null || text.trim().isEmpty()) {
            Toast.makeText(this, "No link available to copy", Toast.LENGTH_SHORT).show();
            return;
        }
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboard != null) {
            clipboard.setPrimaryClip(ClipData.newPlainText(label, text));
            Toast.makeText(this, label + " copied to clipboard!", Toast.LENGTH_SHORT).show();
        }
    }
}
