#!/usr/bin/env bash

echo "Removing old .jar file"
rm src/main/docker/PassGenarator-0.0.1-SNAPSHOT.jar

./mvnw clean package
echo "Generating jar file"

mv target/PassGenarator-0.0.1-SNAPSHOT.jar src/main/docker/

echo "creating images"
docker buildx build -f passGeneratorManager/Dockerfile -t fe --platform linux/amd64 ./passGeneratorManager/
docker buildx build -f src/main/docker/Dockerfile -t be --platform linux/amd64 ./src/main/docker/

echo "running docker-compose"
docker compose up -d