FROM openjdk:11
EXPOSE 8090
ADD target/cliq.jar cliq.jar
ENTRYPOINT ["java", "-jar", "/cliq.jar"]