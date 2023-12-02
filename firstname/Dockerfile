FROM openjdk:17-oracle
WORKDIR /app

COPY build/libs/*.jar app.jar

ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000", "-jar", "app.jar"]
