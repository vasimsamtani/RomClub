package com.example.romclub;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

public class DeviceSelectorActivity extends AppCompatActivity {

    private DeviceManager deviceManager;
    private DeviceAdapter deviceAdapter;
    private EditText etDeviceSearch;
    private ImageButton btnClearSearch;
    private RecyclerView rvDevices;
    private View layoutEmptyDevices;
    private String currentBrandFilter = "All";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_selector);

        deviceManager = new DeviceManager(this);

        initViews();
        setupRecyclerView();
        setupSearchAndFilters();
        setupMyDeviceCard();
        filterDevices();
    }

    private void initViews() {
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
        etDeviceSearch = findViewById(R.id.etDeviceSearch);
        btnClearSearch = findViewById(R.id.btnClearSearch);
        rvDevices = findViewById(R.id.rvDevices);
        layoutEmptyDevices = findViewById(R.id.layoutEmptyDevices);
    }

    private void setupRecyclerView() {
        rvDevices.setLayoutManager(new LinearLayoutManager(this));
        deviceAdapter = new DeviceAdapter(device -> {
            deviceManager.setSelectedDevice(device);
            Toast.makeText(this, "Active device set to: " + device.getDisplayName(), Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        });
        rvDevices.setAdapter(deviceAdapter);
    }

    private void setupMyDeviceCard() {
        DeviceModel userDevice = deviceManager.getUserDetectedDevice();
        TextView tvSubtitle = findViewById(R.id.tvMyDeviceSubtitle);
        tvSubtitle.setText("Detected: " + userDevice.getDisplayName() + " (" + userDevice.getCodename() + ")");

        findViewById(R.id.btnUseMyDevice).setOnClickListener(v -> {
            deviceManager.resetToUserDevice();
            Toast.makeText(this, "Switched to your physical device!", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        });
    }

    private void setupSearchAndFilters() {
        etDeviceSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnClearSearch.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
                filterDevices();
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        btnClearSearch.setOnClickListener(v -> etDeviceSearch.setText(""));

        ChipGroup chipGroupBrands = findViewById(R.id.chipGroupBrands);
        chipGroupBrands.setOnCheckedStateChangeListener((group, checkedIds) -> {
            if (checkedIds.isEmpty()) {
                currentBrandFilter = "All";
            } else {
                Chip selectedChip = findViewById(checkedIds.get(0));
                currentBrandFilter = selectedChip != null ? selectedChip.getText().toString() : "All";
            }
            filterDevices();
        });
    }

    private void filterDevices() {
        String query = etDeviceSearch.getText().toString();
        List<DeviceModel> filtered = deviceManager.filterDevices(query, currentBrandFilter);
        DeviceModel selected = deviceManager.getSelectedDevice();

        deviceAdapter.updateData(filtered, selected);
        boolean empty = filtered.isEmpty();
        layoutEmptyDevices.setVisibility(empty ? View.VISIBLE : View.GONE);
        rvDevices.setVisibility(empty ? View.GONE : View.VISIBLE);
    }
}
