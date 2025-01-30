# Application for Employee Information

## Build application with maven

```Bash
mvn clean install
```

## Run Application with maven

```Bash
mvn spring-boot:run
```

## Verify End Point Controller

### Verify application Health

```Bash
curl --location 'http://localhost:8080/actuator/health'
```

### Create Employee

```Bash
curl --location 'http://localhost:8080/employee/create/employee' \
--header 'Content-Type: application/json' \
--data '{
     "id":"1",
     "name": "emp1",
     "age":25
}'
```

### Read All Employee Data

```Bash
curl --location 'http://localhost:8080/employee/get/all/employee'
```

### Update Employee Data

```Bash
curl --location --request PUT 'http://localhost:8080/employee/update/employee?id=1&age=22'
```

### Delete Employee Data

```Bash
curl --location --request DELETE 'http://localhost:8080/employee/delete/employee?id=2'
```
