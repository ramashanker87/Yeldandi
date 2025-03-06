## Create Docker image

     docker build -t prashant-parking-app .

## Create ECR for parking 

    aws cloudformation deploy --template-file 1-parking-ecr-template.yml --stack-name prashant-parking-ecr-repo 

### Login to ECR (for Docker):

    aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 975050323630.dkr.ecr.us-east-1.amazonaws.com

### Tag Image with ECR Repository URL:

    docker tag prashant-parking-app:latest 975050323630.dkr.ecr.us-east-1.amazonaws.com/prashant-parking-app:latest

### Push images:

    docker push 975050323630.dkr.ecr.us-east-1.amazonaws.com/prashant-parking-app

### Create VPC Networking(Created only once)

    aws cloudformation deploy --template-file 2-vpc-networking.yml --stack-name vpc-network

### Create Load Balancer and integrate with VPC

    aws cloudformation deploy --template-file 3-1-load-balancer.yml --stack-name prashant-load-balancer

### Create Security Group

    aws cloudformation deploy --template-file 3-2-security-groups.yml --stack-name prashant-security-group


### Create Dynamodb Infrastructure

    aws cloudformation deploy --template-file 4-create-dynamodb.yml --stack-name prashant-dynamodb 

## Create ECS Cluster infra

    aws cloudformation deploy --template-file 5-ecs-cluster.yml --stack-name prashant-ecs-parking-cluster --capabilities CAPABILITY_NAMED_IAM 

## Create ECS Service and Task infra

    aws cloudformation deploy --template-file 6-ecs-service-task.yml  --stack-name prashant-ecs-service-task-parking --capabilities CAPABILITY_NAMED_IAM 

## Start Parking (To send the data )
ALB Link - PrashantMicroserviceALB-676054469.us-east-1.elb.amazonaws.com
    curl --location 'http://PrashantMicroserviceALB-676054469.us-east-1.elb.amazonaws.com/start/parking' \
    --header 'sessionid: sessionid-1' \
    --header 'Content-Type: application/json' \
    --data '{
    "carRegNo": "car1",
    "parkingNo":"park1",
    "parkingStatus":"start"
    }
    '

##  Parking status

   http://PrashantMicroserviceALB-676054469.us-east-1.elb.amazonaws.com/parking/status?carRegNo=car33&parkingNo=park33

    curl --location 'http://PrashantMicroserviceALB-676054469.us-east-1.elb.amazonaws.com/parking/status?carRegNo=car33&parkingNo=park33'
