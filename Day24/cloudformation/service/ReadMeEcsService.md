# Creating ECS Service

## Create Using Cloudformation
    aws cloudformation deploy --template-file ecs-service.yml --stack-name create-ecs-service --capabilities CAPABILITY_NAMED_IAM

### List cluster service
    aws ecs list-services --cluster <CLUSTER_NAME> 
    aws ecs list-services --cluster rama-test-calculator-cluster