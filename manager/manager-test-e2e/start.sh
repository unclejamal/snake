#!/bin/bash
ROOT_DIR=$1

echo "Trying to start the application"

mkdir -p /tmp/manager-e2e
cp ${ROOT_DIR}/manager-main/build/distributions/manager-main-1.0.zip /tmp/manager-e2e
unzip -qq /tmp/manager-e2e/*.zip -d /tmp/manager-e2e/

/tmp/manager-e2e/manager-main-1.0/bin/manager-main &

echo "Succeeded in starting the application"