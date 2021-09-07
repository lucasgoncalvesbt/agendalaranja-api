##Hackaton-Api
Back-End/API de uma aplicação web de agendamento desenvolvida durante o Hackaton do Programa de Formação do Grupo Fcamara

## Pré-Requisitos

Para rodar a aplicação você vai precisar de:

- [JDK 11](https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html)
- [Maven 3](https://maven.apache.org)
- [IntelliJ IDEA](https://www.jetbrains.com/pt-br/idea/download)  (Opcional)
- [Postman](https://www.postman.com/downloads/)

## Rodando a aplicação localmente

Existem várias maneiras de executar um aplicativo Spring Boot em sua máquina local. Uma maneira é executar o método `main` na classe `br.com.fcamara.hackatonapi.HackatonApiApplication` do seu IDE.

Ou como alternativa você pode utilizar o [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html):

```shell
mvn spring-boot:run
```

##Documentação
Para verificar os endpoints da API importe no Postman o arquivo HackatonCollections que está na raíz do projeto.