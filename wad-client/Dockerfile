FROM node:alpine

WORKDIR /app

COPY package.json .

RUN npm install

COPY . .

RUN npm run build-prod


FROM nginx:alpine

COPY --from=0 /app/dist/wad-client/ /usr/share/nginx/html/

EXPOSE 80

