package com.example.careerguide.agent;

import com.example.careerguide.llm.GeminiClient;
import com.example.careerguide.model.CareerOption;
import com.example.careerguide.model.RoadmapStep;
import com.example.careerguide.model.StudentProfile;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.adk.agents.LlmAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class RoadmapAgent extends LlmAgent {

    private static final Logger log = LoggerFactory.getLogger(RoadmapAgent.class);

    private final GeminiClient geminiClient;
    private final ObjectMapper mapper = new ObjectMapper();

    public RoadmapAgent(GeminiClient geminiClient) {
        super("roadmap-agent");
        this.geminiClient = geminiClient;
    }

    public List<RoadmapStep> buildRoadmap(StudentProfile profile, CareerOption career) {
        log.info("Running RoadmapAgent");

        if (career == null) {
            log.warn("No career provided, returning empty roadmap");
            return new ArrayList<>();
        }

        String prompt = "You are a career roadmap planner.\n" +
                "Create a high-level learning roadmap (4–6 steps) for the following student profile targeting this career.\n" +
                "Each step should have title, timeframe, and description.\n" +
                "Return strictly JSON array of objects with keys: title, timeframe, description.\n\n" +
                "Student profile:\n" + profile.toString() + "\n\n" +
                "Target career:\n" + career.toString();

        String json = geminiClient.generateJson(prompt);
        try {
            return mapper.readValue(json, new TypeReference<List<RoadmapStep>>() {});
        } catch (Exception e) {
            log.warn("Failed to parse roadmap, using fallback. Error: {}", e.getMessage());
            List<RoadmapStep> fallback = new ArrayList<>();
            fallback.add(new RoadmapStep(
                    "Learn programming fundamentals",
                    "1–2 months",
                    "Focus on basic syntax, control structures, and problem solving using one language such as Java or Python."
            ));
            fallback.add(new RoadmapStep(
                    "Build small projects",
                    "2–3 months",
                    "Implement simple console or web applications to practice real-world problem solving."
            ));
            return fallback;
        }
    }
}
