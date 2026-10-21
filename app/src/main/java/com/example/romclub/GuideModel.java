package com.example.romclub;

import java.io.Serializable;
import java.util.List;

public class GuideModel implements Serializable {
    private String id;
    private String title;
    private String subtitle;
    private String difficulty; // "Beginner", "Intermediate", "Advanced"
    private String estimatedTime;
    private String warning;
    private List<String> steps;

    public GuideModel() {
    }

    public GuideModel(String id, String title, String subtitle, String difficulty,
                      String estimatedTime, String warning, List<String> steps) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.difficulty = difficulty;
        this.estimatedTime = estimatedTime;
        this.warning = warning;
        this.steps = steps;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getEstimatedTime() {
        return estimatedTime;
    }

    public String getWarning() {
        return warning;
    }

    public List<String> getSteps() {
        return steps;
    }
}
