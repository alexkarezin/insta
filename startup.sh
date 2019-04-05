#!/bin/bash -ex
echo 35.204.191.166 mongodb-cloud >> /etc/hosts;

java -jar -Dspring.profiles.active=${ENVIRONMENT:qa} be-ms-template-0.0.1-SNAPSHOT.jar
