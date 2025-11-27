package com.example.careerguide.agent;

import com.example.careerguide.llm.GeminiClient;
import com.example.careerguide.model.StudentProfile;
import com.google.adk.agents.LlmAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudentSkillAnalysisAgent extends LlmAgent {

    private static final Logger log = LoggerFactory.getLogger(StudentSkillAnalysisAgent.class);

    private final GeminiClient geminiClient;

    public StudentSkillAnalysisAgent(GeminiClient geminiClient) {
        super("student-skill-analysis");
        this.geminiClient = geminiClient;
    }

    public StudentProfile analyze(String questionnaire, String resumeText) {
        log.info("Running StudentSkillAnalysisAgent");

        String prompt = "You are a career guidance assistant.\n" +
                "Analyse the following student information and extract:\n" +
                "- 5 to 10 key skills as simple strings\n" +
                "- top interests (3 to 5)\n" +
                "- preferred work style (team / solo / mixed)\n" +
                "- constraints (short phrases)\n\n" +
                "Return strictly JSON with the keys:\n" +
                "skills (array of strings),\n" +
                "interests (array of strings),\n" +
                "workStyle (string),\n" +
                "constraints (array of strings).\n\n" +
                "Questionnaire:\n" + questionnaire + "\n\n" +
                "Resume:\n" + resumeText;

        String json = geminiClient.generateJson(prompt);
        StudentProfile profile = StudentProfile.fromJson(json);
        if (profile.getSkills().isEmpty()) profile.getSkills().add("problem solving");
        return profile;
    }

    public StudentProfile updateProfile(StudentProfile base,
                                        String questionnaire,
                                        String resumeText) {
        StudentProfile newProfile = analyze(questionnaire, resumeText);
        base.mergeFrom(newProfile);
        return base;
    }
}
