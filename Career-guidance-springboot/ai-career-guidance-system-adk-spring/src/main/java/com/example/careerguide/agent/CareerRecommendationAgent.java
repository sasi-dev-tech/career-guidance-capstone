package com.example.careerguide.agent;

import com.example.careerguide.llm.GeminiClient;
import com.example.careerguide.model.CareerOption;
import com.example.careerguide.model.StudentProfile;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.adk.agents.LlmAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CareerRecommendationAgent extends LlmAgent {

    private static final Logger log = LoggerFactory.getLogger(CareerRecommendationAgent.class);

    private final GeminiClient geminiClient;
    private final ObjectMapper mapper = new ObjectMapper();

    public CareerRecommendationAgent(GeminiClient geminiClient) {
        super("career-recommendation");
        this.geminiClient = geminiClient;
    }

    public List<CareerOption> recommendCareers(StudentProfile profile) {
        log.info("Running CareerRecommendationAgent");

        String prompt = "You are an expert career counsellor.\n" +
                "Based on the following student profile, recommend 3 to 5 careers that would be a good match.\n" +
                "For each career, provide: name, shortDescription, matchReason.\n" +
                "Return strictly JSON array of objects with keys: name, shortDescription, matchReason.\n\n" +
                "Student profile:\n" + profile.toString();

        String json = geminiClient.generateJson(prompt);
        try {
            return mapper.readValue(json, new TypeReference<List<CareerOption>>() {});
        } catch (Exception e) {
            log.warn("Failed to parse career recommendations, using fallback. Error: {}", e.getMessage());
            List<CareerOption> fallback = new ArrayList<>();
            fallback.add(new CareerOption("Software Engineer",
                    "Designs and builds software applications.",
                    "Good match for students interested in programming, problem solving, and technology."));
            fallback.add(new CareerOption("Data Analyst",
                    "Analyses data to extract insights.",
                    "Fits students who enjoy working with numbers and patterns."));
            return fallback;
        }
    }
}
