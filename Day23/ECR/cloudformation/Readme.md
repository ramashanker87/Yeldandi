# Create CloudFormation for ECR

## Execute cloudformation to create ecr repository

    aws cloudformation deploy --template-file ecr-template.yml --stack-name create-ecr-repo 

## Verify ECR

###  List all ECR repositories:

    aws ecr describe-repositories 

### Check details of a specific repository:

    aws ecr describe-repositories --repository-names rama-microservice-repo 

### List images in the repository:

    aws ecr list-images --repository-name rama-microservice-repo

### Login to ECR (for Docker):

    aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 975050323630.dkr.ecr.us-east-1.amazonaws.com

## Push Image to ECR

### Build docker image

### Tag Image with ECR Repository URL:

    docker tag falcon007/calculator-app:1.0.0 975050323630.dkr.ecr.us-east-1.amazonaws.com/rama-microservice-repo:latest

### Login to ECR (for Docker):

    aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 975050323630.dkr.ecr.us-east-1.amazonaws.com

### Push images:

    docker push 975050323630.dkr.ecr.us-east-1.amazonaws.com/rama-microservice-repo

### List images in the repository:
    aws ecr list-images --repository-name rama-microservice-repo
