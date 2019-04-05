FROM maven:3.5.3-jdk-8 AS BUILD_IMAGE
USER root
RUN mkdir /apps
WORKDIR /apps
ADD . .
RUN mvn package

FROM openjdk:8-jre
COPY --from=BUILD_IMAGE /apps/target/be-ms-template-0.0.1-SNAPSHOT.jar .
COPY startup.sh .

CMD bash startup.sh
