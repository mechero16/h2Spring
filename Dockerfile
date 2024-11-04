FROM eclipse-temurin:21
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk
COPY --from=build /target/Project-0.0.1-SNAPSHOT.jar Project.jar
EXPOSE 8090
ENTRYPOINT [ "java","-jar","Project.jar"]
