FROM openjdk:17-jdk

WORKDIR /server


COPY ./bankApp/target/bankApp-1.0.0.jar /server/bank_server.jar
COPY ./bankApp/scans /server/scans

EXPOSE 8081

CMD ["java", "-jar", "bank_server.jar"]