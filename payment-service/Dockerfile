FROM     maven:3.8.3-openjdk-17 AS builder

RUN      mkdir -p /app
WORKDIR /app

# For caching the dependencies
COPY    pom.xml /app
RUN     mvn -B dependency:go-offline

# Compiling
COPY    . /app/
RUN     mvn clean install -DskipTests

FROM     eclipse-temurin:17-jdk AS executor

WORKDIR /app
COPY --from=builder /app/target/*-1.0-SNAPSHOT.jar app.jar
EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]
