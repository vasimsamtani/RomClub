package com.example.romclub;

import java.io.Serializable;

public class DeviceModel implements Serializable {
    private String id;
    private String brand;
    private String modelName;
    private String codename;
    private String chipset;
    private boolean isUserDevice;
    private int romCount;
    private int kernelCount;

    public DeviceModel() {
    }

    public DeviceModel(String id, String brand, String modelName, String codename, String chipset, boolean isUserDevice, int romCount, int kernelCount) {
        this.id = id;
        this.brand = brand;
        this.modelName = modelName;
        this.codename = codename;
        this.chipset = chipset;
        this.isUserDevice = isUserDevice;
        this.romCount = romCount;
        this.kernelCount = kernelCount;
    }

    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModelName() {
        return modelName;
    }

    public String getCodename() {
        return codename;
    }

    public String getChipset() {
        return chipset;
    }

    public boolean isUserDevice() {
        return isUserDevice;
    }

    public void setUserDevice(boolean userDevice) {
        isUserDevice = userDevice;
    }

    public int getRomCount() {
        return romCount;
    }

    public int getKernelCount() {
        return kernelCount;
    }

    public String getDisplayName() {
        return brand + " " + modelName;
    }
}
