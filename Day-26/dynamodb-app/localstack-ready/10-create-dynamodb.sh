#!/usr/bin/env bash
set -euo pipefail
#enable debug:
#set -x
echo "configuring dynamodb "
echo "==================="
LOCALSTACK_URL=http://localhost:4566
AWS_REGION=eu-west-1

create_dynamodb(){
  local TABLE_NAME_TO_CREATE=$1
   awslocal --endpoint-url=${LOCALSTACK_URL} dynamodb create-table \
      --table-name ${TABLE_NAME_TO_CREATE} \
      --key-schema AttributeName=carRegNo,KeyType=HASH AttributeName=parkingNo,KeyType=RANGE \
      --attribute-definitions AttributeName=carRegNo,AttributeType=S AttributeName=parkingNo,AttributeType=S \
      --billing-mode PAY_PER_REQUEST \
      --region ${AWS_REGION}
  }

list_dynamodb_tables(){
  awslocal --endpoint-url=${LOCALSTACK_URL} dynamodb list-tables
}

create_dynamodb rama-parking-status

echo $(list_dynamodb_tables)
