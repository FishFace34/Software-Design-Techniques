FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
# Copy parent POM
COPY pom.xml ./pom.xml
# Copy service directory
COPY listing-service ./listing-service
# Build service
WORKDIR /app/listing-service
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/listing-service/target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
