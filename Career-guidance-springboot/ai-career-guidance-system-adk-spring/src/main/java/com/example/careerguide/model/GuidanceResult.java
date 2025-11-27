package com.example.careerguide.model;

import com.google.adk.tools.Annotations.Schema;

import java.util.List;
import java.util.StringJoiner;

@Schema(description = "Combined output of the guidance session.")
public class GuidanceResult {

    private StudentProfile profile;
    private List<CareerOption> recommendedCareers;
    private CareerOption chosenCareer;
    private List<RoadmapStep> roadmap;
    private List<Course> suggestedCourses;

    public StudentProfile getProfile() { return profile; }
    public void setProfile(StudentProfile profile) { this.profile = profile; }

    public List<CareerOption> getRecommendedCareers() { return recommendedCareers; }
    public void setRecommendedCareers(List<CareerOption> recommendedCareers) { this.recommendedCareers = recommendedCareers; }

    public CareerOption getChosenCareer() { return chosenCareer; }
    public void setChosenCareer(CareerOption chosenCareer) { this.chosenCareer = chosenCareer; }

    public List<RoadmapStep> getRoadmap() { return roadmap; }
    public void setRoadmap(List<RoadmapStep> roadmap) { this.roadmap = roadmap; }

    public List<Course> getSuggestedCourses() { return suggestedCourses; }
    public void setSuggestedCourses(List<Course> suggestedCourses) { this.suggestedCourses = suggestedCourses; }

    public String toPrettyString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student Profile:\n");
        if (profile != null) sb.append(profile.toPrettyString()).append("\n");

        sb.append("Recommended Careers:\n");
        if (recommendedCareers != null) {
            int idx = 1;
            for (CareerOption option : recommendedCareers) {
                sb.append("  ").append(idx++).append(". ")
                  .append(option.getName())
                  .append(" - ").append(option.getShortDescription())
                  .append("\n");
            }
        }

        if (chosenCareer != null) {
            sb.append("\nChosen Career:\n");
            sb.append("  ").append(chosenCareer.getName())
              .append(" - ").append(chosenCareer.getShortDescription())
              .append("\n");
        }

        if (roadmap != null && !roadmap.isEmpty()) {
            sb.append("\nRoadmap:\n");
            for (RoadmapStep step : roadmap) {
                sb.append("  - ").append(step.getTitle())
                  .append(" (").append(step.getTimeframe()).append(")\n");
                sb.append("    ").append(step.getDescription()).append("\n");
            }
        }

        if (suggestedCourses != null && !suggestedCourses.isEmpty()) {
            sb.append("\nSuggested Courses:\n");
            for (Course c : suggestedCourses) {
                StringJoiner joiner = new StringJoiner(" | ");
                joiner.add(c.getTitle());
                if (c.getPlatform() != null) joiner.add("Platform: " + c.getPlatform());
                if (c.getLevel() != null) joiner.add("Level: " + c.getLevel());
                if (c.getUrl() != null) joiner.add(c.getUrl());
                sb.append("  - ").append(joiner).append("\n");
            }
        }

        return sb.toString();
    }
}
