## To run the application

### right now this project is created on mysql, later will migrate into DynamoDM

### First run the docker compose to start rabbitMQ and mysql
docker-compose up

### Rabbit MQ link - http://localhost:15672/#/queues

### now run the registration application- mvn spring-boot:run
 POST http://localhost:8082/parking/start

#### in param - parkingNumber 1-A
#### Body 
{
"ownerName": "car1",
"registrationNumber":"park1",
"modelNumber":"start",
"fuelType":"petrol"
}

now we can see the data in start queue

### now start the process application 

DELETE  http://localhost:8082/parking/end
param - parkingNumber : 1-A

### To check data in mysql
docker exec -it mysql mysql -u user -p

password

use parking;

select * from car;
select * from parking_start;
select * from parking_end;


