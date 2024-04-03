FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/CliqAssignent-0.0.1-SNAPSHOT.jar /app/CliqAssignent-0.0.1-SNAPSHOT.jar

EXPOSE 8090

CMD ["java", "-jar", "CliqAssignent-0.0.1-SNAPSHOT.jar"]