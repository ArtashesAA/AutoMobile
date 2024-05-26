# Etapa 1: Construir la aplicación Angular
FROM node:18 as build
WORKDIR /app
COPY package*.json ./
RUN npm ci
RUN npm install -g @angular/cli
COPY . .
RUN npm run build --configuration=prod

# Etapa 2: Servir la aplicación Angular con Nginx
FROM nginx:latest

COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=build /app/dist/auto-mobile /usr/share/nginx/html

EXPOSE 80
