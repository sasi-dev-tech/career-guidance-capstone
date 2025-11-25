package com.career.navigator.tools;
import org.springframework.stereotype.Component;
@Component
public class ResumeParserTool {
    public String parse(String text) {
        return text.contains("Java") ? "Skill: Java detected" : "No major skills detected";
    }
}
