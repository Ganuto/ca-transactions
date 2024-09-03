# Customer Account & Transactions

## Overview

This project is a Spring Boot application packaged as a Docker container. It demonstrates how to build and deploy a Spring Boot application using Docker.

## Prerequisites

- [Java 21+](https://download.oracle.com/java/21/latest/jdk-21_macos-x64_bin.tar.gz)
- [Maven](https://maven.apache.org/install.html) (for building the application)
- [Docker](https://docs.docker.com/get-docker/) (for containerization)

## Getting Started

### 1. Clone the Repository

```
git clone https://github.com/Ganuto/transactions.git
cd your-springboot-app
```

### 2. Build the Spring Boot Application:

If you wish to build the application locally before Dockerizing, use:

````
./mvnw clean package
````
This will generate a JAR file in the target directory.

### 3. Running the Application
Ensure Docker is running on your machine. Then build the Docker image using the Dockerfile provided:

```
docker build -t springboot-docker-example .
```
Once the image is built, you can run the Docker container:

```
docker run -p 8080:8080 springboot-docker-example
```
This command maps port 8080 on your local machine to port 8080 on the Docker container. You can access the application at http://localhost:8080.

### Without Docker
If you prefer running the application outside Docker, use the command below (assuming you have built the JAR file):

````
java -jar target/springboot-docker-example-0.0.1-SNAPSHOT.jar
````

### Testing
To run tests, use the following Maven command:

```
./mvnw test
```

To run tests within Docker, ensure you have built the image and then execute:

````
docker run --rm springboot-docker-example mvnw test
````
