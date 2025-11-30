package com.example.careerguide.api;

import com.example.careerguide.agent.OrchestratorAgent;
import com.example.careerguide.llm.GeminiClient;
import com.example.careerguide.llm.MockGeminiClient;
import com.example.careerguide.llm.RealGeminiClient;
import com.example.careerguide.memory.InMemorySessionStore;
import com.example.careerguide.memory.StudentMemoryBank;
import com.example.careerguide.model.GuidanceResult;
import com.example.careerguide.tools.CourseCatalogTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Paths;

@RestController
@RequestMapping("/api/v1")
public class GuidanceController {

    private static final Logger log = LoggerFactory.getLogger(GuidanceController.class);

    private final OrchestratorAgent orchestrator;

    public GuidanceController() {
        //String apiKey = System.getenv("GEMINI_API_KEY");
        
        GeminiClient geminiClient;
        if (apiKey != null && !apiKey.isBlank()) {
            log.info("Using RealGeminiClient (Gemini HTTP API)");
            geminiClient = new RealGeminiClient(apiKey, "gemini-1.5-flash");
        } else {
            log.warn("GEMINI_API_KEY not set; using MockGeminiClient");
            geminiClient = new MockGeminiClient();
        }

        CourseCatalogTool courseCatalogTool = new CourseCatalogTool();
        InMemorySessionStore sessionStore = new InMemorySessionStore();
        StudentMemoryBank memoryBank = new StudentMemoryBank(Paths.get("student-memory.json"));

        this.orchestrator = OrchestratorAgent.build(
                geminiClient,
                courseCatalogTool,
                sessionStore,
                memoryBank
        );
    }

    @PostMapping("/full-session")
    public GuidanceResult runFullSession(@RequestBody GuidanceRequest request) {
        String studentId = (request.getStudentId() == null || request.getStudentId().isBlank())
                ? "student-" + System.currentTimeMillis()
                : request.getStudentId();

        String questionnaire = request.getQuestionnaire() != null ? request.getQuestionnaire() : "";
        String resume = request.getResume() != null ? request.getResume() : "";

        log.info("Received full-session request for studentId={}", studentId);

        return orchestrator.runGuidanceSession(studentId, questionnaire, resume);
    }
}
