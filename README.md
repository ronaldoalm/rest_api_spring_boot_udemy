
# Spring Boot 2.1.3
## Curso Udemy - REST API's RESTFul do 0 à AWS Com Spring Boot 2.x e Docker

![](header.png)

This project can be used as a base implementation for other API's.

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

## Requirements for development environment:
* Java 11
* Apache Maven 3.6.3
* Postgres
* Eclipse (20-09)

## Setup

To run on Eclipse it is necessary to adjust the database settings in the directory  ```src / main / resources / application.properties```, change the following lines:
```sh
spring.datasource.url= jdbc:postgresql://URL_BANCO_DEDADOS:PORTA_BANCO_DE_DADOS/NOME_DO_BANCO_DE_DADOS
spring.datasource.username=USUARIO_DO_BANCO
spring.datasource.password=SENHA_DO_BANCO
```

Also, change Flyway credentials in the pom.xml file

```sh

<configuration>
  <url>jdbc:postgresql://URL_BANCO_DEDADOS:PORTA_BANCO_DE_DADOS/NOME_DO_BANCO_DE_DADOS?useTimezone=true&amp;serverTimezone=UTC&amp;useSSL=false</url>
  <user>USUARIO_DO_BANCO</user>
  <password>SENHA_DO_BANCO</password>
</configuration>
```

Agora basta executar o Main.java como: Run as --> Java Application.


## Requirements for production environment:
* Java 11
* Apache Maven 3.6.3
* Docker-Compose

## Production setup


## Meta

Ronaldo Almeida – [Linkedin](https://www.linkedin.com/in/ronaldo-almeida-9a8a8316b/) – ronaldoalm1812@edu.unifor.br

[https://github.com/ronaldoalm](https://github.com/ronaldoalm)
