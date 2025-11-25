package com.career.navigator.agents;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class MarketAnalystAgent {
    public List<String> getTrends() {
        return List.of("AI Engineer","Backend Developer","ML Engineer");
    }
}
