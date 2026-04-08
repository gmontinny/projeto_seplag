FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -B -DskipTests

FROM eclipse-temurin:17-jre-alpine
COPY --from=build /app/target/*.jar /app/app.jar
ARG APP_PORT=8083
EXPOSE ${APP_PORT}
ENTRYPOINT ["java","-jar","/app/app.jar"]


