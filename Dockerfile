# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR file into the container at /app
COPY target/mvcapp-0.0.1-SNAPSHOT.jar /app

RUN mkdir -p /app/data
COPY data/testdb.mv.db /app/data
COPY data/testdb.trace.db /app/data

# Specify the command to run on container start
CMD ["java", "-jar", "mvcapp-0.0.1-SNAPSHOT.jar"]
