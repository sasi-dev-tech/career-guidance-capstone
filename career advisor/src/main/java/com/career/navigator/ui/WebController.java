package com.career.navigator.ui;
import com.career.navigator.agents.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api")
public class WebController {
    private final UserProfilerAgent profile;
    private final MarketAnalystAgent market;
    private final PathPlannerAgent path;
    private final CoachingAgent coach;
    public WebController(UserProfilerAgent p,MarketAnalystAgent m,PathPlannerAgent pa,CoachingAgent c){
        this.profile=p;this.market=m;this.path=pa;this.coach=c;
    }
    @PostMapping("/profile") public String save(@RequestBody String j){ return profile.profileUser(j); }
    @GetMapping("/market") public List<String> market(){ return market.getTrends(); }
    @PostMapping("/roadmap") public List<String> road(@RequestBody List<String> s){ return path.roadmap(s); }
    @GetMapping("/interview") public List<String> q(){ return coach.interviewQ(); }
}
