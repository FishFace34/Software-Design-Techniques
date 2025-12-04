FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
# Copy parent POM
COPY pom.xml ./pom.xml
# Copy service directory
COPY search-service ./search-service
# Build service
WORKDIR /app/search-service
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/search-service/target/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]
