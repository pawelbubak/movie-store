FROM node:12.16.1-alpine3.11 as node-builder

WORKDIR /app

ENV PATH /app/node_modules/.bin:$PATH

COPY package.json /app/package.json
RUN npm install
RUN npm install -g @angular/cli@9.1.7

COPY . /app

CMD ng serve --host 0.0.0.0 -c prod --prod
