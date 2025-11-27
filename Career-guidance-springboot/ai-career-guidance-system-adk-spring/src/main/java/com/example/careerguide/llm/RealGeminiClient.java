package com.example.careerguide.llm;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RealGeminiClient implements GeminiClient {

    private static final Logger log = LoggerFactory.getLogger(RealGeminiClient.class);

    private final String apiKey;
    private final String model;
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public RealGeminiClient(String apiKey, String model) {
        this.apiKey = apiKey;
        this.model = model;
    }


  /*  @Override
    public String generateJson(String prompt) {
        try {
            String url = "https://generativelanguage.googleapis.com/v1beta/models/"
                    + model + ":generateContent?key=" + apiKey;

            ObjectMapper mapper = new ObjectMapper();

            // Correct request format for Gemini
            String requestBody = mapper.writeValueAsString(
                    Map.of(
                            "contents", List.of(
                                    Map.of(
                                            "role", "user",
                                            "parts", List.of(
                                                    Map.of("text", prompt)
                                            )
                                    )
                            )
                    )
            );

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                log.warn("Gemini API error {} -> {}", response.statusCode(), response.body());
                return "{}";
            }

            JsonNode root = mapper.readTree(response.body());
            return root.path("candidates").get(0).path("content").path("parts").get(0).path("text").asText();

        } catch (Exception e) {
            log.error("Error calling Gemini API: {}", e.getMessage());
            return "{}";
        }
    }
}*/



    @Override
    public String generateJson(String prompt) {
        try {
            String url = "https://generativelanguage.googleapis.com/v1beta/models/" +
                    model + ":generateContent?key=" + apiKey;

            String body = mapper.createObjectNode()
                    .putArray("contents")
                    .addObject()
                    .putArray("parts")
                    .addObject()
                    .put("text", prompt)
                    //.parent().parent().parent()
                    .toString();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                log.warn("Gemini API status {} body: {}", response.statusCode(), response.body());
                return "{}";
            }

            JsonNode root = mapper.readTree(response.body());
            JsonNode candidates = root.path("candidates");
            if (candidates.isArray() && candidates.size() > 0) {
                JsonNode content = candidates.get(0).path("content");
                JsonNode parts = content.path("parts");
                if (parts.isArray() && parts.size() > 0) {
                    return parts.get(0).path("text").asText();
                }
            }

            log.warn("Unexpected Gemini response structure: {}", response.body());
            return "{}";
        } catch (Exception e) {
            log.error("Error calling Gemini API: {}", e.getMessage(), e);
            return "{}";
        }
    }
}
