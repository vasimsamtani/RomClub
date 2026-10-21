package com.example.romclub;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    private DeviceManager deviceManager;
    private RomClubRepository repository;

    private RomAdapter homeFeaturedAdapter;
    private RomAdapter romsCatalogAdapter;
    private ModuleAdapter modulesAdapter;

    private View sectionHome, sectionRoms, sectionModules, sectionGuides, sectionProfile;
    private BottomNavigationView bottomNavigationView;

    private TextView tvActiveDeviceName, tvHomeDeviceTitle, tvHomeCodenameBadge, tvHomeChipset;
    private TextView tvHomeRomCount, tvHomeKernelCount;

    private ChipGroup chipGroupRomsCategory, chipGroupModulesCategory;
    private EditText etRomSearch, etModuleSearch;
    private String currentRomCategory = "ALL";
    private String currentModuleCategory = "ALL";

    private final ActivityResultLauncher<Intent> deviceSelectorLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) refreshAllData();
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        deviceManager = new DeviceManager(this);
        repository = new RomClubRepository(this);

        initViews();
        setupNavigation();
        setupAdapters();
        setupListeners();
        refreshAllData();
    }

    private void initViews() {
        sectionHome = findViewById(R.id.sectionHome);
        sectionRoms = findViewById(R.id.sectionRoms);
        sectionModules = findViewById(R.id.sectionModules);
        sectionGuides = findViewById(R.id.sectionGuides);
        sectionProfile = findViewById(R.id.sectionProfile);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        tvActiveDeviceName = findViewById(R.id.tvActiveDeviceName);
        tvHomeDeviceTitle = findViewById(R.id.tvHomeDeviceTitle);
        tvHomeCodenameBadge = findViewById(R.id.tvHomeCodenameBadge);
        tvHomeChipset = findViewById(R.id.tvHomeChipset);
        tvHomeRomCount = findViewById(R.id.tvHomeRomCount);
        tvHomeKernelCount = findViewById(R.id.tvHomeKernelCount);

        chipGroupRomsCategory = findViewById(R.id.chipGroupRomsCategory);
        etRomSearch = findViewById(R.id.etRomSearch);
        chipGroupModulesCategory = findViewById(R.id.chipGroupModulesCategory);
        etModuleSearch = findViewById(R.id.etModuleSearch);
    }

    private void setupNavigation() {
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) showSection(sectionHome);
            else if (id == R.id.nav_roms) showSection(sectionRoms);
            else if (id == R.id.nav_modules) showSection(sectionModules);
            else if (id == R.id.nav_guides) showSection(sectionGuides);
            else if (id == R.id.nav_profile) showSection(sectionProfile);
            return true;
        });
    }

    private void showSection(View active) {
        sectionHome.setVisibility(active == sectionHome ? View.VISIBLE : View.GONE);
        sectionRoms.setVisibility(active == sectionRoms ? View.VISIBLE : View.GONE);
        sectionModules.setVisibility(active == sectionModules ? View.VISIBLE : View.GONE);
        sectionGuides.setVisibility(active == sectionGuides ? View.VISIBLE : View.GONE);
        sectionProfile.setVisibility(active == sectionProfile ? View.VISIBLE : View.GONE);
    }

    private void setupAdapters() {
        RomAdapter.OnRomClickListener romClickListener = new RomAdapter.OnRomClickListener() {
            @Override
            public void onRomClick(RomModel rom) {
                startActivity(new Intent(DashboardActivity.this, RomDetailActivity.class)
                        .putExtra(RomDetailActivity.EXTRA_ROM, rom));
            }

            @Override
            public void onDownloadClick(RomModel rom) {
                openWebLink(rom.getDownloadUrl());
            }

            @Override
            public void onWebsiteClick(RomModel rom) {
                openWebLink(!rom.getWebsiteUrl().isEmpty() ? rom.getWebsiteUrl() : rom.getDownloadUrl());
            }

            @Override
            public void onGitHubClick(RomModel rom) {
                openWebLink(!rom.getGithubUrl().isEmpty() ? rom.getGithubUrl() : rom.getWebsiteUrl());
            }

            @Override
            public void onCopyLinkClick(RomModel rom) {
                copyToClipboard("ROM Download Link", !rom.getDownloadUrl().isEmpty() ? rom.getDownloadUrl() : rom.getGithubUrl());
            }
        };

        // Home Featured ROMs
        RecyclerView rvHomeFeatured = findViewById(R.id.rvHomeFeaturedRoms);
        rvHomeFeatured.setLayoutManager(new LinearLayoutManager(this));
        homeFeaturedAdapter = new RomAdapter(romClickListener);
        rvHomeFeatured.setAdapter(homeFeaturedAdapter);

        // ROMs Catalog
        RecyclerView rvRoms = findViewById(R.id.rvRomsCatalog);
        rvRoms.setLayoutManager(new LinearLayoutManager(this));
        romsCatalogAdapter = new RomAdapter(romClickListener);
        rvRoms.setAdapter(romsCatalogAdapter);

        // Modules Catalog
        RecyclerView rvModules = findViewById(R.id.rvModulesCatalog);
        rvModules.setLayoutManager(new LinearLayoutManager(this));
        modulesAdapter = new ModuleAdapter(new ModuleAdapter.OnModuleClickListener() {
            @Override
            public void onModuleClick(ModuleModel module) {
                openWebLink(module.getDownloadUrl());
            }

            @Override
            public void onDownloadClick(ModuleModel module) {
                openWebLink(module.getDownloadUrl());
            }

            @Override
            public void onGitHubClick(ModuleModel module) {
                openWebLink(module.getGithubUrl());
            }

            @Override
            public void onCopyLinkClick(ModuleModel module) {
                copyToClipboard("Module Link", !module.getDownloadUrl().isEmpty() ? module.getDownloadUrl() : module.getGithubUrl());
            }
        });
        rvModules.setAdapter(modulesAdapter);

        // Guides
        RecyclerView rvGuides = findViewById(R.id.rvGuides);
        rvGuides.setLayoutManager(new LinearLayoutManager(this));
        GuideAdapter guidesAdapter = new GuideAdapter(guide ->
                startActivity(new Intent(DashboardActivity.this, GuideDetailActivity.class)
                        .putExtra(GuideDetailActivity.EXTRA_GUIDE, guide)));
        rvGuides.setAdapter(guidesAdapter);
        guidesAdapter.updateData(repository.getGuides());
    }

    private void setupListeners() {
        // Device Selector triggers
        View.OnClickListener openDeviceSelector = v ->
                deviceSelectorLauncher.launch(new Intent(this, DeviceSelectorActivity.class));

        findViewById(R.id.btnDeviceSelectorPill).setOnClickListener(openDeviceSelector);
        findViewById(R.id.btnSearchDeviceIcon).setOnClickListener(openDeviceSelector);
        findViewById(R.id.btnChangeDevice).setOnClickListener(openDeviceSelector);
        findViewById(R.id.btnProfileSearchDevice).setOnClickListener(openDeviceSelector);

        // Quick Category shortcuts
        findViewById(R.id.cardCatRoms).setOnClickListener(v -> switchRomCategory("ROM", R.id.chipRomRoms));
        findViewById(R.id.cardCatKernels).setOnClickListener(v -> switchRomCategory("KERNEL", R.id.chipRomKernels));
        findViewById(R.id.cardCatRecoveries).setOnClickListener(v -> switchRomCategory("RECOVERY", R.id.chipRomRecoveries));
        findViewById(R.id.tvViewAllRoms).setOnClickListener(v -> switchRomCategory("ALL", R.id.chipRomAll));
        findViewById(R.id.cardCatModules).setOnClickListener(v -> bottomNavigationView.setSelectedItemId(R.id.nav_modules));

        // ROMs Category filter
        chipGroupRomsCategory.setOnCheckedStateChangeListener((group, checkedIds) -> {
            int selected = checkedIds.isEmpty() ? R.id.chipRomAll : checkedIds.get(0);
            if (selected == R.id.chipRomRoms) currentRomCategory = "ROM";
            else if (selected == R.id.chipRomKernels) currentRomCategory = "KERNEL";
            else if (selected == R.id.chipRomRecoveries) currentRomCategory = "RECOVERY";
            else currentRomCategory = "ALL";
            filterRoms();
        });

        // Modules Category filter
        chipGroupModulesCategory.setOnCheckedStateChangeListener((group, checkedIds) -> {
            int selected = checkedIds.isEmpty() ? R.id.chipModAll : checkedIds.get(0);
            if (selected == R.id.chipModSafety) currentModuleCategory = "SafetyNet";
            else if (selected == R.id.chipModAudio) currentModuleCategory = "Audio";
            else if (selected == R.id.chipModCustom) currentModuleCategory = "Customization";
            else if (selected == R.id.chipModPerf) currentModuleCategory = "Performance";
            else if (selected == R.id.chipModTools) currentModuleCategory = "Tools";
            else currentModuleCategory = "ALL";
            filterModules();
        });

        // Search inputs
        onSearchChanged(etRomSearch, this::filterRoms);
        onSearchChanged(etModuleSearch, this::filterModules);

        // Contact developer page
        findViewById(R.id.btnContactUs).setOnClickListener(v ->
                startActivity(new Intent(this, ContactUsActivity.class)));
    }

    private void switchRomCategory(String category, int chipId) {
        currentRomCategory = category;
        bottomNavigationView.setSelectedItemId(R.id.nav_roms);
        chipGroupRomsCategory.check(chipId);
        filterRoms();
    }

    private void onSearchChanged(EditText editText, Runnable onSearch) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { onSearch.run(); }
            @Override public void afterTextChanged(Editable s) {}
        });
    }

    private void refreshAllData() {
        DeviceModel selected = deviceManager.getSelectedDevice();

        tvActiveDeviceName.setText(selected.getModelName() + " (" + selected.getCodename() + ")");
        tvHomeDeviceTitle.setText(selected.getDisplayName());
        tvHomeCodenameBadge.setText(selected.getCodename());
        tvHomeChipset.setText(selected.getChipset());
        tvHomeRomCount.setText(selected.getRomCount() + " Available");
        tvHomeKernelCount.setText(selected.getKernelCount() + " Available");

        filterRoms();
        filterModules();

        // Physical telemetry on Device info tab
        ((TextView) findViewById(R.id.tvTelemetryManufacturer)).setText("Manufacturer: " + Build.MANUFACTURER);
        ((TextView) findViewById(R.id.tvTelemetryModel)).setText("Model: " + Build.MODEL);
        ((TextView) findViewById(R.id.tvTelemetryCodename)).setText("Physical Codename: " + Build.DEVICE);
        ((TextView) findViewById(R.id.tvTelemetryAndroid)).setText("Android Version: " + Build.VERSION.RELEASE);
    }

    private void filterRoms() {
        DeviceModel selected = deviceManager.getSelectedDevice();
        String query = etRomSearch.getText().toString();
        List<RomModel> filtered = repository.getRomsForDevice(selected.getCodename(), currentRomCategory, "ALL", query);
        homeFeaturedAdapter.updateData(filtered);
        romsCatalogAdapter.updateData(filtered);
    }

    private void filterModules() {
        String query = etModuleSearch.getText().toString();
        modulesAdapter.updateData(repository.getModules(currentModuleCategory, query));
    }

    private void openWebLink(String url) {
        if (url == null || url.trim().isEmpty()) return;
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        } catch (Exception e) {
            Toast.makeText(this, "Could not open link", Toast.LENGTH_SHORT).show();
        }
    }

    private void copyToClipboard(String label, String text) {
        if (text == null || text.trim().isEmpty()) {
            Toast.makeText(this, "No link available to copy", Toast.LENGTH_SHORT).show();
            return;
        }
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboard != null) {
            clipboard.setPrimaryClip(ClipData.newPlainText(label, text));
            Toast.makeText(this, "Link copied to clipboard!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        if (bottomNavigationView.getSelectedItemId() != R.id.nav_home) {
            bottomNavigationView.setSelectedItemId(R.id.nav_home);
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Exit RomClub")
                    .setMessage("Do you want to close RomClub?")
                    .setPositiveButton("Exit", (dialog, which) -> finish())
                    .setNegativeButton("Cancel", null)
                    .show();
        }
    }
}