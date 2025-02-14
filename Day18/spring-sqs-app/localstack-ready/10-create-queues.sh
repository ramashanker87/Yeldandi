#!/usr/bin/env bash
set -euo pipefail
#enable debug:
#set -x
echo "configuring sqs "
echo "==================="
LOCALSTACK_URL=http://localhost:4566
AWS_REGION=eu-west-1

create_queue() {
  local QUEUE_NAME_TO_CREATE=$1
  awslocal --endpoint-url=${LOCALSTACK_URL} sqs create-queue \
    --queue-name ${QUEUE_NAME_TO_CREATE} \
    --region ${AWS_REGION} \
    --attributes VisibilityTimeout=30
}

list_queues() {
  awslocal --endpoint-url=${LOCALSTACK_URL} sqs list-queues
}

create_queue message-in-queue

echo $(list_queues)
