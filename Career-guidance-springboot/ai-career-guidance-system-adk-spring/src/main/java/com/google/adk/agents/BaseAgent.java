package com.google.adk.agents;

public abstract class BaseAgent {

    private final String name;

    protected BaseAgent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
