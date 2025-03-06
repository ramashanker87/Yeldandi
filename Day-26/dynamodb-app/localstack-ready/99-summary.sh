#!/usr/bin/env bash
set -euo pipefail
LOCALSTACK_URL=http://localhost:4566
AWS_REGION=eu-west-1
awslocal --endpoint-url=${LOCALSTACK_URL} --region ${AWS_REGION} dynamodb list-tables
curl localstack:4566/_localstack/init | jq -r '.'
