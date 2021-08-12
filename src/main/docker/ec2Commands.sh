#!/usr/bin/env bash

kill -9 $(lsof -t -i:8081)
echo "Killed process running on port 8081"

java -jar PassGenarator-0.0.1-SNAPSHOT.jar
echo "Started server using java -jar command"