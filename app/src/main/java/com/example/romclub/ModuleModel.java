package com.example.romclub;

import java.io.Serializable;

public class ModuleModel implements Serializable {
    private String id;
    private String name;
    private String category; // "Audio", "Customization", "Performance", "SafetyNet", "Tools"
    private String author;
    private String version;
    private String compatibility; // "Magisk / KernelSU / APatch"
    private String description;
    private String downloadUrl;
    private String githubUrl;
    private String supportUrl;

    public ModuleModel() {
    }

    public ModuleModel(String id, String name, String category, String author,
                       String version, String compatibility, String description,
                       String downloadUrl, String githubUrl) {
        this(id, name, category, author, version, compatibility, description, downloadUrl, githubUrl, "");
    }

    public ModuleModel(String id, String name, String category, String author,
                       String version, String compatibility, String description,
                       String downloadUrl, String githubUrl, String supportUrl) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.author = author;
        this.version = version;
        this.compatibility = compatibility;
        this.description = description;
        this.downloadUrl = downloadUrl;
        this.githubUrl = githubUrl;
        this.supportUrl = supportUrl != null ? supportUrl : "";
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getAuthor() {
        return author;
    }

    public String getVersion() {
        return version;
    }

    public String getCompatibility() {
        return compatibility;
    }

    public String getDescription() {
        return description;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public String getSupportUrl() {
        return supportUrl;
    }
}
