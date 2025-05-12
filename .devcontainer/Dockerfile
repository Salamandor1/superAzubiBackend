# Basis-Image: OpenJDK + Maven
FROM mcr.microsoft.com/devcontainers/java:1-21-bullseye

# Installiere Maven Wrapper (optional)
RUN apt-get update && apt-get install -y curl unzip

# Falls du `mvnw` verwendest
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

# Arbeitsverzeichnis
WORKDIR /workspace
