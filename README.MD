# MVP - BarberShop Scheduling

## Descrição do projeto
BarberShop é um gestor de barbearias, oferecendo funcionalidades para agendamento de serviços, gerenciamento de clientes, barbeiros e histórico de atendimentos.
## Tecnologias usadas
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-336791?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)

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

## Swagger
Swagger está integrado na aplicação para fornecer uma interface amigável para testar e documentar os endpoints da API. 
Você pode acessar a interface do Swagger na seguinte URL:

```text
http://localhost:8080/swagger-ui/index.html
```
Esta interface permite explorar os endpoints disponíveis, visualizar os formatos de requisição e resposta, e executar chamadas de API diretamente do navegador.

## Criado por

**Filipe Santana Cordeiro**  
**Backend Developer**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/filipesantanacordeiro/)
[![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/Filipescordeiro2)