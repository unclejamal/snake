#!/bin/bash
ROOT_DIR=$1

echo "Trying to stop the application"

kill $(ps aux | grep '[s]nake.main.Main' | awk '{print $2}')
rm -rf /tmp/snake-e2e

echo "Succeeded in stopping the application"