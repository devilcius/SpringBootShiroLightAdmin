# SBSLA: Spring Boot Shiro LightAdmin Application
---

## Description
Three frameworks integrated in one simple MVC, OOP application:
- Spring Boot
- Apache Shiro 
- LightAdmin 

Useful to learn some basic functionality from these frameworks.

## Dependencies
For assets:

- Nodejs
- Npm
- Bower

Application:
- Java >= 1.7
- Maven >= 3.3

## Install
Static assets:

```sh
npm install
bower install
grunt
```
Application:
```sh
mvn clean install
```
## Configuration
It should work out of the box. If you want to use recover email feature, fill these properties:
```properties
application.email.defaultsender=
spring.mail.*
```

## Run
```sh
mvn spring-boot:run
```
And open http://localhost:8080
>Administrator credentials: admin@acme.com:123456