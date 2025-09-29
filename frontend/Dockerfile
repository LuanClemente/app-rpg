### Multi-stage Dockerfile para buildar o frontend React e servir com nginx
FROM node:18-alpine AS build
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm ci --legacy-peer-deps
COPY public ./public
COPY src ./src
RUN npm run build

FROM nginx:stable-alpine
COPY --from=build /app/build /usr/share/nginx/html
# Remove default nginx config and use a basic one that serves the SPA
RUN rm /etc/nginx/conf.d/default.conf
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["/usr/sbin/nginx","-g","daemon off;"]
