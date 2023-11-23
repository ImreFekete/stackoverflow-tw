# Use the official PostgreSQL image as the base image
FROM postgres:latest

# Create a custom data directory and set appropriate permissions
RUN mkdir -p /custom_postgresql_data && chown -R postgres:postgres /custom_postgresql_data

# Set the custom data directory as the location for PostgreSQL data
ENV PGDATA=/custom_postgresql_data

# Copy initialization scripts into the container
COPY ./init-scripts /docker-entrypoint-initdb.d/

# Set permissions for the scripts
RUN chown -R postgres:postgres /docker-entrypoint-initdb.d/*

# Expose the default PostgreSQL port (5432)
EXPOSE 5432

# Start PostgreSQL service when the container starts
CMD ["postgres"]
  