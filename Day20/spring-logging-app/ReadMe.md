# Logging Application

## Build Application
    mvn clean install

## Run Application

    mvn spring-boot:run

## Add dependency for log configuration

    <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-log4j2</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>

## Update properties for logging

    # Set root logging level
    logging.level.root=INFO
    
    # Enable DEBUG level for specific packages
    logging.level.com.logging.app=TRACE
    
    # Output logs to file
    logging.file.name=app.log
    logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} - %level - %msg%n
    logging.pattern.file=%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n
    management.endpoints.web.exposure.include=loggers

## Actuator url
    curl http://localhost:8081/actuator
    curl http://localhost:8081/actuator/loggers
    
    search for configuredLevel

## Verify logs using url
    http://localhost:8081/log

## Update log level run time

    curl -X POST "http://localhost:8081/actuator/loggers/com.logging.app" -H "Content-Type: application/json" -d '{"configuredLevel": "DEBUG"}'

## Verify logs using url after update

    http://localhost:8081/log

## Show the app.log
