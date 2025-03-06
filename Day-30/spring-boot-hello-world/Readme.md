## Build Application

    mvn clean install
## Run Locally and verify
    
    mvn spring-boot:run
    http://localhost:8080/actuator/health
    http://localhost:8080/hello

## Dockerization

    docker build -t spring-hello-app .

## List docker images

    docker images

## Run and test application

    docker run -p 8080:8080 spring-hello-app:latest

## Create ECR for hello app

```Bash
    aws cloudformation deploy --template-file 1-ecr-template.yml --stack-name rama-hello-ecr-repo 
```
### Login to ECR (for Docker):

```Bash
    aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 975050323630.dkr.ecr.us-east-1.amazonaws.com
```

### Tag Image with ECR Repository URL:
```Bash
    docker tag spring-hello-app:latest 975050323630.dkr.ecr.us-east-1.amazonaws.com/rama-hello-app:latest
```

### Push images:
```Bash
    docker push 975050323630.dkr.ecr.us-east-1.amazonaws.com/rama-hello-app
```

### Create VPC Networking(Created only once do not run it if it exist)
```Bash
    aws cloudformation deploy --template-file 2-vpc-networking.yml --stack-name vpc-network
```
## Create ECS Service and Task infra
```Bash
    aws cloudformation deploy --template-file 3-ecs-farget-cluster.yml  --stack-name rama-cluster-hello --capabilities CAPABILITY_NAMED_IAM 
```
## Verify the rest end point

    curl --location 'http://rama-test-hello-alb-1784220606.us-east-1.elb.amazonaws.com/hello'
    curl --location 'http://rama-test-hello-alb-1784220606.us-east-1.elb.amazonaws.com/actuator/health'