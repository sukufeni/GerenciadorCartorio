#!/usr/bin/env bash

# echo "Building DB image"
# docker buildx build -f src/DB/docker/Dockerfile -t dbpostgresql --platform linux/amd64 ./src/DB/

echo "Removing old .jar file"
rm src/main/docker/PassGenarator-0.0.1-SNAPSHOT.jar

./mvnw clean package -DskipTests
echo "Generating jar file"

mv target/PassGenarator-0.0.1-SNAPSHOT.jar src/main/docker/

echo "creating images"
docker buildx build -f FE/Dockerfile -t fe --platform linux/amd64 ./FE/
docker buildx build -f src/main/docker/Dockerfile -t be --platform linux/amd64 ./src/main/docker/

echo "running docker-compose"
docker compose up -d

docker compose logs -f