FROM openjdk:11

RUN mkdir -p /app

WORKDIR /app

COPY . .

RUN sh gradlew build

CMD ["java", "-jar", "build/libs/drones-0.0.1-SNAPSHOT.jar"]