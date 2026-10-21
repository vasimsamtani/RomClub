package com.example.romclub;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DeviceManager {
    private static final String PREF_NAME = "romclub_device_prefs";
    private static final String KEY_SELECTED_CODENAME = "selected_device_codename";

    private final Context context;
    private final SharedPreferences prefs;
    private final List<DeviceModel> supportedDevices = new ArrayList<>();
    private DeviceModel userDetectedDevice;

    public DeviceManager(Context context) {
        this.context = context.getApplicationContext();
        this.prefs = this.context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        initDevices();
    }

    private void initDevices() {
        // Detect current user physical/emulated device
        String userManufacturer = capitalize(Build.MANUFACTURER);
        String userModel = Build.MODEL;
        String userCodename = Build.DEVICE != null && !Build.DEVICE.isEmpty() ? Build.DEVICE.toLowerCase(Locale.US) : "generic";
        String userBoard = Build.BOARD != null ? Build.BOARD : "Unknown SoC";

        userDetectedDevice = new DeviceModel(
                "user_device",
                userManufacturer,
                userModel,
                userCodename,
                userBoard,
                true,
                6,
                4
        );
        supportedDevices.add(userDetectedDevice);

        // Community Favorites - Google Pixel
        supportedDevices.add(new DeviceModel("px8p", "Google", "Pixel 8 Pro", "husky", "Google Tensor G3", false, 8, 5));
        supportedDevices.add(new DeviceModel("px8", "Google", "Pixel 8", "shiba", "Google Tensor G3", false, 7, 4));
        supportedDevices.add(new DeviceModel("px7p", "Google", "Pixel 7 Pro", "cheetah", "Google Tensor G2", false, 12, 6));
        supportedDevices.add(new DeviceModel("px7", "Google", "Pixel 7", "panther", "Google Tensor G2", false, 11, 5));
        supportedDevices.add(new DeviceModel("px6a", "Google", "Pixel 6a", "bluejay", "Google Tensor G1", false, 9, 4));

        // Xiaomi & POCO & Redmi
        supportedDevices.add(new DeviceModel("pocof3", "Xiaomi / POCO", "POCO F3 / Redmi K40", "alioth", "Snapdragon 870", false, 18, 9));
        supportedDevices.add(new DeviceModel("pocox3p", "Xiaomi / POCO", "POCO X3 Pro", "vayu", "Snapdragon 860", false, 15, 8));
        supportedDevices.add(new DeviceModel("rn10p", "Xiaomi / Redmi", "Redmi Note 10 Pro", "sweet", "Snapdragon 732G", false, 20, 7));
        supportedDevices.add(new DeviceModel("pocof5", "Xiaomi / POCO", "POCO F5 / Redmi Note 12 Turbo", "marble", "Snapdragon 7+ Gen 2", false, 10, 6));
        supportedDevices.add(new DeviceModel("pocof1", "Xiaomi / POCO", "POCO F1", "beryllium", "Snapdragon 845", false, 25, 12));

        // OnePlus
        supportedDevices.add(new DeviceModel("op9p", "OnePlus", "OnePlus 9 Pro", "lemonade", "Snapdragon 888", false, 12, 5));
        supportedDevices.add(new DeviceModel("op8t", "OnePlus", "OnePlus 8T", "kebab", "Snapdragon 865", false, 14, 6));
        supportedDevices.add(new DeviceModel("op7p", "OnePlus", "OnePlus 7 Pro", "guacamole", "Snapdragon 855", false, 16, 7));
        supportedDevices.add(new DeviceModel("opnord", "OnePlus", "OnePlus Nord", "avicii", "Snapdragon 765G", false, 9, 3));

        // Nothing
        supportedDevices.add(new DeviceModel("np1", "Nothing", "Nothing Phone (1)", "spacewar", "Snapdragon 778G+", false, 11, 5));
        supportedDevices.add(new DeviceModel("np2", "Nothing", "Nothing Phone (2)", "pong", "Snapdragon 8+ Gen 1", false, 8, 4));

        // Samsung
        supportedDevices.add(new DeviceModel("s20fe", "Samsung", "Galaxy S20 FE (Snapdragon)", "r8q", "Snapdragon 865", false, 7, 3));
        supportedDevices.add(new DeviceModel("a52s", "Samsung", "Galaxy A52s 5G", "a52sxq", "Snapdragon 778G", false, 9, 4));

        // Motorola
        supportedDevices.add(new DeviceModel("motoe30", "Motorola", "Moto Edge 30", "dubai", "Snapdragon 778G+", false, 6, 2));

        // Realme
        supportedDevices.add(new DeviceModel("realmegt2", "Realme", "Realme GT Neo 2", "bitra", "Snapdragon 870", false, 8, 3));
    }

    public List<DeviceModel> getAllDevices() {
        return new ArrayList<>(supportedDevices);
    }

    public DeviceModel getUserDetectedDevice() {
        return userDetectedDevice;
    }

    public DeviceModel getSelectedDevice() {
        String savedCodename = prefs.getString(KEY_SELECTED_CODENAME, userDetectedDevice.getCodename());
        for (DeviceModel device : supportedDevices) {
            if (device.getCodename().equalsIgnoreCase(savedCodename)) {
                return device;
            }
        }
        return userDetectedDevice;
    }

    public void setSelectedDevice(DeviceModel device) {
        prefs.edit().putString(KEY_SELECTED_CODENAME, device.getCodename()).apply();
    }

    public void resetToUserDevice() {
        setSelectedDevice(userDetectedDevice);
    }

    public List<DeviceModel> filterDevices(String query, String brandFilter) {
        List<DeviceModel> filtered = new ArrayList<>();
        String lowerQuery = query == null ? "" : query.trim().toLowerCase(Locale.US);
        boolean filterBrand = brandFilter != null && !brandFilter.equalsIgnoreCase("All");

        for (DeviceModel device : supportedDevices) {
            boolean matchesBrand = !filterBrand || device.getBrand().toLowerCase(Locale.US).contains(brandFilter.toLowerCase(Locale.US));
            boolean matchesQuery = lowerQuery.isEmpty()
                    || device.getModelName().toLowerCase(Locale.US).contains(lowerQuery)
                    || device.getCodename().toLowerCase(Locale.US).contains(lowerQuery)
                    || device.getBrand().toLowerCase(Locale.US).contains(lowerQuery)
                    || device.getChipset().toLowerCase(Locale.US).contains(lowerQuery);

            if (matchesBrand && matchesQuery) {
                filtered.add(device);
            }
        }
        return filtered;
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return "";
        return str.substring(0, 1).toUpperCase(Locale.US) + str.substring(1).toLowerCase(Locale.US);
    }
}
