#!/bin/sh
set -e

# Replace env vars in nginx.conf and start nginx
envsubst '\$PORT \$BACKEND_URL' < /etc/nginx/conf.d/default.conf.template > /etc/nginx/conf.d/default.conf

exec nginx -g 'daemon off;'
