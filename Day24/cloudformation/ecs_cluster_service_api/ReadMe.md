# Create CloudFormation for ECS Farget

## Execute cloudformation to create ECS

    aws cloudformation deploy --template-file ecs-farget-api-gateway.yml --stack-name rama-create-ecs-farget --capabilities CAPABILITY_NAMED_IAM 

## Aws CLI command

### List cluster

    aws ecs list-clusters

### List cluster service

    aws ecs list-services --cluster <CLUSTER_NAME> 
    aws ecs list-services --cluster rama-test-calculator-cluster  

### Describe services

    aws ecs describe-services --cluster <CLUSTER_NAME> --services <SERVICE_NAME> --profile rama

### List Running Tasks

    aws ecs list-tasks --cluster <CLUSTER_NAME> --desired-status RUNNING

    aws ecs list-tasks --cluster <CLUSTER_NAME> --service-name <SERVICE_NAME> --profile rama

### Describe task

    aws ecs describe-tasks --cluster <CLUSTER_NAME> --tasks <TASK_ARN_1> <TASK_ARN_2>


### Debugging and Logs

    aws logs get-log-events \
    --log-group-name "/ecs/<SERVICE_NAME>" \
    --log-stream-name "<LOG_STREAM_NAME>"

    aws logs get-log-events \
    --log-group-name "/ecs/fargate/springboot" \
    --log-stream-name "ecs/calculator-app/74983ed9de3a442d8cfc035a4b3e6180" \
     --profile rama

### ALB command  to describe LB

    aws elbv2 describe-load-balancers --profile rama

### Get ALB DNS Name

    aws elbv2 describe-load-balancers \
    --names CalculatorALB \
    --query "LoadBalancers[0].DNSName" \
    --profile rama \
    --output text

### Invoke calculator api using ALB

    curl  "<ALB-DNS-Name>/actuator/health/"
    curl  "http://CalculatorALB-749001651.us-east-1.elb.amazonaws.com/actuator/health/"

    curl -X GET "http://CalculatorALB-749001651.us-east-1.elb.amazonaws.com/addition?a=9&b=5"
    curl -X GET "http://CalculatorALB-749001651.us-east-1.elb.amazonaws.com/substraction?a=9&b=5"
    curl -X POST "http://CalculatorALB-749001651.us-east-1.elb.amazonaws.com/multiplication?a=9&b=5"
    curl -X PUT "http://CalculatorALB-749001651.us-east-1.elb.amazonaws.com/division?a=9&b=5"

## Delete Stack

    aws cloudformation delete-stack \
        --stack-name create-ecs-farget \
        --profile rama