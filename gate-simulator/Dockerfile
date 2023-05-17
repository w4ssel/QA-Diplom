FROM node:alpine3.16
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
EXPOSE 9999
CMD ["npm", "start"]