package com.example.careerguide.tools;

import com.example.careerguide.model.Course;
import com.example.careerguide.model.RoadmapStep;
import com.google.adk.tools.FunctionTool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class CourseCatalogTool implements FunctionTool<List<RoadmapStep>, List<Course>> {

    private final List<Course> catalog = new ArrayList<>();

    public CourseCatalogTool() {
        catalog.add(new Course(
                "Introduction to Programming with Java",
                "Local University",
                "Beginner",
                "https://example.com/java-intro"
        ));
        catalog.add(new Course(
                "Object-Oriented Programming in Java",
                "Online Platform",
                "Intermediate",
                "https://example.com/oop-java"
        ));
        catalog.add(new Course(
                "Data Analysis with Python",
                "Online Platform",
                "Beginner",
                "https://example.com/data-analysis-python"
        ));
        catalog.add(new Course(
                "Machine Learning Foundations",
                "Online Platform",
                "Intermediate",
                "https://example.com/ml-foundations"
        ));
        catalog.add(new Course(
                "Portfolio Project: Build a Web App",
                "Project-based",
                "Intermediate",
                "https://example.com/web-app-project"
        ));
    }

    @Override
    public String getName() {
        return "course-catalog-tool";
    }

    @Override
    public List<Course> execute(List<RoadmapStep> roadmap) {
        return findRelevantCourses(roadmap);
    }

    public List<Course> findRelevantCourses(List<RoadmapStep> roadmap) {
        List<Course> results = new ArrayList<>();
        StringBuilder allText = new StringBuilder();
        for (RoadmapStep step : roadmap) {
            allText.append(step.getTitle()).append(" ").append(step.getDescription()).append(" ");
        }
        String text = allText.toString().toLowerCase(Locale.ROOT);

        List<String> keywords = Arrays.asList("programming", "java", "web", "data", "machine learning");

        for (Course c : catalog) {
            String cText = (c.getTitle() + " " + c.getLevel()).toLowerCase(Locale.ROOT);
            boolean match = false;
            for (String k : keywords) {
                String token = k.split(" ")[0];
                if (text.contains(token) && cText.contains(token)) {
                    match = true;
                    break;
                }
            }
            if (match || results.isEmpty()) {
                results.add(c);
            }
        }
        if (results.isEmpty()) results.addAll(catalog);
        return results;
    }
}
