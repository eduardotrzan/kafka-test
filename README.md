# Kafka Test

This is a Kafka Test Restful Service with Events.

## Local use

Pre-requirement: Have Kafka 2.12-2.4.0 or higher installed.

### Database

#### Local

##### Kafka
Go to Kafka folder

Run in terminal for Zookeeper
```bash
bin/zookeeper-server-start.sh config/zookeeper.properties
```

Run in terminal for Kafka server
```bash
bin/kafka-server-start.sh config/server.properties
```

### Running application (one of the following)

#### Pre-requirement: 
- Have Java 13+ installed;

#### Run Options
1 - in the maven sub-module `server` run: mvn spring-boot:run
    
2 - run as java application from the maven sub-module `server`: Application.java

##### Lombok

This is a plugin to help avoid boiler plate in the code. 

Site: https://projectlombok.org/

Git: https://github.com/mplushnikov/lombok-intellij-plugin#installation

###### Installation

Make sure to have lombok properly installed as showed in the github. There are some configurations to be done in the IDE.

##### Docker

###### Local

####### Run Docker Dockerfile
docker image build -t kafka-test .

####### Run Docker compose 
docker-compose up

####### Accessing Container's bash
docker exec -ti kafka-test /bin/bash

## Using the Application
- Download [Postman](https://www.getpostman.com/);
- Import Postman collections from `~/doc/api/kafka-test.postman_collection.json`;
- Import Postman environment from `~/doc/api/Kafka-Test.postman_environment.json`;
- Use the Valid and Invalid calls to use the system.

## Testing the system
- In the root folder run `mvn test` and it will run all available tests;

### Maven 

#### Running all tests
mvn test

#### Running integration tests
mvn test-compile failsafe:integration-test failsafe:verify

#### Running Kafka Test with Java
In the server folder run:
- `mvn clean install`

In the same server folder run:
- `java -jar -Dspring.profiles.active=local target/server-1.0-SNAPSHOT.jar`

#### Running Kafka Test with Maven
In the server folder run:
- `mvn clean spring-boot:run -Dspring-boot.run.profiles=local`
