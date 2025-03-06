## Create SNS Topic

    aws cloudformation deploy --template-file create-sns-topic.yml --stack-name prashant-sns-topic 

## Delete a Stack

    aws cloudformation delete-stack --stack-name prashant-sns-topic 

## Create SNS Topic with sqs subscriber

    aws cloudformation deploy --template-file create-sns-sqs-subscribe.yml --stack-name prashant-sns-sqs-topic 

## Delete a Stack for sns with sqs subscriber

    aws cloudformation delete-stack --stack-name prashant-sns-sqs-topic

# SNS Command for topics use when we are not using cloudformation

## List SNS Topics:
    aws sns list-topics

## Create an SNS Topic:
    aws sns create-topic --name MyTopic

## Subscribe to an SNS Topic:
    aws sns subscribe --topic-arn arn:aws:sns:us-east-1:975050323630:MyTopic --protocol email --notification-endpoint "example@example.com"

## Publish a Message to an SNS Topic:
    aws sns publish --topic-arn arn:aws:sns:us-east-1:975050323630:prashant-test-data-events-topics --message "Hello, this is a test message!"

## Unsubscribe from an SNS Topic:
    aws sns unsubscribe --subscription-arn arn:aws:sns:us-east-1:975050323630:prashant-test-data-events-topics:abcd1234-abcd-1234-abcd-1234abcd1234

## Delete an SNS Topic:
    aws sns delete-topic --topic-arn arn:aws:sns:us-east-1:975050323630:MyTopic
