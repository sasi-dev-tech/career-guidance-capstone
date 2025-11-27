package com.example.careerguide.model;

import com.google.adk.tools.Annotations.Schema;

@Schema(description = "One step in the personalised learning roadmap.")
public class RoadmapStep {

    private String title;
    private String timeframe;
    private String description;

    public RoadmapStep() {}
    public RoadmapStep(String title, String timeframe, String description) {
        this.title = title;
        this.timeframe = timeframe;
        this.description = description;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getTimeframe() { return timeframe; }
    public void setTimeframe(String timeframe) { this.timeframe = timeframe; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return "RoadmapStep{" +
                "title='" + title + '\'' +
                ", timeframe='" + timeframe + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
