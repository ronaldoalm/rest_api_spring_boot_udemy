
# Spring Boot 2.1.3
## Curso Udemy - REST API's RESTFul do 0 à AWS With Spring Boot 2.x e Docker



This project can be used as a base implementation for other API's.

### Languages and Tools

<br/>

<p align="left">

  <a href="https://www.docker.com/" target="_blank">
    <img
      src="https://devicons.github.io/devicon/devicon.git/icons/docker/docker-original-wordmark.svg"
      alt="docker"
      width="40"
      height="40"
    />
  </a>
  
  <a href="https://www.eclipse.org/downloads/" target="_blank">
    <img
      src="https://www.eclipse.org/downloads/assets/public/images/logo-eclipse.png"
      alt="jenkins"
      width="40"
      height="40"
    />
  </a>

  <a href="https://www.postgresql.org" target="_blank">
    <img
      src="https://devicons.github.io/devicon/devicon.git/icons/postgresql/postgresql-original-wordmark.svg"
      alt="postgresql"
      width="40"
      height="40"
    />
  </a>
  
   <a href="https://spring.io/projects/spring-boot" target="_blank">
    <img
      src="https://spring.io/images/spring-logo-9146a4d3298760c2e7e49595184e1975.svg"
      alt="aws"
      width="120"
      height="40"
    />
  </a>
  
   <a href="https://spring.io/projects/spring-boot" target="_blank">
    <img
      src="https://upload.wikimedia.org/wikipedia/pt/3/30/Java_programming_language_logo.svg"
      alt="aws"
      width="40"
      height="40"
    />
  </a>
</p>


## Content:
   * Implementation of DTO's
   * API versioning
   * Migrations with Flyway
   * Content Negotioation (Json, XML, YAML)
   * Implementation of Hateoas
   * Swagger support
   * Authentication with JWT and Spring Security
   * Integration with Travis CI and docker-compose
   * Configuration for CORS
   * Pagination
   * Upload and Download Files

## Requirements for development environment:
* Java 11
* Apache Maven 3.6.3
* Postgres
* Eclipse (20-09)

## Setup

To run on Eclipse it is necessary to adjust the database settings in the directory  ```src/main/resources/application.properties```, change the following lines:
```sh
spring.datasource.url= jdbc:postgresql://URL_DB:PORT_DB/NAME_DB
spring.datasource.username=USER_DB
spring.datasource.password=PASSWORD_DB
```

Also, change Flyway credentials in the pom.xml file

```sh

<configuration>
  <url>jdbc:postgresql://URL_DB:PORT_DB/NAME_DB?useTimezone=true&amp;serverTimezone=UTC&amp;useSSL=false</url>
  <user>USER_DB</user>
  <password>PASSWORD_DB</password>
</configuration>
```


Now just run Main.java as: Run as -> Java Application.

![](execmain.png)


## Requirements for production environment:
* Java 11
* Apache Maven 3.6.3
* Docker-Compose

## Production setup
Access the directory of ```docker-compose.yml``` and exec the command ```sudo docker-compose up```

## Meta

Ronaldo Almeida – [Linkedin](https://www.linkedin.com/in/ronaldo-almeida-9a8a8316b/) – ronaldoalm1812@edu.unifor.br

[https://github.com/ronaldoalm](https://github.com/ronaldoalm)
