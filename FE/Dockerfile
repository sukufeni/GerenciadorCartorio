FROM node:14-alpine3.11

WORKDIR /usr/src/app

COPY package.json .
COPY package-lock.json .

RUN npm install --force

COPY . .

EXPOSE 8080

CMD /usr/src/app/node_modules/.bin/ng serve --host 0.0.0.0 --disableHostCheck