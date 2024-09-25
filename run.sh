#!/bin/bash

# Define the JAR file and properties path
JAR_FILE="yourapp.jar"
PROPS_PATH="./"

# Run the JAR file with properties
java -jar "$JAR_FILE" --spring.config.location="$PROPS_PATH" &

# Wait for the Spring Boot application to start
sleep 10  # Adjust the sleep time if your app starts faster/slower

# Open the browser with the index.html page
open "http://localhost:8080"
