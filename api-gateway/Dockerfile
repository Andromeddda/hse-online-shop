FROM     maven:3.8.3-openjdk-17 AS builder

RUN      mkdir -p /app
COPY    . /app/
WORKDIR /app

RUN     mvn install package

EXPOSE 8081

ENTRYPOINT ["mvn", "spring-boot:run"]