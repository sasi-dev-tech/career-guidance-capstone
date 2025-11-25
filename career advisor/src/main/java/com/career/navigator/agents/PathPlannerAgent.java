package com.career.navigator.agents;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
@Service
public class PathPlannerAgent {
    public List<String> roadmap(List<String> skills) {
        List<String> required = List.of("Java","Spring","Docker");
        return required.stream().filter(s -> !skills.contains(s)).collect(Collectors.toList());
    }
}
