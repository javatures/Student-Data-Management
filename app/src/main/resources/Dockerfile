FROM postgres
ENV POSTGRES_USER records
ENV POSTGRES_PASSWORD p@ssword
ADD schema.sql /docker-entrypoint-initdb.d
EXPOSE 5432