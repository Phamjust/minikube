FROM maven:3.8.5-openjdk-8  AS BUILD_STAGE

COPY ./ ./

RUN mvn clean package

FROM openjdk:8-jre

COPY --from=BUILD_STAGE /target/Project1rebuild-0.0.1-SNAPSHOT-jar-with-dependencies.jar /project1.jar
ENV db_url=jdbc:postgresql://database-1.cujgkusgasn5.us-east-1.rds.amazonaws.com:5432/postgres
ENV db_username=java_login
ENV db_password=p4ssw0rd

CMD ["java","-jar","project1.jar"]

