#!/bin/bash

# Path to your JAR file and properties
JAR_FILE="/path/to/yourapp.jar"
PROPS_PATH="./"

# Run the JAR file
java -jar "$JAR_FILE" --spring.config.location="$PROPS_PATH" &

# Wait for Spring Boot to start
sleep 10

# Open the default browser to the landing page
open "http://localhost:8080"
