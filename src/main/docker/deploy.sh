#!/usr/bin/env bash

rm -rf build/
echo "Deleted build/ folder"

././mvnw clean package
echo "Generating jar file"

#Copy execute_commands_on_ec2.sh file which has commands to be executed on server... Here we are copying this file
# every time to automate this process through 'deploy.sh' so that whenever that file changes, it's taken care of
scp -i "~/Downloads/passgenerator-BE.pem" ./ec2Commands.sh ec2-user@ec2-35-164-159-243.us-west-2.compute.amazonaws.com:/home/ec2-user
echo "Copied latest 'execute_commands_on_ec2.sh' file from local machine to ec2 instance"

scp -i "~/Downloads/passgenerator-BE.pem" ./PassGenarator-0.0.1-SNAPSHOT.jar ec2-user@ec2-35-164-159-243.us-west-2.compute.amazonaws.com:/home/ec2-user
echo "Copied jar file from local machine to ec2 instance"

echo "Connecting to ec2 instance and starting server using java -jar command"
ssh -i "~/Downloads/passgenerator-BE.pem" ec2-user@ec2-35-164-159-243.us-west-2.compute.amazonaws.com ./ec2Commands.sh