package com.example.careerguide.model;

import com.google.adk.tools.Annotations.Schema;

@Schema(description = "Course metadata used by the course suggestion tool.")
public class Course {

    private String title;
    private String platform;
    private String level;
    private String url;

    public Course() {}
    public Course(String title, String platform, String level, String url) {
        this.title = title;
        this.platform = platform;
        this.level = level;
        this.url = url;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getPlatform() { return platform; }
    public void setPlatform(String platform) { this.platform = platform; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    @Override
    public String toString() {
        return "Course{" +
                "title='" + title + '\'' +
                ", platform='" + platform + '\'' +
                ", level='" + level + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
