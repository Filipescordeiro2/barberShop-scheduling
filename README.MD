# MVP - BarberShop Scheduling

## Descrição do projeto
BarberShop é um gestor de barbearias, oferecendo funcionalidades para agendamento de serviços, gerenciamento de clientes, barbeiros e histórico de atendimentos.
## Tecnologias usadas
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-336791?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

## Arquitetura usada
A aplicação segue a arquitetura em camadas, permitindo escalabilidade e manutenção facilitada. Utiliza Spring Boot para a criação dos serviços RESTful.

## Utilização de IA
A aplicação utiliza Inteligência Artificial (IA) para garantir a qualidade do código e a criação de testes unitários.

A seguir, são descritas as principais funcionalidades da IA integrada:  

### Validação de Clean Code e Boas Práticas
A IA é utilizada para analisar o código-fonte e garantir que ele siga os princípios de clean code e boas práticas de desenvolvimento. Isso inclui:  

-  Análise de Complexidade: Verificação da complexidade ciclomática para garantir que o código seja fácil de entender e manter.
-  Padrões de Código: Verificação de aderência a padrões de codificação, como nomenclatura de variáveis, métodos e classes.
-  Refatoração: Sugestões de refatoração para melhorar a legibilidade e a eficiência do código.
-  Detecção de Code Smells: Identificação de trechos de código que podem indicar problemas de design ou implementação.

### Criação de Testes Unitários
A IA também auxilia na criação de testes unitários, automatizando parte do processo de desenvolvimento de testes. Isso inclui:  

- Geração de Testes: Criação automática de testes unitários para métodos e classes, garantindo cobertura de código.
- Sugestões de Casos de Teste: Identificação de casos de teste adicionais para cobrir cenários não contemplados.
- Validação de Testes: Verificação da eficácia dos testes unitários, garantindo que eles realmente validem o comportamento esperado do código.
- Com essas funcionalidades, a aplicação garante um código de alta qualidade e bem testado, facilitando a manutenção e evolução do sistema.

## Funcionalidades
- [x] Cadastro de clientes
- [x] Cadastro de barbeiros
- [x] Cadastro de serviços
- [x] Agendamento de serviços
- [x] Histórico de atendimentos
- [x] Relatórios de atendimentos
- [x] Cancelamento de agendamentos
- [x] ETC...

### Banco de Dados
A aplicação utiliza o banco de dados PostgreSQL para armazenar as informações.

Para configurar e executar o banco de dados localmente utilizando Docker, siga os passos abaixo:

1- Certifique-se de ter o Docker instalado em sua máquina.

2 - Execute o comando abaixo no terminal para criar e iniciar um contêiner Docker com o PostgreSQL:

```bash
   docker run --name barbershopScheduling-db -e POSTGRES_DB=barbershopScheduling -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -p 5433:5432 -d postgres
 ```
Este comando faz o seguinte:

``` text
--name barbershopScheduling-db: Define o nome do contêiner como barbershopScheduling-db.

-e POSTGRES_DB=barbershopScheduling: Cria um banco de dados chamado barbershopScheduling.

-e POSTGRES_USER=postgres: Define o usuário do banco de dados como postgres.

-e POSTGRES_PASSWORD=postgres: Define a senha do banco de dados como postgres.

-p 5433:5432: Mapeia a porta 5433 do host para a porta 5432 do contêiner.

-d postgres: Utiliza a imagem oficial do PostgreSQL e executa o contêiner em segundo plano.

```

## Cliente

### Cadastro de clientes
POST
```text
 http://localhost:8080/cliente
 ```
- Request
```json
    {
      "cpf": "CPF",
      "name": "João",
      "surname": "Silva",
      "phone": "123456789",
      "email": "joao.silva@example.com",
      "password":"teste",
      "dateOfBirth": "2002-08-01"
    }
```
- Reponse
```json
    {
      "message": "Cliente cadastrado com sucesso",
      "cpf": "CPF",
      "createdAt": "2025-03-12T07:56:49.2283062"
    }
```

### Buscar Cliente CPF
GET
```text
 http://localhost:8080/cliente/{cpf}
```

### Auth Cliente
POST
```text
 http://localhost:8080/cliente/auth
 ```
- Request
```json
    {
      "login":"joao.silva@example.com",
      "password":"teste"
    }
```
- Response
```json
{
    "cpf": "CPF",
    "name": "João",
    "surname": "Silva",
    "nameComplete": "João Silva",
    "phone": "123456789",
    "email": "joao.silva@example.com",
    "age": 23,
    "dateOfBirth": "2002-08-01",
    "createdAt": "2025-03-12T07:56:49.228306",
    "updatedAt": "2025-03-12T07:56:49.228306",
    "active": true
}
```

## Criado por

**Filipe Santana Cordeiro**  
**Backend Developer**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/filipesantanacordeiro/)
[![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/Filipescordeiro2)