package com.example.careerguide.agent;

import com.example.careerguide.llm.GeminiClient;
import com.example.careerguide.model.Course;
import com.example.careerguide.model.RoadmapStep;
import com.example.careerguide.tools.CourseCatalogTool;
import com.google.adk.agents.BaseAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CourseSuggestionAgent extends BaseAgent {

    private static final Logger log = LoggerFactory.getLogger(CourseSuggestionAgent.class);

    private final CourseCatalogTool courseCatalogTool;
    @SuppressWarnings("unused")
    private final GeminiClient geminiClient;

    public CourseSuggestionAgent(CourseCatalogTool courseCatalogTool, GeminiClient geminiClient) {
        super("course-suggestion");
        this.courseCatalogTool = courseCatalogTool;
        this.geminiClient = geminiClient;
    }

    public List<Course> suggestCourses(List<RoadmapStep> roadmap) {
        log.info("Running CourseSuggestionAgent");
        if (roadmap == null || roadmap.isEmpty()) return new ArrayList<>();
        return courseCatalogTool.findRelevantCourses(roadmap);
    }
}
