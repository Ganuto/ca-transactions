FROM openjdk:21
WORKDIR /app
COPY ./target/transactions-1.0.0.jar /app
EXPOSE 8080
CMD ["java", "-jar", "transactions-1.0.0.jar"]