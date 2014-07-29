#!/bin/bash
ROOT_DIR=$1

echo "Trying to start the application"

mkdir -p /tmp/snake-e2e
cp ${ROOT_DIR}/snake-main/build/distributions/snake-main-1.0.zip /tmp/snake-e2e
unzip -qq /tmp/snake-e2e/*.zip -d /tmp/snake-e2e/

/tmp/snake-e2e/snake-main-1.0/bin/snake-main &

echo "Succeeded in starting the application"