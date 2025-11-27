package com.example.careerguide.agent;

import com.example.careerguide.llm.GeminiClient;
import com.example.careerguide.memory.InMemorySessionStore;
import com.example.careerguide.memory.StudentMemoryBank;
import com.example.careerguide.model.*;
import com.example.careerguide.tools.CourseCatalogTool;
import com.google.adk.agents.BaseAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class OrchestratorAgent extends BaseAgent {

    private static final Logger log = LoggerFactory.getLogger(OrchestratorAgent.class);

    private final StudentSkillAnalysisAgent skillAnalysisAgent;
    private final CareerRecommendationAgent careerRecommendationAgent;
    private final RoadmapAgent roadmapAgent;
    private final CourseSuggestionAgent courseSuggestionAgent;
    private final InMemorySessionStore sessionStore;
    private final StudentMemoryBank memoryBank;

    public OrchestratorAgent(StudentSkillAnalysisAgent skillAnalysisAgent,
                             CareerRecommendationAgent careerRecommendationAgent,
                             RoadmapAgent roadmapAgent,
                             CourseSuggestionAgent courseSuggestionAgent,
                             InMemorySessionStore sessionStore,
                             StudentMemoryBank memoryBank) {
        super("orchestrator");
        this.skillAnalysisAgent = skillAnalysisAgent;
        this.careerRecommendationAgent = careerRecommendationAgent;
        this.roadmapAgent = roadmapAgent;
        this.courseSuggestionAgent = courseSuggestionAgent;
        this.sessionStore = sessionStore;
        this.memoryBank = memoryBank;
    }

    public static OrchestratorAgent build(GeminiClient geminiClient,
                                          CourseCatalogTool courseCatalogTool,
                                          InMemorySessionStore sessionStore,
                                          StudentMemoryBank memoryBank) {

        StudentSkillAnalysisAgent skillAnalysisAgent = new StudentSkillAnalysisAgent(geminiClient);
        CareerRecommendationAgent careerRecommendationAgent = new CareerRecommendationAgent(geminiClient);
        RoadmapAgent roadmapAgent = new RoadmapAgent(geminiClient);
        CourseSuggestionAgent courseSuggestionAgent = new CourseSuggestionAgent(courseCatalogTool, geminiClient);

        return new OrchestratorAgent(
                skillAnalysisAgent,
                careerRecommendationAgent,
                roadmapAgent,
                courseSuggestionAgent,
                sessionStore,
                memoryBank
        );
    }

    public GuidanceResult runGuidanceSession(String studentId,
                                             String questionnaireText,
                                             String resumeText) {
        log.info("Starting guidance session for studentId={}", studentId);

        GuidanceResult result = new GuidanceResult();

        StudentProfile existingProfile = memoryBank.loadProfile(studentId);
        StudentProfile profile;
        if (existingProfile != null) {
            log.info("Found existing profile for studentId={}, updating", studentId);
            profile = skillAnalysisAgent.updateProfile(existingProfile, questionnaireText, resumeText);
        } else {
            profile = skillAnalysisAgent.analyze(questionnaireText, resumeText);
        }
        result.setProfile(profile);
        sessionStore.put(studentId, "profile", profile);
        memoryBank.saveProfile(studentId, profile);

        List<CareerOption> careers = careerRecommendationAgent.recommendCareers(profile);
        result.setRecommendedCareers(careers);
        CareerOption chosen = careers.isEmpty() ? null : careers.get(0);
        result.setChosenCareer(chosen);
        sessionStore.put(studentId, "chosenCareer", chosen);

        List<RoadmapStep> roadmap = roadmapAgent.buildRoadmap(profile, chosen);
        result.setRoadmap(roadmap);
        sessionStore.put(studentId, "roadmap", roadmap);

        List<Course> courses = courseSuggestionAgent.suggestCourses(roadmap);
        result.setSuggestedCourses(courses);

        log.info("Completed guidance session for studentId={}", studentId);
        return result;
    }
}
