FROM eclipse-temurin:25-jdk-jammy

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/crbs-booking-service-0.0.1-SNAPSHOT.jar"]

