#!/usr/bin/env sh
set -eu
envsubst '${BACKEND_HOST} ${BACKEND_PORT}' < /etc/nginx/conf.d/nginx.conf.template > /etc/nginx/conf.d/nginx.conf
exec "$@"