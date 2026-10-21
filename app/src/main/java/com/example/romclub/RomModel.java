package com.example.romclub;

import java.io.Serializable;

public class RomModel implements Serializable {
    private String id;
    private String title;
    private String deviceCodename;
    private String androidVersion;
    private String type; // "Official" or "Unofficial"
    private String maintainer;
    private String releaseDate;
    private String fileSize;
    private String changelog;
    private String downloadUrl;
    private String sha256;
    private String category; // "ROM", "KERNEL", "RECOVERY"
    private boolean isGappsIncluded;
    private String websiteUrl;
    private String supportUrl;
    private String githubUrl;

    public RomModel() {
    }

    public RomModel(String id, String title, String deviceCodename, String androidVersion,
                    String type, String maintainer, String releaseDate, String fileSize,
                    String changelog, String downloadUrl, String sha256, String category,
                    boolean isGappsIncluded) {
        this(id, title, deviceCodename, androidVersion, type, maintainer, releaseDate, fileSize,
                changelog, downloadUrl, sha256, category, isGappsIncluded, "", "", "");
    }

    public RomModel(String id, String title, String deviceCodename, String androidVersion,
                    String type, String maintainer, String releaseDate, String fileSize,
                    String changelog, String downloadUrl, String sha256, String category,
                    boolean isGappsIncluded, String websiteUrl, String supportUrl) {
        this(id, title, deviceCodename, androidVersion, type, maintainer, releaseDate, fileSize,
                changelog, downloadUrl, sha256, category, isGappsIncluded, websiteUrl, supportUrl, "");
    }

    public RomModel(String id, String title, String deviceCodename, String androidVersion,
                    String type, String maintainer, String releaseDate, String fileSize,
                    String changelog, String downloadUrl, String sha256, String category,
                    boolean isGappsIncluded, String websiteUrl, String supportUrl, String githubUrl) {
        this.id = id;
        this.title = title;
        this.deviceCodename = deviceCodename;
        this.androidVersion = androidVersion;
        this.type = type;
        this.maintainer = maintainer;
        this.releaseDate = releaseDate;
        this.fileSize = fileSize;
        this.changelog = changelog;
        this.downloadUrl = downloadUrl;
        this.sha256 = sha256;
        this.category = category;
        this.isGappsIncluded = isGappsIncluded;
        this.websiteUrl = websiteUrl != null ? websiteUrl : "";
        this.supportUrl = supportUrl != null ? supportUrl : "";
        this.githubUrl = githubUrl != null ? githubUrl : "";
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDeviceCodename() {
        return deviceCodename;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public String getType() {
        return type;
    }

    public String getMaintainer() {
        return maintainer;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getFileSize() {
        return fileSize;
    }

    public String getChangelog() {
        return changelog;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public String getSha256() {
        return sha256;
    }

    public String getCategory() {
        return category;
    }

    public boolean isGappsIncluded() {
        return isGappsIncluded;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public String getSupportUrl() {
        return supportUrl;
    }

    public String getGithubUrl() {
        return githubUrl;
    }
}
