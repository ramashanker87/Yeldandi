# Cloud Formation Commands

## Create a Stack

     aws cloudformation deploy --template-file create-queue-template.yml --stack-name create-queue --capabilities CAPABILITY_NAMED_IAM 

## Delete a Stack

    aws cloudformation delete-stack --stack-name create-queue

## Describe a Stack

    aws cloudformation describe-stacks --stack-name create-queue

##  List All Stacks

    aws cloudformation list-stacks

    
## Validate a Template

    aws cloudformation validate-template --template-body file://my-template.json

## Preview Changes with Change Sets

### Create a change set
    
    aws cloudformation create-change-set --stack-name MyStack --template-body file://updated-template.json --change-set-name MyChangeSet

### View the change set
    
    aws cloudformation describe-change-set --change-set-name MyChangeSet --stack-name MyStack

### Execute the change set

    aws cloudformation execute-change-set --change-set-name MyChangeSet --stack-name MyStack

## Export Stack Outputs

    aws cloudformation describe-stacks --stack-name MyStack --query "Stacks[0].Outputs"


    
