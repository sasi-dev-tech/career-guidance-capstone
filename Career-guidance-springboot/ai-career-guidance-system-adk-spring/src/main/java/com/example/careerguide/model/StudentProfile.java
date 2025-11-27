package com.example.careerguide.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.adk.tools.Annotations.Schema;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Schema(description = "Structured student skill and interest profile.")
public class StudentProfile {

    private List<String> skills = new ArrayList<>();
    private List<String> interests = new ArrayList<>();
    private String workStyle;
    private List<String> constraints = new ArrayList<>();

    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) { this.skills = skills; }

    public List<String> getInterests() { return interests; }
    public void setInterests(List<String> interests) { this.interests = interests; }

    public String getWorkStyle() { return workStyle; }
    public void setWorkStyle(String workStyle) { this.workStyle = workStyle; }

    public List<String> getConstraints() { return constraints; }
    public void setConstraints(List<String> constraints) { this.constraints = constraints; }

    public static StudentProfile fromJson(String json) {
        StudentProfile profile = new StudentProfile();
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(json);
            if (root.has("skills") && root.get("skills").isArray()) {
                root.get("skills").forEach(n -> profile.skills.add(n.asText()));
            }
            if (root.has("interests") && root.get("interests").isArray()) {
                root.get("interests").forEach(n -> profile.interests.add(n.asText()));
            }
            if (root.has("workStyle")) profile.workStyle = root.get("workStyle").asText();
            if (root.has("constraints") && root.get("constraints").isArray()) {
                root.get("constraints").forEach(n -> profile.constraints.add(n.asText()));
            }
        } catch (IOException e) {
            // ignore, keep defaults
        }
        return profile;
    }

    public void mergeFrom(StudentProfile other) {
        if (other == null) return;
        for (String s : other.skills) if (!this.skills.contains(s)) this.skills.add(s);
        for (String i : other.interests) if (!this.interests.contains(i)) this.interests.add(i);
        if (other.workStyle != null && !Objects.equals(other.workStyle, this.workStyle)) {
            this.workStyle = other.workStyle;
        }
        for (String c : other.constraints) if (!this.constraints.contains(c)) this.constraints.add(c);
    }

    public String toPrettyString() {
        return "Skills: " + skills + "\n" +
               "Interests: " + interests + "\n" +
               "Work style: " + workStyle + "\n" +
               "Constraints: " + constraints + "\n";
    }

    @Override
    public String toString() {
        return "StudentProfile{" +
                "skills=" + skills +
                ", interests=" + interests +
                ", workStyle='" + workStyle + '\'' +
                ", constraints=" + constraints +
                '}';
    }
}
