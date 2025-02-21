# Create project

``` 
https://start.spring.io/
```

# Build the project

``` 
mvn clean install
```

# Run application

``` 
mvn spring-boot:run
```
## Dockerization

```
$ mvn install dockerfile:build
```

## List docker images

```
$ docker images
```

### Running the application in docker container

```
$ docker run -p 8080:8080 falcon007/calculator-app:1.0.0 
```

## List docker running container

```
$ docker ps
```

# Testing the application

## Curl Commend

### Addition
``` 
curl -X GET 'http://localhost:8080/substraction?a=9&b=5'

curl -X GET 'http://localhost:8080/addition?a=9&b=5'
```

## Swagger Page:
http://localhost:8080/swagger-ui/index.html
