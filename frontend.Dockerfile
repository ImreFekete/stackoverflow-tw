FROM node:21-alpine3.17

WORKDIR /stackoverflow
COPY ./stackoverflow .

RUN npm install 

CMD [ "npm", "start"]