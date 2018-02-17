FROM openjdk:8-jre-alpine

WORKDIR /app

COPY target/ata-meetup-jar-with-dependencies.jar ./app.jar

CMD ["java", "-jar", "app.jar"]
