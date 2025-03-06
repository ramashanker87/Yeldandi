# AWS CI CD Pipeline

## Create the CodeCommit repository:
    aws codecommit create-repository --repository-name PrashantSpringBootAppRepo --repository-description " Prashant Spring Boot app deployment"

## Clone the repository to your local machine:

    git clone https://git-codecommit.us-east-1.amazonaws.com/v1/repos/PrashantSpringBootAppRepo
    cd RamaSpringBootAppRepo

## Push the Spring Boot application code
    
    git add .
    git commit -m "Initial Spring Boot application commit"
    git push origin main

## Use AWS CLI to deploy the CloudFormation stack:

    aws cloudformation create-stack --stack-name RamaSpringBootStack --template-body file://ci-cd-pipeline-template.yml --capabilities CAPABILITY_NAMED_IAM

