# AI Career Guidance System – ADK + Gemini + Spring Boot

This is a Spring Boot REST API version of the **AI Career Guidance System**:

- Java 17
- Spring Boot 3 (spring-boot-starter-web)
- ADK-style agents:
  - `BaseAgent`, `LlmAgent`
  - `@Schema` on models
  - `FunctionTool` for course tool
- Real Gemini integration via simple HTTP client (`RealGeminiClient`)
- Fallback `MockGeminiClient` if `GEMINI_API_KEY` is not set
- Single endpoint for full session:

## Endpoint

### `POST /api/v1/full-session`

**Request body:**
```json
{
  "studentId": "sam01",
  "questionnaire": "I like coding and math",
  "resume": "Optional resume text here"
}
```

**Response body:** `GuidanceResult` JSON containing:
- `profile`
- `recommendedCareers`
- `chosenCareer`
- `roadmap`
- `suggestedCourses`

## How to run

```bash
mvn clean package
mvn spring-boot:run
```

Optionally set your Gemini key before running:

```bash
export GEMINI_API_KEY="YOUR_GEMINI_KEY"
# or on Windows PowerShell:
# $env:GEMINI_API_KEY="YOUR_GEMINI_KEY"
```

Then call:

```bash
curl -X POST http://localhost:8080/api/v1/full-session \
  -H "Content-Type: application/json" \
  -d '{
    "studentId": "demo1",
    "questionnaire": "I love problem solving and programming.",
    "resume": ""
  }'
```
