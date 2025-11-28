
# Docker Run

docker build -t ai-career-guidance .
docker run -p 8080:8080 ai-career-guidance

With Gemini:
docker run -p 8080:8080 -e GEMINI_API_KEY="KEY" ai-career-guidance

Compose:
docker compose up --build
