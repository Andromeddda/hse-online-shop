FROM     maven:3.8.3-openjdk-17 AS builder

RUN      mkdir -p /app
COPY    . /app/
WORKDIR /app

RUN     mvn clean install -DskipTests

FROM    eclipse-temurin:17-jdkr AS executor

WORKDIR /app
COPY    --from=builder /app/target/*.jar app.jar

EXPOSE   8081

ENTRYPOINT ["java", "-jar", "app.jar"]