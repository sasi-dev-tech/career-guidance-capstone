package com.example.careerguide.model;

import com.google.adk.tools.Annotations.Schema;

@Schema(description = "A recommended career option for the student.")
public class CareerOption {

    private String name;
    private String shortDescription;
    private String matchReason;

    public CareerOption() {}
    public CareerOption(String name, String shortDescription, String matchReason) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.matchReason = matchReason;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getShortDescription() { return shortDescription; }
    public void setShortDescription(String shortDescription) { this.shortDescription = shortDescription; }

    public String getMatchReason() { return matchReason; }
    public void setMatchReason(String matchReason) { this.matchReason = matchReason; }

    @Override
    public String toString() {
        return "CareerOption{" +
                "name='" + name + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", matchReason='" + matchReason + '\'' +
                '}';
    }
}
