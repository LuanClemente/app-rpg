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
# Prepare nginx config template and entrypoint to substitute env vars at runtime
RUN rm /etc/nginx/conf.d/default.conf || true
COPY nginx.conf /etc/nginx/conf.d/default.conf.template
COPY docker-entrypoint.sh /docker-entrypoint.sh
RUN chmod +x /docker-entrypoint.sh

# Install envsubst (gettext) to allow env var substitution
RUN apk add --no-cache gettext

EXPOSE 80
ENTRYPOINT ["/docker-entrypoint.sh"]
