### To run program

### To prepare rabbitMQ and Mysql container
docker-compose up 
 
## Run Producer
Now run producer mvn spring-boot:run

### To send data via postman
http://localhost:8089/student/save

{
"name":"stu1",
"schoolName": "school1",
"age":25,
"gender": "F"
}

### To delete data by name
http://localhost:8089/student/delete?name=stu1

### Now go to Rabbit MQ  verify data reached to queue or not
http://localhost:15672

### Now run consumer to consume data from queue
mvn spring-boot:run


### Now check data in mysql
docker exec -it mysql mysql -u root -p
password
show databases;
use student;
show tables;
select * from student;



