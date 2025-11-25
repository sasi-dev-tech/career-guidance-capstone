package com.career.navigator.agents;
import com.career.navigator.memory.MemoryBank;
import com.career.navigator.utils.JSONUtil;
import org.springframework.stereotype.Service;
@Service
public class UserProfilerAgent {
    public String profileUser(String userJson) {
        MemoryBank.save("user_profile", userJson);
        return "Profile saved: " + JSONUtil.pretty(userJson);
    }
}
