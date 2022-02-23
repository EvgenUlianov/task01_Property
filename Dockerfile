FROM adoptopenjdk/openjdk11:jre-11.0.13_8-alpine
# next string from hometask doesn't work
#FROM openjdk:11-jdk-alpine

## devapp
#EXPOSE 8081
#ENV NETOLOGY_PROFILE_DEV=TRUE

## prodapp
EXPOSE 8080
ENV NETOLOGY_PROFILE_DEV=FALSE

ADD target/task01_Property-0.0.1-SNAPSHOT.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]