## Build Application

    mvn clean install

## Start localstack docker 

    docker-compose -f docker-compose-localstack.yml up

## Running Local Profile

    mvn spring-boot:run "-Dspring-boot.run.profiles=local"

## Start Parking

    curl --location 'http://localhost:8081/start/parking' \
    --header 'sessionid: sessionid-1' \
    --header 'Content-Type: application/json' \
    --data '{
    "carRegNo": "car1",
    "parkingNo":"park1",
    "parkingStatus":"start"
    }
    '

##  Parking status

    curl --location 'http://localhost:8081/parking/status?carRegNo=car2&parkingNo=park1'

## Dockerization

    docker build -t parking-app .

## List docker images

    docker images

## Start and run both container same time

    docker-compose up

## Stop docker container
    
    docker-compose down
