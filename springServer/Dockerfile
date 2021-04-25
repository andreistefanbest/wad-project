FROM openjdk:15-oracle

WORKDIR /app

COPY ./target/springServer-1.0-SNAPSHOT.jar ./server.jar

EXPOSE 8080

CMD ["java",  "-jar", "server.jar"]