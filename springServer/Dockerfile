FROM openjdk:15-oracle

WORKDIR /app

COPY . .

RUN ./mvnw clean install

EXPOSE 8080

CMD ["java",  "-jar", "./target/springServer-1.0-SNAPSHOT.jar"]