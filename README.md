# 🖥️ Sobre o projeto

<p>📅 Agenda Laranja - é um meio prático e eficiente para programar o dia do trabalho presencial, respeitando as normas de segurança.</p>

Essa é a API/Back-end do projeto, para poder utilizar o Front-End [acesse aqui!](https://github.com/lucasgoncalvesbt/agendalaranja-front)

<p>Projeto desenvolvido durante o Hackaton do Programa de Formação do Grupo FCamara 2021 Season 2.</p>
<br>

# ⚙️ Funcionalidades

- Através dos endpoints é possivel:
  - Registrar, Excluir, Atualizar um escritório
  - Pegar informações de um ou mais escritórios
  - Registrar, Excluir, Atualizar uma estação
  - Pegar informações de uma ou mais estações
  - Registrar, Excluir, Atualizar um agendamento
  - Pegar informações de um ou mais agendamentos
  
<br>


# 💡 Tecnologias Utilizadas
O projeto foi desenvolvido utilizando as seguintes tecnologias:
- [Java](https://www.java.com/pt-BR/)
- [Spring Framework](https://spring.io/)

<br>

# 🚀 Como executar o projeto
## Pré-requisitos
  - [Git](https://git-scm.com/downloads) ( Para clonar o repositório )
  - [JDK 11](https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html)
  - [Maven 3](https://maven.apache.org)
  - [IntelliJ IDEA](https://www.jetbrains.com/pt-br/idea/download)  (Opcional)
  - [Postman](https://www.postman.com/downloads/)

<br>

## Rodando a aplicação localmente
```
#Clone este repositorio
$ git clone https://github.com/lucasgoncalvesbt/agendalaranja-api.git

#Dentro da pasta server execute o comando:
$ mvn spring-boot:run

#A aplicação será aberta na porta:8080 
```

Existem várias maneiras de executar um aplicativo Spring Boot em sua máquina local. Uma outra maneira é executar o método `main` na classe `br.com.fcamara.agendalaranjaapi.AgendaLaranjaApiApplication` do seu IDE.

<br>

## Documentação
Para verificar os endpoints da API importe no Postman o arquivo AgendaLaranjaCollection que está na raíz do projeto.
Ou como alternativa você também pode acessar o swagger no endereço http://localhost:8080/swagger-ui.html#/

