package com.example.careerguide.memory;

import java.util.HashMap;
import java.util.Map;

public class InMemorySessionStore {

    private final Map<String, Map<String, Object>> store = new HashMap<>();

    public void put(String sessionId, String key, Object value) {
        store.computeIfAbsent(sessionId, id -> new HashMap<>()).put(key, value);
    }

    public Object get(String sessionId, String key) {
        Map<String, Object> session = store.get(sessionId);
        if (session == null) return null;
        return session.get(key);
    }
}
