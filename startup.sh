#!/bin/bash -ex
java -jar -Dspring.profiles.active=${ENVIRONMENT:qa} be-ms-template-0.0.1-SNAPSHOT.jar
