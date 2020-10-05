
# Spring Boot 2.1.3
## Curso Udemy - REST API's RESTFul do 0 à AWS Com Spring Boot 2.x e Docker
> Short blurb about what your product does.

[![NPM Version][npm-image]][npm-url]
[![Build Status][travis-image]][travis-url]
[![Downloads Stats][npm-downloads]][npm-url]

![](header.png)

Este projeto pode ser utilizado como implementação base para outras API's.

## Content:
    * Implementação de DTO's
    * Versionamento da API
    * Migrations com Flyway
    * Content Negotioation(Json,XML,YAML)
    * Implementação de Hateoas
    * Suporte a Swagger
    * Autenticação com JWT e Spring Security
    * Integração com Travis CI e docker-compose
    * Configuração para CORS
    * Paginação

## Pré-requisitos modo desenvolvimento usando o Eclipse 20-09 no Windows/Linux:
* Java 11
* Apache Maven 3.6.3
* Postgres

## Development setup

Para rodar no Eclipse é necessário ajustar as configurações do banco de dados no diretório ```src/main/resources/application.properties```, altere as seguintes linhas:
```sh
spring.datasource.url= jdbc:postgresql://URL_BANCO_DEDADOS:PORTA_BANCO_DE_DADOS/NOME_DO_BANCO_DE_DADOS
spring.datasource.username=USUARIO_DO_BANCO
spring.datasource.password=SENHA_DO_BANCO
```

Além disso, altere as credenciais do Flyway no arquivo pom.xml

```sh

<configuration>
  <url>jdbc:postgresql://URL_BANCO_DEDADOS:PORTA_BANCO_DE_DADOS/NOME_DO_BANCO_DE_DADOS?useTimezone=true&amp;serverTimezone=UTC&amp;useSSL=false</url>
  <user>USUARIO_DO_BANCO</user>
  <password>SENHA_DO_BANCO</password>
</configuration>
```

Agora basta executar o Main.java como: Run as --> Java Application.



## Meta

Ronaldo Almeida – [Linkedin](https://www.linkedin.com/in/ronaldo-almeida-9a8a8316b/) – ronaldoalm1812@edu.unifor.br

[https://github.com/ronaldoalm](https://github.com/ronaldoalm)
