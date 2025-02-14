#!/usr/bin/env bash
set -euo pipefail
#enable debug:
#set -x§
echo "prerequisites "
echo "==================="
apt-get update && apt-get --yes --force-yes install jq
jq --version
mkdir -p /workdir/component-test/scripts/tmp