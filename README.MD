# MVP - BarberShop Agendamento

## Descrição do projeto
BarberShop é um gestor de barbearias, oferecendo funcionalidades para agendamento de serviços, gerenciamento de clientes, barbeiros e histórico de atendimentos.
## Tecnologias usadas
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![SQL Database](https://img.shields.io/badge/SQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white)

## Arquitetura usada
A aplicação segue a arquitetura em camadas, permitindo escalabilidade e manutenção facilitada. Utiliza Spring Boot para a criação dos serviços RESTful.

## Funcionalidades
- [x] Cadastro de clientes
- [x] Cadastro de barbeiros
- [x] Cadastro de serviços
- [x] Agendamento de serviços
- [x] Histórico de atendimentos
- [x] Relatórios de atendimentos
- [x] Cancelamento de agendamentos
- [x] ETC...

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