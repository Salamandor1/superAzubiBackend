# Base image with Java 21 and Maven
FROM maven:3.9.4-eclipse-temurin-21

# Create working directory
WORKDIR /workspace/super_azubi_pets

# Optional: Pre-load dependencies (speeds up start)
# COPY ../super_azubi_pets/pom.xml .
# RUN mvn dependency:go-offline

# Copy source code (optional if mounting in volume)
# COPY . .

# Set JAVA env vars if needed
ENV JAVA_HOME=/usr/local/openjdk-21

CMD ["bash"]