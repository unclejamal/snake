#!/bin/bash
ROOT_DIR=$1

echo "Trying to stop the application"

kill $(ps aux | grep '[m]anager.main.Main' | awk '{print $2}')
rm -rf /tmp/manager-e2e

echo "Succeeded in stopping the application"