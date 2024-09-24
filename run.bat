@echo off
set JAR_FILE=yourapp.jar
set PROPS_PATH=./

java -jar %JAR_FILE% --spring.config.location=%PROPS_PATH%
start http://localhost:8080
