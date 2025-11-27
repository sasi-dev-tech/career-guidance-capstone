package com.example.careerguide.llm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MockGeminiClient implements GeminiClient {

    private static final Logger log = LoggerFactory.getLogger(MockGeminiClient.class);

    @Override
    public String generateJson(String prompt) {
        String truncated = prompt.replace("\n", " ");
        if (truncated.length() > 120) truncated = truncated.substring(0, 120) + "...";
        log.debug("MockGeminiClient prompt: {}", truncated);

        String lower = prompt.toLowerCase();

        if (lower.contains("student profile") && lower.contains("target career")) {
            return "[{\"title\":\"Strengthen core fundamentals\",\"timeframe\":\"1–2 months\"," +
                   "\"description\":\"Review core concepts relevant to the chosen career using structured learning resources.\"}," +
                   "{\"title\":\"Build a portfolio project\",\"timeframe\":\"2–3 months\"," +
                   "\"description\":\"Create at least one project that can be shown to recruiters or mentors.\"}]";
        } else if (lower.contains("recommend 3 to 5 careers")) {
            return "[" +
                    "{\"name\":\"Software Engineer\",\"shortDescription\":\"Designs and builds software applications.\"," +
                    "\"matchReason\":\"Matches students who enjoy problem solving, coding, and creating digital products.\"}," +
                    "{\"name\":\"Data Analyst\",\"shortDescription\":\"Analyses data to uncover patterns and insights.\"," +
                    "\"matchReason\":\"Good for students who like working with numbers and structured thinking.\"}" +
                    "]";
        } else {
            return "{" +
                    "\"skills\":[\"problem solving\",\"basic programming\",\"communication\"]," +
                    "\"interests\":[\"technology\",\"learning new things\"]," +
                    "\"workStyle\":\"mixed\"," +
                    "\"constraints\":[]" +
                    "}";
        }
    }
}
