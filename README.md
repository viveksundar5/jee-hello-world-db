# Hello World DB

## Description
A simple application in Java EE 7, which responds to REST requests and fetches from a Database.

## Requirements
* Wildfly with MySQL datasource. Example: kerdlix/wildfly-alpine-mysql

## Setup
Create a Docker MySQL instance:
```
docker run --name mysqlHelloWorldDB -e MYSQL_ROOT_PASSWORD=redhat@123 -e MYSQL_DATABASE=test -e MYSQL_USER=redhat -e MYSQL_PASSWORD=redhat@123 -p 3306:3306  -d mysql:latest
```
Run the [database script](./database/init.sql).


## Build
```
mvn clean package
docker build --rm --tag=kerdlix/docker-wildfly-hello-world-db .
```

## Run
```
docker run -it -p 8080:8080 -p 9990:9990 -e DATASOURCE_CONNECTION=jdbc:mysql://mysqlHelloWorldDB:3306/test -e DATASOURCE_USERNAME=redhat -e DATASOURCE_PASSWORD=redhat@123 --link mysqlHelloWorldDB:mysqlHelloWorldDB kerdlix/docker-wildfly-hello-world-db
```

## HTTP Calls
```
http://localhost:8080/1
http://localhost:8080/2
```


## References
* [Docker MySQL](https://hub.docker.com/r/library/mysql/)
