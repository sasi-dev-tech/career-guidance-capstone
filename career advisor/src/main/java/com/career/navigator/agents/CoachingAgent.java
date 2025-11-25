package com.career.navigator.agents;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CoachingAgent {
    public List<String> interviewQ() {
        return List.of("Tell me about yourself?","Explain OOP?");
    }
}
