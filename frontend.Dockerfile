FROM nginx
COPY /frontend/stackoverflow/build /usr/share/nginx/html
RUN rm /etc/nginx/conf.d/default.conf
COPY nginx.conf.template /etc/nginx/conf.d/nginx.conf.template
COPY frontend_entrypoint.sh /
EXPOSE 80
ENTRYPOINT ["/frontend_entrypoint.sh"]
CMD ["nginx", "-g", "daemon off;"]