# Deployment Guide

This guide outlines how to deploy the Smart Interview Preparation Tracker locally, via Docker, and on Render.

## 1. Local Build

Ensure you have Java 17 and Maven installed.

```bash
# Compile and package the WAR file
mvn clean package
```
Copy `target/interview-tracker.war` to your Tomcat 10 `webapps` directory.

## 2. Docker Build

To run the application using Docker:

```bash
# Build the image
docker build -t interview-tracker .

# Run the container (Mapping port 8080)
docker run -p 8080:8080 interview-tracker
```
The application will be accessible at `http://localhost:8080`.

## 3. Environment Variables
The application looks for the following environment variables. If they are not found, it falls back to `src/main/resources/db.properties`.
- `DB_URL` (e.g., `jdbc:mysql://host:port/interview_tracker?useSSL=true`)
- `DB_USERNAME`
- `DB_PASSWORD`

## 4. Render Deployment (GitHub)

This repository includes a `render.yaml` file for Infrastructure-as-Code.
1. Create an account on [Render](https://render.com).
2. Connect your GitHub repository.
3. Render will automatically detect `render.yaml` and deploy the Dockerfile.
4. Set the `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD` in the Render Environment Variables tab pointing to your cloud MySQL instance (e.g., Aiven or Railway).
