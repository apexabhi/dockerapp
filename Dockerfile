#FROM openjdk:11
#EXPOSE 8090
#ADD target/cliq.jar cliq.jar
#ENTRYPOINT ["java", "-jar", "/cliq.jar"]
FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/cliq.jar /app/cliq.jar

EXPOSE 8090

CMD ["java", "-jar", "cliq.jar"]