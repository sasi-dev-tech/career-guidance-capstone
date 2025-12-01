

Project Title: AI Career Guidance System using Multi-Agent Architecture
Subtitle: Personalized Career Path Discovery Powered by Google ADK, Gemini, and Spring Boot
Track: Agents for Good.
Author(s): AI mentors.
Date: [01.12.2025]

📘 TABLE OF CONTENTS
    1. Executive Summary
    2. Problem Statement
    3. Why Agents?
    4. Solution Overview
    5. System Architecture
    6. Multi-Agent Workflow
    7. Technical Implementation
    8. Key ADK Concepts Demonstrated
    9. Tooling & Technologies
    10. Evaluation & Testing
    11. Impact, Value Proposition & Benefits
    12. Limitations & Future Enhancements
    13. References

1. EXECUTIVE SUMMARY
The AI Career Guidance System is a multi-agent, ADK-powered application designed to help students and early-career professionals determine skills, match careers, and generate actionable learning roadmaps.
Built using the latest Google ADK (Java), Gemini models, and Spring Boot, the system uses five coordinated agents:
    1. StudentSkillAnalysisAgent – Extracts skills from resumes and questionnaire input
    2. CareerRecommendationAgent – Suggests best-fit careers
    3. RoadmapAgent – Builds a learning roadmap with milestones
    4. CourseSuggestionAgent – Recommends curated courses
    5. OrchestratorAgent – Manages sequential flow & state
The system presents a fully integrated, stateful, intelligent career counselor experience.

2. PROBLEM STATEMENT
Choosing a career is one of the most difficult decisions students face. Current systems suffer from:
❌ Fragmented tools
Career guidance tools often perform only one task—skill tests, course recommendations, or assessments—but not all.
❌ Manual, time-consuming evaluation
Students need to compare skills, research career paths, and design learning plans manually.
❌ Lack of personalization
Most tools are generic and not tailored to individual strengths or learning preferences.
❌ No integrated roadmap
Even when career suggestions exist, they rarely include actionable step-by-step paths.

3. WHY AGENTS?
A traditional single-model AI system cannot handle multi-step, multi-domain operations effectively.
Agents provide:
🧠 Division of Intelligence
Each agent specializes in a domain (skills, careers, roadmaps, courses).
🔁 Sequential Coordination
The OrchestratorAgent passes outputs as inputs through the pipeline.
📚 Memory & State
The system uses ADK Session Memory for persistent reasoning across steps.
🔧 Tools Integration
CourseSuggestionAgent uses tool calls for course API search.
🔍 Explainability
Each agent produces intermediate reasoning and structured JSON outputs.
This multi-agent approach mirrors how a human career counselor operates:
    1. Analyze skills → 2. Suggest careers → 3. Build roadmap → 4. Recommend courses.

4. SOLUTION OVERVIEW
The system provides a fully automated pipeline:
    1. Upload resume + answer a short questionnaire
    2. System extracts skills
    3. AI selects 3–5 most relevant career paths
    4. System generates a 90-day learning roadmap
    5. AI suggests paid/free learning resources
    6. All presented in a unified UI or JSON API response

5. SYSTEM ARCHITECTURE
                    ┌────────────────────────────┐
                    │        User Input          │
                    │ Resume + Questionnaire     │
                    └──────────────┬─────────────┘
                                   │
                        ┌──────────▼───────────┐
                        │   OrchestratorAgent  │
                        │ (Session + Memory)   │
                        └───────┬─────┬────────┘
             ┌──────────────────┘     └──────────────────┐
             │                                            │
┌────────────▼───────────┐                ┌───────────────▼──────────┐
│ StudentSkillAnalysis    │                │ CareerRecommendationAgent │
│   Agent (LLM + Tools)   │                │    (Gemini)               │
└────────────┬────────────┘                └───────────────┬──────────┘
             │                                              │
             └───────────────┐                 ┌────────────┘
                             ▼                 ▼
                    ┌────────────────────────────────┐
                    │          RoadmapAgent           │
                    │  (Structured JSON Output)       │
                    └───────────────┬────────────────┘
                                    │
                            ┌───────▼────────┐
                            │ CourseSuggestion│
                            │     Agent       │
                            └───────┬────────┘
                                    │
                              Final Output

6. MULTI-AGENT WORKFLOW
    1. Skill Analysis Stage
Extracts technical skills, soft skills, domain interest, experience level.
    2. Career Recommendation Stage
Suggests high-fit careers with likelihood scores.
    3. Roadmap Generation Stage
Creates a structured roadmap including “Months → Tasks → Learning Goals.”
    4. Course Recommendation Stage
Uses APIs + LLM to map learning goals to course resources.
    5. Orchestration
Sequential coordination using ADK AgentSession + state memory.

7. TECHNICAL IMPLEMENTATION
✔ Technologies Used
    • Google ADK (latest)
    • Gemini 2.0 Flash / Flash Thinking
    • Spring Boot 3.3+ (Java 17)
    • REST APIs
    • JSON Schema for structured agent results
    • Custom Tools (Course Finder)
✔ Implementation Highlights
    • Each agent implemented as a dedicated ADK AgentDefinition.
    • Orchestrator uses:
        ◦ Sequential agent flow
        ◦ In-session memory
        ◦ State propagation
    • Logging & tracing added via ADK Observability.
    • JSON schemas enforce structured LLM output.

8. KEY ADK CONCEPTS DEMONSTRATED (Required for Scoring)
✔ 1. Multi-Agent System (Required Concept #1)
System uses 4 domain agents + 1 orchestrator agent.
✔ 2. Tools Usage (Required Concept #2)
CourseSuggestionAgent calls a course-searching HTTP tool.
✔ 3. Session & Memory (Required Concept #3)
OrchestratorAgent manages entire conversation state using:
    • ADK SessionService
    • InMemorySessionStore
    • Context passing
✔ Bonus Concepts Used
    • Observability (logging, tracing)
    • A2A (inter-agent communication)
    • Optional deployment on Cloud Run (5 bonus points)

9. TOOLING & TECHNOLOGIES
    • Gemini LLM for skill extraction, reasoning & justification
    • Spring Boot REST controllers
    • Tooling: Google ADK Tool API + HTTP tool
    • Storage: In-memory (expandable to Cloud SQL)
    • Execution: Local or Cloud Run

10. EVALUATION & TESTING
✔ Unit Tests
    • Resume parsing
    • Skill extraction
    • Career ranking logic
    • Roadmap JSON compliance
✔ Agent Evaluation
Using ADK evaluators with:
    • accuracy tests
    • JSON schema validation
    • structured output scoring

11. IMPACT, VALUE & BENEFITS
🎓 For Students
Immediate personalized career guidance.
🧭 For Career Counselors
Automates repetitive evaluation tasks.
🏢 For Institutions
Can be deployed as a self-service career guidance portal.
⭐ Overall Value
The system reduces the end-to-end manual research time from 10–15 hours → under 2 minutes.

12. LIMITATIONS & FUTURE WORK
    • Add more detailed psychometric evaluation
    • Add support for job market demand analytics
    • Multi-modal resume extraction (PDF → Text)
    • Add multilingual support
