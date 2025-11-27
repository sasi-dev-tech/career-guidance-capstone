package com.example.careerguide.memory;

import com.example.careerguide.model.StudentProfile;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class StudentMemoryBank {

    private static final Logger log = LoggerFactory.getLogger(StudentMemoryBank.class);

    private final Path path;
    private final ObjectMapper mapper = new ObjectMapper();

    public StudentMemoryBank(Path path) {
        this.path = path;
    }

    public void saveProfile(String studentId, StudentProfile profile) {
        try {
            ObjectNode root;
            if (Files.exists(path)) {
                root = (ObjectNode) mapper.readTree(Files.readString(path));
            } else {
                root = mapper.createObjectNode();
            }
            root.set(studentId, mapper.valueToTree(profile));
            Files.writeString(path, mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root));
        } catch (IOException e) {
            log.warn("Failed to save profile for {}: {}", studentId, e.getMessage());
        }
    }

    public StudentProfile loadProfile(String studentId) {
        try {
            if (!Files.exists(path)) return null;
            JsonNode root = mapper.readTree(Files.readString(path));
            JsonNode node = root.get(studentId);
            if (node == null) return null;
            return mapper.treeToValue(node, StudentProfile.class);
        } catch (IOException e) {
            log.warn("Failed to load profile for {}: {}", studentId, e.getMessage());
            return null;
        }
    }
}
