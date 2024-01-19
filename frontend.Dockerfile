FROM node:21-alpine3.17

WORKDIR /frontend
COPY /frontend/stackoverflow .

RUN npm install 

CMD [ "npm", "start"]