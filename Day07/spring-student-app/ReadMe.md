# Student Application

## Build with maven

```Bash
mvn clean install
```
## Run the application with maven

```Bash
mvn spring-boot:run
```

## Application Rest end point

### Health Check End point

     Get http://localhost:8080/actuator/health

### Post Student End point

    Post http://localhost:8080/student/post

    body data

     {
        "name":"Student1",
        "age": 24,
        "role":"Role1"
    }

### Get Student End point

    Get http://localhost:8080/student/get

    parameter is name

### Put Student End point

    Put http://localhost:8080/student/put
    
    parameter 
    
    name
    age

### Delete Student End point

    Delete http://localhost:8080/student/delete

    parameter
    name


## Student Module Json

    {
        "name":"Student1",
        "age": 24,
        "role":"Role1"
    }
