# AI Career Guidance System using Multi-Agent Architecture  
### *Personalized Career Path Discovery Powered by Google ADK, Gemini & Spring Boot*  
📌 Track: Agents for Good  
👤 Author(s): AI Mentors  
📅 Date: 01.12.2025  

---

## 📘 Table of Contents
- [Executive Summary](#executive-summary)  
- [Problem Statement](#problem-statement)  
- [Why Agents?](#why-agents)  
- [Solution Overview](#solution-overview)  
- [System Architecture](#system-architecture)  
- [Multi-Agent Workflow](#multi-agent-workflow)  
- [Technical Implementation](#technical-implementation)  
- [Key ADK Concepts Demonstrated](#key-adk-concepts-demonstrated)  
- [Tooling & Technologies](#tooling--technologies)  
- [Evaluation & Testing](#evaluation--testing)  
- [Impact, Value & Benefits](#impact-value--benefits)  
- [Limitations & Future Enhancements](#limitations--future-enhancements)  
- [References](#references)  

---

## 1. Executive Summary  
The **AI Career Guidance System** is a multi-agent application built using **Google ADK (Java), Gemini, and Spring Boot**, designed to evaluate skills, recommend career paths, and generate personalized roadmaps.

### Core Agents
| Agent | Responsibility |
|-------|---------------|
| StudentSkillAnalysisAgent | Extracts skills from resume & questionnaire |
| CareerRecommendationAgent | Suggests best-fit career paths |
| RoadmapAgent | Generates structured learning roadmap |
| CourseSuggestionAgent | Maps learning tasks to learning resources |
| OrchestratorAgent | Manages sequencing & session memory |

The system behaves like a real career counselor—automated, scalable, and personalized.

---

## 2. Problem Statement  
Modern learners struggle with:

❌ *Fragmented guidance tools*  
❌ *Manual and time-consuming research*  
❌ *Low personalization*  
❌ *No full roadmap from skills → career → learning path*

---

## 3. Why Agents?  
A single model is not enough for multi-domain reasoning. Agents enable:

| Benefit | Description |
|--------|-------------|
| 🧠 Division of Intelligence | Each agent specializes in one area |
| 🔁 Coordinated Flow | Orchestrator pipelines outputs → next step |
| 📚 Memory & State | ADK session memory maintains conversation |
| 🔧 Tool Integration | External API calls for course lookup |
| 🔍 Explainability | Intermediate JSON reasoning available |

📌 Mirrors human counseling → **Skills → Career → Roadmap → Courses**  

---

## 4. Solution Overview  
Flow:

1. Upload resume + answer questionnaire  
2. System extracts skillset  
3. Recommends career roles (3–5 best)  
4. Builds a 90-day learning roadmap  
5. Suggests curated free/paid learning resources  
6. Presented via UI or JSON API  

---
## 5. SYSTEM ARCHITECTURE  

### 🔹 Personalized Career Guidance System – Agent Architecture

![Agent Architecture Mind Map](NotebookLM%20Mind%20Map%20(1).png)

---

### System Flow Summary

| Layer | Components | Role |
|-------|-----------|------|
| **Input & API Trigger** | Resume + Questionnaire, Spring Boot Backend API | Accepts input and triggers workflow |
| **Orchestration Layer** | Orchestrator Functions, Support Services | Controls flow, manages memory & state |
| **Sequential Agent Chain** | Skill Analysis Agent → Career Agent → Roadmap Agent → Course Agent | Multi-agent pipeline executes step-by-step |
| **Final Assembly & Output** | Orchestrator Final Assembly + Output Components | Generates final structured result for UI/API |

---

<!--
## 5. System Architecture  

```
┌────────────────────────────┐
│         User Input         │
│ Resume + Questionnaire     │
└──────────────┬─────────────┘
               │
       ┌───────▼───────────┐
       │  OrchestratorAgent │
       │ (Session + Memory) │
       └───────┬─────┬──────┘
               │     │
 ┌─────────────▼──┐ ┌▼────────────────────┐
 │SkillAnalysisAgent│ │CareerRecommendation │
 └─────────────┬────┘ └─────────────▲──────┘
               │                    │
               └────────────┬───────┘
                            ▼
                ┌─────────────────────┐
                │     RoadmapAgent    │
                └───────────┬────────┘
                            ▼
                ┌────────────────────┐
                │ CourseSuggestion    │
                └─────────┬──────────┘
                          ▼
                     Final Output
```
-->

---

## 6. Multi-Agent Workflow  

| Stage | Output |
|------|---------|
| Skill Analysis | Extracts soft/technical skills |
| Career Recommendation | Suggests relevant roles + fit score |
| Roadmap Generation | Creates structured monthly learning tasks |
| Course Recommendation | Maps tasks to real learning resources |
| Orchestration | Manages conversation state & flow |

---

## 7. Technical Implementation  
### ✔ Core Technologies  
- Google ADK  
- Gemini 2.0 Flash / Flash Thinking  
- Spring Boot 3.3+ (Java 17)  
- REST APIs + Custom Tools  
- JSON Structured Output  

### Implementation Notes  
- Each agent = independent ADK AgentDefinition  
- SessionService → stateful reasoning  
- Logs, tracing, structured JSON validation  

---

## 8. Key ADK Concepts Demonstrated  
| Requirement | Status |
|------------|--------|
| Multi-Agent System | ✔ Used 5 agents |
| Tool Usage | ✔ HTTP Tool for course search |
| Session + Memory | ✔ Full session management |

### Bonus  
🔹 Observability  
🔹 A2A communication  
🔹 Deployable in Cloud Run  

---

## 9. Tooling & Technologies  
- Gemini LLM reasoning  
- Spring Boot REST  
- ADK Tooling + Cloud Ready  
- In-memory + optional SQL storage  

---

## 10. Evaluation & Testing  
✔ Resume parser unit tests  
✔ Skill extraction accuracy  
✔ Career mapping correctness  
✔ JSON Schema validation  

---

## 11. Impact & Value  
🎓 Students – Instant personalized guidance  
🧭 Counselors – Automation of evaluation  
🏫 Institutions – Self-service scalable portal  

⏳ Time reduced: **15 hrs → 2 mins**  

---

## 12. Limitations & Future Work  
- Add psychometric testing  
- Job-market analytics  
- Multimodal PDF extraction  
- Multilingual agents  

---

