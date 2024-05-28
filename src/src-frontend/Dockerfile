# Etapa 1: Construir la aplicación Angular
FROM node:18 as build
WORKDIR /app
COPY package*.json ./
RUN npm ci
RUN npm install -g @angular/cli
COPY . .
RUN npm run build --configuration=production

# Etapa 2: Ejecutar la aplicación Angular localmente
FROM node:18
WORKDIR /app
COPY --from=build /app /app
RUN npm install -g @angular/cli

EXPOSE 4200
CMD ["ng", "serve", "--host", "0.0.0.0"]
