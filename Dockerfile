FROM openjdk:8-jdk-alpine
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
RUN apk update && \
    apk add maven && \
    mvn clean package

EXPOSE 8090
EXPOSE 8091

ENTRYPOINT ["mvn", "spring-boot:run"]